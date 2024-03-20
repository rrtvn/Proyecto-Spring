package cl.nvrrt.cvseguro.controllers;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.nvrrt.cvseguro.entities.Login;
import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.repositories.LoginRepository;
import cl.nvrrt.cvseguro.services.login.LoginService;
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
    private LoginRepository loginService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, Login login) {

        
        if (!userService.authenticate(user)) {
            
            return ResponseEntity.badRequest()
                                .body("Usuario o contraseña incorrectos");
        }else{

            login.setEmail(user.getEmail());
            login.setFecha(LocalDateTime.now());

            loginService.save(login);
            String token = jwtUtil.generateToken(user.getId());
            System.out.println(token);
            return ResponseEntity.ok(token);
        }
    }

    @GetMapping("/login/get")
    public ResponseEntity<List<Login>> findAllLogin(Login login){
        try {
            return ResponseEntity.ok(loginService.findAll());
        } catch (Exception e) {
            // TODO: handle exception
            throw new Error(e.getMessage());
        }
    }

}
