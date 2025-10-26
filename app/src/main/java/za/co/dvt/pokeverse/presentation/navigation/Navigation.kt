package za.co.dvt.pokeverse.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import za.co.dvt.pokeverse.features.menu.presentation.MenuScreen
import za.co.dvt.pokeverse.features.menu.presentation.MenuScreenViewModel
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexScreen
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexScreenViewModel
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexStatScreen
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexStatScreenViewModel

@Composable
fun Navigation(
    onCheckChangedDarkMode: (isDarkMode: Boolean) -> Unit
) {
    val navController = rememberNavController()
    val navigator = koinInject<Navigator>()
    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when (action) {
            is NavigationAction.Navigate -> {
                navController.navigate(action.destination) {
                    action.navOptions(this)
                }
            }

            is NavigationAction.NavigateUp -> {
                navController.navigateUp()
            }
        }
    }
    NavHost(navController = navController, startDestination = navigator.startDestination) {
        navigation(
            route = Destination.HomeGraph.route,
            startDestination = Destination.PokedexScreen.route
        ) {
            composable(
                route = Destination.MenuScreen.route
            ) {
                val menuScreenViewModel = koinViewModel<MenuScreenViewModel>()
                MenuScreen(
                    isDarkModeMutableState = menuScreenViewModel.isDarkModeMutableState,
                    onNavigateUp = {
                        menuScreenViewModel.navigateToPokedexScreen()
                    }
                ) { isDarkMode ->
                    onCheckChangedDarkMode(isDarkMode)
                }
            }

            composable(
                route = Destination.PokedexScreen.route
            ) { backStackEntry ->
                val pokedexScreenViewModel = koinViewModel<PokedexScreenViewModel>()
                PokedexScreen(
                    pokemonListState = pokedexScreenViewModel.pokemonListState,
                    displayProgressDialogState = pokedexScreenViewModel.displayProgressDialogState,
                    onNavigateToPokedexStatScreenClick = { pokemon ->
                    pokedexScreenViewModel.saveSelectedPokemon(pokemon)
                    pokedexScreenViewModel.navigateToPokedexStatScreen()
                }) {
                    pokedexScreenViewModel.navigateToMenuScreen()
                }
            }

            composable(
                route = Destination.PokedexStatScreen.route
            ) {
                val pokedexStatScreenViewModel = koinViewModel<PokedexStatScreenViewModel>()

                PokedexStatScreen (
                    pokemon = pokedexStatScreenViewModel.getSelectedPokemon(),
                    onFavouriteClick = { pokemon ->
                        pokedexStatScreenViewModel.updatePokemon(pokemon)
                    },
                    displayProgressDialogState = pokedexStatScreenViewModel.displayProgressDialogState,
                    pokemonFavouriteState = pokedexStatScreenViewModel.pokemonFavouriteState,
                    onNavigateUp = {
                        pokedexStatScreenViewModel.navigateToPokedexScreen()
                    }
                )
            }
        }
    }
}