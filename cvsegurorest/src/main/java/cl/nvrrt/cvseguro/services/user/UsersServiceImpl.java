package cl.nvrrt.cvseguro.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
