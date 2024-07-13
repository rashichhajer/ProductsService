package com.project.productsservice.services;


import com.project.productsservice.elasticsearch.models.ESProduct;
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

    private ProductRepository productRepository;

    private ProductSearchRepository productSearchRepository;

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
        Product savedProduct= productRepository.save(product);
        ESProduct esProduct = convertToESProduct(savedProduct);
        productSearchRepository.save(esProduct);
        return savedProduct;
    }

    public List<Product> searchProducts(String keyword) {
        return productSearchRepository.findByNameContainingOrDescriptionContaining(keyword, keyword);
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

    private ESProduct convertToESProduct(Product product) {
        ESProduct esProduct = new ESProduct();
        esProduct.setId(product.getId());
//        esProduct.setName(product.getName());
//        esProduct.setDescription(product.getDescription());
//        esProduct.setPrice(product.getPrice());
//        esProduct.setCurrency(product.getCurrency());
//        esProduct.setAvailability(product.isAvailability());
//        esProduct.setQuantity(product.getQuantity());
//        esProduct.setVariants(product.getVariants());
//        esProduct.setCategories(product.getCategories());
//        esProduct.setBrand(product.getBrand());
//        esProduct.setReviews(product.getReviews());
//        esProduct.setSellerInfo(product.getSellerInfo());
        return esProduct;
    }
}
