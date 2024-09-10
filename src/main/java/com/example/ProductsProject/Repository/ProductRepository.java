package com.example.ProductsProject.Repository;

import com.example.ProductsProject.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products,Integer> {

}
