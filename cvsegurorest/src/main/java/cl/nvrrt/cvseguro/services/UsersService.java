package cl.nvrrt.cvseguro.services;

import java.util.List;

import cl.nvrrt.cvseguro.entities.User;

public interface UsersService {

	User save(User u);
	List<User> getAll();
	void delete(String  id);
}
