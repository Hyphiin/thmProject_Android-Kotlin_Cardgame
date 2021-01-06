package com.example.cardsagainstyourliver

class HandClass(val deck:DeckClass, val name:String) {

    var hand:MutableList<Null> = mutableListOf()

    init{
        if (name === "Schwimmen"){
            take()
            take()
            take()
        }
        if (name === "Null"){

        }
    }

    fun take() {
        hand.add(deck.drawCard())
    }

    fun add(card:Null){
        hand.add(card)
    }

    fun clear() {
        hand.clear()
    }

    fun delete(card:Null){
        hand.remove(card)
    }

    //wäre eine sehr brauchbare methode... nur geht iwie nicht :C
    /*fun getValue(hand:HandClass):Int{
        var value = 0
        for (i in 0..hand.getSize()){
            value = value + hand.getCard(i).getValueNumber()
        }
        return value
    }*/

    fun contains(card: CardClass) = hand.contains(card)

    fun getCard(i:Int):Null{
        var handCard:Null = hand.get(i)
        return  handCard
    }

    fun getSize(): Int = hand.size

    override fun toString() = hand.joinToString()
}