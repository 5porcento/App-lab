package org.example.project.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import java.io.File

fun pdfOrcamento(
    nomeCliente: String, cnpj: String, nomeComercial: String, siteEmpresa: String, endereco: String,
    telefone: String, emailEmpresa: String, nomeRepresentante: String, foneRepresentante: String, emailRepresentante: String,
    nomeLaboratorio: String, siglaLaboratorio: String, nomeCoordenador: String, cpfCoordenador: String, tecnico1: String,
    cpfTecnico1: String, tecnico2: String, cpfTecnico2: String, detalhesDoServico: String,
    dataInicio: String, dataTermino: String, totalDiasTrabalhado: String, valor: String, desconto: String, valorFinal: String, formaDePagamento: List<String>
) {
    val document = PDDocument()
    var page = PDPage()
    document.addPage(page)

    // contentStream precisa ser mutável para ser recriado
    var contentStream = PDPageContentStream(document, page)
    var yPosition = 700f

    try {
        // Criar diretório de PDFs se não existir
        val pdfDir = File("pdfs")
        if (!pdfDir.exists()) {
            pdfDir.mkdir()
        }

        fun verificarEspaco() {
            if (yPosition <= 50f) {
                contentStream.close()
                page = PDPage()
                document.addPage(page)
                contentStream = PDPageContentStream(document, page)
                yPosition = 750f
            }
        }


        fun escreverTitulo(titulo: String) {
            verificarEspaco()
            contentStream.beginText()
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14f)
            contentStream.newLineAtOffset(50f, yPosition)
            contentStream.showText(titulo)
            contentStream.endText()
            yPosition -= 25f
        }

        fun escreverLista(lista: List<String>) {
            val maxWidth = 500f
            val font = PDType1Font.HELVETICA
            val fontSize = 12f
            val lineHeight = 15f

            lista.forEach { itemCompleto ->

                val linhasSeparadas = itemCompleto.lines()

                linhasSeparadas.forEachIndexed { index, linhaBruta ->
                    val words = linhaBruta.split(" ")

                    var currentLine = if (index == 0) "-" else "  "

                    words.forEach { word ->
                        val testLine = if (currentLine.isBlank() || currentLine == "-") "$currentLine $word" else "$currentLine $word"
                        val testLineWidth = (font.getStringWidth(testLine) / 1000f) * fontSize

                        if (testLineWidth > maxWidth) {
                            // Se a palavra sozinha for muito grande, quebrar ela manualmente
                            if (font.getStringWidth(word) / 1000f * fontSize > maxWidth) {
                                // escreve o que tem antes
                                if (currentLine.isNotBlank() && currentLine != "-") {
                                    verificarEspaco()
                                    contentStream.beginText()
                                    contentStream.setFont(font, fontSize)
                                    contentStream.newLineAtOffset(50f, yPosition)
                                    contentStream.showText(currentLine.trim())
                                    contentStream.endText()
                                    yPosition -= lineHeight
                                }

                                // quebra a palavra muito longa
                                var tempWord = ""
                                for (char in word) {
                                    tempWord += char
                                    val tempWidth = (font.getStringWidth(tempWord) / 1000f) * fontSize
                                    if (tempWidth > maxWidth) {
                                        verificarEspaco()
                                        contentStream.beginText()
                                        contentStream.setFont(font, fontSize)
                                        contentStream.newLineAtOffset(50f, yPosition)
                                        contentStream.showText(tempWord.dropLast(1)) // escreve sem o último caractere
                                        contentStream.endText()
                                        yPosition -= lineHeight
                                        tempWord = char.toString()
                                    }
                                }
                                // escreve o que sobrou
                                if (tempWord.isNotBlank()) {
                                    verificarEspaco()
                                    contentStream.beginText()
                                    contentStream.setFont(font, fontSize)
                                    contentStream.newLineAtOffset(50f, yPosition)
                                    contentStream.showText(tempWord)
                                    contentStream.endText()
                                    yPosition -= lineHeight
                                }

                                currentLine = "  " // inicia nova linha depois da palavra quebrada
                            } else {
                                // se a palavra é normal, apenas quebra normal
                                verificarEspaco()
                                contentStream.beginText()
                                contentStream.setFont(font, fontSize)
                                contentStream.newLineAtOffset(50f, yPosition)
                                contentStream.showText(currentLine.trim())
                                contentStream.endText()
                                yPosition -= lineHeight
                                currentLine = "  $word"
                            }
                        } else {
                            currentLine = testLine
                        }
                    }

                    if (currentLine.isNotBlank()) {
                        verificarEspaco()
                        contentStream.beginText()
                        contentStream.setFont(font, fontSize)
                        contentStream.newLineAtOffset(50f, yPosition)
                        contentStream.showText(currentLine.trim())
                        contentStream.endText()
                        yPosition -= lineHeight
                    }
                }

                yPosition -= 5f // espaço extra entre itens
            }

            yPosition -= 10f // espaço extra depois da lista
        }




        // Conteúdo
        escreverTitulo("Dados do Contratante:")
        escreverLista(listOf(
            "Razão Social/Nome Completo: $nomeCliente",
            "Nome Comercial: $nomeComercial",
            "Fone: $foneRepresentante",
            "Email: $emailRepresentante",
            "Email da Empresa: $emailEmpresa",
            "Endereço Completo: $endereco",
            "Nome do Representante: $nomeRepresentante",
            "Site da empresa: $siteEmpresa",
            "DDD/Telefone: $telefone",
            "CNPJ/CPF: $cnpj"
        ))

        escreverTitulo("Dados do Laboratório Prestador Do Serviço:")
        escreverLista(listOf(
            "Nome do Laboratorio: $nomeLaboratorio",
            "Sigla do Laboratorio: $siglaLaboratorio",
            "Nome do Coordenador Responsável: $nomeCoordenador",
            "CPF Coordenador: $cpfCoordenador",
            "Nome do Técnico 1: $tecnico1",
            "CPF Técnico 1: $cpfTecnico1",
            "Nome do Técnico 2: $tecnico2",
            "CPF Técnico 2: $cpfTecnico2"
        ))

        escreverTitulo("Dados do Serviço:")
        escreverLista(listOf(
            "Detalhes do serviço: $detalhesDoServico",
            "Data de início: $dataInicio",
            "Data de término: $dataTermino",
            "Total de dias trabalhados: $totalDiasTrabalhado"
        ))

        escreverTitulo("Pagamento:")
        escreverLista(listOf(
            "Valor: $valor",
            "Desconto de até 20%: $desconto",
            "Valor Final (R$): $valorFinal"
        ))

        escreverTitulo("Forma de Pagamento:")
        escreverLista(formaDePagamento)

        escreverTitulo("Dados bancários para pagamento:")
        escreverLista(listOf(
            "Banco do Brasil",
            "Agência: 3702-8",
            "Conta Corrente: 35.174-1",
            "Razão Social/CNPJ: Fundação de Ciência e Tecnologia Guamá - 11.024.200/0001-09"
        ))


        contentStream.close()

        val timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
        val fileName = "Orcamento_${nomeCliente.replace(" ", "_")}_$timestamp.pdf"
        val file = File(pdfDir, fileName)
        document.save(file)

    } catch (e: Exception) {
        throw Exception("Erro ao gerar PDF: ${e.message}")
    } finally {
        document.close()
    }
}
