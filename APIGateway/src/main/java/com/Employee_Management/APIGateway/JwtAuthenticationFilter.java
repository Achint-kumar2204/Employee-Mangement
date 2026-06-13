package com.Employee_Management.APIGateway;

import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        System.out.println("JWT FILTER EXECUTED");

        String path = exchange.getRequest()
                .getURI()
                .getPath();

        // Allow Auth APIs without token
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        // Header missing or invalid format
        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse()
                    .setComplete();
        }

        String token = authHeader.substring(7);

        try {

            String role =
                    jwtUtil.extractRole(token);

            String method =
                    exchange.getRequest()
                            .getMethod()
                            .name();

            System.out.println(
                    "ROLE = " + role);

            if ("POST".equals(method)
                    && !"ADMIN".equals(role)) {

                exchange.getResponse()
                        .setStatusCode(
                                HttpStatus.FORBIDDEN);

                return exchange.getResponse()
                        .setComplete();
            }

            return chain.filter(exchange);

        } catch (Exception ex) {

            exchange.getResponse()
                    .setStatusCode(
                            HttpStatus.UNAUTHORIZED);

            return exchange.getResponse()
                    .setComplete();
        }
    }
}
