package com.albert.com.productservice.controller;

import com.albert.com.productservice.dto.ProductDto;
import com.albert.com.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("all")
    public Flux<ProductDto> getAll() {
        return this.productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable(value = "id") String id) {
        return this.productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDto) {
        return this.productService.insertProduct(productDto);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProducts(@PathVariable String id, @RequestBody Mono<ProductDto> productDt) {
        return this.productService.updateProduct(id, productDt)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return this.productService.deleteProduct(id);
    }

    @GetMapping("price-range")
    public Flux<ProductDto> findByPriceRange(@RequestParam("min") int min,
                                             @RequestParam("max") int max) {
        return this.productService.getProductsByRange(min, max);
    }
}
