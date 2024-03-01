package cl.nvrrt.cvseguro.services.user;

import java.util.List;
import java.util.Optional;

import javax.management.Query;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCursor;

import cl.nvrrt.cvseguro.entities.Login;
import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.repositories.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService{

	//Comunicacion con MongoDB
	
	@Autowired
	private UsersRepository userRepo;

	

	@Override
	public User save(User u) {
		// REGISTRA UN USUARIO

		return userRepo.save(u);
	}

	@Override
	public List<User> getAll() {
		// OBTENER LISTADO DE USUARIOS
		return userRepo.findAll();
	}

	@Override
	public void delete(String id) {
		//ELIMINA UN USUARIO POR ID
		userRepo.deleteById(id);
		System.out.println("User " + id + " eliminado...");
		
	}

	// @Override
	// public Optional<User> findByEmailAndPassword(String email, String  password) {
	// 	// TODO Auto-generated method stub
	// 	return userRepo.findByEmailAndPassword(email, password);
	// }

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	@Override
	public boolean authenticate(String email, String password) {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(email);
		return user != null ;
	}

	



	

	

	

}
