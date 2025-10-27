package za.co.dvt.composecorelib.features.presentation.buttons

import za.co.dvt.composecorelib.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import za.co.dvt.composecorelib.features.presentation.informational.ExpandableTextView
import za.co.dvt.composecorelib.features.presentation.miscellaneous.DividerView
import za.co.dvt.composecorelib.common.data.model.Item
import za.co.dvt.composecorelib.presentation.ui.theme.LocalDimensions

@Composable
fun CustomCardItemView(
    modifier: Modifier = Modifier,
    itemBuilder: Item.Builder,
    isFavoriteItem: Boolean = false,
    showFavoriteIcon: Boolean = false,
    onFavoriteClick: (item: Item) -> Unit,
    onClick: (item: Item) -> Unit
) {
    val dimensions = LocalDimensions.current
    val item = itemBuilder.build()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Card(
        modifier = modifier
            .padding(top = dimensions.spacing16, start = dimensions.spacing16, end = dimensions.spacing16)
            .wrapContentSize()
            .clickable { onClick(item) }
    ) {
        if (showFavoriteIcon) {
            Box(modifier = Modifier.offset((screenWidth.minus(dimensions.spacing80)), dimensions.spacing24)) {
                Icon(
                    imageVector = if (isFavoriteItem) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(R.string.custom_card_item_view_favourite),
                    modifier = Modifier.clickable { onFavoriteClick(item) },
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensions.spacing16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = "itemImage",
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .width(dimensions.spacing100)
                    .height(dimensions.spacing120)
            )
            Column(
                modifier = Modifier.padding(start = dimensions.spacing16, end = dimensions.spacing16)
            ) {
                Text(
                    text = item.title,
                    modifier = modifier,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.subTitle,
                    modifier = modifier,
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    Text(
                        text = item.rating,
                        modifier = modifier,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = stringResource(R.string.custom_card_item_view_star),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        if (item.description.isNotBlank()) {
            DividerView()
            ExpandableTextView(
                text = item.description,
                modifier = modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCustomCardItemView() {
    CustomCardItemView(
        itemBuilder = Item.Builder()
            .description("Hello"),
        onFavoriteClick = {}) {
    }
}