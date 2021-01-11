package com.example.cardsagainstyourliver


import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources.getDrawable
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

   open fun getPic(): Int {
        val test: Int = R.drawable.card_kreuz_07
        return test
    }
}