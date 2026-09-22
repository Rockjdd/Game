package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = GamePurple,
    onPrimary = Color.White,
    primaryContainer = GamePurpleBg,
    onPrimaryContainer = GamePurpleDark,
    secondary = GamePink,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFDF2F8),
    onSecondaryContainer = GamePinkDark,
    tertiary = GameGold,
    onTertiary = Color.White,
    tertiaryContainer = GameGoldLight,
    onTertiaryContainer = GameGoldDark,
    background = GameCanvas,
    onBackground = GameTextPrimary,
    surface = GameCardBg,
    onSurface = GameTextPrimary,
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = GameTextSecondary,
    outline = GameCardBorder
)

private val DarkColorScheme = darkColorScheme(
    primary = GamePurpleLight,
    onPrimary = Color(0xFF1E1035),
    primaryContainer = GamePurpleDark,
    onPrimaryContainer = Color.White,
    secondary = GamePinkLight,
    onSecondary = Color(0xFF380D21),
    secondaryContainer = GamePinkDark,
    onSecondaryContainer = Color.White,
    tertiary = GameGoldLight,
    onTertiary = Color(0xFF332002),
    tertiaryContainer = GameGoldDark,
    onTertiaryContainer = Color.White,
    background = Color(0xFF0F172A),
    onBackground = Color(0xFFF8FAFC),
    surface = Color(0xFF1E293B),
    onSurface = Color(0xFFF8FAFC),
    surfaceVariant = Color(0xFF334155),
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = Color(0xFF475569)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // For a vibrant game experience, use intentional game palette
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
