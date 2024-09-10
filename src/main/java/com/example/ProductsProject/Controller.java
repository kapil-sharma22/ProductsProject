//package com.example.ProductsProject;
//
//import com.example.ProductsProject.Service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/Products")
//public class Controller {
//
//    @Autowired
//    ProductService productService;
//
//    @PostMapping
//    public Products createProducts(@RequestBody Products products) {
//        return productService.saving(products);
//    }
//
//   @GetMapping
//    public List<Products> getAllProducts(@RequestBody  Products products) {
//       return productService.PrintProducts(products);
//   }
//
//   @GetMapping("/{id}")
//    public Optional<Products> getProductId(@PathVariable int id) {
//       return productService.FindByID(id);
//   }
//
//}
