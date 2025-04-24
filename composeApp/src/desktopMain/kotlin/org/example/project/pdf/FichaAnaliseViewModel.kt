package org.example.project.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
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
    origemAmostra: List<String>
) {
    val document = PDDocument()
    val page = PDPage()
    document.addPage(page)

    try {
        val contentStream = PDPageContentStream(document, page)
        var yPosition = 700f

        fun escreverTitulo(titulo: String) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14f)
            contentStream.beginText()
            contentStream.newLineAtOffset(50f, yPosition)
            contentStream.showText(titulo)
            contentStream.endText()
            yPosition -= 20f
        }

        fun escreverLista(lista: List<String>) {
            contentStream.setFont(PDType1Font.HELVETICA, 12f)
            lista.forEach { item ->
                contentStream.beginText()
                contentStream.newLineAtOffset(50f, yPosition)
                contentStream.showText("- $item")
                contentStream.endText()
                yPosition -= 20f
            }
            yPosition -= 10f
        }

        // Informações do usuário
        escreverTitulo("Informações do usuário:")
        val dadosUsuario = listOf(
            "Nome do Aluno: $nomeAluno",
            "Fone: $fone",
            "Email: $email",
            "Nome Do Projeto: $nomeProjeto",
            "Local Da Coleta: $localColeta",
            "Data da Coleta: $dataColeta",
            "Hora : $horaColeta",
            "Responsável Pela Coleta: $responsavelColeta",
            "Número de Amostras: $numeroAmostra"
        )
        escreverLista(dadosUsuario)

        // Parâmetros Selecionados
        escreverTitulo("Parâmetros Selecionados:")
        escreverLista(opcoes)

        // Tratamento da Água
        escreverTitulo("Tratamento da Água:")
        escreverLista(opcoesTratamento)

        // Uso da Água
        escreverTitulo("Uso da Água:")
        escreverLista(usoDaAgua)

        // Origem da Amostra
        escreverTitulo("Origem da Amostra:")
        escreverLista(origemAmostra)

        contentStream.close()
        val file = File("Ficha_do_Aluno.pdf")
        document.save(file)

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        document.close()
    }
}
