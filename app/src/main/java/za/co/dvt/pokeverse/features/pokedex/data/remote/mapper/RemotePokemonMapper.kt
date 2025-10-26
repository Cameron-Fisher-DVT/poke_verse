package za.co.dvt.pokeverse.features.pokedex.data.remote.mapper

import za.co.dvt.pokeverse.common.extensions.extractIdFromUrl
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.PokemonInformationResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto.PokemonAbilityDto
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto.StatDto
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto.StatsDto
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.PokemonAbility
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.PokemonInformation
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Stat
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Stats

object RemotePokemonMapper {
    fun mapToPokemonList(pokemonListResponse: PokemonListResponse): List<Pokemon> {
        return pokemonListResponse.pokemonResultDtoList.map {
            Pokemon(
                name = it.name,
                pokemonId = it.url.extractIdFromUrl()
            )
        }
    }

    fun mapToPokemonInformation(pokemonInformationResponse: PokemonInformationResponse): PokemonInformation {
        return PokemonInformation(
            frontDefaultSprite = pokemonInformationResponse.spriteDto.frontDefault,
            pokemonAbilityList = mapToPokemonAbilityList(pokemonInformationResponse.pokemonAbilityDtoList),
            isBattleOnly = pokemonInformationResponse.isBattleOnly,
            stats = mapToStatsList(pokemonInformationResponse.stats)
        )
    }

    fun mapToPokemonAbility(pokemonAbilityDto: PokemonAbilityDto): PokemonAbility {
        return PokemonAbility(
            pokemonAbilityId = pokemonAbilityDto.abilityDto.url.extractIdFromUrl(),
            name = pokemonAbilityDto.abilityDto.name
        )
    }

    fun mapToPokemonAbilityList(pokemonAbilityDtoList: List<PokemonAbilityDto>): List<PokemonAbility> {
        return pokemonAbilityDtoList.map { mapToPokemonAbility(it) }
    }

    fun mapToStat(statDto: StatDto): Stat {
        return Stat(
            name = statDto.name
        )
    }

    fun mapToStats(statsDto: StatsDto): Stats {
        return Stats(
            score = statsDto.score,
            stat = mapToStat(statsDto.statDto)
        )
    }

    fun mapToStatsList(statsDtoList: List<StatsDto>): List<Stats> {
        return statsDtoList.map { mapToStats(it) }
    }
}