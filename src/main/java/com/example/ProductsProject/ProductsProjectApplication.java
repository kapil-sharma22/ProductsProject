package com.example.ProductsProject;

import com.example.ProductsProject.Service.ProductService;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/Products")
public class ProductsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsProjectApplication.class, args);
	}

	@Autowired
	ProductService productService;

//    Status: HttpStatus.CREATED (201) – This tells the client that the product was created successfully.
//    Body: "Product Created successfully with ID: 1" – This is the message you send back to the client with details about the creation.


    //return ResponseEntity.status(HttpStatus.CREATED).body( "Product Created Successfully with ID: " +  productService.saving(products).getProductId());
    @PostMapping("/add")
    public ResponseEntity<String> createProducts(@RequestBody Products products) {

       try{
           Products savedProduct = productService.saveProduct(products);
           String responseMessage = "Product Created Successfully with ID: " + savedProduct.getProductId();
           return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
       }
       catch(IllegalArgumentException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Input Data for Creating Product");
       }
       catch(DataAccessException e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accessing the database, Please try again later");
       }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Products> products = productService.PrintProducts();
            return ResponseEntity.ok(products);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while fetching the data");
        }
    }


    //Retrieving the Products Details using -->ProductId ---> Using try and catch --> Exception Handling
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {

        try {
            Optional<Products> product = productService.FindByID(id);

            if(product.isPresent()) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + "is not found , Please provide valid ID");
            }
        }
        catch(DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DataBase error occurred");
        }
        catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Input Provided");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Unexpected Error occurred. Please try again later");
        }
    }

    //Deleting the product using the ID of the Product
   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id) {
         boolean isDeleted =  productService.deletingById(id);

        try{
            if(isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Product Deleted successfully"); //Status code 200 OK for successful deletion
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");  //status code 404 Not found if product was not Found
            }
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID, Please Provide Appropriate Input");
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such ID is Present");
        }
   }
}




//   @GetMapping("/{id}")
//    public Optional<Products> getProductId(@PathVariable int id) {
//       return productService.FindByID(id);
//   }

//   @GetMapping
//    public List<Products> getAllProducts() {
//       return productService.PrintProducts();
//   }

//Retrieving the data using the ID of the Product
//@GetMapping("/{id}")
//public ResponseEntity<String> getProductById(@PathVariable int id) {
//    Optional<Products> product = productService.FindByID(id);
//
//
//    if (product.isPresent()) {
//        Products p = product.get();
//        String responseMessage = "Details of Product with ID: " + p.getProductId() + "Name: " + p.getProductName() + "Price: " + p.getPrice();
//        //If the product exists, return it with a 200 OK status and a responseMessage
//        return ResponseEntity.ok(responseMessage);
//    } else {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products with given ID: " + id + " is not found");
//    }
