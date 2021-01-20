package com.example.cardsagainstyourliver

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
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
    var p1hand = HandClass(deck, "Null")
    var p2hand = HandClass(deck, "Null")
    var table= HandClass(deck, "Null")
    var dump = HandClass(deck, "Null")

    var startHand1 = HandClass(deck, "Null")
    var startHand2 = HandClass(deck, "Null")

    var object1 = 0
    var object2 = 0

    var hand:HandClass = HandClass(deck, "Null")

    var shove = false
    var shoveStarterHand:HandClass = HandClass(deck, "Null")

    var player1Name = "Jürgen"
    var player2Name = "Klaus"

    var player1drink = 0
    var player2drink = 0
    var player1weight = 0
    var player2weight = 0
    var player1gender = 0
    var player2gender = 0
    var player1permille = 0.0
    var player2permille = 0.0


    var player1Hearts = 3
    var player2Hearts = 3

    var name = "derzeitiger Spieler"
    var playerStart = player1Name

    var knock = false
    var knockStarterHand:HandClass = HandClass(deck, "Null")
    var textGewinner:String = "Gewinne, Gewinne, Gewinne!"

    var thirtyOne = false

    var p1Pos = 0
    var p2Pos = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schwimmen)

        val p1Id = intent.getIntExtra("idP1", -1)
        val p2Id = intent.getIntExtra("idP2", -1)

        Log.d("Spieler:", p1Id.toString()+" "+p2Id.toString())

        val p1Pos =intent.getIntExtra("idPos1",-1)
        val p2Pos =intent.getIntExtra("idPos2",-1)

        val context=this
        var db =DBHandler(context)
        var data=db.readData()

        var PRechner = PerMilleCalculator()
        var percentage = 0.0
        var ml = 0.0

        Log.d("onCreate: ","onCreate")

        player1Name = data.get(p1Pos).playerName
        player2Name = data.get(p2Pos).playerName
        playerStart = player1Name


        player1drink = data.get(p1Pos).drink
        player2drink = data.get(p2Pos).drink
        player1weight = data.get(p1Pos).weight
        player2weight = data.get(p2Pos).weight
        player1gender = data.get(p1Pos).gender
        player2gender = data.get(p2Pos).gender
        player1permille = data.get(p1Pos).alcoholLevel
        player2permille = data.get(p2Pos).alcoholLevel

        when(player1drink){
            0 -> {
                percentage = 5.0
                ml = 100.0
                player1permille = PRechner.permille(player1weight, player1gender, percentage, ml, 2)
            }
        }
        when(player2drink){
            0 -> {
                percentage = 5.0
                ml = 100.0
                PRechner.permille(player2weight, player2gender, percentage, ml, 2)
            }
        }


        startHandView()
    }

    override fun onResume() {
        super.onResume()

        Log.d("onResume: ","onResume")

        val p1Id = intent.getIntExtra("idP1", -1)
        val p2Id = intent.getIntExtra("idP2", -1)

        //Log.d("Spieler onResume:", p1Id.toString()+" "+p2Id.toString())

        val p1Pos =intent.getIntExtra("idPos1",-1)
        val p2Pos =intent.getIntExtra("idPos2",-1)

        val dragView1: ImageView = findViewById(R.id.player_card_01)!!
        val dragView2: ImageView = findViewById(R.id.player_card_02)!!
        val dragView3: ImageView = findViewById(R.id.player_card_03)!!

        val dragView01: ImageView = findViewById(R.id.table_card_01)!!
        val dragView02: ImageView = findViewById(R.id.table_card_02)!!
        val dragView03: ImageView = findViewById(R.id.table_card_03)!!

        val context=this
        var db =DBHandler(context)
        var data=db.readData()

        //var text3 = textView3.getText().toString()
        // Log.d("View: ",text3)

        player1Name = data.get(p1Pos).playerName
        player2Name = data.get(p2Pos).playerName
        playerStart = player1Name

        val a = intent.getBooleanExtra("a",false)
        Log.d("Intent in Schwimmen:", a.toString())


        if (a === true){
            Log.d("Intent in if:", a.toString())
            p1hand.add(startHand1.getCard(0))
            p1hand.add(startHand1.getCard(1))
            p1hand.add(startHand1.getCard(2))

            table.add(startHand2.getCard(0))
            table.add(startHand2.getCard(1))
            table.add(startHand2.getCard(2))
        }else {
            Log.d("Intent in else:", a.toString())
            p1hand.add(startHand2.getCard(0))
            p1hand.add(startHand2.getCard(1))
            p1hand.add(startHand2.getCard(2))

            table.add(startHand1.getCard(0))
            table.add(startHand1.getCard(1))
            table.add(startHand1.getCard(2))
        }

        hand = p1hand


        dragView1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
        dragView2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
        dragView3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))

        dragView01.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        dragView02.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        dragView03.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

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

    override fun onPause() {
        super.onPause()
        Log.d("onPause: ","onPause")
    }


    fun startHandView() {
        //val toast = Toast.makeText(applicationContext, "Starthand", Toast.LENGTH_LONG)

        game = SchwimmenClass()
        game.startGame(game)
        deck = DeckClass(2)
        deck.shuffle()
        p1hand = HandClass(deck, "Null")
        p2hand = HandClass(deck, "Schwimmen")
        table= HandClass(deck, "Null")
        dump = HandClass(deck, "Null")

        startHand1 = HandClass(deck, "Schwimmen")
        startHand2 = HandClass(deck, "Schwimmen")
        val card1 = startHand1.getPic(startHand1.getCard(0))
        val card2 = startHand1.getPic(startHand1.getCard(1))
        val card3 = startHand1.getPic(startHand1.getCard(2))
        val toast = Toast.makeText(applicationContext, " ${startHand1.toString()}", Toast.LENGTH_LONG)
        toast.show()

        val intent = Intent(this, PopUpKartenauswahlActivity::class.java)
        intent.putExtra("p1hand", card1)
        intent.putExtra("p1hand2", card2)
        intent.putExtra("p1hand3", card3)
        intent.putExtra("name", name)
        intent.putExtra("player1name", player1Name)
        intent.putExtra("player2name", player2Name)
        intent.putExtra("idPos1",p1Pos)
        intent.putExtra("idPos2",p2Pos)
        startActivity(intent)
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
            intent.putExtra("knock",knock)
            intent.putExtra("shove",shove)
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

    fun rundenEnde(view: View) {
        //val toast = Toast.makeText(applicationContext, "nächste Runde", Toast.LENGTH_LONG)
        //toast.show()

        if(player1Hearts >= 0 || player2Hearts >= 0) {
            val intent = Intent(this, PopUpRundenendeActivity::class.java)
            intent.putExtra("textGewinner", textGewinner)
            intent.putExtra("player1name", player1Name)
            intent.putExtra("player2name", player2Name)
            intent.putExtra("playerStart", playerStart)
            startActivity(intent)

            //game.close(p1hand,p2hand,deck,table,dump,hand,playerStart,player1Name)
            Handler().postDelayed({
                deck = DeckClass(2)
                deck.shuffle()
                p1hand = HandClass(deck, "Null")
                p2hand = HandClass(deck, "Null")
                table = HandClass(deck, "Null")
                dump = HandClass(deck, "Null")
                if (playerStart == player1Name) {
                    game.startHand(p2hand, table, deck)
                    p1hand = HandClass(deck, "Schwimmen")
                    hand = p2hand
                    playerStart = player2Name
                    val toast =
                        Toast.makeText(applicationContext, "Player1Starter", Toast.LENGTH_LONG)
                    //toast.show()
                } else if (playerStart == player2Name) {
                    game.startHand(p1hand, table, deck)
                    p2hand = HandClass(deck, "Schwimmen")
                    hand = p1hand
                    playerStart = player1Name
                    val toast =
                        Toast.makeText(applicationContext, "Player2Starter", Toast.LENGTH_LONG)
                    //toast.show()
                } else {
                    val toast =
                        Toast.makeText(applicationContext, "hand nicht def", Toast.LENGTH_LONG)
                    //toast.show()
                }

                val hand1: ImageView = findViewById(R.id.player_card_01)!!
                val table1: ImageView = findViewById(R.id.table_card_01)!!
                val hand2: ImageView = findViewById(R.id.player_card_02)!!
                val table2: ImageView = findViewById(R.id.table_card_02)!!
                val hand3: ImageView = findViewById(R.id.player_card_03)!!
                val table3: ImageView = findViewById(R.id.table_card_03)!!

                hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
                table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
                hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
                table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
                hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
                table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

                thirtyOne = false

            }, 1000)
            Handler().postDelayed({
                startHandView()
            }, 3000)
        }else if(player1Hearts < 0 || player2Hearts < 0){
            val intent = Intent(this, PopUpSpielendeActivity::class.java)
            startActivity(intent)
        }


    }

    fun spielEnde(view: View) {
        //val toast = Toast.makeText(applicationContext, "nächste Runde", Toast.LENGTH_LONG)
        //toast.show()

        val SpielendePopUpEvent = Intent(this, PopUpSpielendeActivity::class.java)
        startActivity(SpielendePopUpEvent)
    }


    fun nextRoundMenu(view: View) {
        val toast = Toast.makeText(applicationContext, "nächste Runde", Toast.LENGTH_LONG)
        toast.show()

        val NextRoundMenuPopUpEvent = Intent(this, PopUpRundenendeActivity::class.java)
        startActivity(NextRoundMenuPopUpEvent)
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

        hand1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))

        thirtyOne(view)

        if (thirtyOne === false) {
            if (shove) {
                shove = false
                shoveStarterHand = HandClass(deck, "Null")
            }
            if (knock) {
                knock = false
                knockStarterHand = HandClass(deck, "Null")

                val winner: Int = game.endGame(p1hand, p2hand)
                if (winner === 1) {
                    textGewinner = player1Name
                    player2Hearts--
                } else if (winner === 2) {
                    textGewinner = player2Name
                    player1Hearts--
                } else {
                    textGewinner = "Yippieh, Unentschieden!"
                }
                //val toast2 = Toast.makeText(applicationContext, textGewinner, Toast.LENGTH_LONG)
                //toast2.show()

                rundenEnde(view)

            } else {
                Handler().postDelayed({
                    nextPlayerMenu(view)
                }, 1500)
            }
        }

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

        //val toast2 = Toast.makeText(applicationContext, "${hand.getCard(0)}, ${hand.getCard(1)}, ${hand.getCard(2)} ", Toast.LENGTH_LONG)
        //toast2.show()

        hand1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))

        if (shove){
            shove = false
            shoveStarterHand = HandClass(deck,"Null")
        }

        if(knock){
            knock = false
            knockStarterHand = HandClass(deck,"Null")

            val winner:Int = game.endGame(p1hand, p2hand)
            if(winner === 1){
                textGewinner = player1Name
                player2Hearts --
            }else if(winner === 2){
                textGewinner = player2Name
                player1Hearts --
            }else{
                textGewinner = "Yippieh, Unentschieden!"
            }
            //val toast2 = Toast.makeText(applicationContext, textGewinner, Toast.LENGTH_LONG)
            //toast2.show()

            rundenEnde(view)

        } else {
            Handler().postDelayed({
                nextPlayerMenu(view)
            }, 1500)
        }

    }

    fun thirtyOne(view:View){
        var winner:Int = game.endGame(p1hand, p2hand)
        val toast = Toast.makeText(applicationContext, "${winner}", Toast.LENGTH_LONG)
        toast.show()
        if(winner === 331){
            textGewinner = player1Name
            player2Hearts --
            thirtyOne = true
            rundenEnde(view)
        }else if(winner === 332){
            textGewinner = player2Name
            player1Hearts --
            thirtyOne = true
            rundenEnde(view)
        }else if(winner === 311){
            textGewinner = player1Name
            player2Hearts --
            thirtyOne = true
            rundenEnde(view)
        }else if(winner === 312){
            textGewinner = player2Name
            player1Hearts --
            thirtyOne = true
            rundenEnde(view)
        }else{
            val toast = Toast.makeText(applicationContext, "nix", Toast.LENGTH_LONG)
            //toast.show()
        }
    }

    fun onClickKnockCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Klopfen", Toast.LENGTH_LONG)
        //toast.show()
        val hand1 : ImageView = findViewById(R.id.player_card_01)!!
        val table1 : ImageView = findViewById(R.id.table_card_01)!!
        val hand2 : ImageView = findViewById(R.id.player_card_02)!!
        val table2 : ImageView = findViewById(R.id.table_card_02)!!
        val hand3 : ImageView = findViewById(R.id.player_card_03)!!
        val table3 : ImageView = findViewById(R.id.table_card_03)!!

        if (knockStarterHand != p1hand && knockStarterHand != p2hand){
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
        }else if(knock){
            knock = false
            knockStarterHand = HandClass(deck,"Null")
            var winner:Int = game.endGame(p1hand, p2hand)
            if(winner === 1){
                textGewinner = player1Name
                player2Hearts --
            }else if(winner === 2){
                textGewinner = player2Name
                player1Hearts --
            }else{
                textGewinner = "Yippieh, Unentschieden!"
            }
            //val toast2 = Toast.makeText(applicationContext, textGewinner, Toast.LENGTH_LONG)
            //toast2.show()
            rundenEnde(view)

        } else {
            Handler().postDelayed({
                nextPlayerMenu(view)
            }, 1500)
        }

        hand1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))


        if (shove){
            shove = false
            shoveStarterHand = HandClass(deck,"Null")
        }


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onClickShoveCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Schieben", Toast.LENGTH_LONG)
        toast.show()
        val hand1 : ImageView = findViewById(R.id.player_card_01)!!
        val table1 : ImageView = findViewById(R.id.table_card_01)!!
        val hand2 : ImageView = findViewById(R.id.player_card_02)!!
        val table2 : ImageView = findViewById(R.id.table_card_02)!!
        val hand3 : ImageView = findViewById(R.id.player_card_03)!!
        val table3 : ImageView = findViewById(R.id.table_card_03)!!

        if (!shove) {
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
            }else{
                val toast2 = Toast.makeText(applicationContext, "Hand nicht definiert", Toast.LENGTH_LONG)
                toast2.show()
            }
        }else if(shove){
            if(hand===p2hand && shoveStarterHand == p1hand){
                game.push(table, dump, deck, true)

                val table1: ImageView = findViewById(R.id.table_card_01)!!
                val table2: ImageView = findViewById(R.id.table_card_02)!!
                val table3: ImageView = findViewById(R.id.table_card_03)!!

                table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
                table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
                table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

                val toast2 = Toast.makeText(applicationContext, "${table.toString()} ", Toast.LENGTH_LONG)
                toast2.show()

                shove = false
                shoveStarterHand = HandClass(deck,"Null")

                Handler().postDelayed({
                    nextPlayerMenu(view)
                }, 1500)
            }else if(hand===p1hand && shoveStarterHand == p2hand){
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
        }else {
            val toast2 = Toast.makeText(applicationContext, "Keine ShoveStarterHand", Toast.LENGTH_LONG)
            toast2.show()
        }

        hand1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))

        if(knock){
            knock = false
            knockStarterHand = HandClass(deck,"Null")

            val winner:Int = game.endGame(p1hand, p2hand)
            if(winner === 1){
                textGewinner = player1Name
                player2Hearts --
            }else if(winner === 2){
                textGewinner = player2Name
                player1Hearts --
            }else{
                textGewinner = "Yippieh, Unentschieden!"
            }
            //val toast2 = Toast.makeText(applicationContext, textGewinner, Toast.LENGTH_LONG)
            //toast2.show()

            rundenEnde(view)
        }
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




