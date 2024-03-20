package cl.nvrrt.cvseguro.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.nvrrt.cvseguro.entities.Login;

public interface LoginRepository extends MongoRepository<Login, String> {
    
}
