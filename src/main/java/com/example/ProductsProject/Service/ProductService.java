package com.example.ProductsProject.Service;

import com.example.ProductsProject.Products;
import com.example.ProductsProject.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Products saveProduct(Products products) {
        return productRepository.save(products);
    }

    public List<Products> PrintProducts() {
        return productRepository.findAll();
    }

    public Optional<Products> FindByID(int id) {
        return productRepository.findById(id);
    }


    public boolean deletingById(int id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
