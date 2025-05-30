package org.example.project.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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
import org.example.project.data.Tela4State
import org.example.project.pdf.generatePdfQuantitativo
import org.example.project.theme.WaterLabCard
import org.example.project.theme.WaterLabTextField

@Composable
fun Tela4() {


    var state by remember { mutableStateOf(Tela4State()) }
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                            text = "Analise Quantitativa",
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
                            text = "Coliformes",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        WaterLabTextField(
                            value = state.coliformesTotais,
                            onValueChange = { state = state.copy(coliformesTotais = it) },
                            label = "Coliformes Totais"
                        )
                        WaterLabTextField(
                            value = state.coliformesPcGr,
                            onValueChange = { state = state.copy(coliformesPcGr = it) },
                            label = "Pç. Gr"
                        )
                        WaterLabTextField(
                            value = state.coliformesPcPeq,
                            onValueChange = { state = state.copy(coliformesPcPeq = it) },
                            label = "Pç. Peq"
                        )
                        WaterLabTextField(
                            value = state.coliformesResultado,
                            onValueChange = { state = state.copy(coliformesResultado = it) },
                            label = "Resultado"
                        )
                        WaterLabTextField(
                            value = state.coliformesCepaATCC,
                            onValueChange = { state = state.copy(coliformesCepaATCC = it) },
                            label = "Cepa ATCC"
                        )
                    }
                }
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "E.coli",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        WaterLabTextField(
                            value = state.Ecoli,
                            onValueChange = { state = state.copy(Ecoli = it) },
                            label = "E.coli"
                        )
                        WaterLabTextField(
                            value = state.ecoliPcGr,
                            onValueChange = { state = state.copy(ecoliPcGr = it) },
                            label = "Pç. Gr"
                        )
                        WaterLabTextField(
                            value = state.ecoliPcPeq,
                            onValueChange = { state = state.copy(ecoliPcPeq = it) },
                            label = "Pç. Peq"
                        )
                        WaterLabTextField(
                            value = state.ecoliResultado,
                            onValueChange = { state = state.copy(ecoliResultado = it) },
                            label = "Resultado"
                        )
                        WaterLabTextField(
                            value = state.ecoliResultado,
                            onValueChange = { state = state.copy(ecoliCepaATCC = it) },
                            label = "Cepa ATCC"
                        )


                    }

                } // fds

                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Enterococos/Enterolert",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        WaterLabTextField(
                            value = state.enterococosDiluicao,
                            onValueChange = { state = state.copy(enterococosDiluicao = it) },
                            label = "Diluição"
                        )
                        WaterLabTextField(
                            value = state.enterococosLote,
                            onValueChange = { state = state.copy(enterococosLote = it) },
                            label = "Lote"
                        )
                        WaterLabTextField(
                            value = state.enterococosVal,
                            onValueChange = { state = state.copy(enterococosVal = it) },
                            label = "Val"
                        )
                        WaterLabTextField(
                            value = state.enterococosPcGr,
                            onValueChange = { state = state.copy(enterococosPcGr = it) },
                            label = "PcGr"
                        )
                        WaterLabTextField(
                            value = state.enterococosPcPeq,
                            onValueChange = { state = state.copy(enterococosPcPeq = it) },
                            label = "PcPeq"
                        )
                        WaterLabTextField(
                            value = state.enterococosResultado,
                            onValueChange = { state = state.copy(enterococosResultado = it) },
                            label = "PcPeq"
                        )
                        WaterLabTextField(
                            value = state.enterococosCepaATCC,
                            onValueChange = { state = state.copy(enterococosCepaATCC = it) },
                            label = "Cepa ATCC"
                        )
                        WaterLabTextField(
                            value = state.enterococosLeituraFeitaPor,
                            onValueChange = { state = state.copy(enterococosLeituraFeitaPor = it) },
                            label = "Leitura feita por"
                        )
                        WaterLabTextField(
                            value = state.enterococosData,
                            onValueChange = { state = state.copy(enterococosData = it) },
                            label = "Data"
                        )
                        WaterLabTextField(
                            value = state.enterococosHora,
                            onValueChange = { state = state.copy(enterococosHora = it) },
                            label = "Hora"
                        )
                    }

                }
                    // fds 2
                WaterLabCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "C.Termotolerantes",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        WaterLabTextField(
                            value = state.termotolerantesDiluicao,
                            onValueChange = { state = state.copy(termotolerantesDiluicao = it) },
                            label = "Diluição"
                        )
                        WaterLabTextField(
                            value = state.termotolerantesLote,
                            onValueChange = { state = state.copy(termotolerantesLote = it) },
                            label = "Lote"
                        )
                        WaterLabTextField(
                            value = state.termotolerantesVal,
                            onValueChange = { state = state.copy(termotolerantesVal = it) },
                            label = "Val"
                        )
                        WaterLabTextField(
                            value = state.termotolerantesPcGr,
                            onValueChange = { state = state.copy(termotolerantesPcGr = it) },
                            label = "PcGr"
                        )
                        WaterLabTextField(
                            value = state.termotolerantesPcPeq,
                            onValueChange = { state = state.copy(termotolerantesPcPeq = it) },
                            label = "PcPeq"
                        )
                        WaterLabTextField(
                            value = state.termotolerantesResultado,
                            onValueChange = { state = state.copy(termotolerantesResultado = it) },
                            label = "PcPeq"
                        )
                        WaterLabTextField(
                            value = state.termotolerantesCepaATCC,
                            onValueChange = { state = state.copy(termotolerantesCepaATCC = it) },
                            label = "Cepa ATCC"
                        )
                        WaterLabTextField(
                            value = state.termotolerantesLeituraFeitaPor,
                            onValueChange = { state = state.copy(termotolerantesLeituraFeitaPor = it) },
                            label = "Leitura feita por"
                        )
                        WaterLabTextField(
                            value = state.termotolerantesData,
                            onValueChange = { state = state.copy(termotolerantesData = it) },
                            label = "Data"
                        )
                        WaterLabTextField(
                            value = state.termotolerantesHora,
                            onValueChange = { state = state.copy(termotolerantesHora = it) },
                            label = "Hora"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        generatePdfQuantitativo(state)
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                ){
                    Text("Gerar")
                }
            }
        }
    }
}