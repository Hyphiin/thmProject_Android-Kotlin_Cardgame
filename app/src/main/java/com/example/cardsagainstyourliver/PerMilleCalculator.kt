package com.example.cardsagainstyourliver

class PerMilleCalculator {

    fun permille(mPerson: Int, gender: Int, percentage: Double, ml: Double, endurance: Int): Double{

        var r: Double
        var BAK: Double = 0.0
        var hours: Int

        var konsuming: Double

        konsuming = (ml*percentage)/(100.0*0.8)

        when(gender){
            0 ->{
                r = 0.7
                BAK = konsuming / mPerson*r}
            1 ->{
                r = 0.6
                BAK = konsuming / mPerson*r
            }
        }


        for (i in 0 until endurance) {
            BAK *= 0.85
        }

        BAK = Math.round(BAK * 100.0) / 100.0

        return BAK



    }

}