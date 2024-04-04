package cl.nvrrt.cvseguro.controllers;


import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.nvrrt.cvseguro.config.security.jwt.model.payload.request.LoginRequest;
import cl.nvrrt.cvseguro.config.security.jwt.model.payload.response.JwtResponse;
import cl.nvrrt.cvseguro.config.security.jwt.service.JWTUtil;
import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.repositories.LoginRepository;
import cl.nvrrt.cvseguro.services.login.LoginService;
import cl.nvrrt.cvseguro.services.user.UsersService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/auth")
public class AuthControllers {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTUtil jwtUtil;

    private Logger logger = LoggerFactory.getLogger(AuthControllers.class);
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            doAuthenticate(loginRequest.getEmail(), loginRequest.getPassword());
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Invalid Credentials");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = this.jwtUtil.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .token(token)
                .username(userDetails.getUsername()).build();
        
        return new ResponseEntity<>(response, HttpStatus.OK);
       
    }

    private void doAuthenticate(String email, String password) throws Exception{
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(auth);
        } catch (DisabledException e) {
            throw new Exception("USUARIO DESABILITADO" + e.getMessage());
        }catch (BadCredentialsException e) {
            throw new Exception("CREDENCIALES INVALIDAS"+ e.getMessage());
        }
    }

   

    

}
