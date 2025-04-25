package org.example.project.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.models.Item
import org.example.project.models.listaItens
import org.example.project.theme.WaterLabCard
import org.example.project.theme.WaterLabButton
import org.example.project.theme.WaterLabTextField


@Composable
fun Tela2() {
    var nomeCliente by remember { mutableStateOf("") }
    var cpfCliente by remember { mutableStateOf("") }
    var enderecoCliente by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("PA") }
    var cep by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tipoDeAmostra by remember { mutableStateOf("") }

    val opcoesSelecionadas = remember { mutableStateListOf<Item>() }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                            text = "Ficha de orçamento",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }

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
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = nomeCliente,
                                onValueChange = { nomeCliente = it },
                                label = "Cliente",
                                modifier = Modifier.weight(1f)
                            )

                            WaterLabTextField(
                                value = cpfCliente,
                                onValueChange = { cpfCliente = it },
                                label = "CPF",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = enderecoCliente,
                                onValueChange = {enderecoCliente = it},
                                label = "Endereço",
                                modifier = Modifier.weight(1f)
                            )}
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                            WaterLabTextField(
                                value = cidade,
                                onValueChange = {cidade = it},
                                label = "Cidade",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = estado,
                                onValueChange = {estado = it},
                                label = "Estado",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = cep,
                                onValueChange = {cep = it},
                                label = "CEP",
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = telefone,
                                onValueChange = {telefone = it},
                                label = "Telefone",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = email,
                                onValueChange = {email = it},
                                label = "Email",
                                modifier = Modifier.weight(1f)
                            )
                            WaterLabTextField(
                                value = tipoDeAmostra,
                                onValueChange = {tipoDeAmostra = it},
                                label = "Tipo de amostra",
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Text(
                            text = "Selecione os parâmetros que deseja incluir no relatório:",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Grid de checkboxes em duas colunas
                        val numColunas = 2
                        val itensPorColuna = listaItens.chunked((listaItens.size + numColunas - 1) / numColunas)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            itensPorColuna.forEach { colunaOpcoes ->
                                Column(
                                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    colunaOpcoes.forEach { opcao ->
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        ) {
                                            Checkbox(
                                                checked = opcao in opcoesSelecionadas,
                                                onCheckedChange = { isChecked ->
                                                    if (isChecked) opcoesSelecionadas.add(opcao)
                                                    else opcoesSelecionadas.remove(opcao)
                                                },
                                                colors = CheckboxDefaults.colors(
                                                    checkedColor = MaterialTheme.colors.primary
                                                )
                                            )
                                            Text(
                                                text = "${opcao.nome} - ${opcao.metodo} - R$ %.2f".format(opcao.preco),
                                                style = MaterialTheme.typography.body2,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                //TODO adiconar algo pra ver o valor total

                WaterLabButton(
                    onClick = {
                        println("Teste")
                    },
                    text = "Gerar Relatório PDF",
                    enabled = opcoesSelecionadas.isNotEmpty()
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

