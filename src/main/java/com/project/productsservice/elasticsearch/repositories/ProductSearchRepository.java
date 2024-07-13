package com.project.productsservice.elasticsearch.repositories;


import com.project.productsservice.elasticsearch.models.ESProduct;
import com.project.productsservice.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ProductSearchRepository extends ElasticsearchRepository<ESProduct, String> {
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

}