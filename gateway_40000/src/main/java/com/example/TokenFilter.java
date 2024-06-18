package com.example;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TokenFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        String requestPath = exchange.getRequest().getPath().toString();

        if (requestPath.contains("/user/login")||requestPath.contains("/user/register")) {
            return chain.filter(exchange);
        }
        if (token == null || !validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    private boolean validateToken(String token) {
        // 在这里实现验证 Token 的逻辑，可以调用远程服务进行验证
        // 返回 true 表示 Token 有效，返回 false 表示 Token 无效
        return true;
    }
}

