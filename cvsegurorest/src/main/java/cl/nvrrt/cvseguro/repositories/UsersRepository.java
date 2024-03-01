package cl.nvrrt.cvseguro.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.nvrrt.cvseguro.entities.User;


public interface UsersRepository extends MongoRepository<User, String> {
    
    Optional<User> findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

}
