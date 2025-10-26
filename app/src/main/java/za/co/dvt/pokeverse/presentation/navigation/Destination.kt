package za.co.dvt.pokeverse.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    val route: String

    @Serializable
    data object HomeGraph : Destination {
        override val route = "HomeGraph"
    }

    @Serializable
    data object MenuScreen : Destination {
        override val route = "MenuScreen"
    }

    @Serializable
    data object PokedexStatScreen: Destination {
        override val route = "PokedexStatScreen"
    }

    @Serializable
    data object PokedexScreen: Destination {
        override val route = "PokedexScreen"
    }
}