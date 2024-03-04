package cl.nvrrt.cvseguro.services.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;

import cl.nvrrt.cvseguro.entities.User;

public interface UsersService {

	User save(User u);
	List<User> getAll();
	void delete(String  id);

	
	
	User  findByEmail(String email);
	
	Boolean authenticate(User user);
	// Optional<User> findByEmailAndPassword(String email, String password);
}
