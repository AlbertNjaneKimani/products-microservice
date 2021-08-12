package com.albert.com.productservice.service;

import com.albert.com.productservice.dto.ProductDto;
import com.albert.com.productservice.repository.ProductRepository;
import com.albert.com.productservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Flux<ProductDto> getAllProducts() {
        return this.productRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Flux<ProductDto> getProductsByRange(int min, int max) {
        return this.productRepository.findByPriceBetween(Range.closed(min, max))
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return this.productRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDto) {
        return productDto.map(EntityDtoUtil::toEntity)
                .flatMap(this.productRepository::insert)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDto) {
        return this.productRepository.findById(id)
                .flatMap(p -> productDto.map(
                        EntityDtoUtil::toEntity))
                .doOnNext(e -> e.setId(id))
                .flatMap(this.productRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteProduct(String id) {
        return this.productRepository.deleteById(id);
    }


}
