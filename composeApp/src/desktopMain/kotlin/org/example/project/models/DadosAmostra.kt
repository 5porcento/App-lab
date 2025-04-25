package org.example.project.models

data class Amostra(val parametro:String,val tipoRecipiente:String, val volume:String,val preservacao:String,val prazo:String)

val tabelaAmostra = listOf(
    Amostra("pH", "Plástico ou Vidro", "25 mL", "Imediato", "2 horas"),
    Amostra("Cor", "Plástico ou Vidro", "50 mL", "Resfriado a 4°C", "48 horas"),
    Amostra("Condutividade Elétrica", "Plástico ou Vidro", "100 mL", "Resfriado a 4°C", "28 dias"),
    Amostra("Turbidez", "Plástico ou Vidro", "100 mL", "Resfriado a 4°C", "48 horas/7 dias"),
    Amostra("Cloreto", "Plástico ou Vidro", "50 mL", "Sem Preservação", "7 dias"),
    Amostra("Cloro Livre", "Plástico ou Vidro", "50 mL", "Resfriado a 4°C", "24 horas"),
    Amostra("Cloro total (livre + residual)", "Plástico ou Vidro", "50 mL", "Resfriado a 4°C", "24 horas"),
    Amostra("Dureza", "Plástico ou Vidro", "100 mL", "Resfriado a 4°C com HNO₃ (pH<2)", "6 meses"),
    Amostra("Fluoreto", "Plástico ou Vidro", "100 mL", "Sem Preservação", "7 dias"),
    Amostra("Fosfato", "Plástico ou Vidro", "50 mL", "Resfriado a 4°C", "24h / não ultrapassar 7 dias"),
    Amostra("Fósforo Total", "Plástico ou Vidro", "500 mL", "Resfriado a 4°C com H₂SO₄ (pH<2)", "24h/28 dias"),
    Amostra("Oxigênio Dissolvido", "Vidro âmbar", "300 mL", "Fixação no local", "8 horas"),
    Amostra("Temperatura", "Plástico ou Vidro", "1000 mL", "Sem preservação", "Determinação imediata"),
    Amostra("Nitrogênio Total", "Plástico ou Vidro", "250 mL", "Refrigeração (4°C ±2°C) e adição de H₂SO₄ a pH<2", "48 horas"),
    Amostra("Amônia (NH₄)", "Plástico ou Vidro", "250 mL", "Refrigeração (4°C ±2°C)", "7 dias"),
    Amostra("Nitrato", "Plástico ou Vidro", "250 mL", "Refrigeração (4°C ±2°C) e adição de H₂SO₄ a pH<2", "48 horas"),
    Amostra("Nitrito", "Plástico ou Vidro", "250 mL", "Refrigeração (4°C ±2°C) e adição de H₂SO₄ a pH<2", "48 horas"),
    Amostra("Sulfato", "Plástico ou Vidro", "250 mL", "Refrigeração (4°C ±2°C)", "28 dias"),
    Amostra("Sulfeto", "Plástico ou Vidro", "500 mL", "NaOH pH>9 e refrigeração (4°C ±2°C)", "28 dias"),
    Amostra("Acidez", "Plástico ou Vidro", "250 mL", "Refrigeração (4°C ±2°C)", "24 horas"),
    Amostra("Alcalinidade", "Plástico ou Vidro", "250 mL", "Refrigeração (4°C ±2°C)", "24 horas"),
    Amostra("DQO", "Plástico ou Vidro", "250 mL", "Analisar o mais breve possível, adicionando H₂SO₄ pH<2", "7 dias"),
    Amostra("DBO", "Plástico ou Vidro", "500 mL", "Refrigeração (4°C ±2°C)", "6 horas"),
    Amostra("Sólidos Filtrável/dissolvidos", "Plástico ou Vidro", "100 mL", "Resfriado a 4°C", "7 dias"),
    Amostra("Sólidos Não filtrável/em suspensão", "Plástico ou Vidro", "100 mL", "Resfriado a 4°C", "7 dias"),
    Amostra("Sólidos Total", "Plástico ou Vidro", "100 mL", "Resfriado a 4°C", "7 dias"),
    Amostra("Sólidos Volátil", "Plástico ou Vidro", "100 mL", "Resfriado a 4°C", "7 dias"),
    Amostra("Sólidos Sedimentáveis/decantáveis", "Plástico ou Vidro", "1000 mL", "Sem preservação", "24 horas"),
    Amostra("Metais Totais (Ca, Cd, Co, Cu, Cr, Fe, Mg, Mn, Ni, Pb, K, Na, Zn)", "Plástico", "200 mL", "Resfriado a 4°C com HNO₃ (pH<2)", "6 meses"),
    Amostra("Metais Dissolvidos (Ca, Cd, Co, Cu, Cr, Fe, Mg, Mn, Ni, Pb, K, Na, Zn)", "Plástico", "200 mL", "Resfriado a 4°C com HNO₃ (pH<2)", "6 meses"),
    Amostra("Coliformes Totais/Termotolerantes", "Sacos ou frascos coletores de plástico estéreis", "100 mL água de consumo humano; 300 mL água de meio ambiente", "Resfriado a 4°C", "24 horas"),
    Amostra("Enterococcus spp.", "Sacos ou frascos coletores de plástico estéreis", "100 mL água de consumo humano; 300 mL água de meio ambiente", "Resfriado a 4°C", "24 horas"),
    Amostra("E. coli", "Sacos ou frascos coletores de plástico estéreis", "100 mL água de consumo humano; 300 mL água de meio ambiente", "Resfriado a 4°C","24horas")

)