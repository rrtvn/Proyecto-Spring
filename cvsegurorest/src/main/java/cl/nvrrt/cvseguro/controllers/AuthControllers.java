package cl.nvrrt.cvseguro.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.repositories.UsersRepository;
import cl.nvrrt.cvseguro.services.user.UsersService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/auth")
public class AuthControllers {

    @Autowired
    private UsersService loginService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {

        if (loginService.authenticate(email, password)) {
            User userLogged = loginService.findByEmail(email);
            return "Usuario autenticado " + userLogged.getEmail();
        } else {
            return "Email o contraseña incorrectos";
        }

    }

}
