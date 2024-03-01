package cl.nvrrt.cvseguro.services.login;

import cl.nvrrt.cvseguro.entities.User;

public interface LoginService {
    
	User verifyLogin(String email);
}
