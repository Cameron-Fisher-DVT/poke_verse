package za.co.dvt.pokeverse.features.pokedex.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.UpdatePokemonUseCase
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexScreenViewModel.PokemonListState
import za.co.dvt.pokeverse.presentation.BaseViewModel
import za.co.dvt.pokeverse.presentation.navigation.Navigator

class PokedexStatScreenViewModel(
    private val pokedexFlowManager: PokedexFlowManager,
    private val navigator: Navigator,
    private val updatePokemonUseCase: UpdatePokemonUseCase
): BaseViewModel() {
    fun navigateToPokedexScreen() = viewModelScope.launch(Dispatchers.Main) {
        navigator.navigateUp()
    }

    fun getSelectedPokemon(): Pokemon = pokedexFlowManager.selectedPokemon

    private val displayProgressDialogMutableState = mutableStateOf(false)
    val displayProgressDialogState: State<Boolean> = displayProgressDialogMutableState

    private fun displayProgressDialog(shouldDisplay: Boolean = true) {
        displayProgressDialogMutableState.value = shouldDisplay
    }

    data class PokemonFavouriteState(
        val pokemon: Pokemon = Pokemon(),
        val errorMessage: String = ""
    )

    private val pokemonFavouriteMutableState = mutableStateOf(PokemonFavouriteState(pokemon = pokedexFlowManager.selectedPokemon))
    val pokemonFavouriteState: State<PokemonFavouriteState> = pokemonFavouriteMutableState

    fun updatePokemon(pokemon: Pokemon) = CoroutineScope(Dispatchers.IO).launch {
        displayProgressDialog()
        val result = updatePokemonUseCase(pokemon)
        when (result) {
            is Result.Error -> {
                displayProgressDialog(false)
                pokemonFavouriteMutableState.value = PokemonFavouriteState(errorMessage = result.message)
            }

            is Result.Success<Pokemon> -> {
                displayProgressDialog(false)
                pokemonFavouriteMutableState.value = PokemonFavouriteState(pokemon = result.data)
                if (result.data.isFavourite) {
                    displaySnackbar("Added to favourites.")
                } else {
                    displaySnackbar("Removed from favourites.")
                }
            }
        }
    }
}