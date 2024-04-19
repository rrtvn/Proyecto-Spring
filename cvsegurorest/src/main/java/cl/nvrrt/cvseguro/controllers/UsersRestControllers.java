package cl.nvrrt.cvseguro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.nvrrt.cvseguro.config.security.jwt.service.JWTUtil;
import cl.nvrrt.cvseguro.entities.TipoUser;
import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.services.user.UsersService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/users") //http://localhost:8080/cvsegurodb
public class UsersRestControllers {


	//Aqui es donde se recibe la peticion HTTP

	@Autowired
	private UsersService userService;
	@Autowired
	private TipoUserControllers tipoUserService;

	@Autowired
	private JWTUtil jwtUtil;

	// @GetMapping("/get/{token}")
	// public ResponseEntity<User> getAll(@RequestHeader(value = "Authorization") String token) {
	// 	// System.out.println(token);
		
	// }

	@GetMapping("/get")
	public ResponseEntity< List<User>> findAllUsers(User user) {
		try {
			
			return ResponseEntity.ok(userService.getAll());
		} catch (Exception e) {
			// TODO: handle exception
			throw new Error(e.getMessage());
		}
	}
	

	// @GetMapping("/get/{token}")
	// public ResponseEntity<?> getUserForToken(@RequestHeader(value = "Authorization") String token ){
	// 	if (validarToken(token) == null ) { 
	// 		return null; 
	// 	}else{

	// 		String id = jwtUtil.generateToken(token);
	// 		User userFind = userService.findById(id);
	// 		return ResponseEntity.ok(userFind);
	// 	}
		
	// }

	// public User validarToken(String token){
	// 	String userId = jwtUtil.getUsrFromToken(token);
	// 	User user = userService.findById(userId);
	// 	System.out.println(userId);
	// 	return user ;
	// }

	
	@PostMapping("/post")
	public ResponseEntity<?> post (@RequestBody User user){
		// ENCRIPTADO DE CONTRASEÑA
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash = argon2.hash(1, 1024, 1, user.getPassword());
		user.setPassword(hash);

		// String tipoUserFind = tipoUserService.getByTipoUser(user.getTipoUser());
		// System.out.println("tipo de usuario : "+tipoUserFind);
		// if ( tipoUserFind	!= null) {
		// }

		

		// String token = jwtUtil.generateToken(null , user.getUsername() );
		// System.out.println(token);

		
		userService.save(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		userService.delete(id);
		return ResponseEntity.ok(id);
	}
}
