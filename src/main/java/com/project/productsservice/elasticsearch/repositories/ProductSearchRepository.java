package com.project.productsservice.elasticsearch.repositories;


import com.project.productsservice.elasticsearch.models.ESProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<ESProduct, String> {

}
