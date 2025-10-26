package za.co.dvt.pokeverse.features.menu.presentation

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.dvt.pokeverse.presentation.BaseViewModel
import za.co.dvt.pokeverse.presentation.navigation.Navigator

class MenuScreenViewModel(
    private val navigator: Navigator
): BaseViewModel() {
    val isDarkModeMutableState = mutableStateOf(false)

    fun navigateToPokedexScreen() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigateUp()
    }
}