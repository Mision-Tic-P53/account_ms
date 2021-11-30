package co.misiontic.p53.account_ms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.misiontic.p53.account_ms.models.Pokemon;
import co.misiontic.p53.account_ms.repositories.IPokemonRepository;
import reactor.core.publisher.Mono;

@RestController
public class PokemonController {
    @Autowired
    private IPokemonRepository pokemonRepository;

    @GetMapping("getPokemon/{idPokemon}")
    public Mono<Pokemon> getPokemon(@PathVariable Integer idPokemon) {
        return pokemonRepository.GetPokemon(idPokemon);
    }
}
