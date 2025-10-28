package za.co.dvt.pokeverse.common.data.local.mapper

import org.koin.core.qualifier.named
import za.co.dvt.pokeverse.common.data.local.model.pokemon.PokemonAbilityEntity
import za.co.dvt.pokeverse.common.data.local.model.pokemon.PokemonEntity
import za.co.dvt.pokeverse.common.data.local.model.pokemon.PokemonWithAbilities
import za.co.dvt.pokeverse.common.data.local.model.search.SearchHistoryEntity
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.PokemonAbility
import za.co.dvt.pokeverse.features.pokedex.domain.model.search.SearchHistory

object LocalPokemonMapper {
    fun mapToPokemon(pokemonEntity: PokemonEntity): Pokemon {
        return Pokemon(
            pokemonId = pokemonEntity.pokemonId,
            name = pokemonEntity.name,
            imageUrl = pokemonEntity.imageUrl,
            isFavourite = pokemonEntity.isFavourite
        )
    }

    fun mapToPokemonEntity(pokemon: Pokemon): PokemonEntity {
        return PokemonEntity(
            pokemonId = pokemon.pokemonId,
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            isFavourite = pokemon.isFavourite
        )
    }

    fun mapToPokemonEntityList(pokemonList: List<Pokemon>): List<PokemonEntity> {
        return pokemonList.map { mapToPokemonEntity(it) }
    }

    fun mapToPokemon(pokemonWithAbilities: PokemonWithAbilities): Pokemon {
        return Pokemon(
            pokemonId = pokemonWithAbilities.pokemon.pokemonId,
            name = pokemonWithAbilities.pokemon.name,
            imageUrl = pokemonWithAbilities.pokemon.imageUrl,
            abilityList = mapToPokemonAbilityList(pokemonWithAbilities.pokemonAbilityList)
        )
    }

    fun mapToPokemonList(pokemonWithAbilitiesList: List<PokemonWithAbilities>): List<Pokemon> {
        return pokemonWithAbilitiesList.map { mapToPokemon(it) }
    }

    fun mapToPokemonAbility(pokemonAbilityEntity: PokemonAbilityEntity): PokemonAbility {
        return PokemonAbility(
            pokemonAbilityId = pokemonAbilityEntity.pokemonAbilityId,
            name = pokemonAbilityEntity.name
        )
    }

    fun mapToPokemonAbilityList(pokemonAbilityEntityList: List<PokemonAbilityEntity>): List<PokemonAbility> {
        return pokemonAbilityEntityList.map { mapToPokemonAbility(it) }
    }

    fun mapToSearchHistory(searchHistoryEntity: SearchHistoryEntity): SearchHistory {
        return SearchHistory(
            query = searchHistoryEntity.query
        )
    }

    fun mapToSearchHistoryList(searchHistoryEntityList: List<SearchHistoryEntity>): List<SearchHistory> {
        return searchHistoryEntityList.map {
            SearchHistory(
                query = it.query
            )
        }
    }

    fun mapToSearchHistoryEntity(searchHistory: SearchHistory): SearchHistoryEntity {
        return SearchHistoryEntity(
            query = searchHistory.query
        )
    }
}