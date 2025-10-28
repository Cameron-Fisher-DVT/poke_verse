package za.co.dvt.composecorelib.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val spacing1: Dp = 1.dp,
    val spacing2: Dp = 2.dp,
    val spacing3: Dp = 3.dp,
    val spacing4: Dp = 4.dp,
    val spacing5: Dp = 5.dp,
    val spacing6: Dp = 6.dp,
    val spacing8: Dp = 8.dp,
    val spacing10: Dp = 10.dp,
    val spacing12: Dp = 12.dp,
    val spacing14: Dp = 14.dp,
    val spacing16: Dp = 16.dp,
    val spacing20: Dp = 20.dp,
    val spacing24: Dp = 24.dp,
    val spacing28: Dp = 28.dp,
    val spacing32: Dp = 32.dp,
    val spacing40: Dp = 40.dp,
    val spacing48: Dp = 48.dp,
    val spacing56: Dp = 56.dp,
    val spacing64: Dp = 64.dp,
    val spacing80: Dp = 80.dp,
    val spacing96: Dp = 96.dp,
    val spacing100: Dp = 100.dp,
    val spacing120: Dp = 120.dp,
    val spacing150: Dp = 150.dp
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }