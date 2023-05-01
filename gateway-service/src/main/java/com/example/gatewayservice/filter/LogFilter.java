package com.example.gatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Args> {
    public LogFilter() {
        super(Args.class);
    }

    @Data
    public static class Args {
        private String message;
    }

    @Override
    public GatewayFilter apply(Args config) {
        return (exchange, chain) -> {
            log.info("LogFilter start: {}", config.getMessage());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                ServerHttpRequest request = exchange.getRequest();
                ServerHttpResponse response = exchange.getResponse();
                log.info("LogFilter end: {} {}", request.getId(), response.getStatusCode());
            }));
        };
    }

}
