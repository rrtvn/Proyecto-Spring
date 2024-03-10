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


import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.services.user.UsersService;
import cl.nvrrt.cvseguro.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/users") //http://localhost:8080/cvsegurodb
public class UsersRestControllers {


	//Aqui es donde se recibe la peticion HTTP

	@Autowired
	private UsersService userService;

	@Autowired
	private JWTUtil jwtUtil;

	@GetMapping("/get/{token}")
	public ResponseEntity<User> getAll(@RequestHeader(value = "Authorization") String token) {
		// System.out.println(token);
		if (!jwtUtil.validateJwtToken(token)) {
			return null;
		}else{

			String userId = jwtUtil.getUserNameFromJwtToken(token);
			System.out.println(userId);
			return ResponseEntity.ok().body(userService.findById(userId));
		}
	}

	@GetMapping("/get")
	public List<User> findAllUsers(User user) {
		return userService.getAll();
	}
	

	// @GetMapping("/get/{token}")
	// public ResponseEntity<?> getUserForToken(@RequestHeader(value = "Authorization") String token ){
	// 	if (!validarToken(token)) { return null; }
	// 	String id = jwtUtil.getUsernameFromToken(token);
	// 	User userFind = userService.findById(id);
	// 	return ResponseEntity.ok(userFind);
	// }

	// public boolean validarToken(String token){
	// 	String userId = jwtUtil.getUsernameFromToken(token);
	// 	return userId != null;
	// }

	
	@PostMapping("/post")
	public ResponseEntity<?> post (@RequestBody User user){
		// ENCRIPTADO DE CONTRASEÑA
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash = argon2.hash(1, 1024, 1, user.getPassword());
		user.setPassword(hash);


		String token = jwtUtil.generateToken(user.getId());
		System.out.println(token);

		
		
		return ResponseEntity.ok(token);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		userService.delete(id);
		return ResponseEntity.ok(id);
	}
}
