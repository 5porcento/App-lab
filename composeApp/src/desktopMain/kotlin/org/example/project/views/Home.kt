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
import org.example.project.pdf.PdfPost
import org.example.project.models.*
import org.example.project.viewModels.formatarData
import org.example.project.theme.WaterLabCard
import org.example.project.theme.WaterLabButton
import org.example.project.theme.WaterLabTextField


@Composable
fun Home() {
    var nomeAluno by remember { mutableStateOf("") }
    var fone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nomeProjeto by remember { mutableStateOf("") }
    var localColeta by remember { mutableStateOf("") }
    var dataColeta by remember { mutableStateOf("") }
    var horaColeta by remember { mutableStateOf("") }
    var responsalvelColeta by remember { mutableStateOf("") }
    var numeroAmostra by remember { mutableStateOf("") }
    var responsavelRecebimento by remember { mutableStateOf("") }
    var responsavelColeta by remember { mutableStateOf("") }
    val opcoesLista = remember { mutableStateListOf<String>() }
    val opcoesTratamento = remember { mutableStateListOf<String>() }
    val usoDaAgua = remember { mutableStateListOf<String>() }
    val origemAmostra = remember { mutableStateListOf<String>() }
    val numColunas = 2 // Número de colunas para os checkboxes

    val scrollState = rememberScrollState()
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text("Erro") },
            text = { Text(errorMessage) },
            confirmButton = {
                Button(onClick = { showErrorDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("Sucesso") },
            text = { Text("PDF gerado com sucesso!") },
            confirmButton = {
                Button(onClick = { showSuccessDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
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
                            text = "Cadastro de Análise de Água",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }

                // Informações do Aluno
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Informações do Aluno",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        WaterLabTextField(
                            value = nomeAluno,
                            onValueChange = { nomeAluno = it },
                            label = "Nome"
                        )

                        WaterLabTextField(
                            value = fone,
                            onValueChange = { fone = it },
                            label = "Telefone"
                        )

                        WaterLabTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = "Email"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Informações do Projeto
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Informações do Projeto",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        WaterLabTextField(
                            value = nomeProjeto,
                            onValueChange = { nomeProjeto = it },
                            label = "Nome do Projeto"
                        )

                        WaterLabTextField(
                            value = localColeta,
                            onValueChange = { localColeta = it },
                            label = "Local da Coleta"
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = dataColeta,
                                onValueChange = { dataColeta = formatarData(it) },
                                label = "Data da Coleta",
                                modifier = Modifier.weight(1f)
                            )

                            WaterLabTextField(
                                value = horaColeta,
                                onValueChange = { horaColeta = it },
                                label = "Hora da Coleta",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Informações da Amostra
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Informações da Amostra",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        WaterLabTextField(
                            value = numeroAmostra,
                            onValueChange = { numeroAmostra = it },
                            label = "Número de Amostras"
                        )

                        WaterLabTextField(
                            value = responsavelColeta,
                            onValueChange = { responsavelColeta = it },
                            label = "Responsável pela Coleta"
                        )

                        WaterLabTextField(
                            value = responsavelRecebimento,
                            onValueChange = { responsavelRecebimento = it },
                            label = "Responsável pelo Recebimento"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Características da Água
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Características da Água",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        // Seção Ponto de Coleta
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            elevation = 2.dp,
                            backgroundColor = MaterialTheme.colors.surface
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "Ponto de Coleta",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )

                                // Organizando os checkboxes em duas colunas
                                val opcoesPorColuna = pontoColeta.chunked((pontoColeta.size + numColunas - 1) / numColunas)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    opcoesPorColuna.forEach { colunaOpcoes ->
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
                                                        checked = opcao in opcoesLista,
                                                        onCheckedChange = { isChecked ->
                                                            if (isChecked) opcoesLista.add(opcao)
                                                            else opcoesLista.remove(opcao)
                                                        },
                                                        colors = CheckboxDefaults.colors(
                                                            checkedColor = MaterialTheme.colors.primary
                                                        )
                                                    )
                                                    Text(
                                                        text = opcao,
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

                        // Seção Tratamento da Água
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            elevation = 2.dp,
                            backgroundColor = MaterialTheme.colors.surface
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "Tratamento da Água",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )

                                // Organizando os checkboxes em duas colunas
                                val opcoesTratamentoPorColuna = tratamentoAgua.chunked((tratamentoAgua.size + numColunas - 1) / numColunas)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    opcoesTratamentoPorColuna.forEach { colunaOpcoes ->
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
                                                        checked = opcao in opcoesTratamento,
                                                        onCheckedChange = { isChecked ->
                                                            if (isChecked) opcoesTratamento.add(opcao)
                                                            else opcoesTratamento.remove(opcao)
                                                        },
                                                        colors = CheckboxDefaults.colors(
                                                            checkedColor = MaterialTheme.colors.primary
                                                        )
                                                    )
                                                    Text(
                                                        text = opcao,
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

                        // Seção Uso Predominante da Água
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            elevation = 2.dp,
                            backgroundColor = MaterialTheme.colors.surface
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "Uso Predominante da Água",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )

                                // Organizando os checkboxes em duas colunas
                                val opcoesUsoPorColuna = usoDaAguaList.chunked((usoDaAguaList.size + numColunas - 1) / numColunas)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    opcoesUsoPorColuna.forEach { colunaOpcoes ->
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
                                                        checked = opcao in usoDaAgua,
                                                        onCheckedChange = { isChecked ->
                                                            if (isChecked) usoDaAgua.add(opcao)
                                                            else usoDaAgua.remove(opcao)
                                                        },
                                                        colors = CheckboxDefaults.colors(
                                                            checkedColor = MaterialTheme.colors.primary
                                                        )
                                                    )
                                                    Text(
                                                        text = opcao,
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
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                WaterLabButton(
                    onClick = {
                        try {
                            PdfPost(
                                nomeAluno, fone, email,
                                nomeProjeto, localColeta, horaColeta, dataColeta,
                                numeroAmostra, responsavelColeta, opcoesLista.toList(),
                                opcoesTratamento.toList(), usoDaAgua.toList(),
                                origemAmostra.toList()
                            )
                            showSuccessDialog = true
                        } catch (e: Exception) {
                            errorMessage = "Erro ao gerar PDF: ${e.message}"
                            showErrorDialog = true
                        }
                    },
                    text = "Gerar Relatório PDF",
                    enabled = nomeAluno.isNotBlank() && nomeProjeto.isNotBlank() // Adicione mais validações conforme necessário
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
