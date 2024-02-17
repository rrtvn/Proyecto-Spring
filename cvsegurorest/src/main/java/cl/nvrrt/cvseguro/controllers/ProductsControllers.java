package cl.nvrrt.cvseguro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.nvrrt.cvseguro.entities.Product;
import cl.nvrrt.cvseguro.services.product.ProdcutsService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/products")
public class ProductsControllers {

    @Autowired
    private ProdcutsService productService;

    @PostMapping("/post")
    public Product post(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/get")
    public List<Product> getProducts() {
        return productService.getAll();

    }
}