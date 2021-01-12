package com.example.cardsagainstyourliver

class PerMilleCalculator {

    fun permille(mPerson: Double, gender: String, mAlkohol: Double, vDrink: Double): Double{

        var r: Double

        if(gender == "m"){
            r = 0.7
        }else{
            r = 0.6
        }

        var alkM : Double = 10.0 * 1 * mAlkohol * 0.8

        var p : Double = (alkM*(mPerson*r))

        return p

    }

}