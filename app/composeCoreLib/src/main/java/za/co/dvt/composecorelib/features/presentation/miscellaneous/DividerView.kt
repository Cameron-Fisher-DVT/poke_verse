package za.co.dvt.composecorelib.features.presentation.miscellaneous

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import za.co.dvt.composecorelib.presentation.ui.theme.LocalDimensions

@Composable
fun DividerView(
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray
) {
    val dimensions = LocalDimensions.current
    HorizontalDivider(
        modifier
            .fillMaxWidth()
            .width(dimensions.spacing1)
            .padding(top = dimensions.spacing5, bottom = dimensions.spacing5),
        color = color
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewDividerView() {
    DividerView()
}