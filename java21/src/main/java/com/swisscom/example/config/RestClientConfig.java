package com.swisscom.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private static final String URL = "https://jsonplaceholder.typicode.com";

    @Bean("placeHolderRestClient")
    public RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl(URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
