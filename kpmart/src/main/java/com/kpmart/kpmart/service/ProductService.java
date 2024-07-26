package com.kpmart.kpmart.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kpmart.kpmart.Entity.ProductEntity;
import com.kpmart.kpmart.Repo.ProductRepo;
import com.kpmart.kpmart.dto.ProductDto;

@Service
public class ProductService {

    ProductRepo productRepo;

    ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductDto addProduct( ProductDto product, MultipartFile imageFile ) throws  IOException{
      
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        ProductEntity productEntity = new ProductEntity();

        BeanUtils.copyProperties(product, productEntity);
        
        productRepo.save(productEntity);
        return product;
        
    }

    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productsEnt = productRepo.findAll();
        List<ProductDto> productsDto = new ArrayList<>();

        for (ProductEntity ent : productsEnt) {
            ProductDto dto = new ProductDto();
           
            dto.setId(ent.getId());
            dto.setName(ent.getName());
            dto.setBrand(ent.getBrand());
            dto.setDescr(ent.getDescr());
            dto.setCategory(ent.getCategory());
            dto.setPrice(ent.getPrice());
            dto.setStockQuantity(ent.getStockQuantity());
            dto.setReleaseDate(ent.getReleaseDate());
            dto.setProductAvailable(ent.isProductAvailable());
            // dto.setImageName(ent.getImageName());
            // dto.setImageData(ent.getImageData());
            // dto.setImageType(ent.getImageType());

            productsDto.add(dto);
        }
       return  productsDto;
    }

    public ProductDto getProductById(int id) {
            ProductEntity prdEnt = productRepo.findById(id).orElse(null);
            ProductDto prodDto = new ProductDto();
            BeanUtils.copyProperties(prdEnt, prodDto);
        return  prodDto;
    }

    public ProductDto updateProduct(ProductDto product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        ProductEntity productEntity = new ProductEntity();

        BeanUtils.copyProperties(product, productEntity);
        
        productRepo.save(productEntity);
     
        return product;
    }

    public void deleteProductByID(int id) {
      productRepo.deleteById(id);
    }

    public List<ProductDto> searchProduct(String keyword) {
        List<ProductEntity> productsEnt = productRepo.findAll();
        List<ProductDto> productsDto = new ArrayList<>();

        // productsDto = productRepo.searchProduct(keyword);
        productsEnt = productRepo.searchProduct(keyword);

        for(ProductEntity ent : productsEnt){
            ProductDto dto = new ProductDto();
           
            dto.setId(ent.getId());
            dto.setName(ent.getName());
            dto.setBrand(ent.getBrand());
            dto.setDescr(ent.getDescr());
            dto.setCategory(ent.getCategory());
            dto.setPrice(ent.getPrice());
            dto.setStockQuantity(ent.getStockQuantity());
            dto.setReleaseDate(ent.getReleaseDate());
            // dto.setImageName(ent.getImageName());
            // dto.setImageData(ent.getImageData());
            // dto.setImageType(ent.getImageType());

            productsDto.add(dto);
        }
        return productsDto;
    }

    
   
}
