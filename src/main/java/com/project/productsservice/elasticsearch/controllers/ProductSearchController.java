package com.project.productsservice.elasticsearch.controllers;

import com.project.productsservice.elasticsearch.models.ESProduct;
import com.project.productsservice.elasticsearch.services.ESProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/elasticsearch")

public class ProductSearchController {

    ESProductService esProductService;

    public ProductSearchController(ESProductService esProductService) {
        this.esProductService = esProductService;
    }

    @GetMapping
    @PreAuthorize("hasRole('Customer')")
    public ResponseEntity<List<ESProduct>> searchProducts(@RequestParam String keyword) throws IOException {
        return ResponseEntity.ok(esProductService.searchProducts(keyword));
    }

    @GetMapping("/transfer")
    public ResponseEntity<String> transferDataToElasticsearch()  {
        esProductService.transferDataToElasticsearch();
        return ResponseEntity.ok( "Data transfer to Elasticsearch completed.");
    }
}
