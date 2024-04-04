package cl.nvrrt.cvseguro.services.login;

import java.util.List;

import cl.nvrrt.cvseguro.entities.Login;

public interface LoginService {
    
	Login save(Login login);
	List<Login> getAll();
}
