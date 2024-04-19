package cl.nvrrt.cvseguro.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.nvrrt.cvseguro.config.security.jwt.service.JWTUtil;
import cl.nvrrt.cvseguro.dto.ReqRes;
import cl.nvrrt.cvseguro.services.auth.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/auth")
public class AuthControllers {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTUtil jwtUtil;

    private Logger logger = LoggerFactory.getLogger(AuthControllers.class);
    

                       
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody ReqRes signInRequest) throws Exception {
        
        return ResponseEntity.ok(authService.singIn(signInRequest));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody ReqRes signUpRequest) {
        return ResponseEntity.ok(authService.singUp(signUpRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
    

}
