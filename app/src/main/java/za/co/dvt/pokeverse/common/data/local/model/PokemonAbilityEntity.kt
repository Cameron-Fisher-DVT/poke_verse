package za.co.dvt.pokeverse.common.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index(value = ["pokemonAbilityId"], unique = true),
        Index(value = ["pokemonId"])
    ], tableName = "PokemonAbility",
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["pokemonId"],
            childColumns = ["pokemonId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class PokemonAbilityEntity(
    @PrimaryKey
    val pokemonAbilityId: String = "",
    val name: String = "",
    val pokemonId: String = ""
)