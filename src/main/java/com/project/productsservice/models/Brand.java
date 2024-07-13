package com.project.productsservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Brand {
    @Id
    private String id;
    private String name;
    private String description;
}