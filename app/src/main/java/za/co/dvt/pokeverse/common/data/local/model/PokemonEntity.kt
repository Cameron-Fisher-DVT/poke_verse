package za.co.dvt.pokeverse.common.data.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["pokemonId"], unique = true)], tableName = "Pokemon")
data class PokemonEntity(
    @PrimaryKey
    val pokemonId: String = "",
    val name: String = "",
    val imageUrl: String = ""
)