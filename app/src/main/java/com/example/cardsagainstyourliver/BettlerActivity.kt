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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bettler.*

class BettlerActivity : AppCompatActivity() {


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
        for (i in 0..p1hand.getSize()-1) {
            Log.d("hand1:", p1hand.getCard(i).toString())
        }
        for (i in 0..p2hand.getSize()-1) {
            Log.d("hand2:", p2hand.getCard(i).toString())
        }
        var table= HandClass(deck, "Null")

        val dragView1: ImageView = findViewById(R.id.card_01)!!
        val dragView2: ImageView = findViewById(R.id.card_02)!!
        val dragView3: ImageView = findViewById(R.id.card_03)!!
        val dragView4: ImageView = findViewById(R.id.card_04)!!
        val dragView5: ImageView = findViewById(R.id.card_05)!!
        val dragView6: ImageView = findViewById(R.id.card_06)!!

        dragView1.setImageDrawable(getDrawable(p1hand.getPic(p1hand.getCard(0))))
        dragView2.setImageDrawable(getDrawable(p1hand.getPic(p1hand.getCard(1))))
        dragView3.setImageDrawable(getDrawable(p1hand.getPic(p1hand.getCard(2))))
        dragView4.setImageDrawable(getDrawable(p1hand.getPic(p1hand.getCard(3))))
        dragView5.setImageDrawable(getDrawable(p1hand.getPic(p1hand.getCard(4))))
        dragView6.setImageDrawable(getDrawable(p1hand.getPic(p1hand.getCard(5))))
        //Hier fehlen weitere 10 karten, da layout noch nicht angepasst
        //Sollte zum anpassen bei weiteren zügen in sinnvolle funktion gesteckt werden.






        player_cards.setOnDragListener(dragListener)
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
                view.invalidate()
                true
            }
            else -> true
        }
    }
}