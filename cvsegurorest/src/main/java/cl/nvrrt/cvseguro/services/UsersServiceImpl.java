package cl.nvrrt.cvseguro.services;

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
		// TODO Auto-generated method stub

		return userRepo.save(u);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public void delete(String id) {
		
		userRepo.deleteById(id);
		System.out.println("User " + id + " eliminado...");
		
		
		
	}

}