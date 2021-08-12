package com.albert.com.productservice.util;

import com.albert.com.productservice.dto.ProductDto;
import com.albert.com.productservice.entity.Product;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public static Product toEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
