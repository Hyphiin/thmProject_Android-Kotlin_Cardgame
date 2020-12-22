package com.example.cardsagainstyourliver

import java.io.OutputStream

/**
 enthält die print Methode
 */

open class Null {
    open fun print(outputStream: OutputStream) = outputStream.write("Null\r\n".toByteArray())
}