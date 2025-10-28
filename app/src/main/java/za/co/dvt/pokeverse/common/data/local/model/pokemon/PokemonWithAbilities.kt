package za.co.dvt.pokeverse.common.data.local.model.pokemon

import androidx.room.Embedded
import androidx.room.Relation

class PokemonWithAbilities(
    @Embedded
    val pokemon: PokemonEntity,

    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "pokemonId"
    )
    val pokemonAbilityList: List<PokemonAbilityEntity>
)