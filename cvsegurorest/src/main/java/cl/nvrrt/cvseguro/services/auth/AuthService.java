package cl.nvrrt.cvseguro.services.auth;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import cl.nvrrt.cvseguro.config.security.jwt.service.JWTUtil;
import cl.nvrrt.cvseguro.dto.ReqRes;
import cl.nvrrt.cvseguro.entities.ETipoUser;
import cl.nvrrt.cvseguro.entities.TipoUser;
import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.repositories.UsersRepository;

@Service
public class AuthService {

    @Autowired
    private JWTUtil jwtUtil;
    
    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes singUp(ReqRes signUpRequest) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ReqRes resp = new ReqRes();
        try {
            User user = new User();
            System.out.println(signUpRequest.getTUser());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(signUpRequest.getPassword());
            user.setFecha(LocalDateTime.now()); 
            user.setTUser( ETipoUser.USER.name() );
            user.setName(signUpRequest.getName());
            user.setFecha(localDateTime);
            user.setLastname(signUpRequest.getLastname());
            user.setUsername(signUpRequest.getEmail());
            user.setDireccion(signUpRequest.getDireccion());
            User userSaved = userRepo.save(user);
            // System.out.println(token);
            if (userSaved != null) {
                //ENVIAMOS DATOS AL BODY DE MOMENTO YDEAN ENVIAR AL 
                // resp.setUsername(userSaved.getUsername());
                String token = jwtUtil.generateToken(userSaved);
                resp.setToken(token);
                resp.setMessage("User saved Succesfully");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
    public ReqRes singIn(ReqRes signInRequest) {
        ReqRes resp = new ReqRes();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
            User user = userRepo.findByEmail(signInRequest.getEmail()).orElseThrow();
            System.out.println(user);
            String jwt = jwtUtil.generateToken(user);
            // String refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), user);
            resp.setStatusCode(200);
            resp.setToken(jwt);    
            // resp.setRefreshToken(refreshToken);
            resp.setExpirationTime("24Hr");
            resp.setMessage("Successfully Signed In");

            
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReq) {
        ReqRes resp = new ReqRes();
        String userEmail =  jwtUtil.extractUsername(refreshTokenReq.getToken());
        User users =  userRepo.findByEmail(userEmail).orElseThrow();
        if (jwtUtil.isTokenValid(refreshTokenReq.getToken(), users)) {
            String jwt = jwtUtil.generateToken(users);
            resp.setStatusCode(200);
            resp.setToken(jwt);    
            resp.setRefreshToken(refreshTokenReq.getToken());
            resp.setExpirationTime("24Hr");
            resp.setMessage("Successfully Refreshed Token");
        }
        resp.setStatusCode(500);
        return resp;
    }


    
}
