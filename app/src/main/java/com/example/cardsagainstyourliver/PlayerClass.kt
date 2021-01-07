package com.example.cardsagainstyourliver



class PlayerClass{
    var id:Int=0
    var playerName:String=""
    var size:Int=0
    var weight:Int=0
    var age:Int=0
    var gender:Int=0
    var drink:Int=0
    var gamesWon:Int=0
    var gamesLost:Int=0
    var alcoholLevel:Int=0

    constructor(playerName:String, size:Int, weight:Int, age:Int, gender:Int, drink:Int, gamesWon:Int, gamesLost:Int, alcoholLevel:Int){
        this.playerName=playerName
        this.size=size
        this.weight=weight
        this.age=age
        this.gender=gender
        this.drink=drink
        this.gamesWon=gamesWon
        this.gamesLost=gamesLost
        this.alcoholLevel=alcoholLevel
    }

    constructor(){}
    //val handCardDeck = DeckClass()
    /*var zwischen : MutableList<KarteClass> = mutableListOf((handKartenDeck.karteZiehen())
    var hand : HandClass */


    //fun numCards() = hand.size()


    fun clear() {
        //  hand.clear()
        // playerStats.delete()
    }
}
