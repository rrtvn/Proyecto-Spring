package cl.nvrrt.cvseguro.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.nvrrt.cvseguro.entities.User;

public interface UsersRepository extends MongoRepository<User, String> {

}
