package org.example.project.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.theme.WaterLabCard
import org.example.project.theme.WaterLabTextField
import org.example.project.viewModels.formatarData

@Composable
fun Tela3(){

    var data by remember { mutableStateOf("") }
    var clienteIdentficacao by remember { mutableStateOf("") }
    var identificacaoInterna by remember { mutableStateOf("") }
    var analista by remember { mutableStateOf("") }
    var od by remember { mutableStateOf("") }
    var ph by remember { mutableStateOf("") }
    var std by remember { mutableStateOf("") }
    var cond by remember { mutableStateOf("") }
    var turb by remember { mutableStateOf("") }
    var cor by remember { mutableStateOf("") }
    var observacao by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Container para centralizar e limitar a largura dos cards
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.widthIn(max = 800.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Card do título
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    elevation = 4.dp,
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ficha Interna",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
                //seção indentifocação da amostra
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Dados",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        WaterLabTextField(
                            value = clienteIdentficacao,
                            onValueChange = {clienteIdentficacao = it},
                            label = "Cliente e Identificação amostra Cliente",
                        )
                        WaterLabTextField(
                            value = analista,
                            onValueChange = {analista = it},
                            label = "Analista",
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = data,
                                onValueChange = { data = formatarData(it) },
                                label = "Data da Coleta",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = identificacaoInterna,
                                onValueChange = {identificacaoInterna = it},
                                label = "Identificação Interna da amostra",
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        // tabela
                        Text(
                            text = "Dados",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = od,
                                onValueChange = { od = formatarData(it) },
                                label = "OD",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = ph,
                                onValueChange = {ph = it},
                                label = "pH",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = std,
                                onValueChange = {std = it},
                                label = "STD",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = cond,
                                onValueChange = {cond = it},
                                label = "cond",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = turb,
                                onValueChange = {turb = it},
                                label = "turb",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = cor,
                                onValueChange = {cor = it},
                                label = "cor",
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }

                    }
                }
            }
        }
    }





}