package com.project.productsservice.models;

import lombok.Data;

import java.util.List;

@Data
public class Variant {
    private String size;
    private String color;
    private double price;
    private int quantity;
}
