package com.kpmart.kpmart.Repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kpmart.kpmart.Entity.ProductEntity;



@Repository
public interface  ProductRepo extends  JpaRepository<ProductEntity, Integer> {

    @Query("SELECT p from ProductEntity p WHERE "+
    "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER(p.descr) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ProductEntity> searchProduct(String keyword);
}
