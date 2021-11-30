package co.misiontic.p53.account_ms.repositories;

import co.misiontic.p53.account_ms.models.Pokemon;
import reactor.core.publisher.Mono;

public interface IPokemonRepository {
    Mono<Pokemon> GetPokemon(int idPokemon);
}
