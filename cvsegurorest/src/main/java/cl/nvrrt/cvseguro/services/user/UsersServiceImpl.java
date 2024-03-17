package cl.nvrrt.cvseguro.services.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.repositories.UsersRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

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

	
	@Override
	public User findByEmail(String email) {
		// Buscamos por  correo electronico
		User user = userRepo.findByEmail(email);
		return user;
	}

	

	@Override
	public Boolean authenticate(User user) {
		// Se busca usuario por email 
		User userFind = userRepo.findByEmail(user.getEmail());
		// Se valida si es null
		if(userFind != null){

			String passLogged = userFind.getPassword();
	
			Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

			boolean verifyPass = argon2.verify(passLogged, user.getPassword());
			if (!verifyPass) {
				
				System.out.println("Contraseña incorrecta...");
				return false;
			}else{
				System.out.println(user.getEmail()+ " Email autenticado correctamente.");
				return true;
			}
		}else{
			System.out.println(user.getEmail()+" no existe en la base de datos.");
			return false;
		}

		
			
		
	}

	@Override
	public User findById(String id) {
		// Buscamos por id
		return userRepo.findById(id).orElse(null);
	}

	

	



	

	

	

}
