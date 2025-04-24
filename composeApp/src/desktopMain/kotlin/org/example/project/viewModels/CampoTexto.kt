package org.example.project.viewModels

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CampoTexto(
    valor: String,
    aoMudar: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = valor,
        onValueChange = aoMudar,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    )
}
