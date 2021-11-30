package co.misiontic.p53.account_ms.repositories;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import co.misiontic.p53.account_ms.models.Pokemon;
import reactor.core.publisher.Mono;

@Service
public class PokemonRepository implements IPokemonRepository {

    // @Autowired
    // WebClient webClient;

    @Override
    public Mono<Pokemon> GetPokemon(int idPokemon) {
        WebClient webClient = WebClient.create("https://pokeapi.co/api/v2/");
        return webClient.get()
                .uri("pokemon/" + idPokemon)
                .retrieve()
                .bodyToMono(Pokemon.class);
    }

}
