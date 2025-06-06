package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.views.App


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "LabAPp",
    ) {
        App()
    }
}