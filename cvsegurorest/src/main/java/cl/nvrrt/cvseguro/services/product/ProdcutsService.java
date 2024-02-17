package cl.nvrrt.cvseguro.services.product;

import java.util.List;

import cl.nvrrt.cvseguro.entities.Product;

public interface ProdcutsService {
    
    Product save(Product p);
    List<Product> getAll();
    void delete(String id);
}
