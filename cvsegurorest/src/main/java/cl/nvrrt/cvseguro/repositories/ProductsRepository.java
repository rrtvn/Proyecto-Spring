package cl.nvrrt.cvseguro.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.nvrrt.cvseguro.entities.Product;

public interface ProductsRepository extends MongoRepository<Product, String> {
    
}
