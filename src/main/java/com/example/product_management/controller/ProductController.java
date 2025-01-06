package com.example.product_management.controller;

import com.example.product_management.exception.ProductNotFoundException;
import com.example.product_management.exception.InvalidPriceException;
import com.example.product_management.model.Product;
import com.example.product_management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET: Retrieve all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // GET: Retrieve a single product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        return ResponseEntity.ok(product);
    }

    // POST: Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        validateProductPrice(product.getPrice());
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // PUT: Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody Product product) {
        validateProductPrice(product.getPrice());
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE: Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (!isDeleted) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        return ResponseEntity.noContent().build();
    }

    // Utility method for price validation
    private void validateProductPrice(Double price) {
        if (price == null || price <= 0) {
            throw new InvalidPriceException("Price must be a positive value");
        }
    }
}
