package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = RosePrimary,
    onPrimary = BlushSurface,
    primaryContainer = RoseLight,
    onPrimaryContainer = RosePrimaryDark,
    secondary = RoseVivid,
    onSecondary = BlushSurface,
    secondaryContainer = BlushSurfaceVariant,
    onSecondaryContainer = EspressoText,
    tertiary = StarGold,
    background = BlushBackground,
    onBackground = EspressoText,
    surface = BlushSurface,
    onSurface = EspressoText,
    surfaceVariant = BlushSurfaceVariant,
    onSurfaceVariant = EspressoMuted,
    outline = BlushBorder
)

private val DarkColorScheme = darkColorScheme(
    primary = RosePrimaryDarkTheme,
    onPrimary = BackgroundDarkTheme,
    primaryContainer = SurfaceVariantDarkTheme,
    onPrimaryContainer = TextLightDarkTheme,
    secondary = RosePrimaryDarkTheme,
    onSecondary = BackgroundDarkTheme,
    secondaryContainer = SurfaceDarkTheme,
    onSecondaryContainer = TextLightDarkTheme,
    tertiary = StarGold,
    background = BackgroundDarkTheme,
    onBackground = TextLightDarkTheme,
    surface = SurfaceDarkTheme,
    onSurface = TextLightDarkTheme,
    surfaceVariant = SurfaceVariantDarkTheme,
    onSurfaceVariant = TextMutedDarkTheme,
    outline = BorderDarkTheme
)

@Composable
fun BlushAndBrewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
