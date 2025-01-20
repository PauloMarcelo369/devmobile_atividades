package com.example.postagram.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import backgroundDark
import backgroundLight
import errorDark
import errorLight
import onBackgroundDark
import onBackgroundLight
import onErrorDark
import onErrorLight
import onPrimaryDark
import onPrimaryLight
import onSecondaryDark
import onSecondaryLight
import onSurfaceDark
import onSurfaceLight
import primaryDark
import primaryLight
import secondaryDark
import secondaryLight
import surfaceDark
import surfaceLight

private val lightScheme = lightColors(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    error = errorLight,
    onError = onErrorLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight
)

private val darkScheme = darkColors(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    error = errorDark,
    onError = onErrorDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark
)

@Composable
fun PostagramTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) darkScheme else lightScheme

    MaterialTheme(
        colors = colors,
        content = content
    )
}