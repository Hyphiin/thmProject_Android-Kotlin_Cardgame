package com.example.cardsagainstyourliver

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bettler.*

class BettlerActivity : AppCompatActivity() {

    var p1Pos = 0
    var p2Pos = 0
    var player1id = 0
    var player2id = 0
    var player1age = 0
    var player2age = 0
    var player1size = 0
    var player2size = 0
    var player1drink = 0
    var player2drink = 0
    var player1weight = 0
    var player2weight = 0
    var player1gender = 0
    var player2gender = 0
    var player1permille = 0
    var player2permille = 0
    var player1ml = 0.0
    var player2ml = 0.0
    var timerStart = System.currentTimeMillis()
    var timerEnd= System.currentTimeMillis()

    var db = DBHandler(this)

    companion object {
        var currentPlayer:Int=0
        var winner:Int=0
        var opposition:Int=0
        var cardChoice:Boolean=false
        var nuOfCardsPlayed=0
        var p1Name=""
        var p2Name=""
        var currentPlayerName=""
        var deck = DeckClass(2)
        var p1hand:HandClass=HandClass(deck, "null")
        var p2hand:HandClass=HandClass(deck, "null")
        var table = HandClass(deck, "Null")
        var temp = HandClass(deck,"Null")
        var cardViews:MutableList<ImageView> = mutableListOf()
        var opCardViews:MutableList<ImageView> = mutableListOf()
        private val SECOND_ACTIVITY_REQUEST_CODE = 0
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bettler)
        Log.d("debug","in create")
        val p1Id = intent.getIntExtra("idP1", -1)
        val p2Id = intent.getIntExtra("idP2", -1)
        p1Pos =intent.getIntExtra("idPos1",-1)
        p2Pos =intent.getIntExtra("idPos2",-1)

        val context=this
        db =DBHandler(context)
        var data=db.readData()
        p1Name=data.get(p1Pos).playerName
        p2Name=data.get(p2Pos).playerName

        Log.d("Spieler:", p1Id.toString()+" "+p2Id.toString()+" name1: "+data.get(p1Pos).playerName+" name2: "+data.get(p2Pos).playerName)
        deck = DeckClass(2)
        deck.shuffle()
        p1hand = HandClass(deck, "Bettler")
        p2hand = HandClass(deck, "Bettler")


        player1id = data.get(p1Pos).id
        player2id = data.get(p2Pos).id
        player1age = data.get(p1Pos).age
        player2age = data.get(p2Pos).age
        player1size = data.get(p1Pos).size
        player2size = data.get(p2Pos).size
        player1drink = data.get(p1Pos).drink
        player2drink = data.get(p2Pos).drink
        player1weight = data.get(p1Pos).weight
        player2weight = data.get(p2Pos).weight
        player1gender = data.get(p1Pos).gender
        player2gender = data.get(p2Pos).gender
        player1permille = data.get(p1Pos).alcoholLevel
        player2permille = data.get(p2Pos).alcoholLevel


        timerStart = System.currentTimeMillis()


        for (i in 0..p1hand.getSize() - 1) {
            Log.d("hand1:", p1hand.getCard(i).toString())
        }

        for (i in 0..p2hand.getSize() - 1) {
            Log.d("hand2:", p2hand.getCard(i).toString())
        }
        table = HandClass(deck, "Null")
        temp = HandClass(deck,"Null")

        nuOfCards.setText("0")

        val dragView1: ImageView = findViewById(R.id.card1)!!
        val dragView2: ImageView = findViewById(R.id.card2)!!
        val dragView3: ImageView = findViewById(R.id.card3)!!
        val dragView4: ImageView = findViewById(R.id.card4)!!
        val dragView5: ImageView = findViewById(R.id.card5)!!
        val dragView6: ImageView = findViewById(R.id.card6)!!
        val dragView7: ImageView = findViewById(R.id.card7)!!
        val dragView8: ImageView = findViewById(R.id.card8)!!
        val dragView9: ImageView = findViewById(R.id.card9)!!
        val dragView10: ImageView = findViewById(R.id.card10)!!
        val dragView11: ImageView = findViewById(R.id.card11)!!
        val dragView12: ImageView = findViewById(R.id.card12)!!
        val dragView13: ImageView = findViewById(R.id.card13)!!
        val dragView14: ImageView = findViewById(R.id.card14)!!
        val dragView15: ImageView = findViewById(R.id.card15)!!
        val dragView16: ImageView = findViewById(R.id.card16)!!

        val gegnerView1: ImageView = findViewById(R.id.card9_ki1)!!
        val gegnerView2: ImageView = findViewById(R.id.card9_ki2)!!
        val gegnerView3: ImageView = findViewById(R.id.card9_ki)!!
        val gegnerView4: ImageView = findViewById(R.id.card9_ki4)!!
        val gegnerView5: ImageView = findViewById(R.id.card9_ki5)!!
        val gegnerView6: ImageView = findViewById(R.id.card9_ki6)!!
        val gegnerView7: ImageView = findViewById(R.id.card9_ki7)!!
        val gegnerView8: ImageView = findViewById(R.id.card9_ki8)!!
        val gegnerView9: ImageView = findViewById(R.id.card9_ki9)!!
        val gegnerView10: ImageView = findViewById(R.id.card9_ki10)!!
        val gegnerView11: ImageView = findViewById(R.id.card9_ki11)!!
        val gegnerView12: ImageView = findViewById(R.id.card9_ki12)!!
        val gegnerView13: ImageView = findViewById(R.id.card9_ki13)!!
        val gegnerView14: ImageView = findViewById(R.id.card9_ki14)!!
        val gegnerView15: ImageView = findViewById(R.id.card9_ki15)!!
        val gegnerView16: ImageView = findViewById(R.id.card9_ki16)!!
        
        val playerSign: TextView =findViewById(R.id.playerSign)!!
        val nuOfCards: TextView= findViewById(R.id.nuOfCards)!!


        setOpposition()
        playerSign.setText("")
        nuOfCards.setText(nuOfCardsPlayed.toString())
        


        cardViews = mutableListOf(dragView1, dragView2, dragView3, dragView4, dragView5, dragView6, dragView7, dragView8,dragView9,dragView10,dragView11,dragView12,dragView13,dragView14,dragView15,dragView16 )
        opCardViews= mutableListOf(gegnerView1,gegnerView2,gegnerView3,gegnerView4,gegnerView5,gegnerView6,gegnerView7,gegnerView8,gegnerView9,gegnerView10,gegnerView11,gegnerView12,gegnerView13,gegnerView14,gegnerView15,gegnerView16)

        setStartingPlayer(p1hand)// bestimmt wer beginnt
        fillView()//erste bestückung der Views

        submit.setOnClickListener{
            var currentHand: HandClass = p2hand
            if(currentPlayer == 1) {
                currentHand = p1hand
            }
            if(temp.getSize()==0){
                var chooseCard = getString(R.string.choose_card)
                Toast.makeText(this, "$chooseCard", Toast.LENGTH_SHORT).show()
            }
            else if(table.getSize()==0){
                playCard(currentHand)
            }
            else if(temp.getSize()== nuOfCardsPlayed){
                if(temp.getCard(0).getValueNumberBettler()>table.getCard(table.getSize()-1).getValueNumberBettler()){
                    playCard(currentHand)
                }
                else{
                    var higherCards = getString(R.string.higher_cards)
                    Toast.makeText(this, "$higherCards", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                var sameCards = getString(R.string.same_cards)
                Toast.makeText(this, "$sameCards", Toast.LENGTH_SHORT).show()
            }
        }

        playerSign.setOnClickListener{
            if(playerSign.getText().toString().equals(winner.toString()+" ist könig! Drücken für neue Runde")){

            }
            else {
                nuOfCardsPlayed=0
                changePlayer(playerSign)
                temp.clear()
                table.clear()
                Handler().postDelayed({
                fillView()},1000)
            }
            table_card.setImageResource(R.drawable.card_platzhalter)
        }


        for(i in 0..15){
            cardViews[i].setOnClickListener{//logik für einen Spielzug
                Log.d("selected Card pos:", i.toString())
                var currentHand: HandClass = p2hand
                if(currentPlayer == 1) {
                    currentHand = p1hand
                }
                if(cardChoice){//Hier wird kartentausch
                    if(winner==1){
                        var bestCard=p2hand.getHighestCard(p2hand)
                        p1hand.add(bestCard)
                        p2hand.delete(bestCard)
                        p2hand.add(p1hand.getCard(i))
                        p1hand.delete(p1hand.getCard(i))
                    }
                    else{
                        var bestCard=p1hand.getHighestCard(p1hand)
                        p2hand.add(bestCard)
                        p1hand.delete(bestCard)
                        p1hand.add(p2hand.getCard(i))
                        p2hand.delete(p2hand.getCard(i))
                    }
                    changePlayer(playerSign)
                    Handler().postDelayed({
                    fillView()
                        playerSign.setVisibility(View.VISIBLE)
                        var startString = getString(R.string.start)
                    playerSign.setText("Arschloch: "+currentPlayerName+ startString)},1000)
                    cardChoice=false
                }
                else if(temp.getSize()<1){
                    Log.d("state:","tisch leer")
                    selectCard(currentHand,i)
                }
                else{
                    if(currentHand.getCard(i).getValue()==temp.getCard(temp.getSize()-1).getValue()) {
                        Log.d("state:","legaler move")
                        selectCard(currentHand,i)
                    }
                    else{
                        Log.d("state:","illegaler move")
                        var sameCards2 = getString(R.string.same_cards2)
                        Toast.makeText(this, "$sameCards2", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun calculatePermille() {

        var PRechner = PerMilleCalculator()
        var percentage = 0.0
        var ml = 0.0
        val deltaHours = (timerEnd/(60 * 60 * 1000 ) - timerStart/(60 * 60 * 1000 )).toInt()

        when (player1drink) {
            0 -> {
                percentage = 5.0
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            1 -> {
                percentage = 12.0
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            2 -> {
                percentage = 10.0
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            3 -> {
                percentage = 65.0
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            4 -> {
                percentage = 37.5
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            5 -> {
                percentage = 40.0
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            6 -> {
                percentage = 38.0
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            7 -> {
                percentage = 37.5
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            8 -> {
                percentage = 12.8
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            9 -> {
                percentage = 4.0
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            10 -> {
                percentage = 5.5
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            11 -> {
                percentage = 6.9
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
        }
        when (player2drink) {
            0 -> {
                percentage = 5.0
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            1 -> {
                percentage = 12.0
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            2 -> {
                percentage = 10.0
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            3 -> {
                percentage = 65.0
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            4 -> {
                percentage = 37.5
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            5 -> {
                percentage = 40.0
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            6 -> {
                percentage = 38.0
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            7 -> {
                percentage = 37.5
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            8 -> {
                percentage = 12.8
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            9 -> {
                percentage = 4.0
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            10 -> {
                percentage = 5.5
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
            11 -> {
                percentage = 6.9
                ml = player2ml
                player2permille = (PRechner.permille(
                    player2weight,
                    player2gender,
                    percentage,
                    ml,
                    deltaHours
                ) * 100).toInt()
            }
        }
    }

    fun checkDrink(v : Int): Double {
        var ml = 0.0

        if(v == 1){
            when (player1drink) {
                0 -> {
                    ml = 500.0
                }
                1 -> {
                    ml = 300.0
                }
                2 -> {
                    ml = 500.0
                }
                3 -> {
                    ml = 200.0
                }
                4 -> {
                    ml = 100.0
                }
                5 -> {
                    ml = 100.0
                }
                6 -> {
                    ml = 100.0
                }
                7 -> {
                    ml = 100.0
                }
                8 -> {
                    ml = 500.0
                }
                9 -> {
                    ml = 500.0
                }
                10 -> {
                    ml = 500.0
                }
                11 -> {
                    ml = 500.0
                }
            }}
        else{
            when (player2drink) {
                0 -> {
                    ml = 500.0
                }
                1 -> {
                    ml = 300.0
                }
                2 -> {
                    ml = 500.0
                }
                3 -> {
                    ml = 200.0
                }
                4 -> {
                    ml = 100.0
                }
                5 -> {
                    ml = 100.0
                }
                6 -> {
                    ml = 100.0
                }
                7 -> {
                    ml = 100.0
                }
                8 -> {
                    ml = 500.0
                }
                9 -> {
                    ml = 500.0
                }
                10 -> {
                    ml = 500.0
                }
                11 -> {
                    ml = 500.0
                }
            }}
        return ml
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("result","ist da")
        deck = DeckClass(2)
        deck.shuffle()
        p1hand = HandClass(deck, "Bettler")
        p2hand = HandClass(deck, "Bettler")
        table = HandClass(deck, "Null")
        val intent = Intent(this, PopUpPlayerChangeBettlerActivity::class.java)
        var winnerName= p2Name
        if(winner==1){
            winnerName= p1Name
        }
        intent.putExtra("playerName", winnerName)
        intent.putExtra("ende", true)
        startActivity(intent)
        cardChoice=true
        temp.clear()
        nuOfCardsPlayed=0
        Handler().postDelayed({
        fillView()},1000)
    }

    fun selectCard(currentHand: HandClass, i:Int){
        var deleted=false
        var posToDelete:Int=-1
        for(n in 0..temp.getSize()-1){
            if(temp.getCard(n).getSign()==currentHand.getCard(i).getSign()){
                posToDelete=n
                cardViews[i].setBackgroundColor(Color.WHITE)
                deleted=true
            }
        }
        if(posToDelete>-1){
            temp.deleteAt(posToDelete)
        }
        if(deleted!=true) {
            cardViews[i].setBackgroundColor(Color.parseColor("#007D7C"))
            temp.add(currentHand.getCard(i))
        }
        Log.d("added",temp.toString())
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun playCard(currentHand:HandClass){
        nuOfCardsPlayed=temp.getSize()
        Log.d("temp", (temp.getSize()).toString())
        Log.d("current", (currentHand.getSize()).toString())
        table_card.setImageDrawable(getDrawable(temp.getPic(temp.getCard(temp.getSize()-1))))
        for(i in 0..temp.getSize()-1){
           table.add(temp.getCard(i))
            currentHand.delete(temp.getCard(i))
        }
        temp.clear()
        Log.d("neue Hand: größe",currentHand.getSize().toString())
        if(currentHand.getSize()==0){
            Log.d("gameState","letzte Karte gelegt")
            endGame(table.getCard(table.getSize()-1),playerSign)
        }
        else {
            changePlayer(playerSign)
            Handler().postDelayed({
            fillView()},1000)
            nuOfCards.setText(nuOfCardsPlayed.toString())
        }
    }

    fun checkIfLegal(playedCard:Null, tableCard:Null):Boolean{
        Log.d("tischkarte:",tableCard.toString())
        Log.d("LegalCheck:",playedCard.getValueNumberBettler().toString()+"-playedCard "+tableCard.getValueNumberBettler().toString()+"-tableCard")
        return playedCard.getValueNumberBettler()>tableCard.getValueNumberBettler()
    }

    fun endGame(lastCard:Null, playerSign:TextView){
        Log.d("in Endgame","in Endgame")
        var winnerName=""
        if(lastCard.getValueNumberBettler()==14){
            winner= opposition
            currentPlayer=opposition
        }
        else{
            winner=currentPlayer
        }
        if(winner==1){
            winnerName= p1Name
            player2ml += checkDrink(2)
        }
        else{
            winnerName= p2Name
            player1ml += checkDrink(1)
        }

        calculatePermille()

        db.updateData(player1id, p1Name, player1age, player1size, player1weight, player1gender, player1drink, player1permille)
        db.updateData(player2id, p2Name, player2age, player2size, player2weight, player2gender, player2drink, player2permille)

        val intent = Intent(this, PopUpEndGameBettlerActicity::class.java)
        intent.putExtra("playerName", winnerName)
        intent.putExtra("p1Pos", p1Pos)
        intent.putExtra("p2Pos", p2Pos)
        intent.putExtra("player1Name", p1Name)
        intent.putExtra("player2Name", p2Name)
        intent.putExtra("p1ID", player1id)
        intent.putExtra("p2ID", player2id)
        intent.putExtra("player1permille",player1permille)
        intent.putExtra("player2permille",player2permille)
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)

        playerSign.setVisibility(View.INVISIBLE)

        table_card.setImageResource(R.drawable.card_platzhalter)
        nuOfCardsPlayed=0

    }

    fun setOpposition(){
        opposition=1
        if(currentPlayer==1){
            opposition=2
        }
    }

    fun changePlayer(playerSign: TextView){
        var opName=""
        if(currentPlayer==1){
            currentPlayer=2
            currentPlayerName=p2Name
            opName= p1Name
        }
        else{
            currentPlayer=1
            currentPlayerName=p1Name
            opName=p2Name
        }
        setOpposition()
            Log.d("debug",opName)
        Handler().postDelayed({

        var pushString = getString(R.string.pushBettler)
        playerSign.setText("$pushString $opName")},1000)
        val intent = Intent(this, PopUpPlayerChangeBettlerActivity::class.java)
            intent.putExtra("playerName", currentPlayerName)
            startActivity(intent)
    }

    fun setStartingPlayer(p1hand:HandClass) {
        currentPlayer=2
        currentPlayerName=p2Name
        for (i in 0..p1hand.getSize() - 1) {
            if (p1hand.getCard(i).toString() == "Karte: KARO SIEBEN") {
                Log.d("beginn:", "p1")
                currentPlayer = 1
                currentPlayerName=p1Name
            }
        }
        val intent = Intent(this, PopUpPlayerChangeBettlerActivity::class.java)
        intent.putExtra("playerName", currentPlayerName)
        intent.putExtra("beginn", true)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun fillView() {
        var currentHand: HandClass = p2hand
        var opSize= p1hand.getSize()
        if(currentPlayer==1){
            currentHand=p1hand
            opSize= p2hand.getSize()
        }
        Log.d("debug",opSize.toString())
        Log.d("gerade:", currentHand.getSize().toString())
            for (i in 0..currentHand.getSize()-1) {

                    cardViews[i].setImageDrawable(
                        getDrawable(
                            currentHand.getPic(
                                currentHand.getCard(
                                    i
                                )
                            )
                        )
                    )
                    cardViews[i].setVisibility(View.VISIBLE)
                    cardViews[i].setBackgroundColor(Color.WHITE)

            }
            for (i in currentHand.getSize()..15){
                    cardViews[i].setVisibility(View.INVISIBLE)
            }
            for(i in 0..opSize-1){
                opCardViews[i].setVisibility(View.VISIBLE)
            }
            for(i in (opSize)..15){
                opCardViews[i].setVisibility(View.INVISIBLE)
            }

        nuOfCards.setText(nuOfCardsPlayed.toString())
        }

    fun onClickPauseMenuButton(view: View) {
        val intent = Intent(this, PauseMenuActivity::class.java)
        startActivity(intent)
    }

    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }

}


