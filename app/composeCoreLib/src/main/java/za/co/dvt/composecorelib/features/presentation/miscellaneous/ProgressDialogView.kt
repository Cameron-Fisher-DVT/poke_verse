package za.co.dvt.composecorelib.features.presentation.miscellaneous

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import za.co.dvt.composecorelib.presentation.ui.theme.LocalDimensions

@Composable
fun ProgressDialogView(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    val dimensions = LocalDimensions.current
    if (isLoading) {
        Dialog(
            onDismissRequest = { },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(dimensions.spacing100)
                    .background(Color(Color.White.value), shape = RoundedCornerShape(dimensions.spacing8))
            ) {
                CircularProgressIndicator()
            }
        }
    }

}

@Preview
@Composable
fun ProgressDialogViewPreview() {
    ProgressDialogView(isLoading = true)
}