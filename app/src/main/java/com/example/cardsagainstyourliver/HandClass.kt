package com.example.cardsagainstyourliver

import android.util.Log

//Die Karten, die man auf der Hand hat
class HandClass(val deck:DeckClass, val name:String) {

    var hand:MutableList<Null> = mutableListOf()

    //für Schwimmen werden am anfang 3 handkarten ausgeteilt, beim Ablagestapel ist es zB Null mit keinen Karten
    init{
        if (name === "Schwimmen"){
            take()
            take()
            take()
        }
        if (name === "Null"){

        }
        if (name === "Bettler"){
            for(i in 1..16){
                take()
            }
        }
    }

    //eine Karte aus dem Deck auf die Hand nehmen
    fun take(){
        hand.add(deck.drawCard())
    }

    //eine spezifische Karte adden
    fun add(card:Null){
        hand.add(card)
    }

    fun addAt(index:Int, card: Null){
        hand.add(index, card)
    }

    fun clear() {
        hand.clear()
    }

    //eine spezifische Karte löschen
    fun delete(card:Null){
        hand.remove(card)
    }

    fun deleteAt(index:Int){
        hand.removeAt(index)
    }

    //die Value Number einer Karte zum rechnen bekommen
    fun getValue(card:Null):Int{
        var valueNumber:Int = card.getValueNumber()
        return valueNumber
    }

    fun getPic(card:Null):Int{
        val picture: Int = card.getPic()
        return picture
    }

    //gibt die Höchste Hand Karte aus
    fun getMaxValue(hand:HandClass):Int{
        var maxNum = 0
        var value1 = hand.getValue(hand.getCard(0))
        var value2 = hand.getValue(hand.getCard(1))
        var value3 = hand.getValue(hand.getCard(2))

        if (value1 > value2){
            maxNum = value1
        }else {
            maxNum = value2
        }

        if (value3 > maxNum){
            maxNum = value3
        }

        return maxNum
    }

    //bestimmt den gesamt Value der Hand nach Schwimmen Regeln
    fun getValueHand(hand:HandClass):Int{
        var valueHand:Int = 0
        var twoSign = false
        var threeSign = false
        var oneSign = false
        var threeNum = false
        var oneNum = false

        //abfrage wie viele Signs übereinstimmen
        if (hand.legalCombiSign(hand.getCard(0), hand.getCard(1)) === true && hand.legalCombiSign(hand.getCard(1), hand.getCard(2)) === true && hand.legalCombiSign(hand.getCard(0), hand.getCard(2)) === true){
            threeSign = true
        }else if (hand.legalCombiSign(hand.getCard(0), hand.getCard(1)) === true || hand.legalCombiSign(hand.getCard(1), hand.getCard(2)) === true || hand.legalCombiSign(hand.getCard(0), hand.getCard(2)) === true){
            twoSign = true
        }else{oneSign = true}

        //abfrage wie viele Nums übereinstimmen
        if (hand.legalCombiNum(hand.getCard(0), hand.getCard(1)) === true && hand.legalCombiNum(hand.getCard(1), hand.getCard(2)) === true && hand.legalCombiNum(hand.getCard(0), hand.getCard(2)) === true){
            threeNum = true
            if (hand.getValue(hand.getCard(0)) == 11) {
                valueHand = 33
            }else{
                valueHand = 31
            }
        }else{oneNum = true}

        //zusammenrechnung der Übereinstimmenden Karten
        if (threeSign === true){
            for (i in 0..hand.getSize()-1){
                valueHand = valueHand + hand.getValue(hand.getCard(i))
            }
        }else if (twoSign === true){
            if (hand.legalCombiSign(hand.getCard(0), hand.getCard(1)) === true){
                valueHand = valueHand + hand.getValue(hand.getCard(0))
                valueHand = valueHand + hand.getValue(hand.getCard(1))
            }else if(hand.legalCombiSign(hand.getCard(1), hand.getCard(2)) === true){
                valueHand = valueHand + hand.getValue(hand.getCard(1))
                valueHand = valueHand + hand.getValue(hand.getCard(2))
            }else if(hand.legalCombiSign(hand.getCard(0), hand.getCard(2)) === true){
                valueHand = valueHand + hand.getValue(hand.getCard(0))
                valueHand = valueHand + hand.getValue(hand.getCard(2))
            }
        }else if (oneSign === true){
            valueHand = hand.getMaxValue(hand)
        }else if (oneNum === true){
            valueHand = hand.getMaxValue(hand)
        }

        return valueHand
    }

    //überprüfen ob signs zweier Karten übereinstimmen
    fun legalCombiSign(card1:Null, card2:Null):Boolean{
        var legalSign = false
        if (card1.getSign() == card2.getSign()){
            legalSign = true
        }
        return legalSign
    }

    //überprüfen ob values zweier Karten übereinstimmen
    fun legalCombiNum(card1:Null, card2:Null):Boolean{
        var legalNum = false
        if (card1.getValue() == card2.getValue()){
            legalNum = true
        }
        return legalNum
    }

    //schaut ob die Hand eine Spezielle Karte enthält, gibt true oder false aus (eig unnütze Funktion)
    fun contains(card: CardClass) = hand.contains(card)

    //Gibt das aktuelle Karten Objekt aus, wenn im println ausgegeben->Format = "Karte: KREUZ ASS"
    fun getCard(i:Int):Null{
        var handCard:Null = hand.get(i)
        return  handCard
    }

    fun getIndex(card: Null):Int{
        return hand.indexOf(card)
    }

    fun getSize(): Int = hand.size

    override fun toString() = hand.joinToString()
}
