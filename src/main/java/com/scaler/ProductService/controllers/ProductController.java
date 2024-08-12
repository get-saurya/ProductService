package com.scaler.ProductService.controllers;
import com.scaler.ProductService.exceptions.ProductNotFoundException;
import com.scaler.ProductService.models.Product;
import com.scaler.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService")
                             ProductService productService) {
        this.productService = productService;
    }

    //http://localhost:8080/products/10
    //@GetMapping("/{id}")
    //without exception handling--> Simple API CAll
    // public Product getProductById(@PathVariable("id") Long id) {
    //    return productService.getSingleProduct(id);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {

//        throw new RuntimeException("Something went wrong");
//        ResponseEntity<Product> responseEntity = null;
//
//        try {
//            Product product = productService.getSingleProduct(id);
//
//            responseEntity = new ResponseEntity<>(
//                    product,
//                    HttpStatus.OK
//            );
//        } catch (RuntimeException e) {
//            responseEntity = new ResponseEntity<>(
//                    HttpStatus.NOT_FOUND
//            );
//        }
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );

        return response;
    }

    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return productService.getAllProducts(pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
    }

    //PATCH -> http://localhost:8080/products/1
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
       return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return null;
    }

    //@ExceptionHandler(ArithmeticException.class)
    //    public ResponseEntity<String> handleArithmeticException() {
    //        ResponseEntity<String> response = new ResponseEntity<>(
    //                "ArrayIndexOutOfBoundsException has happened, Inside the controller",
    //                HttpStatus.BAD_REQUEST
    //        );
    //
    //        return response;
    //    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
