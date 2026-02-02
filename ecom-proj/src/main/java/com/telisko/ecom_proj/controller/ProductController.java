package com.telisko.ecom_proj.controller;

import com.telisko.ecom_proj.model.Product;
import com.telisko.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);

    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
Product product = service.getProductById(id);
if(product != null)
        return new ResponseEntity<> (product, HttpStatus.OK);
else
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
@PutMapping("/product")
public ResponseEntity<?>addProduct(@RequestPart Product product,
                                    @RequestPart MultipartFile imageFile) {
    try {
        Product Product1 = service.addProduct(product, imageFile);
        return new ResponseEntity<>(Product1, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}

