package com.web.client.webClient.service.demande;

import com.web.client.webClient.web.dto.CommandeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICommandeService {

    Flux<CommandeDto> listCommande( );

    Mono<CommandeDto> commandeWithID( String code);


    Mono<CommandeDto> saveCommande( CommandeDto body);



}
