package cl.nvrrt.cvseguro.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.services.user.UsersService;
import cl.nvrrt.cvseguro.utils.JWTUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/auth")
public class AuthControllers {

    @Autowired
    private UsersService userService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        
        if (!userService.authenticate(user)) {
            
            return ResponseEntity.badRequest()
                                .body("Usuario o contraseña incorrectos");
        }else{
            String token = jwtUtil.generateToken(user.getId());
            System.out.println(token);
            return ResponseEntity.ok(token);
        }


    }

}
