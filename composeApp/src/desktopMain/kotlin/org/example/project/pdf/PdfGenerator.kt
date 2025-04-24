package org.example.project.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import java.io.File

fun gerarPDF(nomeArquivo:String,nome:String,idade:String,checked:String){

    val documento = PDDocument()
    val pagina = PDPage()
    documento.addPage(pagina)

    PDPageContentStream(documento,pagina).use { conteudo ->
        conteudo.setFont(PDType1Font.HELVETICA_BOLD,14f)
        conteudo.beginText()
        conteudo.newLineAtOffset(100f,700f)
        conteudo.showText("Informaçoes do usuario")
        conteudo.newLineAtOffset(0f,-20f)
        conteudo.showText("Nome: $nome")
        conteudo.newLineAtOffset(0f,-20f)
        conteudo.showText("Idade $idade")
        conteudo.newLineAtOffset(0f,-20f)
        conteudo.showText("Check Box Selecionada $checked")
        conteudo.endText()
    }
    documento.save(File(nomeArquivo))
    documento.close()
}