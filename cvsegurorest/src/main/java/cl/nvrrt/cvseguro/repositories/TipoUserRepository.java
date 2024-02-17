package cl.nvrrt.cvseguro.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.nvrrt.cvseguro.entities.TipoUser;

public interface TipoUserRepository extends MongoRepository<TipoUser, String>{
    
}
