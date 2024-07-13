package com.project.productsservice.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Document
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String currency;
    private boolean availability;
    private int quantity;
    private List<Variant> variants;
    @DBRef
    private List<Category> categories;
    @DBRef
    private Brand brand;
    private List<Review> reviews;
    private List<SellerInfo> sellerInfo;



}
