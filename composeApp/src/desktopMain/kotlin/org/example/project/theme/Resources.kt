package org.example.project.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.Density

object Resources {
    @Composable
    fun loadLogo(): Painter {
        return useResource("logo.svg") { stream ->
            loadSvgPainter(stream, Density(2f))
        }
    }
} 