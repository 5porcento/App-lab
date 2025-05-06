package org.example.project.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.PDPageContentStream
import java.awt.Color
import java.io.File

/**
 * Gera um PDF de orçamento formal no modelo de ficha institucional.
 */
fun pdfOrcamento(
    nomeCliente: String,
    cnpj: String,
    nomeComercial: String,
    siteEmpresa: String,
    endereco: String,
    telefone: String,
    emailEmpresa: String,
    nomeRepresentante: String,
    foneRepresentante: String,
    emailRepresentante: String,
    nomeLaboratorio: String,
    siglaLaboratorio: String,
    nomeCoordenador: String,
    cpfCoordenador: String,
    tecnico1: String,
    cpfTecnico1: String,
    tecnico2: String,
    cpfTecnico2: String,
    detalhesDoServico: String,
    dataInicio: String,
    dataTermino: String,
    totalDiasTrabalhado: String,
    valor: String,
    desconto: String,
    valorFinal: String,
    formaDePagamento: List<String>
) {
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
        /**
         * Desenha campos para assinatura.
         */
        fun desenharAssinaturas() {
            val startY = 150f
            val larguraLinha = 200f

            contentStream.moveTo(80f, startY)
            contentStream.lineTo(80f + larguraLinha, startY)
            contentStream.stroke()

            contentStream.beginText()
            contentStream.setFont(PDType1Font.HELVETICA, 10f)
            contentStream.newLineAtOffset(90f, startY - 15f)
            contentStream.showText("Assinatura do Coordenador Responsável")
            contentStream.endText()

            contentStream.moveTo(320f, startY)
            contentStream.lineTo(320f + larguraLinha, startY)
            contentStream.stroke()

            contentStream.beginText()
            contentStream.setFont(PDType1Font.HELVETICA, 10f)
            contentStream.newLineAtOffset(330f, startY - 15f)
            contentStream.showText("Assinatura do Representante")
            contentStream.endText()
        }

        // Começa o preenchimento
        desenharCabecalho()

        desenharTituloDeSecao("1. DADOS CONTRATANTE")
        desenharTabelaSimples(listOf(
            "Razão Social / Nome:" to nomeCliente,
            "CNPJ / CPF:" to cnpj,
            "Nome Comercial:" to nomeComercial,
            "Site da Empresa:" to siteEmpresa,
            "Endereço Completo:" to endereco,
            "Telefone:" to telefone,
            "Email da Empresa:" to emailEmpresa,
            "Nome do Representante:" to nomeRepresentante,
            "Fone Representante:" to foneRepresentante,
            "Email Representante:" to emailRepresentante
        ))

        desenharTituloDeSecao("2. DADOS DO LABORATÓRIO PRESTADOR DO SERVIÇO")
        desenharTabelaSimples(listOf(
            "Nome do Laboratório:" to nomeLaboratorio,
            "Sigla Laboratório:" to siglaLaboratorio,
            "Coordenador:" to nomeCoordenador,
            "CPF Coordenador:" to cpfCoordenador,
            "Técnico 1:" to tecnico1,
            "CPF Técnico 1:" to cpfTecnico1,
            "Técnico 2:" to tecnico2,
            "CPF Técnico 2:" to cpfTecnico2
        ))

        desenharTituloDeSecao("3. DADOS DO SERVIÇO")
        desenharTabelaSimples(listOf(
            "Detalhes do Serviço:" to detalhesDoServico,
            "Data Início:" to dataInicio,
            "Data Término:" to dataTermino,
            "Dias Trabalhados:" to totalDiasTrabalhado,
            "Valor:" to valor,
            "Desconto:" to desconto,
            "Valor Final:" to valorFinal
        ))

        desenharTituloDeSecao("4. FORMA DE PAGAMENTO")
        desenharTabelaSimples(listOf(
            "Forma de Pagamento:" to formaDePagamento.joinToString(", ")
        ))

        desenharTituloDeSecao("5. DO PAGAMENTO")
        desenharTabelaSimples(listOf(
            "Pagamento:" to "Dados bancários para pagamento (somente via transferência ou depósito):" +
                    "Banco do Brasil" +
                    "Agência: 3702-8" +
                    "Conta Corrente: 35.174-1" +
                    "Razão Social/CNPJ: Fundação de Ciência e Tecnologia Guamá - 11.024.200/0001-09"
        ))

        desenharAssinaturas()
        desenharRodapeCompleto()

        contentStream.close()

        val timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
        val fileName = "Orcamento_${nomeCliente.replace(" ", "")}_$timestamp.pdf"
        val file = File(pdfDir, fileName)
        document.save(file)

    } catch (e: Exception) {
        throw Exception("Erro ao gerar PDF: ${e.message}")
    } finally {
        document.close()
        }
}