package org.example.project.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import java.io.File

fun pdfContrato(
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
    val page = PDPage()
    document.addPage(page)

    try {
        val contentStream = PDPageContentStream(document, page)
        
        // Configuração inicial
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16f)
        contentStream.beginText()
        contentStream.newLineAtOffset(50f, 750f)
        contentStream.showText("CONTRATO DE PRESTAÇÃO DE SERVIÇOS")
        contentStream.endText()

        // Dados do Contratante
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12f)
        contentStream.beginText()
        contentStream.newLineAtOffset(50f, 700f)
        contentStream.showText("DADOS DO CONTRATANTE")
        contentStream.endText()

        contentStream.setFont(PDType1Font.HELVETICA, 10f)
        var yPosition = 680f

        val dadosContratante = listOf(
            "Razão Social / Nome Completo: $nomeCliente",
            "CNPJ / CPF: $cnpj",
            "Nome Comercial / Projeto: $nomeComercial",
            "Site da Empresa: $siteEmpresa",
            "Endereço: $endereco",
            "Telefone: $telefone",
            "E-mail: $emailEmpresa",
            "Nome do Representante: $nomeRepresentante",
            "Telefone do Representante: $foneRepresentante",
            "E-mail do Representante: $emailRepresentante"
        )

        dadosContratante.forEach { dado ->
            contentStream.beginText()
            contentStream.newLineAtOffset(50f, yPosition)
            contentStream.showText(dado)
            contentStream.endText()
            yPosition -= 15f
        }

        // Dados do Laboratório
        yPosition -= 20f
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12f)
        contentStream.beginText()
        contentStream.newLineAtOffset(50f, yPosition)
        contentStream.showText("DADOS DO LABORATÓRIO")
        contentStream.endText()

        contentStream.setFont(PDType1Font.HELVETICA, 10f)
        yPosition -= 20f

        val dadosLaboratorio = listOf(
            "Nome do Laboratório: $nomeLaboratorio",
            "Sigla/Logo: $siglaLaboratorio",
            "Nome do Coordenador: $nomeCoordenador",
            "CPF do Coordenador: $cpfCoordenador",
            "Nome do Técnico 1: $tecnico1",
            "CPF do Técnico 1: $cpfTecnico1",
            "Nome do Técnico 2: $tecnico2",
            "CPF do Técnico 2: $cpfTecnico2"
        )

        dadosLaboratorio.forEach { dado ->
            contentStream.beginText()
            contentStream.newLineAtOffset(50f, yPosition)
            contentStream.showText(dado)
            contentStream.endText()
            yPosition -= 15f
        }

        // Dados do Serviço
        yPosition -= 20f
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12f)
        contentStream.beginText()
        contentStream.newLineAtOffset(50f, yPosition)
        contentStream.showText("DADOS DO SERVIÇO")
        contentStream.endText()

        contentStream.setFont(PDType1Font.HELVETICA, 10f)
        yPosition -= 20f

        val dadosServico = listOf(
            "Descrição do Serviço: $detalhesDoServico",
            "Data de Início: $dataInicio",
            "Data de Término: $dataTermino",
            "Total de Dias Trabalhados: $totalDiasTrabalhado"
        )

        dadosServico.forEach { dado ->
            contentStream.beginText()
            contentStream.newLineAtOffset(50f, yPosition)
            contentStream.showText(dado)
            contentStream.endText()
            yPosition -= 15f
        }

        // Dados do Pagamento
        yPosition -= 20f
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12f)
        contentStream.beginText()
        contentStream.newLineAtOffset(50f, yPosition)
        contentStream.showText("DADOS DO PAGAMENTO")
        contentStream.endText()

        contentStream.setFont(PDType1Font.HELVETICA, 10f)
        yPosition -= 20f

        val dadosPagamento = listOf(
            "Valor: R$ $valor",
            "Desconto: $desconto",
            "Valor Final: R$ $valorFinal",
            "Formas de Pagamento:"
        )

        dadosPagamento.forEach { dado ->
            contentStream.beginText()
            contentStream.newLineAtOffset(50f, yPosition)
            contentStream.showText(dado)
            contentStream.endText()
            yPosition -= 15f
        }

        formaDePagamento.forEach { forma ->
            contentStream.beginText()
            contentStream.newLineAtOffset(70f, yPosition)
            contentStream.showText("- $forma")
            contentStream.endText()
            yPosition -= 15f
        }

        contentStream.close()

        // Salvar o documento
        val file = File("Contrato.pdf")
        document.save(file)

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        document.close()
    }
} 