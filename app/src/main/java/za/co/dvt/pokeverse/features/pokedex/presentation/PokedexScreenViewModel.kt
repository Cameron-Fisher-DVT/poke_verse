package za.co.dvt.pokeverse.features.pokedex.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
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
import kotlin.compareTo
import kotlin.text.compareTo

class PokedexScreenViewModel(
    private val pokedexFlowManager: PokedexFlowManager,
    private val navigator: Navigator,
    private val fetchPokemonListUseCase: FetchPokemonListUseCase,
    private val savePokemonListUseCase: SavePokemonListUseCase,
    private val fetchPokemonInformationUseCase: FetchPokemonInformationUseCase
) : BaseViewModel() {

    data class PokemonListState(
        val pokemonList: List<Pokemon> = emptyList(),
        val errorMessage: String = ""
    )

    data class RequestParameterState(
        val offset: Int = 0,
        val limit: Int = 20
    )

    private val requestParameterMutableState = mutableStateOf(RequestParameterState())
    val requestParameterState: State<RequestParameterState> = requestParameterMutableState

    private val displayProgressDialogMutableState = mutableStateOf(true)
    val displayProgressDialogState: State<Boolean> = displayProgressDialogMutableState

    private val pokemonListMutableState = mutableStateOf(PokemonListState())
    val pokemonListState: State<PokemonListState> = pokemonListMutableState

    private companion object {
        const val POKEMON_ITEMS_PER_PAGE = 20
        const val POKEMON_LIST_LIMIT = 100
    }

    val canLoadPreviousMutableState = mutableStateOf((requestParameterState.value.offset + requestParameterState.value.limit) > POKEMON_ITEMS_PER_PAGE)
    val canLoadNextMutableState = mutableStateOf(requestParameterState.value.limit < POKEMON_LIST_LIMIT)
    val pokemonItemsMutableState = mutableStateOf("${requestParameterState.value.offset + 1}-${minOf(requestParameterState.value.offset + requestParameterState.value.limit, POKEMON_LIST_LIMIT)}")

    fun updateOffset(increase: Boolean) {
        val newOffset = if (increase) {
            requestParameterState.value.offset + (requestParameterState.value.limit - requestParameterState.value.offset)
        } else {
            requestParameterState.value.offset - (requestParameterState.value.limit - requestParameterState.value.offset)
        }

        val newLimit = if (increase) {
            requestParameterState.value.limit + (requestParameterState.value.limit - requestParameterState.value.offset)
        } else {
            requestParameterState.value.limit - (requestParameterState.value.limit - requestParameterState.value.offset)
        }

        if (newOffset in 0..POKEMON_LIST_LIMIT) {
            requestParameterMutableState.value = RequestParameterState(offset = newOffset, limit = newLimit)
            fetchPokemonList()
        }

        canLoadPreviousMutableState.value = (requestParameterState.value.offset + requestParameterState.value.limit) > POKEMON_ITEMS_PER_PAGE
        canLoadNextMutableState.value = requestParameterState.value.limit < POKEMON_LIST_LIMIT
        pokemonItemsMutableState.value = "${requestParameterState.value.offset + 1}-${minOf(requestParameterState.value.limit, POKEMON_LIST_LIMIT)}"
    }


    init {
        fetchPokemonList()
    }

    private fun displayProgressDialog(shouldDisplay: Boolean = true) {
        displayProgressDialogMutableState.value = shouldDisplay
    }

    fun navigateToPokedexStatScreen() = viewModelScope.launch(Dispatchers.IO) {
        navigator.navigate(destination = Destination.PokedexStatScreen.route)
    }

    fun saveSelectedPokemon(pokemon: Pokemon) {
        pokedexFlowManager.selectedPokemon = pokemon
    }

    fun navigateToMenuScreen() = viewModelScope.launch(Dispatchers.IO) {
        navigator.navigate(destination = Destination.MenuScreen.route)
    }

    fun fetchPokemonList() = CoroutineScope(Dispatchers.IO).launch {
        displayProgressDialog()
        val result = fetchPokemonListUseCase(requestParameterState.value.offset, requestParameterState.value.limit)
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