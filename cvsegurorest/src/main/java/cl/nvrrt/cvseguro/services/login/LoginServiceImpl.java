package cl.nvrrt.cvseguro.services.login;

import java.util.Optional;

import org.attoparser.dom.Document;
import org.springframework.beans.factory.annotation.Autowired;

import cl.nvrrt.cvseguro.entities.Login;
import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.repositories.UsersRepository;

public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersRepository userRepo;


    @Override
    public User verifyLogin(String email) {
        // TODO Auto-generated method stub
        return null;
    }
}