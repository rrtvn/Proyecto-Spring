package cl.nvrrt.cvseguro.services.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nvrrt.cvseguro.entities.Login;
import cl.nvrrt.cvseguro.repositories.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepo;


    

    @Override
    public Login save(Login login) {
        // TODO Auto-generated method stub

        return loginRepo.save(login);
    }

    @Override
    public List<Login> getAll() {
        // TODO Auto-generated method stub
        return loginRepo.findAll();
    }

   
}
