package com.example.cardsagainstyourliver

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bettler.*

class BettlerActivity : AppCompatActivity() {
    companion object {
        var currentPlayer:Int=0
        var winner:Int=0
        var opposition:Int=0
        var kartenWahl:Boolean=false
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

        Log.d("Spieler:", p1Id.toString()+" "+p2Id.toString()+" name1: "+data.get(p1Pos).playerName+" name2: "+data.get(p2Pos).playerName)

        var game = BettlerClass()
        game.startGame(game) //was passiert hier?
        var deck = DeckClass(2)
        deck.shuffle()
        var p1hand = HandClass(deck, "Bettler")
        var p2hand = HandClass(deck, "Bettler")


        for (i in 0..p1hand.getSize() - 1) {
            Log.d("hand1:", p1hand.getCard(i).toString())
        }

        for (i in 0..p2hand.getSize() - 1) {
            Log.d("hand2:", p2hand.getCard(i).toString())
        }
        var table = HandClass(deck, "Null")

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

        setOpposition()
        playerSign.setText("Schiebe an "+opposition.toString())
        


        var cardViews:MutableList<ImageView> = mutableListOf(dragView1, dragView2, dragView3, dragView4, dragView5, dragView6, dragView7, dragView8,dragView9,dragView10,dragView11,dragView12,dragView13,dragView14,dragView15,dragView16 )
        
        setStartingPlayer(p1hand)// bestimmt wer beginnt
        fillView(cardViews,p1hand,p2hand)//erste bestückung der Views

        playerSign.setOnClickListener{
            if(playerSign.getText().toString().equals(winner.toString()+" ist könig! Drücken für neue Runde")){
                deck = DeckClass(2)
                deck.shuffle()
                 p1hand = HandClass(deck, "Bettler")
                 p2hand = HandClass(deck, "Bettler")
                 table = HandClass(deck, "Null")//Todo: Kartentausch
                 fillView(cardViews,p1hand,p2hand)
            }
            else {
                changePlayer(playerSign)
                table.clear()
                table_card.setImageDrawable(null)
                fillView(cardViews, p1hand, p2hand)
            }
        }

        for(i in 0..15){
            cardViews[i].setOnClickListener{//logik für einen Spielzug
                Log.d("selected Card pos:", i.toString())
                var currentHand: HandClass = p2hand
                if(currentPlayer == 1) {
                    currentHand = p1hand
                }
                if(table.getSize()<1){
                    Log.d("state:","tisch leer")
                    playCard(currentHand,table,i,cardViews,p1hand,p2hand)
                }
                else{
                    if(checkIfLegal(currentHand.getCard(i),table.getCard(table.getSize()-1))) {
                        Log.d("state:","legaler move")
                        playCard(currentHand,table,i,cardViews,p1hand,p2hand)
                    }
                    else{
                        Log.d("state:","illegaler move")
                        Toast.makeText(this, "Die Karte muss Höher sein, siehe Anleitung", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        for(i in 0..15){
            cardViews[i].setOnLongClickListener {
                val clipText = "Player Card Left"
                val item = ClipData.Item(clipText)
                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                val data = ClipData(clipText, mimeTypes, item)

                val dragShadowBuilder = View.DragShadowBuilder(it)
                it.startDragAndDrop(data, dragShadowBuilder, it, 0)

                it.visibility = View.INVISIBLE
                true

            }
        }

        val dragListener = View.OnDragListener { view, event ->
            var currentHand: HandClass = p2hand
            if(currentPlayer == 1) {
                currentHand = p1hand
            }
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> true
                DragEvent.ACTION_DRAG_EXITED -> {

                    var xPos: Float = event.getX();
                    var yPos: Float = event.getY();


                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {
                    val item = event.clipData.getItemAt(0)
                    val dragData = item.text

                    Toast.makeText(this, dragData, Toast.LENGTH_SHORT).show()
                    //var test : ImageView = findViewById(R.id.table_card_01)
                    //test.setImageResource(R.drawable.card_caro_10)


                    view.invalidate()

                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    owner.removeView(v)
                    val destination = view as LinearLayout//view in welchem gedropt wird
                    destination.addView(v)
                    Log.d("destination",destination.toString())
                    v.visibility = View.VISIBLE
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    val view = event.localState as View
                    view.invalidate()
                    view.visibility =
                        View.VISIBLE //damit die Karte wenn sie falschplaziert wurde wieder sichtabr ist
                    true
                }
                else -> true
            }
        }

        player_cards.setOnDragListener(dragListener)
        player_cards2.setOnDragListener(dragListener)
        plcaholder_cards.setOnDragListener(dragListener)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun playCard(currentHand:HandClass, table:HandClass, position:Int, cardViews: MutableList<ImageView>, p1hand: HandClass, p2hand: HandClass){
        table_card.setImageDrawable(getDrawable(currentHand.getPic(currentHand.getCard(position))))
        table.add(currentHand.getCard(position))
        currentHand.delete(currentHand.getCard(position))
        Log.d("neue Hand: größe",currentHand.getSize().toString())
        if(currentHand.getSize()==0){
            Log.d("gameState","letzte Karte gelegt")
            endGame(table.getCard(table.getSize()-1),playerSign)
        }
        else {
            changePlayer(playerSign)
            fillView(cardViews, p1hand, p2hand)
        }
    }

    fun checkIfLegal(playedCard:Null, tableCard:Null):Boolean{
        Log.d("tischkarte:",tableCard.toString())
        Log.d("LegalCheck:",playedCard.getValueNumberBettler().toString()+"-playedCard "+tableCard.getValueNumberBettler().toString()+"-tableCard")
        return playedCard.getValueNumberBettler()>tableCard.getValueNumberBettler()
    }

    fun endGame(lastCard:Null, playerSign:TextView){
        Log.d("in Endgame","in Endgame")

        if(lastCard.getValueNumberBettler()==14){
            winner= opposition
        }
        else{
            winner=currentPlayer
        }
        playerSign.setText(winner.toString()+" ist könig! Drücken für neue Runde")
    }

    fun setOpposition(){
        opposition=1
        if(currentPlayer==1){
            opposition=2
        }
    }

    fun changePlayer(playerSign: TextView){
        if(currentPlayer==1){
            currentPlayer=2
            playerSign.setText("Schieben an Player 1")
        }
        else{
            currentPlayer=1
            playerSign.setText("Schieben an Player 2")
        }
        setOpposition()
    }

    fun setStartingPlayer(p1hand:HandClass) {
        currentPlayer=2
        for (i in 0..p1hand.getSize() - 1) {
            if (p1hand.getCard(i).toString() == "Karte: KARO SIEBEN") {
                Log.d("beginn:", "p1")
                currentPlayer = 1
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun fillView(cardViews:MutableList<ImageView>, p1hand:HandClass, p2hand:HandClass) {
        var currentHand: HandClass = p2hand//hier scheint current hand nicht ganz zu klappen beim schieben
        if(currentPlayer==1){
            currentHand=p1hand
        }
        Log.d("gerade:", currentHand.getSize().toString())
            for (i in 0..currentHand.getSize()-1) {
                cardViews[i].setImageDrawable(getDrawable(currentHand.getPic(currentHand.getCard(i))))
                cardViews[i].setVisibility(View.VISIBLE)
            }
            for (i in currentHand.getSize()..15){
                cardViews[i].setVisibility(View.INVISIBLE)
            }
        }

    fun onClickStartGame(view: View) {
        setContentView(R.layout.activity_bettler)
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