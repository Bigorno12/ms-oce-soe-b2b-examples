package com.swisscom.example.config;

import com.swisscom.example.client.PostClient;
import com.swisscom.example.client.TodoClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class JsonPlaceHolderHttpClient {

    private final RestClient restClient;

    public JsonPlaceHolderHttpClient(@Qualifier("placeHolderRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Bean
    public TodoClient todoClient() {
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return proxyFactory.createClient(TodoClient.class);
    }

    @Bean
    public PostClient postClient() {
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return proxyFactory.createClient(PostClient.class);
    }
}
