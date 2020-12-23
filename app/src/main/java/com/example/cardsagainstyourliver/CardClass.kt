package com.example.cardsagainstyourliver

import java.io.OutputStream


class CardClass(private val sign: Sign,
                 private val value: Value) : Null() {


    override fun toString(): String {
        return "Card: $sign of $value"
    }


    override fun print(outputStream: OutputStream) {
        outputStream.write((this.toString() + "\r\n").toByteArray())
    }
}


enum class Sign {
    HERZ, KARO, PIK, KREUZ
}


enum class Value {
    ZWEI, DREI, VIER, FUENF, SECHS, SIEBEN, ACHT,
    NEUN, ZEHN, BUBE, DAME, KOENIG, ASS
}