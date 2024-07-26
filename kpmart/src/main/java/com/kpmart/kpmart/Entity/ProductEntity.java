package com.kpmart.kpmart.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private String brand;
    private String descr;
    private int price;
    private String category;
    private int stockQuantity;
    private Date releaseDate;
    private boolean productAvailable;
    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageData;
}
