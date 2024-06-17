package com.example;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/user")
    public Mono<String> user(ServerHttpRequest request,
                                                ServerHttpResponse response){
        return  Mono.just("用户服务正忙，请重试");
    }
    @GetMapping("/business")
    public Mono<String> business(ServerHttpRequest request,
                             ServerHttpResponse response){
        return  Mono.just("商家服务正忙，请重试");
    }
    @GetMapping("/cart")
    public Mono<String> cart(ServerHttpRequest request,
                             ServerHttpResponse response){
        return  Mono.just("购物车服务正忙，请重试");
    }
    @GetMapping("/deliveryAddress")
    public Mono<String> deliveryAddress(ServerHttpRequest request,
                             ServerHttpResponse response){
        return  Mono.just("地址服务正忙，请重试");
    }
    @GetMapping("/food")
    public Mono<String> food(ServerHttpRequest request,
                             ServerHttpResponse response){
        return  Mono.just("商品服务正忙，请重试");
    }
    @GetMapping("/orders")
    public Mono<String> orders(ServerHttpRequest request,
                             ServerHttpResponse response){
        return  Mono.just("订单服务正忙，请重试");
    }
}
