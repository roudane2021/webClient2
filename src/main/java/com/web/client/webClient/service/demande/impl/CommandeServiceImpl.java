package com.web.client.webClient.service.demande.impl;

import com.web.client.webClient.service.demande.ICommandeService;
import com.web.client.webClient.service.webClient.IWebClientService;
import com.web.client.webClient.web.dto.CommandeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommandeServiceImpl implements ICommandeService {

    private final IWebClientService<CommandeDto> webClientService;


    private final String baseUrl;

    public CommandeServiceImpl(IWebClientService<CommandeDto> webClientService, @Value("${params.baseUrl}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClientService = webClientService;
    }
    @Override
    public Flux<CommandeDto> listCommande() {
        return webClientService.methodeGetFlux(baseUrl,"/demandes", null , null, CommandeDto.class);
    }

    @Override
    public Mono<CommandeDto> commandeWithID( String code) {
        Map<String, Object> paramGet = new HashMap<>();
        paramGet.put("code", code);
        return webClientService.methodeGetMono(baseUrl,"/demandes/withParamGet", null , paramGet, CommandeDto.class);
    }

    @Override
    public Mono<CommandeDto> saveCommande( CommandeDto body) {
        return webClientService.methodePostMono(baseUrl, "/demandes", null, body, CommandeDto.class);
    }
}
