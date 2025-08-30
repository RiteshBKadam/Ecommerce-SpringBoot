package com.example.appWithReact.controller;


import com.example.appWithReact.model.ProductItem;
import com.example.appWithReact.service.ProducctService;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProducctService service;

    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

    @RequestMapping("/products")
    public ResponseEntity<List<ProductItem>> getProducts(){
        return new ResponseEntity<>(service.getProducts(),HttpStatus.OK);
    }

    @RequestMapping("/product/{id}")
    public ResponseEntity<ProductItem> getProductById(@PathVariable int id){
        return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
    }

    @RequestMapping("/product")
    public ResponseEntity<?> addNewProduct(@RequestPart ProductItem product,
                                     @RequestPart MultipartFile imageFile){
        try {
            return new ResponseEntity<>(service.addNewProduct(product,imageFile),HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProdcutId(@PathVariable int productId){
        ProductItem product=service.getProductById(productId);

        byte[] productImage=product.getImageData();

        return ResponseEntity.ok().body(productImage);
    }
    @PutMapping("/product/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable int productId,@RequestPart ProductItem product,
                                                @RequestPart MultipartFile imageFile ){

        ProductItem product1= null;
        try {
            product1 = service.updateProduct(productId,product,imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Not Updated",HttpStatus.BAD_REQUEST);
        }

        if(product1!=null){
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not Updated",HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        ProductItem productItem1=service.getProductById(id);
        if(productItem1!=null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not Deleted`",HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductItem>> searchResults(@RequestParam String keyword){
        List<ProductItem> searchResultsList=service.searchForKeyword(keyword);
        return new ResponseEntity<>(searchResultsList,HttpStatus.OK);

    }





}
