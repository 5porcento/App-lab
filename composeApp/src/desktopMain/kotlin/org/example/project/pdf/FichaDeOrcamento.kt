package org.example.project.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import java.io.File


fun pdfOrcamento(
     nomeCliente:String, cnpj:String, nomeComercial:String, siteEmpresa:String, endereco:String,
 telefone:String, emailEmpresa:String, nomeRepresentante:String, foneRepresentante:String, emailRepresentante:String,
 nomeLaboratorio:String, siglaLaboratorio:String, nomeCoordenador:String, cpfCoordenador:String, tecnico1:String,
 cpfTecnico1:String, tecnico2:String, cpfTecnico2:String, detalhesDoServico:String,
 dataInicio:String, dataTermino:String, totalDiasTrabalhado :String, valor:String, desconto:String,valorFinal:String,formaDePagamento:List<String>
){
    val document = PDDocument()
    val page = PDPage()
    document.addPage(page)

    try {
        // Criar diretório de PDFs se não existir
        val pdfDir = File("pdfs")
        if (!pdfDir.exists()) {
            pdfDir.mkdir()
        }

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
            val maxWidth = 500f // Largura máxima da linha em pontos
            val lineHeight = 15f // Altura da linha em pontos

            lista.forEach { item ->
                val words = item.split(" ")
                var currentLine = "- "
                
                words.forEach { word ->
                    val testLine = "$currentLine $word"
                    val size = (PDType1Font.HELVETICA.getStringWidth(testLine) / 1000f) * 12f
                    
                    if (size > maxWidth) {
                        // Escrever a linha atual
                        contentStream.beginText()
                        contentStream.newLineAtOffset(50f, yPosition)
                        contentStream.showText(currentLine)
                        contentStream.endText()
                        yPosition -= lineHeight
                        
                        // Começar nova linha com indentação
                        currentLine = "  $word"
                    } else {
                        currentLine = testLine
                    }
                }
                
                // Escrever a última linha
                contentStream.beginText()
                contentStream.newLineAtOffset(50f, yPosition)
                contentStream.showText(currentLine)
                contentStream.endText()
                yPosition -= lineHeight
            }
            yPosition -= 10f
        }

        // Informações do usuário
        escreverTitulo("Dados do Contratante :")
        val dadosContratante = listOf(
            "Razão Social/Nome Completo: $nomeCliente",
            "Nome Comercial: $nomeComercial",
            "Fone: $foneRepresentante",
            "Email: $emailRepresentante",
            "Email da Empresa: $emailEmpresa",
            "Endereço Completo: $endereco",
            "Nome do Representante: $nomeRepresentante",
            "Site da empresa: $siteEmpresa",
            "Fone : $foneRepresentante",
            "DDD/Telefone: $telefone",
            "CNPJ/CPF: $cnpj"
        )
        escreverLista(dadosContratante)

        escreverTitulo("Dados do Laboratório Prestador Do Serviço :")
        val dadosDoLab = listOf(
            "Nome do Laboratorio: $nomeLaboratorio",
            "Fone: $siglaLaboratorio",
            "Nome do Coodernador Reponsavel pela Execução do Serviço: $nomeCoordenador",
            "CPF: $cpfCoordenador",
            "Nome do Tecnico Reponsavel pela Execução do Serviço: $tecnico1",
            "CPF: $cpfTecnico1",
            "Nome do Tecnico Reponsavel pela Execução do Serviço : $tecnico2",
            "CPF: $cpfTecnico2",
        )
        escreverLista(dadosDoLab)

        escreverTitulo("Dados do Serviço:")
        val dadosDoservico = listOf(
            "Necessidades do demandante,serviços a serem execultados e resultados a serem entregues: $detalhesDoServico",
            "Data de inicio: $dataInicio",
            "Data de Termibo: $dataTermino",
            "Total de dias Trabalhados: $totalDiasTrabalhado"
        )
        escreverLista(dadosDoservico)

        escreverTitulo("Pagamento:")
        val dadosDoPagamento = listOf(
            "Valor: $valor",
            "Desconto de ate 20%: $desconto",
            "Valor Final(R$): $valorFinal",
        )
        escreverLista(dadosDoPagamento)

        //TODO adicionar esse texto ao PDF gerado
//        "Dados bancários para pagamento (somente via transferência ou depósito):\n" +
//                "Banco do Brasil\n" +
//                "Agência: 3702-8\n" +
//                "Conta Corrente: 35.174-1\n" +
//                "Razão Social/CNPJ: Fundação de Ciência e Tecnologia Guamá - 11.024.200/0001-09"

        // Parâmetros Selecionados
        escreverTitulo("Forma de Pagamento:")
        escreverLista(formaDePagamento)

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