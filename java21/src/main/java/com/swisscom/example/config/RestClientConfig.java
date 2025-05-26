package com.swisscom.example.config;

import com.swisscom.oce.mise.rest.restclient.OceRestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
class RestClientConfig {

    private static final String URL = "https://jsonplaceholder.typicode.com";

    @Bean("placeHolderRestClient")
    RestClient restClient(OceRestClientBuilder builder) {
        return builder.withLogging(true)
                .createRestClientBuilder()
                .baseUrl(URL)
                .requestFactory(clientHttpRequestFactory())
                .defaultHeader("Accept", "application/json")
                .build();
    }

    ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(5));
        factory.setReadTimeout(Duration.ofSeconds(5));
        return factory;
    }
}
