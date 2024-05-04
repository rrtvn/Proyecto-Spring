package cl.nvrrt.cvseguro.services.auth;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.nvrrt.cvseguro.config.security.jwt.service.JWTUtil;
import cl.nvrrt.cvseguro.dto.ReqRes;
import cl.nvrrt.cvseguro.dto.SignUpResponseDto;
import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.repositories.UsersRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    
    private JWTUtil jwtUtil;
    
    
    private UsersRepository userRepo;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public SignUpResponseDto singUp(ReqRes signUpRequest) {
        LocalDateTime localDateTime = LocalDateTime.now();
        SignUpResponseDto request = new SignUpResponseDto();
        try {
            User user = new User();
            user.setEmail(signUpRequest.getEmail());

            user.setPassword(passwordEncoder.encode(signUpRequest.getLastname()));
            user.setFecha(LocalDateTime.now()); 
            user.setTUser( signUpRequest.getRole() );
            //TODO: VERIFICAR ZONA HORARIA DE LA FECHA
            user.setFecha(localDateTime);
            user.setUsername(signUpRequest.getEmail());
            user.setName(signUpRequest.getName());
            user.setLastname(signUpRequest.getLastname());
            user.setDireccion(signUpRequest.getDireccion());
            User userSaved = userRepo.save(user);
            // System.out.println(token);
            if (userSaved != null) {
            }
            //ENVIAMOS DATOS AL BODY DE MOMENTO YDEAN ENVIAR AL 
            // resp.setUsername(userSaved.getUsername());
            String token = jwtUtil.generateToken(userSaved);
            request.setToken(token);
            request.setEmail(userSaved.getEmail());
            request.setName(userSaved.getName());
            request.setPassword(userSaved.getPassword());
            request.setMessage(userSaved.getTUser() + " resgistered saved Succesfully");
        } catch (Exception e) {
            // request.setStatusCode(500);
            request.setError(e.getMessage());
            System.out.println(e.getMessage());
        }
        return request;
    }
    public ReqRes singIn(ReqRes loginRequest) {
        ReqRes resp = new ReqRes();
        String pass = passwordEncoder.encode(loginRequest.getPassword());
        resp.setUsername(loginRequest.getEmail());
        resp.setPassword(pass);
        try {
            // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(resp.getEmail(), pass));
            User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new RuntimeException("User not found with username: " + loginRequest.getEmail()));
            System.out.println("User found:" +user);
            String jwt = jwtUtil.generateToken(user);
            // String refreshToken = jwtUtil.generateToken(new HashMap<>(), user);
            resp.setStatusCode(200);
            resp.setToken(jwt);    
            // resp.setRefreshToken(refreshToken);
            resp.setExpirationTime("24Hr");
            resp.setMessage("Successfully Signed In");

            
        } catch (BadCredentialsException e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        } catch (Exception e){
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
