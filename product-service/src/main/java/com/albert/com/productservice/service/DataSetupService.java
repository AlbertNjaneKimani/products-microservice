//package com.albert.com.productservice.service;
//
//import com.albert.com.productservice.dto.ProductDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//public class DataSetupService implements CommandLineRunner {
//    @Autowired
//    private ProductService productService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        ProductDto p1 = new ProductDto("Hp Dell", 100);
//        ProductDto p2 = new ProductDto("Hp ", 400);
//        ProductDto p3 = new ProductDto("Samsung", 1500);
//        ProductDto p4 = new ProductDto("Apple", 1000);
//        Flux.just(p1, p2, p3, p4)
//                .flatMap(p -> this.productService.insertProduct(Mono.just(p)))
//                .subscribe(System.out::println);
//    }
//}
