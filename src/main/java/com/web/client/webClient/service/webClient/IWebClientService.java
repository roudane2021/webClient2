package com.web.client.webClient.service.webClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface IWebClientService<T> {



    Flux<T> methodeGetFlux(final String baseUrl, String uri, final Map<String, String> headers, final Map<String, Object> paramGet, Class<T> cl);

    Mono<T> methodeGetMono(final String baseUrl, String uri, final Map<String, String> headers, final Map<String, Object> paramGet, Class<T> cl);

    Mono<T> methodePostMono(final String baseUrl, String uri, final Map<String, String> headers, final Object body, Class<T> cl);

    Flux<T> methodePostFlux(final String baseUrl, String uri, final Map<String, String> headers, final Object body, Class<T> cl);
}
