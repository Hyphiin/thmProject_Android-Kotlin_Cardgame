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
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bettler)

        val p1Id = intent.getIntExtra("idP1", -1)
        val p2Id = intent.getIntExtra("idP2", -1)

        Log.d("Spieler:", p1Id.toString()+" "+p2Id.toString())

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

        val dragView1: ImageView = findViewById(R.id.card_1)!!
        val dragView2: ImageView = findViewById(R.id.card_2)!!
        val dragView3: ImageView = findViewById(R.id.card_3)!!
        val dragView4: ImageView = findViewById(R.id.card_4)!!
        val dragView5: ImageView = findViewById(R.id.card_5)!!
        val dragView6: ImageView = findViewById(R.id.card_6)!!
        val dragView7: ImageView = findViewById(R.id.card_7)!!
        val dragView8: ImageView = findViewById(R.id.card_8)!!
        val dragView9: ImageView = findViewById(R.id.card_9)!!
        val dragView10: ImageView = findViewById(R.id.card_10)!!
        val dragView11: ImageView = findViewById(R.id.card_11)!!
        val dragView12: ImageView = findViewById(R.id.card_12)!!
        val dragView13: ImageView = findViewById(R.id.card_13)!!
        val dragView14: ImageView = findViewById(R.id.card_14)!!
        val dragView15: ImageView = findViewById(R.id.card_15)!!
        val dragView16: ImageView = findViewById(R.id.card_16)!!
        
        val playerSign: TextView =findViewById(R.id.playerSign)!!
        var opposition=1
        if(currentPlayer==1){
             opposition=2
        }
        playerSign.setText("Schiebe an Player"+opposition.toString())
        


        var cardViews:MutableList<ImageView> = mutableListOf(dragView1, dragView2, dragView3, dragView4, dragView5, dragView6, dragView7, dragView8,dragView9,dragView10,dragView11,dragView12,dragView13,dragView14,dragView15,dragView16 )
        
        setStartingPlayer(p1hand)// bestimmt wer beginnt
        fillView(cardViews,p1hand,p2hand)//bestückt views mit karten/macht gespielte karten unsichtbar

        playerSign.setOnClickListener{
            changePlayer(playerSign)
            fillView(cardViews,p1hand,p2hand)
        }

        for(i in 0..15){
            cardViews[i].setOnClickListener{//logik für einen Spielzug
                Log.d("selected Card pos:", i.toString())
                var currentHand: HandClass = p2hand
                if(currentPlayer == 1) {
                    currentHand = p1hand
                }
                //currentHand.getCard(i) sinnlos?
                if(table.getSize()<1){
                    Log.d("state:","tisch leer")
                    table_card.setImageDrawable(getDrawable(currentHand.getPic(currentHand.getCard(i))))
                    table.add(currentHand.getCard(i))
                    currentHand.delete(currentHand.getCard(i))
                    Log.d("neue Hand: größe",currentHand.getSize().toString())
                    changePlayer(playerSign)
                    fillView(cardViews,p1hand,p2hand)
                }
                else{
                    if(checkIfLegal(currentHand.getCard(i),table.getCard(table.getSize()-1))) {
                        Log.d("state:","legaler move")
                        table_card.setImageDrawable(getDrawable(currentHand.getPic(currentHand.getCard(i))))
                        table.add(currentHand.getCard(i))
                        currentHand.delete(currentHand.getCard(i))
                        Log.d("neue Hand: größe",currentHand.getSize().toString())
                        changePlayer(playerSign)
                        fillView(cardViews,p1hand,p2hand)
                    }
                    else{
                        Log.d("state:","illegaler move")
                        Toast.makeText(this, "Die Karte muss Höher sein, siehe Anleitung", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }








        player_cards.setOnDragListener(dragListener)
        player_cards2.setOnDragListener(dragListener)
        plcaholder_cards.setOnDragListener(dragListener)

        // first drag and drop card
        dragView1.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        // first drag and drop card
        dragView2.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item2 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item2)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }
        dragView3.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item3 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item3)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        dragView4.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item4 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item4)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        dragView5.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item5 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item5)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        dragView6.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item6 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item6)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        dragView7.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item7 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item7)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        dragView8.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item8 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item8)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        dragView9.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item9 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item9)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        dragView10.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item10 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item10)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }
        dragView11.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item11 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item11)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }
        dragView12.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item12 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item12)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }
        dragView13.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item13 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item13)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }
        dragView14.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item14 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item14)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }
        dragView15.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item15 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item15)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        dragView16.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item16 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item16)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }
    }

    fun checkIfLegal(playedCard:Null, tableCard:Null):Boolean{
        Log.d("tischkarte:",tableCard.toString())
        Log.d("LegalCheck:",playedCard.getValueNumberBettler().toString()+"-playedCard "+tableCard.getValueNumberBettler().toString()+"-tableCard")
        return playedCard.getValueNumberBettler()>tableCard.getValueNumberBettler()
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
        Log.d("gerade:", currentHand.getSize().toString())
        if(currentPlayer==1){
            currentHand=p1hand
        }
            for (i in 0..currentHand.getSize()-1) {
                cardViews[i].setImageDrawable(getDrawable(currentHand.getPic(currentHand.getCard(i))))
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


    val dragListener = View.OnDragListener { view, event ->
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
                val destination = view as LinearLayout
                destination.addView(v)

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
}