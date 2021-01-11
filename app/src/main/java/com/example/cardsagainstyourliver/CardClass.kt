package com.example.cardsagainstyourliver

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import java.io.IOException
import java.io.OutputStream



//Dies repräsentiert eine Spielkarte, sie hat ein Sign(Pik,Herz...) und ein Value (2,3,Bube...)
class CardClass( private val sign: Sign, private val value: Value) : Null() {

    override fun toString(): String {
        return "Karte: $sign $value"
    }


    override fun print(outputStream: OutputStream) {
        outputStream.write((this.toString() + "\r\n").toByteArray())
    }

    override fun getSign():String{
        return "$sign"
    }

    override fun getValue():String{
        return "$value"
    }

    //das Value in einer Int Zahl
    override fun getValueNumber():Int{
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
    }

    override fun getPic():String{
        var picture:String = "card_heart_07.png"

            when (sign) {
                Sign.HERZ -> {
                    when (value) {
                        Value.SIEBEN -> picture = "card_heart_07.png"
                        Value.ACHT -> picture = "card_heart_08.png"
                        Value.NEUN -> picture = "card_heart_09.png"
                        Value.ZEHN -> picture = "card_heart_10.png"
                        Value.BUBE -> picture = "card_heart_b.png"
                        Value.DAME -> picture = "card_heart_d.png"
                        Value.KOENIG -> picture = "card_heart_k.png"
                        Value.ASS -> picture = "card_heart_a.png"
                        else -> true
                    }
                }
                Sign.KARO -> {
                    when (value) {
                        Value.SIEBEN -> picture = "card_caro_07.png"
                        Value.ACHT -> picture = "card_caro_08.png"
                        Value.NEUN -> picture = "card_caro_09.png"
                        Value.ZEHN -> picture = "card_caro_10.png"
                        Value.BUBE -> picture = "card_caro_b.png"
                        Value.DAME -> picture = "card_caro_d.png"
                        Value.KOENIG -> picture = "card_caro_k.png"
                        Value.ASS -> picture = "card_caro_a.png"
                        else -> true
                    }
                }
                Sign.PIK -> {
                    when (value) {
                        Value.SIEBEN -> picture = "card_pik_07.png"
                        Value.ACHT -> picture = "card_pik_08.png"
                        Value.NEUN -> picture = "card_pik_09.png"
                        Value.ZEHN -> picture = "card_pik_10.png"
                        Value.BUBE -> picture = "card_pik_b.png"
                        Value.DAME -> picture = "card_pik_d.png"
                        Value.KOENIG -> picture = "card_pik_k.png"
                        Value.ASS -> picture = "card_pik_a.png"
                        else -> true
                    }
                }
                Sign.KREUZ -> {
                    when (value) {
                        Value.SIEBEN -> picture = "card_kreuz_07.png"
                        Value.ACHT -> picture = "card_kreuz_08.png"
                        Value.NEUN -> picture = "card_kreuz_09.png"
                        Value.ZEHN -> picture = "card_kreuz_10.png"
                        Value.BUBE -> picture = "card_kreuz_b.png"
                        Value.DAME -> picture = "card_kreuz_d.png"
                        Value.KOENIG -> picture = "card_kreuz_k.png"
                        Value.ASS -> picture = "card_kreuz_a.png"
                        else -> true
                    }
                }
            }
            return picture
        }
}



enum class Sign {
    HERZ, KARO, PIK, KREUZ
}


enum class Value {
    ZWEI, DREI, VIER, FUENF, SECHS, SIEBEN, ACHT,
    NEUN, ZEHN, BUBE, DAME, KOENIG, ASS
}