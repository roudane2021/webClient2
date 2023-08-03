package com.web.client.webClient.service.webClient.impl;

import com.web.client.webClient.service.webClient.IWebClientService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Service
public class WebClientServiceImpl<T> implements IWebClientService<T> {

    private final WebClient webClient = null;

    @Override
    public Flux<T> methodeGetFlux(final String baseUrl, String uri, final Map<String, String> headers, final Map<String, Object> paramGet, Class<T> cl) {

        return  this.webClient(baseUrl).get()
                .uri((UriBuilder ub)->{
                    if (Objects.nonNull(paramGet)){
                        return  ub.path(uri).build(paramGet);
                    }
                    return  ub.path(uri).build();
                })
                .headers((HttpHeaders h)->{
                    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
                    if (Objects.nonNull(headers)) {
                    headers.forEach((key, value) -> multiValueMap.add(key, value));
                        h.addAll(multiValueMap);
                    }
                })
                .exchangeToFlux(e -> e.bodyToFlux(cl));
    }

    @Override
    public Mono<T> methodeGetMono(final String baseUrl, String uri, final Map<String, String> headers, final Map<String, Object> paramGet, Class<T> cl) {

        return  this.webClient(baseUrl).get()
                .uri((UriBuilder ub)->{
                    if (Objects.nonNull(paramGet)){
                        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
                        paramGet.forEach((key, value) -> multiValueMap.add(key, (String)value));
                        return  ub.path(uri).replaceQueryParams(multiValueMap).build();
                    }
                    return  ub.path(uri).build();
                })
                .headers((HttpHeaders h)->{
                    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
                    if (Objects.nonNull(headers)) {
                        headers.forEach((key, value) -> multiValueMap.add(key, value));
                        h.addAll(multiValueMap);
                    }
                })
                .exchangeToMono(e -> e.bodyToMono(cl));
    }

    @Override
    public Mono<T> methodePostMono(final String baseUrl, String uri, final Map<String, String> headers, final Object body, Class<T> cl) {

        return  this.webClient(baseUrl).post()
                .uri(uri)
                .body(Mono.just(body), Object.class)
                .headers((HttpHeaders h)->{
                    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
                    if (Objects.nonNull(headers)) {
                        headers.forEach((key, value) -> multiValueMap.add(key, value));
                        h.addAll(multiValueMap);
                    }
                })
                .exchangeToMono(e -> e.bodyToMono(cl));
    }

    @Override
    public Flux<T> methodePostFlux(final String baseUrl, String uri, final Map<String, String> headers, final Object body, Class<T> cl) {

        return  this.webClient(baseUrl).post()
                .uri(uri)
                .body(Mono.just(body), Object.class)
                .headers((HttpHeaders h)->{
                    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
                    if (Objects.nonNull(headers)) {
                        headers.forEach((key, value) -> multiValueMap.add(key, value));
                        h.addAll(multiValueMap);
                    }
                })
                .exchangeToFlux(e -> e.bodyToFlux(cl));
    }







    private WebClient webClient(String baseUrl){

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
