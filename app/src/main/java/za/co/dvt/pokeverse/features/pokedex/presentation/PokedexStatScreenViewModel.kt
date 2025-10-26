package za.co.dvt.pokeverse.features.pokedex.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.dvt.pokeverse.presentation.BaseViewModel
import za.co.dvt.pokeverse.presentation.navigation.Navigator

class PokedexStatScreenViewModel(
    private val navigator: Navigator
): BaseViewModel() {
    fun navigateToPokedexScreen() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigateUp()
    }
}