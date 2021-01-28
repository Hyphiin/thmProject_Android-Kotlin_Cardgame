package com.example.cardsagainstyourliver

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import java.io.IOException
import java.io.OutputStream


//Dies repräsentiert eine Spielkarte, sie hat ein Sign(Pik,Herz...) und ein Value (2,3,Bube...)
class CardClass(private val sign: Sign, private val value: Value) : Null() {

    override fun toString(): String {
        return "Karte: $sign $value"
    }


    override fun print(outputStream: OutputStream) {
        outputStream.write((this.toString() + "\r\n").toByteArray())
    }

    override fun getSign(): String {
        return "$sign"
    }

    override fun getValue(): String {
        return "$value"
    }

    //das Value in einer Int Zahl
    override fun getValueNumber(): Int {
        var valueNumber: Int = 0
        when (value) {
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

    override fun getValueNumberBettler(): Int {
        var valueNumber: Int = 0
        when (value) {
            Value.ZWEI -> valueNumber = 2
            Value.DREI -> valueNumber = 3
            Value.VIER -> valueNumber = 4
            Value.FUENF -> valueNumber = 5
            Value.SECHS -> valueNumber = 6
            Value.SIEBEN -> valueNumber = 7
            Value.ACHT -> valueNumber = 8
            Value.NEUN -> valueNumber = 9
            Value.ZEHN -> valueNumber = 10
            Value.BUBE -> valueNumber = 11
            Value.DAME -> valueNumber = 12
            Value.KOENIG -> valueNumber = 13
            Value.ASS -> valueNumber = 14
        }
        return valueNumber
    }

    override fun getPic(): Int {
        var picture: Int = R.drawable.card_kreuz_07
        when (sign) {
            Sign.HERZ -> {
                when (value) {
                    Value.SIEBEN -> picture = R.drawable.card_heart_07
                    Value.ACHT -> picture = R.drawable.card_heart_08
                    Value.NEUN -> picture = R.drawable.card_heart_09
                    Value.ZEHN -> picture = R.drawable.card_heart_10
                    Value.BUBE -> picture = R.drawable.card_heart_b
                    Value.DAME -> picture = R.drawable.card_heart_d
                    Value.KOENIG -> picture = R.drawable.card_heart_k
                    Value.ASS -> picture = R.drawable.card_heart_a
                    else -> true
                }
            }
            Sign.KARO -> {
                when (value) {
                    Value.SIEBEN -> picture = R.drawable.card_caro_07
                    Value.ACHT -> picture = R.drawable.card_caro_08
                    Value.NEUN -> picture = R.drawable.card_caro_09
                    Value.ZEHN -> picture = R.drawable.card_caro_10
                    Value.BUBE -> picture = R.drawable.card_caro_b
                    Value.DAME -> picture = R.drawable.card_caro_d
                    Value.KOENIG -> picture = R.drawable.card_caro_k
                    Value.ASS -> picture = R.drawable.card_caro_a
                    else -> true
                }
            }
            Sign.PIK -> {
                when (value) {
                    Value.SIEBEN -> picture = R.drawable.card_pik_07
                    Value.ACHT -> picture = R.drawable.card_pik_08
                    Value.NEUN -> picture = R.drawable.card_pik_09
                    Value.ZEHN -> picture = R.drawable.card_pik_10
                    Value.BUBE -> picture = R.drawable.card_pik_b
                    Value.DAME -> picture = R.drawable.card_pik_d
                    Value.KOENIG -> picture = R.drawable.card_pik_k
                    Value.ASS -> picture = R.drawable.card_pik_a
                    else -> true
                }
            }
            Sign.KREUZ -> {
                when (value) {
                    Value.SIEBEN -> picture = R.drawable.card_kreuz_07
                    Value.ACHT -> picture = R.drawable.card_kreuz_08
                    Value.NEUN -> picture = R.drawable.card_kreuz_09
                    Value.ZEHN -> picture = R.drawable.card_kreuz_10
                    Value.BUBE -> picture = R.drawable.card_kreuz_b
                    Value.DAME -> picture = R.drawable.card_kreuz_d
                    Value.KOENIG -> picture = R.drawable.card_kreuz_k
                    Value.ASS -> picture = R.drawable.card_kreuz_a
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
