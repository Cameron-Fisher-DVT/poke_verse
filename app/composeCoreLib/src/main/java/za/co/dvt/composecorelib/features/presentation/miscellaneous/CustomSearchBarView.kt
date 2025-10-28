package za.co.dvt.composecorelib.features.presentation.miscellaneous

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBarView(
    modifier: Modifier = Modifier,
    searchHistoryList: List<String>,
    onSearch: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = {
            onSearch(it)
            active = false
            text = ""
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text("Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon"
                )
            }
        },
        modifier = modifier.fillMaxWidth(1f)
    ) {
        searchHistoryList.forEach { historyItem ->
            Row(modifier = modifier.padding(all = 16.dp)) {
                Icon(
                    modifier = modifier.padding(end = 10.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "History Icon"
                )
                Text(historyItem)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomSearchBarView() {
    CustomSearchBarView(
        searchHistoryList = listOf()
    ) { }
}