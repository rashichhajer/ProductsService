package com.project.productsservice.controllers;

import com.project.productsservice.elasticsearch.models.ESProduct;
import com.project.productsservice.models.Product;
import com.project.productsservice.models.Variant;
import com.project.productsservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PostMapping("/variants/{productId}")
    public Product addVariant(@PathVariable String productId, @RequestBody Variant variant) {
        return productService.addVariant(productId, variant);
    }

}
