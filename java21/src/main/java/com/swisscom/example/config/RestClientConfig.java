package com.swisscom.example.config;

import com.swisscom.oce.mise.rest.restclient.OceRestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private static final String URL = "https://jsonplaceholder.typicode.com";

    @Bean("placeHolderRestClient")
    public RestClient restClient(OceRestClientBuilder builder) {
        return builder.withLogging(true)
                .createRestClientBuilder()
                .baseUrl(URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
