package za.co.dvt.pokeverse.features.pokedex.presentation

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import za.co.dvt.composecorelib.features.presentation.buttons.CustomCardItemView
import za.co.dvt.composecorelib.features.presentation.miscellaneous.ProgressDialogView
import za.co.dvt.composecorelib.common.data.model.Item
import za.co.dvt.pokeverse.common.extensions.toTitleCase
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.description
import za.co.dvt.pokeverse.presentation.ui.theme.LocalDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexStatScreen(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    pokemonFavouriteState: State<PokedexStatScreenViewModel.PokemonFavouriteState>,
    displayProgressDialogState: State<Boolean>,
    onFavouriteClick: (pokemon: Pokemon) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val dimensions = LocalDimensions.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pokemon name") },
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigateUp() }
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "ArrowBack")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        }
    ) { padding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = modifier.padding(top = dimensions.spacing16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomCardItemView(
                    itemBuilder = Item.Builder()
                        .title(pokemon.name.toTitleCase())
                        .subTitle("${pokemon.statsList.first().stat.name}: ${pokemon.statsList.first().score}")
                        .description(pokemon.description())
                        .imageUrl(pokemon.imageUrl),
                    isFavoriteItem = pokemonFavouriteState.value.pokemon.isFavourite,
                    showFavoriteIcon = true,
                    onFavoriteClick = {
                        onFavouriteClick(pokemon.copy(isFavourite = !pokemonFavouriteState.value.pokemon.isFavourite))
                    }
                ) {}
            }
        }
    }
    ProgressDialogView(isLoading = displayProgressDialogState.value)
}

@Composable
@Preview(showBackground = true)
fun PreviewPokedexStatScreen() {
    PokedexStatScreen(
        pokemon = Pokemon(),
        displayProgressDialogState = remember { mutableStateOf(false) },
        pokemonFavouriteState = remember { mutableStateOf(PokedexStatScreenViewModel.PokemonFavouriteState()) },
        onFavouriteClick = {}
    ) {}
}
