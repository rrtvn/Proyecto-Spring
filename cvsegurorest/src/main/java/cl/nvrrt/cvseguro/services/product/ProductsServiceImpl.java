package cl.nvrrt.cvseguro.services.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nvrrt.cvseguro.entities.Product;
import cl.nvrrt.cvseguro.repositories.ProductsRepository;

@Service
public class ProductsServiceImpl implements ProdcutsService {

    @Autowired
    private ProductsRepository productRepo;

    @Override
    public Product save(Product p) {
       
        return productRepo.save(p);
    }

    @Override
    public List<Product> getAll() {
        
        return productRepo.findAll();
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
