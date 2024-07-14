package com.project.productsservice.elasticsearch.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.project.productsservice.elasticsearch.models.ESBrand;
import com.project.productsservice.elasticsearch.models.ESProduct;
import com.project.productsservice.elasticsearch.repositories.ProductSearchRepository;
import com.project.productsservice.models.Brand;
import com.project.productsservice.models.Product;
import com.project.productsservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ESProductService {

    private final ElasticsearchClient elasticsearchClient;

    private final ProductRepository productRepository;

    private final ProductSearchRepository productSearchRepository;

    public ESProductService(ElasticsearchClient elasticsearchClient, ProductRepository productRepository, ProductSearchRepository productSearchRepository) {
        this.elasticsearchClient = elasticsearchClient;
        this.productRepository = productRepository;
        this.productSearchRepository = productSearchRepository;
    }

    public List<ESProduct> searchProducts(String query) throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("esproduct")
                .query(q -> q
                        .multiMatch(m -> m
                                .query(query)
                                .fields("combined_fields")
                                //.fields("name", "description", "brand.name", "brand.description")
                                .analyzer("custom_analyzer")
                        )
                )
        );

        SearchResponse<ESProduct> searchResponse = elasticsearchClient.search(searchRequest, ESProduct.class);

        return searchResponse.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    public void transferDataToElasticsearch() {
        List<Product> products = productRepository.findAll();
        List<ESProduct> esProducts = products.stream().map(this::convertToESProduct).collect(Collectors.toList());
        productSearchRepository.saveAll(esProducts);

    }

    private ESProduct convertToESProduct(Product product) {
        ESProduct esProduct = new ESProduct();
        esProduct.setId(product.getId());
        esProduct.setName(product.getName());
        esProduct.setDescription(product.getDescription());
        esProduct.setPrice(product.getPrice());
        esProduct.setCurrency(product.getCurrency());
        esProduct.setAvailability(product.isAvailability());
        esProduct.setQuantity(product.getQuantity());
        esProduct.setBrand(convertToESBrand(product.getBrand()));
        return esProduct;
    }

    private ESBrand convertToESBrand(Brand brand) {
        ESBrand esBrand = new ESBrand();
        esBrand.setName(brand.getName());
        esBrand.setDescription(brand.getDescription());
        return esBrand;
    }

}