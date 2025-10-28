package za.co.dvt.pokeverse.features.menu.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.menu.domain.usecase.FetchDarkModeUseCase
import za.co.dvt.pokeverse.features.menu.domain.usecase.SaveDarkModeUseCase
import za.co.dvt.pokeverse.presentation.BaseViewModel
import za.co.dvt.pokeverse.presentation.navigation.Navigator

class MenuScreenViewModel(
    private val navigator: Navigator,
    private val saveDarkModeUseCase: SaveDarkModeUseCase,
    private val fetchDarkModeUseCase: FetchDarkModeUseCase
): BaseViewModel() {
    val isDarkModeMutableState = mutableStateOf(false)

    init {
        fetchDarkMode()
    }

    fun navigateToPokedexScreen() = viewModelScope.launch(Dispatchers.Main) {
        navigator.navigateUp()
    }

    fun saveDarkMode(isDarkMode: Boolean) = viewModelScope.launch {
        isDarkModeMutableState.value = isDarkMode
        saveDarkModeUseCase(isDarkMode)
    }

    fun fetchDarkMode() = viewModelScope.launch {
        when (val result = fetchDarkModeUseCase()) {
            is Result.Error -> {
                isDarkModeMutableState.value = false
            }
            is Result.Success<Boolean> -> {
                isDarkModeMutableState.value = result.data
            }
        }
    }
}