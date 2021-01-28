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

        private val SECOND_ACTIVITY_REQUEST_CODE = 0
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bettler)

        val p1Id = intent.getIntExtra("idP1", -1)
        val p2Id = intent.getIntExtra("idP2", -1)
        val p1Pos =intent.getIntExtra("idPos1",-1)
        val p2Pos =intent.getIntExtra("idPos2",-1)

        val context=this
        var db =DBHandler(context)
        var data=db.readData()
        p1Name=data.get(p1Pos).playerName
        p2Name=data.get(p2Pos).playerName

        Log.d("Spieler:", p1Id.toString()+" "+p2Id.toString()+" name1: "+data.get(p1Pos).playerName+" name2: "+data.get(p2Pos).playerName)

        var game = BettlerClass()
        game.startGame(game)

        deck.shuffle()
        p1hand = HandClass(deck, "Bettler")
        p2hand = HandClass(deck, "Bettler")


        for (i in 0..p1hand.getSize() - 1) {
            Log.d("hand1:", p1hand.getCard(i).toString())
        }

        for (i in 0..p2hand.getSize() - 1) {
            Log.d("hand2:", p2hand.getCard(i).toString())
        }
        table = HandClass(deck, "Null")
        temp = HandClass(deck,"Null")

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
        
        val playerSign: TextView =findViewById(R.id.playerSign)!!
        val nuOfCards: TextView= findViewById(R.id.nuOfCards)!!


        setOpposition()
        playerSign.setText("")
        nuOfCards.setText(nuOfCardsPlayed.toString())
        


        cardViews = mutableListOf(dragView1, dragView2, dragView3, dragView4, dragView5, dragView6, dragView7, dragView8,dragView9,dragView10,dragView11,dragView12,dragView13,dragView14,dragView15,dragView16 )
        
        setStartingPlayer(p1hand)// bestimmt wer beginnt
        fillView()//erste bestückung der Views

        submit.setOnClickListener{
            var currentHand: HandClass = p2hand
            if(currentPlayer == 1) {
                currentHand = p1hand
            }
            if(temp.getSize()==0){
                Toast.makeText(this, "Bitte wähle Karten aus", Toast.LENGTH_SHORT).show()
            }
            else if(table.getSize()==0){
                playCard(currentHand)
            }
            else if(temp.getSize()== nuOfCardsPlayed){
                if(temp.getCard(0).getValueNumberBettler()>table.getCard(table.getSize()-1).getValueNumber()){
                    playCard(currentHand)
                }
                else{
                    Toast.makeText(this, "Die gewählten Karten müssen höher sein, siehe Anleitung", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Du musst genauso viele Karten legen wie dein Gegner", Toast.LENGTH_SHORT).show()
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
            table_card.setImageDrawable(null)//Todo: Durch platzhalter ersetzen
        }


        for(i in 0..15){
            cardViews[i].setOnClickListener{//logik für einen Spielzug
                Log.d("selected Card pos:", i.toString())
                var currentHand: HandClass = p2hand
                if(currentPlayer == 1) {
                    currentHand = p1hand
                }
                if(cardChoice){//Hier wird kartentausch geregelt
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
                    playerSign.setText("Arschloch: "+currentPlayerName+" beginnt")},1000)
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
                        Toast.makeText(this, "Nur Karten gleicher Wertigkeit können zusammen gelegt werden", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("result","ist da")
        deck = DeckClass(2)
        deck.shuffle()
        p1hand = HandClass(deck, "Bettler")
        p2hand = HandClass(deck, "Bettler")
        table = HandClass(deck, "Null")
        playerSign.setVisibility(View.VISIBLE)
        val intent = Intent(this, PopUpPlayerChangeBettlerActivity::class.java)
        intent.putExtra("playerName", currentPlayerName)
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
        }
        else{
            winner=currentPlayer
        }
        if(winner==1){
            winnerName= p1Name
        }
        else{
            winnerName= p2Name
        }
        val intent = Intent(this, PopUpEndGameBettlerActicity::class.java)
        intent.putExtra("playerName", winnerName)
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        Handler().postDelayed({
        playerSign.setText(winnerName +" ist könig! Drücken für neue Runde")},1000)//Das hier musss ausgelagert werden, bzw code oben
        playerSign.setVisibility(View.INVISIBLE)
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
        Handler().postDelayed({
        playerSign.setText("Schieben an "+ opName)},1000)
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
        if(currentPlayer==1){
            currentHand=p1hand
        }
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
        nuOfCards.setText(nuOfCardsPlayed.toString())
        }

    fun onClickPauseMenuButton(view: View) {
        val BackToPauseButton = Intent(this, PauseMenuActivity::class.java)
        startActivity(BackToPauseButton)
    }

    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }

}