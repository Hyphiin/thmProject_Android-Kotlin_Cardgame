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

        var firstHand:HandClass
        for(i in 0..p1hand.getSize() - 1) {
            if (p1hand.getCard(i).toString() == "Karte: KARO SIEBEN") {
                 firstHand=p1hand
            }
            else{
                 firstHand=p2hand
            }
            dragView1.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(0))))
            dragView2.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(1))))
            dragView3.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(2))))
            dragView4.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(3))))
            dragView5.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(4))))
            dragView6.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(5))))
            dragView7.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(6))))
            dragView8.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(7))))
            dragView9.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(8))))
            dragView10.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(9))))
            dragView11.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(10))))
            dragView12.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(11))))
            dragView13.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(12))))
            dragView14.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(13))))
            dragView15.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(14))))
            dragView16.setImageDrawable(getDrawable(firstHand.getPic(firstHand.getCard(15))))
        }

        //Sollte zum anpassen bei weiteren zügen in sinnvolle funktion gesteckt werden.








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