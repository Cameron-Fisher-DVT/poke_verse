package za.co.dvt.pokeverse.features.pokedex.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.FetchPokemonListUseCase
import za.co.dvt.pokeverse.presentation.BaseViewModel

class PokedexScreenViewModel(
    private val fetchPokemonListUseCase: FetchPokemonListUseCase
) : BaseViewModel() {

    init {
        fetchPokemonList()
    }

    data class PokemonListState(
        val pokemonList: List<Pokemon> = emptyList(),
        val errorMessage: String = ""
    )

    private val displayProgressDialogMutableState = mutableStateOf(false)
    val displayProgressDialogState: State<Boolean> = displayProgressDialogMutableState

    private val pokemonListMutableState = mutableStateOf(PokemonListState())
    val pokemonListState: State<PokemonListState> = pokemonListMutableState

    private fun displayProgressDialog(shouldDisplay: Boolean = true) {
        displayProgressDialogMutableState.value = shouldDisplay
    }

    fun fetchPokemonList() = CoroutineScope(Dispatchers.IO).launch {
        displayProgressDialog()
        val result = fetchPokemonListUseCase()
        when (result) {
            is Result.Error -> {
                displayProgressDialog(false)
                pokemonListMutableState.value = PokemonListState(errorMessage = result.message)
            }
            is Result.Success<List<Pokemon>> -> {
                displayProgressDialog(false)
                pokemonListMutableState.value = PokemonListState(pokemonList = result.data)
            }
        }
    }
}