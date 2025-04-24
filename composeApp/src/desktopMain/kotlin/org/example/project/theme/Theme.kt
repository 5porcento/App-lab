package org.example.project.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Cores inspiradas em laboratório de análise de água
val WaterBlue = Color(0xFF0A84FF) // Azul água principal
val DeepBlue = Color(0xFF0055CC) // Azul profundo para elementos de destaque
val AquaBlue = Color(0xFF00B8D4) // Azul aqua para elementos secundários
val LightAqua = Color(0xFFB2EBF2) // Azul aqua claro para backgrounds
val CrystalBlue = Color(0xFFE1F5FE) // Azul cristalino para backgrounds principais
val Teal = Color(0xFF00838F) // Verde-azulado para elementos complementares
val LightTeal = Color(0xFFB2DFDB) // Verde-azulado claro para elementos sutis
val Gray = Color(0xFF607D8B) // Cinza azulado para textos secundários
val LightGray = Color(0xFFECEFF1) // Cinza claro para backgrounds secundários
val White = Color(0xFFFFFFFF) // Branco para cards e superfícies
val AccentGreen = Color(0xFF00C853) // Verde para indicadores de sucesso/positivos
val Red = Color(0xFFD32F2F) // Vermelho para erros e alertas

private val DarkColorPalette = darkColors(
    primary = WaterBlue,
    primaryVariant = DeepBlue,
    secondary = AquaBlue,
    background = Color(0xFF1A1A1A),
    surface = Color(0xFF2D2D2D),
    onPrimary = White,
    onSecondary = Color.Black,
    onBackground = White,
    onSurface = White
)

private val LightColorPalette = lightColors(
    primary = WaterBlue,
    primaryVariant = DeepBlue,
    secondary = Teal,
    secondaryVariant = LightTeal,
    background = CrystalBlue,
    surface = White,
    error = Red,
    onPrimary = White,
    onSecondary = White,
    onBackground = DeepBlue,
    onSurface = DeepBlue,
    onError = White
)

@Composable
fun LabAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
} 