package com.project.productsservice.elasticsearch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ESBrand {

    @Field(type = FieldType.Text, analyzer = "custom_analyzer", searchAnalyzer = "standard")
    private String name;

    @Field(type = FieldType.Text, analyzer = "custom_analyzer", searchAnalyzer = "standard")
    private String description;
}