package cl.nvrrt.cvseguro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.services.UsersService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/users") //http://localhost:8080/cvsegurodb
public class UsersRestControllers {


	//Aqui es donde se recibe la peticion HTTP

	@Autowired
	private UsersService userService;

	@GetMapping("/get")
	public List<User> getAll() {
		return userService.getAll();
	}

	@PostMapping("/post")
	public User post (@RequestBody User user){
		return userService.save(user);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id){
		userService.delete(id);
	}
}
