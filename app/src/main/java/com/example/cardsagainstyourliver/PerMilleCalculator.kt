package com.example.cardsagainstyourliver

class PerMilleCalculator {

    fun permille(mPerson: Double, gender: String, percentage: Double, ml: Double, Dauer: Int): Double{

        var r: Double
        var BAK: Double = 0.0

        var konsuming: Double

        konsuming = (ml*percentage)/(100.0*0.8)


        when(gender){
            "m" ->{
                r = 0.7
                BAK = konsuming / mPerson*r}
            "w" ->{
                r = 0.6
                BAK = konsuming / mPerson*r
            }
        }

        for (i in 0 until Dauer) {
            BAK = BAK * 0.85
        }

        BAK = Math.round(BAK * 100.0) / 100.0

        return BAK



    }

}