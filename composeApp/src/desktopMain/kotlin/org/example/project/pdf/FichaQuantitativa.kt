package org.example.project.pdf

import org.example.project.data.Tela4State
import javax.naming.Context


fun generatePdfQuantitativo(data : Tela4State){
    println(data.coliformesTotais)
    println( data.coliformesPcGr)
    println( data.coliformesPcPeq)
}