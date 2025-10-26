package za.co.dvt.pokeverse.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexScreen
import za.co.dvt.pokeverse.presentation.navigation.Navigation
import za.co.dvt.pokeverse.presentation.ui.theme.PokeVerseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkTheme = remember { mutableStateOf(false) }
            PokeVerseTheme(darkTheme = darkTheme.value) {
                Navigation { isDarkMode ->
                    darkTheme.value = isDarkMode
                }
            }
        }
    }
}