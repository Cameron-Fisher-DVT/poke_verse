package za.co.dvt.pokeverse.features.pokedex.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.PokemonInformation
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.FetchPokemonInformationUseCase
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.FetchPokemonListUseCase
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.SavePokemonListUseCase
import za.co.dvt.pokeverse.presentation.BaseViewModel
import za.co.dvt.pokeverse.presentation.navigation.Destination
import za.co.dvt.pokeverse.presentation.navigation.Navigator

class PokedexScreenViewModel(
    private val fetchPokemonListUseCase: FetchPokemonListUseCase,
    private val savePokemonListUseCase: SavePokemonListUseCase,
    private val fetchPokemonInformationUseCase: FetchPokemonInformationUseCase,
    private val navigator: Navigator
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

    fun navigateToPokedexStatScreen() = viewModelScope.launch(Dispatchers.IO) {
        navigator.navigate(destination = Destination.PokedexStatScreen.route)
    }

    fun navigateToMenuScreen() = viewModelScope.launch(Dispatchers.IO) {
        navigator.navigate(destination = Destination.MenuScreen.route)
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
                fetchPokemonInformationList(result.data)
            }
        }
    }

    fun fetchPokemonInformationList(pokemonList: List<Pokemon>) = CoroutineScope(Dispatchers.IO).launch {
        val deferredResults = pokemonList.associateWith { pokemon ->
            async {
                fetchPokemonInformationUseCase(pokemon.pokemonId)
            }
        }

        val combinedResults = deferredResults.mapValues { (_, deferred) ->
            deferred.await()
        }

        val successfulResults = combinedResults
            .filter { (_, result) -> result is Result.Success }
            .map { (pokemon, result) ->
                val pokemonInformation = (result as Result.Success<PokemonInformation>).data
                pokemon.copy(
                    imageUrl = pokemonInformation.frontDefaultSprite,
                    isBattleOnly = pokemonInformation.isBattleOnly,
                    statsList = pokemonInformation.stats
                )
            }

        savePokemonListUseCase(successfulResults)

        displayProgressDialog(false)
        pokemonListMutableState.value = PokemonListState(pokemonList = successfulResults)
    }
}