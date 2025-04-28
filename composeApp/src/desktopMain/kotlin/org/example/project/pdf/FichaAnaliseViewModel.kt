package org.example.project.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.font.PDType1Font
import java.io.File

fun PdfPost(
    nomeAluno: String,
    fone: String,
    email: String,
    nomeProjeto: String,
    localColeta: String,
    horaColeta: String,
    dataColeta: String,
    numeroAmostra: String,
    responsavelColeta: String,
    opcoes: List<String>,
    opcoesTratamento: List<String>,
    usoDaAgua: List<String>,
    origemAmostra: List<String>,
    parametrosSelecionados: List<String>

) {
    val document = PDDocument()
    val page = PDPage(PDRectangle.A4)
    document.addPage(page)

    try {
        val contentStream = PDPageContentStream(document, page)
        val margin = 50f
        val pageWidth = page.mediaBox.width
        var yPosition = page.mediaBox.height - margin

        // Função para escrever campo simples
        fun escreverCampo(titulo: String, valor: String) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10f)
            contentStream.beginText()
            contentStream.newLineAtOffset(margin, yPosition)
            contentStream.showText(titulo)
            contentStream.endText()

            contentStream.setFont(PDType1Font.HELVETICA, 10f)
            contentStream.beginText()
            contentStream.newLineAtOffset(margin + 150f, yPosition)
            contentStream.showText(valor)
            contentStream.endText()

            yPosition -= 20f
        }

        // Cabeçalho
        fun escreverCabecalho(texto: String, deslocamento: Float = 0f, fonte: PDType1Font = PDType1Font.HELVETICA_BOLD, tamanho: Float = 12f) {
            contentStream.setFont(fonte, tamanho)
            contentStream.beginText()
            contentStream.newLineAtOffset(pageWidth / 2 - deslocamento, yPosition)
            contentStream.showText(texto)
            contentStream.endText()
            yPosition -= 20f
        }

        escreverCabecalho("PARQUE DE CIÊNCIA E TECNOLOGIA GUAMÁ", 100f)
        escreverCabecalho("LABORATÓRIO DE QUALIDADE DE ÁGUA DA AMAZÔNIA", 120f)
        yPosition -= 10f
        escreverCabecalho("FICHA CONTROLE DE ANÁLISE ALUNOS Nº ____ Data: __//", 130f, tamanho = 10f)
        yPosition -= 30f

        // Informações básicas
        escreverCampo("Nome do Aluno:", nomeAluno)
        escreverCampo("Fone:", fone)
        escreverCampo("Email:", email)
        escreverCampo("Nome do Projeto:", nomeProjeto)
        escreverCampo("Local da Coleta:", localColeta)
        escreverCampo("Data da Coleta:", dataColeta)
        escreverCampo("Hora:", horaColeta)
        escreverCampo("Responsável pela Coleta:", responsavelColeta)
        escreverCampo("Número de Amostras:", numeroAmostra)

        yPosition -= 10f

        // Função para escrever opções (X) ou ( )
        fun escreverOpcoes(titulo: String, todasOpcoes: List<String>, selecionadas: List<String>) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10f)
            contentStream.beginText()
            contentStream.newLineAtOffset(margin, yPosition)
            contentStream.showText(titulo)
            contentStream.endText()
            yPosition -= 15f

            todasOpcoes.forEach { opcao ->
                val isSelecionada = selecionadas.contains(opcao)
                contentStream.setFont(if (isSelecionada) PDType1Font.HELVETICA_BOLD else PDType1Font.HELVETICA, 10f)
                contentStream.beginText()
                contentStream.newLineAtOffset(margin + 10f, yPosition)
                val marca = if (isSelecionada) "(X)" else "( )"
                contentStream.showText("$marca $opcao")
                contentStream.endText()
                yPosition -= 15f
            }
            yPosition -= 10f
        }

        // Definindo todas as opções fixas
        val todasOpcoesOrigem = listOf(
            "Sistema de Abastecimento (SAA)",
            "Água Superficial - Rio",
            "Água Superficial - Lago",
            "Água Superficial - Igarapé",
            "Água Superficial - Outro",
            "Água Subterrânea - Coletivo (SAC)",
            "Água Subterrânea - Individual (SAI)",
            "Fonte"
        )
        val todasOpcoesTratamento = listOf(
            "Tratada", "Não Tratada"
        )
        val todasOpcoesUso = listOf(
            "Consumo Humano", "Balneabilidade", "Outros (Especifique)"
        )
        val todasOpcoesParametros = listOf(
            "Turbidez", "Cor", "pH", "Condutividade", "OD", "STD", "Nitrito", "Nitrato", "Fosfato", "Coliformes Totais"
        )

        // Escrever seções com as opções
        escreverOpcoes("Origem da Amostra:", todasOpcoesOrigem, origemAmostra)
        escreverOpcoes("Tratamento da Água:", todasOpcoesTratamento, opcoesTratamento)
        escreverOpcoes("Uso da Água:", todasOpcoesUso, usoDaAgua)
        escreverOpcoes("Parâmetros Selecionados:", todasOpcoesParametros, parametrosSelecionados)

        yPosition -= 30f

        // Espaço para assinatura
        contentStream.addRect(margin, yPosition - 80f, pageWidth - 2 * margin, 80f)
        contentStream.stroke()

        contentStream.beginText()
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10f)
        contentStream.newLineAtOffset(margin + 5f, yPosition - 40f)
        contentStream.showText("Assinatura do Responsável:")
        contentStream.endText()

        contentStream.close()

        // Salvar arquivo
        val file = File("Ficha_do_Aluno.pdf")
        document.save(file)

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        document.close()
        }
}
