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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_schwimmen.*


class SchwimmenActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schwimmen)

        val p1Id = intent.getIntExtra("idP1", -1)
        val p2Id = intent.getIntExtra("idP2", -1)

        Log.d("Spieler:", p1Id.toString()+" "+p2Id.toString())

        val deck = DeckClass(2)
        deck.shuffle()
        val hand1 = HandClass(deck, "Schwimmen")

        val dragView1: ImageView = findViewById(R.id.player_card_01)!!
        val dragView2: ImageView = findViewById(R.id.player_card_02)!!
        val dragView3: ImageView = findViewById(R.id.player_card_03)!!

        val dragView01: ImageView = findViewById(R.id.table_card_01)!!
        val dragView02: ImageView = findViewById(R.id.table_card_02)!!
        val dragView03: ImageView = findViewById(R.id.table_card_03)!!





        table_left.setOnDragListener(dragListener)
        table_middle.setOnDragListener(dragListener)
        table_right.setOnDragListener(dragListener)
        //tableCard3.setOnDragListener(dragListener)

        player_left.setOnDragListener(dragListener)
        player_middle.setOnDragListener(dragListener)
        player_right.setOnDragListener(dragListener)


        // first drag and drop card
        dragView1.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE // damit die karte während dem drag unsichtbar wird
            true

        }

        //new drag and drop card
        dragView2.setOnLongClickListener {
            val clipText = "Player Card Middle"
            val item2 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item2)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        //new drag and drop card
        dragView3.setOnLongClickListener {
            val clipText = "Player Card Right"
            val item3 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item3)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }


        dragView01.setOnLongClickListener {
            val clipText = "Table Card Left"
            val item01 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item01)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }
        dragView02.setOnLongClickListener {
            val clipText = "Table Card Middle"
            val item02 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item02)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }

        dragView03.setOnLongClickListener {
            val clipText = "Table Card Right"
            val item03 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item03)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true

        }
    }


    fun onClickPauseMenuButton(view: View) {
        val PauseMenuButton = Intent(this, PauseMenuActivity::class.java)
        startActivity(PauseMenuButton)
    }

    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }

    fun onClickSwapCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Tauschen", Toast.LENGTH_LONG)
        toast.show()
    }

    fun onClickKnockCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Klopfen", Toast.LENGTH_LONG)
        toast.show()
    }

    fun onClickShoveCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Schieben", Toast.LENGTH_LONG)
        toast.show()
    }

    val dragListener = View.OnDragListener { target, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED -> {

                target.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> {

                var xPos: Float = event.getX();
                var yPos: Float = event.getY();

                var xPos2: String? = event.getX().toString();
                var yPos2: String? = event.getY().toString();

                Toast.makeText(this, xPos2, Toast.LENGTH_SHORT).show()
                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                target.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                val dragData = item.text

                Toast.makeText(this, dragData, Toast.LENGTH_SHORT).show()


                val dragged = event.localState as ImageView


                val oldOwner = dragged.parent as ViewGroup
                val newOwner = target.parent as ViewGroup

                val draggedPosition = oldOwner.indexOfChild(dragged)
                val targetPosition = oldOwner.indexOfChild(dragged)


                oldOwner.removeView(dragged)
                newOwner.addView(dragged, targetPosition)

                newOwner.removeView(target)
                oldOwner.addView(target, draggedPosition)

                target.invalidate()

                ///val v = event.localState as View

                /* ändert image bei ziel
                val target : ImageView = findViewById(v.getId())
                 target.setImageResource(R.drawable.card_caro_10)
                */
                //var test : ImageView = findViewById(R.id.card_02)
                //test.setImageResource(R.drawable.card_caro_10)


                //val owner = v.parent as ViewGroup
                //owner.removeView(v)

                //test
                /// val destination = view as LinearLayout
                ///destination.addView(dragged)

                dragged.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                val v = event.localState as View

                //

                target.invalidate()

                v.visibility =
                    View.VISIBLE //damit die Karte wenn sie falschplaziert wurde wieder sichtabr ist
                true
            }
            else -> true
        }
    }


}