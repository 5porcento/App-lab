package org.example.project.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.models.Views
import org.example.project.theme.LabAppTheme
import org.example.project.theme.LabMenuBar

@Composable
fun App() {
    LabAppTheme {
        var currentScreen by remember { mutableStateOf<Views>(Views.Home) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Menu Bar
                LabMenuBar(
                    currentScreen = currentScreen,
                    onScreenChange = { currentScreen = it }
                )

                // Conteúdo principal
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    when (currentScreen) {
                        Views.Home -> Home()
                        Views.Tela1 -> Tela1()
                        Views.Tela2 -> Tela2()
                        Views.Tela3 ->Tela3()
                        Views.Tela4 ->Tela4()
                    }
                }
            }
        }
    }
}