package com.example.cardsagainstyourliver

class PerMilleCalculator {

    fun permille(mPerson: Double, gender: String, bierml: Double, weinml: Double, wodkaml: Double, Dauer: Int): Double{

        var r: Double
        var BAK: Double

        var konsuming: Double
        var konsumbier: Double
        var konsumwein: Double
        var konsumwodka: Double

        konsumbier = bierml*5.0 / 100*0.8;
        konsumwein = weinml*14.0 / 100*0.8;
        konsumwodka = wodkaml*40.0 / 100*0.8;

        konsuming = konsumbier + konsumwein + konsumwodka

        if(gender == "m"){
            r = 0.7
            BAK = konsuming / mPerson*0.7
        }else{
            r = 0.6
            BAK = konsuming / mPerson*0.6
        }

        for (i in 0 until Dauer) {
            BAK = BAK * 0.85
        }

        BAK = Math.round(BAK * 100.0) / 100.0

        return BAK



    }

}