package com.example.cardsagainstyourliver

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import java.io.IOException
import java.io.OutputStream


//enthält die print Methode


//ist die Oberklasse der Karte
open class Null {
    open fun print(outputStream: OutputStream) = outputStream.write("Null\r\n".toByteArray())

    open fun getValue():String{
        return "hallo"
    }

    open fun getSign():String{
        return "moin"
    }

    open fun getValueNumber():Int {
        return 1
    }

    open fun getPic(): String {
        return "card_heart_07.png"
    }

}