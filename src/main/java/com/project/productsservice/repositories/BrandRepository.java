package com.project.productsservice.repositories;

import com.project.productsservice.models.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandRepository extends MongoRepository<Brand, String> {
}