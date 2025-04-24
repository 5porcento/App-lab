package org.example.project.viewModels


fun formatarData(input: String): String {
    // Remove tudo que não for número
    val somenteNumeros = input.filter { it.isDigit() }.take(8)

    return buildString {
        for (i in somenteNumeros.indices) {
            append(somenteNumeros[i])
            if (i == 1 || i == 3) append('/')
        }
    }
}