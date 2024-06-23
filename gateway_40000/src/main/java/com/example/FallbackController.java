package com.example;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import org.springframework.core.io.buffer.DataBuffer;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @RequestMapping("/user")
    public Mono<String> user(ServerHttpRequest request, ServerHttpResponse response){
        return  Mono.just("用户服务正忙，请重试");
    }
    @RequestMapping("/orders")
    public Mono<Void> orders(ServerHttpRequest request, ServerHttpResponse response){
        response.setStatusCode(HttpStatusCode.valueOf(500));
        String responseBody = "订单服务正忙，请重试";
        DataBuffer dataBuffer = response.bufferFactory().wrap(responseBody.getBytes(StandardCharsets.UTF_8));

        return  response.writeWith(Mono.just(dataBuffer));
    }
    @RequestMapping("/cart")
    public Mono<String> cart(ServerHttpRequest request, ServerHttpResponse response){
        return  Mono.just("购物车服务正忙，请重试");
    }
    @RequestMapping("/deliveryAddress")
    public Mono<String> deliveryAddress(ServerHttpRequest request, ServerHttpResponse response){
        return  Mono.just("地址服务正忙，请重试");
    }
    @RequestMapping("/food")
    public Mono<String> food(ServerHttpRequest request, ServerHttpResponse response){
        return  Mono.just("商品服务正忙，请重试");
    }
    @RequestMapping("/business")
    public Mono<String> business(ServerHttpRequest request, ServerHttpResponse response){
        return  Mono.just("商家服务正忙，请重试");
    }
}
