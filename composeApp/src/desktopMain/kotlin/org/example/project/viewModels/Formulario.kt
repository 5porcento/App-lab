package org.example.project.viewModels

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier


@Composable
fun SecaoFormulario(titulo: String, conteudo: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(titulo)
        Spacer(modifier = Modifier.height(16.dp))
        conteudo()
        Spacer(modifier = Modifier.height(16.dp))
    }
}
