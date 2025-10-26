package za.co.dvt.pokeverse.features.menu.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    isDarkModeMutableState: MutableState<Boolean>,
    onNavigateUp: () -> Unit,
    onToggleDarkMode: (isDarkMode: Boolean) -> Unit
) {
    Scaffold(
        modifier = modifier.wrapContentSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Menu")
                },
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
            Column(modifier = modifier.padding(16.dp)) {
                Text(
                    modifier = modifier.padding(start = 16.dp),
                    text = "Settings",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dark Mode",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Switch(
                        checked = isDarkModeMutableState.value,
                        onCheckedChange = {
                            isDarkModeMutableState.value = it
                            onToggleDarkMode(it)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen(
        isDarkModeMutableState = remember { mutableStateOf(false) },
        onNavigateUp = {
        }
    ) {}
}