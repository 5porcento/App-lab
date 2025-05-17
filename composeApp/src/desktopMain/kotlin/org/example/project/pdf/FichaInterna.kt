package org.example.project.pdf


import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.font.PDType1Font
import java.awt.Color
import java.io.File

fun pdfInterno(
     data: String,
 clienteIdentficacao: String,
 identificacaoInterna: String,
 analista: String,
 Equipamentood: String,
 Equipamentoph: String,
 Equipamentostd: String,
 Equipamentocond: String,
 Equipamentoturb: String,
 Equipamentocor: String,
 Resultadood: String,
 Resultadoph: String,
 Resultadostd: String,
 Resultadocond: String,
 Resultadoturb: String,
 Resultadocor: String,
 Unidadeod: String,
 Unidadeph: String,
 Unidadestd: String,
 Unidadecond: String,
 Unidadeturb: String,
 Unidadecor: String,
 observacao: String
){
    val document = PDDocument()
    var page = PDPage(PDRectangle.A4)
    document.addPage(page)

    var contentStream = PDPageContentStream(document, page)
    var yPosition = 800f
    var pageCount = 1

    try {
        val pdfDir = File("pdfs")
        if (!pdfDir.exists()) pdfDir.mkdir()

        /**
         * Desenha o cabeçalho centralizado.
         */
        fun desenharCabecalho() {
            // TODO adiconar a logo
            val centerX = page.mediaBox.width / 2
            val titulos = listOf(
                "PARQUE DE CIÊNCIA E TECNOLOGIA GUAMÁ",
                "LABORATÓRIO DE QUALIDADE DE ÁGUA DA AMAZÔNIA",
                "COORDENAÇÃO DE PROSPECÇÃO E ENSAIOS TECNOLÓGICOS"
            )

            var posY = 790f
            for (titulo in titulos) {
                val textWidth = (PDType1Font.HELVETICA_BOLD.getStringWidth(titulo) / 1000f) * 12f
                contentStream.beginText()
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12f)
                contentStream.newLineAtOffset(centerX - textWidth / 2, posY)
                contentStream.showText(titulo)
                contentStream.endText()
                posY -= 15f
            }

            yPosition = 735f
        }


        /**
         * Desenha o rodapé institucional, centralizado.
         */
        fun desenharRodapeCompleto() {
            contentStream.moveTo(50f, 70f)
            contentStream.lineTo(550f, 70f)
            contentStream.stroke()

            val textos = listOf(
                "Av. Perimetral da Ciência, S/N, KM 1, Parque de Ciência e Tecnologia Guamá",
                "Prédio Espaço Inovação – 3º Andar, salas 11 e 13. Guamá, Belém/PA – CEP 66075-750",
                "www.pctguama.org.br",
                "Telefone: (091) 3321-8900"
            )

            val centerX = page.mediaBox.width / 2
            var posY = 60f

            for (texto in textos) {
                val textWidth = (PDType1Font.HELVETICA.getStringWidth(texto) / 1000f) * 8f
                contentStream.beginText()
                contentStream.setFont(PDType1Font.HELVETICA, 8f)
                contentStream.newLineAtOffset(centerX - textWidth / 2, posY)
                contentStream.showText(texto)
                contentStream.endText()
                posY -= 10f
            }
        }

        /**
         * Garante que há espaço na página.
         */
        fun verificarEspaco(extraAltura: Float = 40f) {
            if (yPosition <= 100f + extraAltura) {
                contentStream.close()
                desenharRodapeCompleto()
                pageCount++
                page = PDPage(PDRectangle.A4)
                document.addPage(page)
                contentStream = PDPageContentStream(document, page)
                desenharCabecalho()
                yPosition = 735f
            }
        }

        /**
         * Desenha o título de cada seção.
         */
        fun desenharTituloDeSecao(titulo: String) {
            verificarEspaco(30f)
            contentStream.setNonStrokingColor(Color(204, 229, 255))
            contentStream.addRect(50f, yPosition - 20f, 500f, 20f)
            contentStream.fill()
            contentStream.setNonStrokingColor(Color.BLACK)

            contentStream.beginText()
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12f)
            contentStream.newLineAtOffset(55f, yPosition - 15f)
            contentStream.showText(titulo)
            contentStream.endText()

            yPosition -= 30f
        }

        /**
         * Desenha uma tabela simples com vários campos.
         */
        fun desenharTabelaSimples(campos: List<Pair<String, String>>) {
            val startX = 50f
            val labelWidth = 150f
            val valueWidth = 350f
            val fontSize = 10f
            val margin = 5f

            for ((label, valor) in campos) {
                val words = valor.split(" ")
                var currentLine = ""
                val linhas = mutableListOf<String>()

                for (word in words) {
                    val testLine = if (currentLine.isEmpty()) word else "$currentLine $word"
                    val testWidth = (PDType1Font.HELVETICA.getStringWidth(testLine) / 1000f) * fontSize
                    if (testWidth > valueWidth - 2 * margin) {
                        linhas.add(currentLine)
                        currentLine = word
                    } else {
                        currentLine = testLine
                    }
                }
                if (currentLine.isNotEmpty()) {
                    linhas.add(currentLine)
                }

                val alturaLinha = 20f + (linhas.size - 1) * 12f
                verificarEspaco(alturaLinha)

                // Caixas
                contentStream.addRect(startX, yPosition - alturaLinha, labelWidth, alturaLinha)
                contentStream.addRect(startX + labelWidth, yPosition - alturaLinha, valueWidth, alturaLinha)
                contentStream.stroke()

                // Label
                contentStream.beginText()
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize)
                contentStream.newLineAtOffset(startX + margin, yPosition - 15f)
                contentStream.showText(label)
                contentStream.endText()

                // Valor
                var linhaY = yPosition - 15f
                for (linha in linhas) {
                    contentStream.beginText()
                    contentStream.setFont(PDType1Font.HELVETICA, fontSize)
                    contentStream.newLineAtOffset(startX + labelWidth + margin, linhaY)
                    contentStream.showText(linha)
                    contentStream.endText()
                    linhaY -= 12f
                }

                yPosition -= alturaLinha
            }

            yPosition -= 10f
        }


        // Começa o preenchimento
        desenharCabecalho()

        desenharTituloDeSecao("Ficha de Analise Fisico-Quimica")
        desenharTabelaSimples(listOf(
            "Data:" to data,
            "Cliente e identificação amostra Cliente:" to clienteIdentficacao,
            "Analista" to analista,
            "Identificação interna da amostra:" to identificacaoInterna,
        ))

        desenharTituloDeSecao("Equipamento")
        desenharTabelaSimples(listOf(
            "OD:" to Equipamentood,
            "pH:" to Equipamentoph,
            "STD:" to Equipamentostd,
            "cond:" to Equipamentocond,
            "turb:" to Equipamentoturb,
            "Cor:" to Equipamentocor,
        ))

        desenharTituloDeSecao("Resultado")
        desenharTabelaSimples(listOf(
            "OD:" to Resultadood,
            "pH:" to Resultadoph,
            "STD:" to Resultadostd,
            "cond:" to Resultadocond,
            "turb:" to Resultadoturb,
            "Cor:" to Resultadocor,
        ))

        desenharTituloDeSecao("Unidade")
        desenharTabelaSimples(listOf(
            "OD:" to Unidadeod,
            "pH:" to Unidadeph,
            "STD:" to Unidadestd,
            "cond:" to Unidadecond,
            "turb:" to Unidadeturb,
            "Cor:" to Unidadecor,
        ))

        desenharRodapeCompleto()

        contentStream.close()

        val timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
        val fileName = "Ficha Interna${analista.replace(" ", "")}_$timestamp.pdf"
        val file = File(pdfDir, fileName)
        document.save(file)

    } catch (e: Exception) {
        throw Exception("Erro ao gerar PDF: ${e.message}")
    } finally {
        document.close()
    }
}