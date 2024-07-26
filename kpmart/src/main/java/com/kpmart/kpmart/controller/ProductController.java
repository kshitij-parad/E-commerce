package com.kpmart.kpmart.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kpmart.kpmart.dto.ProductDto;
import com.kpmart.kpmart.service.ProductService;






@RestController
@RequestMapping("/api")
@CrossOrigin

public class ProductController {

    ProductService prodService;

    ProductController(ProductService prodService) {
        this.prodService = prodService;
    }

    // @GetMapping("/product")
    // public String getMethodName() {
    //     return "Hello ";
    // }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts()
    {
        return new ResponseEntity<>(prodService.getAllProducts(), HttpStatus.OK);

    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){

        ProductDto product = prodService.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getMethodName(@PathVariable int id) {
        return new ResponseEntity<>(prodService.getProductById(id), HttpStatus.OK );
    }


    @GetMapping("products/search")
    public ResponseEntity<List<ProductDto>> getMethodName(@RequestParam String keyword) {
        return new ResponseEntity<>(prodService.searchProduct(keyword),HttpStatus.OK);
    }
    

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart ProductDto product, @RequestPart MultipartFile imageFile) {
        try {
        
            ProductDto product1 = new ProductDto();
            
          product1 =  prodService.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestPart ProductDto product, @RequestPart MultipartFile imageFile) {
        try {
            System.out.println("this is product desc: "+ product.getDescr());
            ProductDto product1 = new ProductDto();
            
          product1 =  prodService.updateProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("product/{id}")
    public ResponseEntity<?> deleteProductByID(@PathVariable int id)
    {
        prodService.deleteProductByID(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    

}
