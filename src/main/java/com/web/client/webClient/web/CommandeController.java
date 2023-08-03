package com.web.client.webClient.web;

import com.web.client.webClient.service.demande.ICommandeService;
import com.web.client.webClient.web.dto.CommandeDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/commandes")
@AllArgsConstructor
public class CommandeController {

    private final ICommandeService commandeService;

   // @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   @RequestMapping(method = RequestMethod.GET)
    public Flux<CommandeDto> index() throws InterruptedException, ExecutionException, WebClientResponseException {

        return this.commandeService.listCommande();
    }

    @RequestMapping(value = "/methodeGetWithParamGet/{code}",method = RequestMethod.GET)
    public Mono<CommandeDto> methodeGetWithParamGet(@PathVariable String code) throws InterruptedException, ExecutionException, WebClientResponseException {

        return this.commandeService.commandeWithID(code);
    }

    @RequestMapping(value = "/methodeGetWithHeader/{code}",method = RequestMethod.GET)
    public Mono<CommandeDto> methodeGetWithHeader(@PathVariable String code) throws InterruptedException, ExecutionException, WebClientResponseException {
        return this.commandeService.commandeWithID(code);
    }

    @RequestMapping(value = "/methodePostMono",method = RequestMethod.POST)
    public Mono<CommandeDto> methodePostWithHeader(@RequestBody CommandeDto commandeDto) throws InterruptedException, ExecutionException, WebClientResponseException {
        return this.commandeService.saveCommande(commandeDto);
    }

}
