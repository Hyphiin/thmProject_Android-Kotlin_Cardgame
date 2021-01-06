package com.example.cardsagainstyourliver

import java.io.OutputStream


class CardClass(private val sign: Sign,
                private val value: Value) : Null() {


    override fun toString(): String {
        return "Karte: $sign $value"
    }


    override fun print(outputStream: OutputStream) {
        outputStream.write((this.toString() + "\r\n").toByteArray())
    }

    fun getValue():Value{
        return value
    }

    // versucht irgendwie an den Wert des Values zu gelangen... klappt aber iwie nicht...
    /*fun getValueNumber():Int{
       var valueNumber:Int = 0
       when (value){
           Value.ZWEI -> valueNumber = 2
           Value.DREI -> valueNumber = 3
           Value.VIER -> valueNumber = 4
           Value.FUENF -> valueNumber = 5
           Value.SECHS -> valueNumber = 6
           Value.SIEBEN -> valueNumber = 7
           Value.ACHT -> valueNumber = 8
           Value.NEUN -> valueNumber = 9
           Value.ZEHN -> valueNumber = 10
           Value.BUBE -> valueNumber = 10
           Value.DAME -> valueNumber = 10
           Value.KOENIG -> valueNumber = 10
           Value.ASS -> valueNumber = 11
           }
        return valueNumber
    }*/
}


enum class Sign {
    HERZ, KARO, PIK, KREUZ
}


enum class Value {
    ZWEI, DREI, VIER, FUENF, SECHS, SIEBEN, ACHT,
    NEUN, ZEHN, BUBE, DAME, KOENIG, ASS
}