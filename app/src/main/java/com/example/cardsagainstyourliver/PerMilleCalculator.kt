package com.example.cardsagainstyourliver

class PerMilleCalculator {

    fun permille(mPerson: Double, gender: String, bierml: Double, weinml: Double, wodkaml: Double, whiskeyml: Double, ginml: Double, sektml: Double, tequilaml: Double, rumml: Double,  Dauer: Int): Double{

        var r: Double
        var BAK: Double

        var konsuming: Double
        var konsumbier: Double
        var konsumwein: Double
        var konsumwodka: Double
        var konsumwhiskey: Double
        var konsumgin: Double
        var konsumsekt: Double
        var konsumtequila: Double
        var konsumrum: Double

        konsumbier = bierml*5.0 / 100*0.8
        konsumwein = weinml*14.0 / 100*0.8
        konsumwodka = wodkaml*40.0 / 100*0.8
        konsumwhiskey = whiskeyml*63.4 / 100*0.8
        konsumgin = ginml*40.0 / 100*0.8
        konsumsekt = sektml*12.8 / 100*0.8
        konsumtequila = tequilaml*38.0 / 100*0.8
        konsumrum = rumml*37.5 / 100*0.8

        konsuming = konsumbier + konsumwein + konsumwodka + konsumwhiskey + konsumgin + konsumsekt + konsumtequila + konsumrum

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