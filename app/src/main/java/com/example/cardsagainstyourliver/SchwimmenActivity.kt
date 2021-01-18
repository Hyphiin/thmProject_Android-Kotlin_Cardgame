package com.example.cardsagainstyourliver

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class SchwimmenActivity : AppCompatActivity() {

    var game = SchwimmenClass()
    var deck = DeckClass(2)
    var p1hand = HandClass(deck, "Schwimmen")
    var p2hand = HandClass(deck, "Schwimmen")
    var table= HandClass(deck, "Null")
    var dump = HandClass(deck, "Null")

    var object1 = p1hand.getIndex(p1hand.getCard(0))
    var object2 = p1hand.getIndex(p1hand.getCard(0))

    var hand:HandClass = HandClass(deck, "Null")

    var shove = false
    var shoveStarterHand:HandClass = HandClass(deck, "Null")

    var player1Name = "Jürgen"
    var player2Name = "Klaus"

    public var player1Hearts = 3
    public var player2Hearts = 3

    var name = "derzeitiger Spieler"

    var knock = false
    var knockStarterHand:HandClass = HandClass(deck, "Null")
    var textGewinner:String = "Gewinne, Gewinne, Gewinne!"

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schwimmen)

        var p1Id = intent.getIntExtra("idP1", -1)
        var p2Id = intent.getIntExtra("idP2", -1)


        Log.d("Spieler:", p1Id.toString()+" "+p2Id.toString())

        game = SchwimmenClass()
        game.startGame(game)
        deck = DeckClass(2)
        deck.shuffle()
        p1hand = HandClass(deck, "Schwimmen")
        p2hand = HandClass(deck, "Schwimmen")
        table= HandClass(deck, "Null")
        game.startHand(p1hand,table, deck)
        dump = HandClass(deck, "Null")
        hand = p1hand

        val dragView1: ImageView = findViewById(R.id.player_card_01)!!
        val dragView2: ImageView = findViewById(R.id.player_card_02)!!
        val dragView3: ImageView = findViewById(R.id.player_card_03)!!

        val dragView01: ImageView = findViewById(R.id.table_card_01)!!
        val dragView02: ImageView = findViewById(R.id.table_card_02)!!
        val dragView03: ImageView = findViewById(R.id.table_card_03)!!

        dragView1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
        dragView2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
        dragView3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))

        dragView01.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        dragView02.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        dragView03.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

        val p1Pos =intent.getIntExtra("idPos1",-1)
        val p2Pos =intent.getIntExtra("idPos2",-1)

        val context=this
        var db =DBHandler(context)
        var data=db.readData()


        //var text3 = textView3.getText().toString()
       // Log.d("View: ",text3)


        player1Name = data.get(p1Pos).playerName
        player2Name = data.get(p2Pos).playerName

        /*table_left.setOnDragListener(dragListener)
        table_middle.setOnDragListener(dragListener)
        table_right.setOnDragListener(dragListener)
        //tableCard3.setOnDragListener(dragListener)

        player_left.setOnDragListener(dragListener)
        player_middle.setOnDragListener(dragListener)
        player_right.setOnDragListener(dragListener)*/



        //val toast = Toast.makeText(applicationContext, "HandKarte: ${p1hand.getCard(0)} Tischkarte: ${table.getCard(0)}", Toast.LENGTH_LONG).show()
        //val toast2 = Toast.makeText(applicationContext, "HandKarte: ${p2hand.toString()}", Toast.LENGTH_LONG).show()
        //toast2.show()

        // first drag and drop card
        dragView1.setOnLongClickListener {
            val clipText = "Player Card Left"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            object1 = hand.getIndex(hand.getCard(0))

            dragView1.setBackgroundColor(getResources().getColor(R.color.colorAccent))

            /*val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE // damit die karte während dem drag unsichtbar wird*/
            true

        }

        //new drag and drop card
        dragView2.setOnLongClickListener {
            val clipText = "Player Card Middle"
            val item2 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item2)

            object1 = hand.getIndex(hand.getCard(1))

            dragView2.setBackgroundColor(getResources().getColor(R.color.colorAccent))

            /*val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE*/
            true
        }

        //new drag and drop card
        dragView3.setOnLongClickListener {
            val clipText = "Player Card Right"
            val item3 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item3)

            object1 = hand.getIndex(hand.getCard(2))

            dragView3.setBackgroundColor(getResources().getColor(R.color.colorAccent))

            /*val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE*/
            true

        }


        dragView01.setOnLongClickListener {
            val clipText = "Table Card Left"
            val item01 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item01)

            object2 = table.getIndex(table.getCard(0))
            dragView01.setBackgroundColor(getResources().getColor(R.color.colorAccent))

            /*val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)



            it.visibility = View.INVISIBLE*/
            true

        }
        dragView02.setOnLongClickListener {
            val clipText = "Table Card Middle"
            val item02 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item02)

            object2 = table.getIndex(table.getCard(1))
            dragView02.setBackgroundColor(getResources().getColor(R.color.colorAccent))

            /*val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE*/
            true

        }

        dragView03.setOnLongClickListener {
            val clipText = "Table Card Right"
            val item03 = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item03)

            object2 = table.getIndex(table.getCard(2))
            dragView03.setBackgroundColor(getResources().getColor(R.color.colorAccent))

            /*val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE*/
            true

        }
    }

    fun onClickChangeCards(view: View) {
        val hand1: ImageView = findViewById(R.id.player_card_01)!!
        val table1: ImageView = findViewById(R.id.table_card_01)!!
        val hand2: ImageView = findViewById(R.id.player_card_02)!!
        val table2: ImageView = findViewById(R.id.table_card_02)!!
        val hand3: ImageView = findViewById(R.id.player_card_03)!!
        val table3: ImageView = findViewById(R.id.table_card_03)!!


        game.changeCard(object1, object2, hand, table)

        if (object1 == 0 && object2 == 0){
            hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
            table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        }else if(object1 == 0 && object2 == 1){
            hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
            table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        }else if(object1 == 0 && object2 == 2){
            hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
            table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))
        }else if(object1 == 1 && object2 == 0){
            hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
            table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        }else if(object1 == 1 && object2 == 1){
            hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
            table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        }else if(object1 == 1 && object2 == 2){
            hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
            table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))
        }else if(object1 == 2 && object2 == 0){
            hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
            table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        }else if(object1 == 2 && object2 == 1){
            hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
            table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        }else if(object1 == 2 && object2 == 2){
            hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
            table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))
        }else{
            val toast = Toast.makeText(applicationContext, "Upsi", Toast.LENGTH_LONG)
            toast.show()
        }

        val toast = Toast.makeText(applicationContext, "${hand.getCard(0)}, ${hand.getCard(1)}, ${hand.getCard(2)} ", Toast.LENGTH_LONG)
        toast.show()

        hand1.setBackgroundColor(Color.WHITE)
        hand2.setBackgroundColor(Color.WHITE)
        hand3.setBackgroundColor(Color.WHITE)
        table1.setBackgroundColor(Color.WHITE)
        table2.setBackgroundColor(Color.WHITE)
        table3.setBackgroundColor(Color.WHITE)

        if (shove === true){
            shove = false
            shoveStarterHand = HandClass(deck,"Null")
        }

        if(knock === true){
            knock = false
            knockStarterHand = HandClass(deck,"Null")

            game.close(p1hand, p2hand, deck, table, dump, hand)

            val winner:Int = game.endGame(p1hand, p2hand)
            if(winner === 1){
                textGewinner = "Gewinner: ${player1Name}"
                player2Hearts --
            }else if(winner === 2){
                textGewinner = "Gewinner: ${player2Name}"
                player1Hearts --
            }else{
                textGewinner = "Yippieh, Unentschieden!"
            }
            val toast2 = Toast.makeText(applicationContext, textGewinner, Toast.LENGTH_LONG)
            toast2.show()

            spielEnde(view)

        } else {
            Handler().postDelayed({
                nextPlayerMenu(view)
            }, 1500)
        }

    }

    fun nextPlayerMenu(view: View) {
            //val toast = Toast.makeText(applicationContext, name, Toast.LENGTH_LONG)
            //toast.show()
            if(hand == p2hand){
                name = player2Name
            }else{
                name = player1Name
            }

            val intent = Intent(this, PopUpSpielerwechselActivity::class.java)
            intent.putExtra("heart1", player1Hearts)
            intent.putExtra("heart2", player2Hearts)
            intent.putExtra("name", name)
            intent.putExtra("player1name", player1Name)
            intent.putExtra("player2name", player2Name)
            startActivity(intent)

            Handler().postDelayed({
                val hand1: ImageView = findViewById(R.id.player_card_01)!!
                val hand2: ImageView = findViewById(R.id.player_card_02)!!
                val hand3: ImageView = findViewById(R.id.player_card_03)!!

                if (hand == p1hand) {
                    hand = p2hand
                } else {
                    hand = p1hand
                }

                hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
                hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
                hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
            }, 1000)
    }

    fun spielEnde(view: View) {
        //val toast = Toast.makeText(applicationContext, "nächste Runde", Toast.LENGTH_LONG)
        //toast.show()

        val SpielendePopUpEvent = Intent(this, PopUpSpielendeActivity::class.java)
        startActivity(SpielendePopUpEvent)
    }

    fun rundenEnde(view: View) {
        //val toast = Toast.makeText(applicationContext, "nächste Runde", Toast.LENGTH_LONG)
        //toast.show()

        val SpielendePopUpEvent = Intent(this, PopUpSpielendeActivity::class.java)
        startActivity(SpielendePopUpEvent)
    }


    fun nextRoundMenu(view: View) {
        val toast = Toast.makeText(applicationContext, "nächste Runde", Toast.LENGTH_LONG)
        toast.show()

        val NextRoundMenuPopUpEvent = Intent(this, PopUpSpielendeActivity::class.java)
        startActivity(NextRoundMenuPopUpEvent)
    }

    fun promilleAnzeige(view: View) {
        val toast = Toast.makeText(applicationContext, "Promilleanzeige", Toast.LENGTH_LONG)
        toast.show()

        val PromillePopUpEvent = Intent(this, PopUpPromillerechnerActivity::class.java)
        startActivity(PromillePopUpEvent)

    }


    fun onClickPauseMenuButton(view: View) {
        val PauseMenuButton = Intent(this, PauseMenuActivity::class.java)
        startActivity(PauseMenuButton)
    }

    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onClickSwapCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Tauschen", Toast.LENGTH_LONG)
        //toast.show()
        val zwischen1 = table.getIndex(table.getCard(0))
        val zwischen2 = table.getIndex(table.getCard(1))
        val zwischen3 = table.getIndex(table.getCard(2))

        val hand1 : ImageView = findViewById(R.id.player_card_01)!!
        val table1 : ImageView = findViewById(R.id.table_card_01)!!
        val hand2 : ImageView = findViewById(R.id.player_card_02)!!
        val table2 : ImageView = findViewById(R.id.table_card_02)!!
        val hand3 : ImageView = findViewById(R.id.player_card_03)!!
        val table3 : ImageView = findViewById(R.id.table_card_03)!!

        game.changeCard(hand.getIndex(hand.getCard(0)),zwischen1,hand,table)
        game.changeCard(hand.getIndex(hand.getCard(1)),zwischen2,hand,table)
        game.changeCard(hand.getIndex(hand.getCard(2)),zwischen3,hand,table)

        hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
        table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
        table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
        table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

        val toast2 = Toast.makeText(applicationContext, "${hand.getCard(0)}, ${hand.getCard(1)}, ${hand.getCard(2)} ", Toast.LENGTH_LONG)
        //toast2.show()

        if (shove === true){
            shove = false
            shoveStarterHand = HandClass(deck,"Null")
        }

        if(knock === true){
            knock = false
            knockStarterHand = HandClass(deck,"Null")

            game.close(p1hand, p2hand, deck, table, dump, hand)

            val winner:Int = game.endGame(p1hand, p2hand)
            if(winner === 1){
                textGewinner = "Gewinner: ${player1Name}"
                player2Hearts --
            }else if(winner === 2){
                textGewinner = "Gewinner: ${player2Name}"
                player1Hearts --
            }else{
                textGewinner = "Yippieh, Unentschieden!"
            }
            val toast2 = Toast.makeText(applicationContext, textGewinner, Toast.LENGTH_LONG)
            toast2.show()

            spielEnde(view)

        } else {
            Handler().postDelayed({
                nextPlayerMenu(view)
            }, 1500)
        }

    }

    fun onClickKnockCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Klopfen", Toast.LENGTH_LONG)
        //toast.show()

        if (knockStarterHand != p1hand && knockStarterHand != p1hand){
            if (hand === p1hand){
                knockStarterHand = p1hand
                knock = true
                Handler().postDelayed({
                    nextPlayerMenu(view)
                }, 1500)
            }else if(hand === p2hand){
                knockStarterHand = p2hand
                knock = true
                Handler().postDelayed({
                    nextPlayerMenu(view)
                }, 1500)
            }
        }else  if(knock === true){
            knock = false
            knockStarterHand = HandClass(deck,"Null")

            game.close(p1hand, p2hand, deck, table, dump, hand)

            val winner:Int = game.endGame(p1hand, p2hand)
            if(winner === 1){
                textGewinner = "Gewinner: ${player1Name}"
                player2Hearts --
            }else if(winner === 2){
                textGewinner = "Gewinner: ${player2Name}"
                player1Hearts --
            }else{
                textGewinner = "Yippieh, Unentschieden!"
            }
            val toast2 = Toast.makeText(applicationContext, textGewinner, Toast.LENGTH_LONG)
            toast2.show()

            spielEnde(view)

        } else {
            Handler().postDelayed({
                nextPlayerMenu(view)
            }, 1500)
        }


        if (shove === true){
            shove = false
            shoveStarterHand = HandClass(deck,"Null")
        }


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onClickShoveCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Schieben", Toast.LENGTH_LONG)
        toast.show()

        if (shoveStarterHand != p1hand && shoveStarterHand != p2hand) {
            if(hand === p1hand){
                shoveStarterHand = p1hand
                shove = true
                Handler().postDelayed({
                    nextPlayerMenu(view)
                }, 1500)
            }else if(hand === p2hand){
                shoveStarterHand = p2hand
                shove = true
                Handler().postDelayed({
                    nextPlayerMenu(view)
                }, 1500)
            }
        }else if(shoveStarterHand === p1hand && shove === true){
            if(hand===p2hand){
                game.push(table, dump, deck, true)

                val table1: ImageView = findViewById(R.id.table_card_01)!!
                val table2: ImageView = findViewById(R.id.table_card_02)!!
                val table3: ImageView = findViewById(R.id.table_card_03)!!

                table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
                table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
                table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

                val toast2 =
                    Toast.makeText(applicationContext, "${table.toString()} ", Toast.LENGTH_LONG)
                toast2.show()

                shove = false
                shoveStarterHand = HandClass(deck,"Null")

                Handler().postDelayed({
                    nextPlayerMenu(view)
                }, 1500)
            }
        }else if(shoveStarterHand === p2hand && shove === true){
            if(hand===p1hand){
                game.push(table, dump, deck, true)

                val table1: ImageView = findViewById(R.id.table_card_01)!!
                val table2: ImageView = findViewById(R.id.table_card_02)!!
                val table3: ImageView = findViewById(R.id.table_card_03)!!

                table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
                table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
                table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

                val toast2 =
                    Toast.makeText(applicationContext, "${table.toString()} ", Toast.LENGTH_LONG)
                toast2.show()

                shove = false
                shoveStarterHand = HandClass(deck,"Null")

                Handler().postDelayed({
                    nextPlayerMenu(view)
                }, 1500)
            }
        }

        if(knock === true){
            knock = false
            knockStarterHand = HandClass(deck,"Null")

            game.close(p1hand, p2hand, deck, table, dump, hand)

            val winner:Int = game.endGame(p1hand, p2hand)
            if(winner === 1){
                textGewinner = "Gewinner: ${player1Name}"
                player2Hearts --
            }else if(winner === 2){
                textGewinner = "Gewinner: ${player2Name}"
                player1Hearts --
            }else{
                textGewinner = "Yippieh, Unentschieden!"
            }
            val toast2 = Toast.makeText(applicationContext, textGewinner, Toast.LENGTH_LONG)
            toast2.show()

            spielEnde(view)

        } else {
            Handler().postDelayed({
                nextPlayerMenu(view)
            }, 1500)
        }

    }



   /*@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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

                //Toast.makeText(this, xPos2, Toast.LENGTH_SHORT).show()
                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                target.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                val dragData = item.text
                var xPos: Float = event.getX();
                var xPos2: String? = event.getX().toString();

                if (xPos > 180){
                    object2 = table.getIndex(table.getCard(2))
                }else if (xPos > 140){
                    object2 = table.getIndex(table.getCard(1))
                }else {
                    object2 = table.getIndex(table.getCard(0))
                }

                game.changeCard(object1,object2,p1hand,table)

                //Toast.makeText(this, xPos2, Toast.LENGTH_SHORT).show()
                val toast = Toast.makeText(applicationContext, "XPos: ${dragData}", Toast.LENGTH_LONG)
                //toast.show()

                val dragged = event.localState as ImageView //hand 0, dragView1 R.id.player_card_01


                val oldOwner = dragged.parent as ViewGroup  //hand 0
                val newOwner = target.parent as ViewGroup   //table 0

                val draggedPosition = oldOwner.indexOfChild(dragged) // dragView1 R.id.player_card_01
                val targetPosition = oldOwner.indexOfChild(dragged) //  dragView1 R.id.player_card_01


                oldOwner.removeView(dragged)    //dragView1 wird von
                newOwner.addView(dragged, targetPosition)

                newOwner.removeView(target)
                oldOwner.addView(target, draggedPosition)


                /*oldOwner.addView(findViewById(R.id.player_card_01)!!)
                newOwner.addView(findViewById(R.id.table_card_01)!!)

                hand1.setImageDrawable(getDrawable(p1hand.getPic(p1hand.getCard(0))))
                table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))*/

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

                //dragged.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                val v = event.localState as View

                val toast = Toast.makeText(applicationContext, "HandKarte: ${p1hand.getCard(object1)} Tischkarte: ${table.getCard(object2)}", Toast.LENGTH_LONG)
                //toast.show()

                target.invalidate()

                v.visibility =
                    View.VISIBLE //damit die Karte wenn sie falschplaziert wurde wieder sichtabr ist
                true
            }
            else -> true
        }
    }*/
}



