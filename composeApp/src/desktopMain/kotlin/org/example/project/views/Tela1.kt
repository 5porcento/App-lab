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
import org.example.project.models.formaPagamentoList
import org.example.project.pdf.pdfOrcamento
import org.example.project.viewModels.CampoTexto
import org.example.project.viewModels.SecaoFormulario
import org.example.project.theme.WaterLabCard
import org.example.project.theme.WaterLabButton
import org.example.project.theme.WaterLabTextField
import kotlinx.coroutines.launch
import org.example.project.pdf.pdfContrato

@Composable
fun Tela1() {
    var nomeCliente by remember { mutableStateOf("") }
    var cnpj by remember { mutableStateOf("") }
    var nomeComercial by remember { mutableStateOf("") }
    var siteEmpresa by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var emailEmpresa by remember { mutableStateOf("") }
    var nomeRepresentante by remember { mutableStateOf("") }
    var foneRepresentante by remember { mutableStateOf("") }
    var emailRepresentante by remember { mutableStateOf("") }
    var nomeLaboratorio by remember { mutableStateOf("") }
    var siglaLaboratorio by remember { mutableStateOf("") }
    var nomeCoordenador by remember { mutableStateOf("") }
    var cpfCoordenador by remember { mutableStateOf("") }
    var tecnico1 by remember { mutableStateOf("") }
    var cpfTecnico1 by remember { mutableStateOf("") }
    var tecnico2 by remember { mutableStateOf("") }
    var cpfTecnico2 by remember { mutableStateOf("") }
    var detalhesDoServico by remember { mutableStateOf("") }
    var dataInicio by remember { mutableStateOf("") }
    var dataTermino by remember { mutableStateOf("") }
    var totalDiasTrabalhado by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var desconto by remember { mutableStateOf("") }
    var valorFinal by remember { mutableStateOf("") }
    val formaDePagamento = remember { mutableStateListOf<String>() }
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
                            text = "Orçamento de Análise de Água",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }

                // Dados do Contratante
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Dados do Contratante",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        WaterLabTextField(
                            value = nomeCliente,
                            onValueChange = { nomeCliente = it },
                            label = "Razão Social / Nome Completo"
                        )

                        WaterLabTextField(
                            value = cnpj,
                            onValueChange = { cnpj = it },
                            label = "CNPJ / CPF"
                        )

                        WaterLabTextField(
                            value = nomeComercial,
                            onValueChange = { nomeComercial = it },
                            label = "Nome Comercial / Projeto"
                        )

                        WaterLabTextField(
                            value = siteEmpresa,
                            onValueChange = { siteEmpresa = it },
                            label = "Site da Empresa"
                        )

                        WaterLabTextField(
                            value = endereco,
                            onValueChange = { endereco = it },
                            label = "Endereço Completo"
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = telefone,
                                onValueChange = { telefone = it },
                                label = "DDD / Telefones",
                                modifier = Modifier.weight(1f)
                            )

                            WaterLabTextField(
                                value = emailEmpresa,
                                onValueChange = { emailEmpresa = it },
                                label = "E-mail da Empresa",
                                modifier = Modifier.weight(1f)
                            )
                        }

                        WaterLabTextField(
                            value = nomeRepresentante,
                            onValueChange = { nomeRepresentante = it },
                            label = "Nome do Representante"
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = foneRepresentante,
                                onValueChange = { foneRepresentante = it },
                                label = "Fone do Representante",
                                modifier = Modifier.weight(1f)
                            )

                            WaterLabTextField(
                                value = emailRepresentante,
                                onValueChange = { emailRepresentante = it },
                                label = "E-mail do Representante",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Dados do Laboratório
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Dados do Laboratório",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        WaterLabTextField(
                            value = nomeLaboratorio,
                            onValueChange = { nomeLaboratorio = it },
                            label = "Nome do Laboratório"
                        )

                        WaterLabTextField(
                            value = siglaLaboratorio,
                            onValueChange = { siglaLaboratorio = it },
                            label = "Sigla ou Logo"
                        )

                        WaterLabTextField(
                            value = nomeCoordenador,
                            onValueChange = { nomeCoordenador = it },
                            label = "Nome do Coordenador"
                        )

                        WaterLabTextField(
                            value = cpfCoordenador,
                            onValueChange = { cpfCoordenador = it },
                            label = "CPF do Coordenador"
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = tecnico1,
                                onValueChange = { tecnico1 = it },
                                label = "Nome do Técnico 1",
                                modifier = Modifier.weight(1f)
                            )

                            WaterLabTextField(
                                value = cpfTecnico1,
                                onValueChange = { cpfTecnico1 = it },
                                label = "CPF do Técnico 1",
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = tecnico2,
                                onValueChange = { tecnico2 = it },
                                label = "Nome do Técnico 2",
                                modifier = Modifier.weight(1f)
                            )

                            WaterLabTextField(
                                value = cpfTecnico2,
                                onValueChange = { cpfTecnico2 = it },
                                label = "CPF do Técnico 2",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Dados do Serviço
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Dados do Serviço",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        WaterLabTextField(
                            value = detalhesDoServico,
                            onValueChange = { detalhesDoServico = it },
                            label = "Descrição do Serviço"
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = dataInicio,
                                onValueChange = { dataInicio = it },
                                label = "Data de Início",
                                modifier = Modifier.weight(1f)
                            )

                            WaterLabTextField(
                                value = dataTermino,
                                onValueChange = { dataTermino = it },
                                label = "Data de Término",
                                modifier = Modifier.weight(1f)
                            )
                        }

                        WaterLabTextField(
                            value = totalDiasTrabalhado,
                            onValueChange = { totalDiasTrabalhado = it },
                            label = "Total de Dias Trabalhados"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Dados do Pagamento
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Dados do Pagamento",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            WaterLabTextField(
                                value = valor,
                                onValueChange = { valor = it },
                                label = "Valor (R$)",
                                modifier = Modifier.weight(1f)
                            )

                            WaterLabTextField(
                                value = desconto,
                                onValueChange = { desconto = it },
                                label = "Desconto (%)",
                                modifier = Modifier.weight(1f)
                            )
                        }

                        WaterLabTextField(
                            value = valorFinal,
                            onValueChange = { valorFinal = it },
                            label = "Valor Final (R$)"
                        )

                        Text(
                            text = "Forma de Pagamento",
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        // Organizando os checkboxes em duas colunas
                        val numColunas = 2
                        val opcoesPorColuna = formaPagamentoList.chunked((formaPagamentoList.size + numColunas - 1) / numColunas)
                        
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
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp),
                                            elevation = 2.dp,
                                            backgroundColor = MaterialTheme.colors.surface
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.padding(8.dp)
                                            ) {
                Checkbox(
                    checked = opcao in formaDePagamento,
                    onCheckedChange = { isChecked ->
                                                        if (isChecked) formaDePagamento.add(opcao)
                                                        else formaDePagamento.remove(opcao)
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

                Spacer(modifier = Modifier.height(24.dp))

                WaterLabButton(
                    onClick = {
                        try {
                            pdfOrcamento(
                                nomeCliente, cnpj, nomeComercial, siteEmpresa,
                                endereco, telefone, emailEmpresa, nomeRepresentante,
                                foneRepresentante, emailRepresentante, nomeLaboratorio,
                                siglaLaboratorio, nomeCoordenador, cpfCoordenador,
                                tecnico1, cpfTecnico1, tecnico2, cpfTecnico2,
                                detalhesDoServico, dataInicio, dataTermino,
                                totalDiasTrabalhado, valor, desconto, valorFinal,
                                formaDePagamento.toList()
                            )
                            showSuccessDialog = true
                        } catch (e: Exception) {
                            errorMessage = "Erro ao gerar PDF: ${e.message}"
                            showErrorDialog = true
                        }
                    },
                    text = "Gerar Orçamento PDF",
                    enabled = nomeCliente.isNotBlank() && cnpj.isNotBlank() // Adicione mais validações conforme necessário
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

