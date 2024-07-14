package com.project.productsservice.services;


import com.project.productsservice.elasticsearch.repositories.ProductSearchRepository;
import com.project.productsservice.models.Product;
import com.project.productsservice.models.Variant;
import com.project.productsservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductSearchRepository productSearchRepository;

    public ProductService(ProductRepository productRepository, ProductSearchRepository productSearchRepository){
        this.productRepository=productRepository;
        this.productSearchRepository=productSearchRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product updatedProduct) {
        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }

    @Transactional
    public Product addVariant(String productId, Variant variant) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (!productOpt.isPresent()) {
            throw new RuntimeException("Product not found");
        }
        Product product = productOpt.get();
        if (product.getVariants() == null) {
            product.setVariants(new ArrayList<>());
        }
        product.getVariants().add(variant);
        return productRepository.save(product);
    }
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }


}
