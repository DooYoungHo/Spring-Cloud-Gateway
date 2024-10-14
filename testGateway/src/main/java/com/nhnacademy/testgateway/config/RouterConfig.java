package com.nhnacademy.testgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("Task-api", p -> p.path("/account")
                        .filters(f -> f.rewritePath("/account/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost8081"))

                .route("Account-api", p -> p.path("/task")
                        .filters(f -> f.rewritePath("/task/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost8082"))

                .build();
    }
}
