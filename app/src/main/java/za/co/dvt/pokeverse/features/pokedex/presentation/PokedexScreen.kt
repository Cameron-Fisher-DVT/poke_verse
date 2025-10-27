package za.co.dvt.pokeverse.features.pokedex.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import za.co.dvt.composecorelib.common.data.model.Item
import za.co.dvt.composecorelib.features.presentation.buttons.CustomCardItemView
import za.co.dvt.composecorelib.features.presentation.miscellaneous.ProgressDialogView
import za.co.dvt.pokeverse.common.extensions.toTitleCase
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.description
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexScreenViewModel.PokemonListState
import za.co.dvt.pokeverse.presentation.ui.theme.LocalDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexScreen(
    modifier: Modifier = Modifier,
    pokemonListState: State<PokemonListState>,
    displayProgressDialogState: State<Boolean>,
    paginate: (Boolean) -> Unit,
    canLoadPrevious: State<Boolean>,
    canLoadNext: State<Boolean>,
    pokemonItemsMutableState: State<String>,
    onNavigateToPokedexStatScreenClick: (pokemon: Pokemon) -> Unit,
    onNavigateToMenuClick: () -> Unit
) {
    val dimensions = LocalDimensions.current
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        topBar = {
            TopAppBar(
                title = { Text("PokeVerse") },
                actions = {

                    IconButton(
                        onClick = { paginate(false) },
                        enabled = canLoadPrevious.value
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                            contentDescription = "Previous"
                        )
                    }

                    Text(
                        text = pokemonItemsMutableState.value,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    IconButton(
                        onClick = { paginate(true) },
                        enabled = canLoadNext.value
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                            contentDescription = "Next"
                        )
                    }
                    IconButton(
                        onClick = { onNavigateToMenuClick() }
                    ) {
                        Icon(Icons.Rounded.Menu, "Menu")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        },
        sheetContent = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = dimensions.spacing16, end = dimensions.spacing16, bottom = dimensions.spacing16)
            ) {
                Text(
                    text = "Pokemon Go",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                if (pokemonListState.value.pokemonList.isNotEmpty()) {
                    val pokemonGo = pokemonListState.value.pokemonList.random()
                    CustomCardItemView(
                        itemBuilder = Item.Builder()
                            .title(pokemonGo.name.toTitleCase())
                            .description(pokemonGo.description())
                            .imageUrl(pokemonGo.imageUrl),
                        onFavoriteClick = {
                        }
                    ) {
                    }
                }
            }
        },
        sheetPeekHeight = dimensions.spacing100,
        sheetShadowElevation = dimensions.spacing10,
        sheetSwipeEnabled = true,
        scaffoldState = scaffoldState
    ) { padding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val pokemonList = pokemonListState.value.pokemonList
                LazyColumn {
                    items(
                        count = pokemonList.size
                    ) { index ->
                        CustomCardItemView(
                            itemBuilder = Item.Builder()
                                .title(pokemonList[index].name.toTitleCase())
                                .subTitle("${pokemonList[index].statsList.first().stat.name}: ${pokemonList[index].statsList.first().score}")
                                .imageUrl(pokemonList[index].imageUrl),
                            onFavoriteClick = {
                            }
                        ) {
                            onNavigateToPokedexStatScreenClick(pokemonList[index])
                        }
                    }
                }




            }
        }
    }

    ProgressDialogView(isLoading = displayProgressDialogState.value)
}

@Composable
@Preview(showBackground = true)
fun PreviewPokedexScreen() {
    PokedexScreen(
        pokemonListState = remember { mutableStateOf(PokemonListState()) },
        displayProgressDialogState = remember { mutableStateOf(false) },
        onNavigateToPokedexStatScreenClick = {},
        paginate = {},
        canLoadPrevious = remember { mutableStateOf(false)},
        canLoadNext = remember { mutableStateOf(false)},
        pokemonItemsMutableState = remember { mutableStateOf("") }
    ) {}
}