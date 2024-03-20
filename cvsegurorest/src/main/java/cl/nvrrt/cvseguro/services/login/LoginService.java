package cl.nvrrt.cvseguro.services.login;

import java.util.List;

import cl.nvrrt.cvseguro.entities.Login;
import cl.nvrrt.cvseguro.entities.User;

public interface LoginService {
    
	User verifyLogin(String email);
	Login save(Login login);
	List<Login> getAll();
}
