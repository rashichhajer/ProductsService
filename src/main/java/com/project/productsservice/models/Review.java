package com.project.productsservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Review {
    @Id
    private String id;
    private String userId;
    private int rating;
    private String comment;
    private Date date;

}