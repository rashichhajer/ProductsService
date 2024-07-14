package com.project.productsservice.elasticsearch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "esproduct")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ESProduct {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "name", analyzer = "custom_analyzer", searchAnalyzer = "standard")
    private String name;

    @Field(type = FieldType.Text, name = "description", analyzer = "custom_analyzer", searchAnalyzer = "standard")
    private String description;

    @Field(type = FieldType.Double, name = "price")
    private double price;

    @Field(type = FieldType.Keyword, name = "currency")
    private String currency;

    @Field(type = FieldType.Boolean, name = "availability")
    private boolean availability;

    @Field(type = FieldType.Integer, name = "quantity")
    private int quantity;

    @Field(type = FieldType.Nested)
    private ESBrand brand;
}