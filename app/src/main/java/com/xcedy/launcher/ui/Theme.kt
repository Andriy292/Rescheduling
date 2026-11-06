package com.xcedy.launcher.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Green = Color(0xFF3DDC84)
private val DarkGreen = Color(0xFF0B2A1A)
private val Surface = Color(0xFF123524)

private val Colors = darkColorScheme(
    primary = Green,
    onPrimary = Color.Black,
    background = DarkGreen,
    surface = Surface,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun LauncherTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Colors, content = content)
}
