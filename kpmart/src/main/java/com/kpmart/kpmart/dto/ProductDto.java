package com.kpmart.kpmart.dto;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    
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

    private byte[] imageData;
}
