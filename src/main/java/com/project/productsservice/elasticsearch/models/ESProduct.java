package com.project.productsservice.elasticsearch.models;

import com.project.productsservice.models.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Document(indexName = "esproduct")
public class ESProduct {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String currency;
    private boolean availability;
    private int quantity;
    private List<Variant> variants;
    private List<Category> categories;
    private Brand brand;
    private List<Review> reviews;
    private List<SellerInfo> sellerInfo;
}
