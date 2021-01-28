package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class SchwimmenActivity : AppCompatActivity() {

    private val SECOND_ACTIVITY_REQUEST_CODE = 0
    private val THIRD_ACTIVITY_REQUEST_CODE = 1

    var db = DBHandler(this)

    var game = SchwimmenClass()
    var deck = DeckClass(2)
    var p1hand = HandClass(deck, "Null")
    var p2hand = HandClass(deck, "Null")
    var table = HandClass(deck, "Null")
    var dump = HandClass(deck, "Null")

    var startHand1 = HandClass(deck, "Null")
    var startHand2 = HandClass(deck, "Null")

    var object1 = 0
    var object2 = 0

    var hand: HandClass = HandClass(deck, "Null")

    var shove = false
    var shoveStarterHand: HandClass = HandClass(deck, "Null")

    var player1Name = "Jürgen"
    var player2Name = "Klaus"

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

    var player1Hearts = 3
    var player2Hearts = 3

    var name = player1Name
    var playerStart = player1Name

    var knock = false
    var knockStarterHand: HandClass = HandClass(deck, "Null")
    var textWinner: String = "Gewinne, Gewinne, Gewinne!"

    var thirtyOne = false

    var p1Pos = 0
    var p2Pos = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schwimmen)

        val p1Id = intent.getIntExtra("idP1", -1)
        val p2Id = intent.getIntExtra("idP2", -1)

        Log.d("Player:", p1Id.toString() + " " + p2Id.toString())

        val p1Pos = intent.getIntExtra("idPos1", -1)
        val p2Pos = intent.getIntExtra("idPos2", -1)

        val context = this
        db = DBHandler(context)
        val data = db.readData()

        Log.d("onCreate: ", "onCreate")

        player1Name = data.get(p1Pos).playerName
        player2Name = data.get(p2Pos).playerName
        playerStart = player1Name
        name = player1Name

        startHandView()

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

    }


    fun calculatePermille() {

        var PRechner = PerMilleCalculator()
        var percentage = 0.0
        var ml = 0.0

        when (player1drink) {
            0 -> {
                percentage = 5.0
                ml = player1ml
                player1permille = (PRechner.permille(
                    player1weight,
                    player1gender,
                    percentage,
                    ml,
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
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
                    2
                ) * 100).toInt()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume: ", "onResume")

        val dragView1: ImageView = findViewById(R.id.player_card_01)!!
        val dragView2: ImageView = findViewById(R.id.player_card_02)!!
        val dragView3: ImageView = findViewById(R.id.player_card_03)!!

        val dragView01: ImageView = findViewById(R.id.table_card_01)!!
        val dragView02: ImageView = findViewById(R.id.table_card_02)!!
        val dragView03: ImageView = findViewById(R.id.table_card_03)!!

        dragView1.setOnClickListener {
            object1 = hand.getIndex(hand.getCard(0))
            dragView1.setBackgroundColor(getResources().getColor(R.color.backgroundColor2))
            true
        }

        dragView2.setOnClickListener {
            object1 = hand.getIndex(hand.getCard(1))
            dragView2.setBackgroundColor(getResources().getColor(R.color.backgroundColor2))
            true
        }

        dragView3.setOnClickListener {
            object1 = hand.getIndex(hand.getCard(2))
            dragView3.setBackgroundColor(getResources().getColor(R.color.backgroundColor2))
            true
        }

        dragView01.setOnClickListener {
            object2 = table.getIndex(table.getCard(0))
            dragView01.setBackgroundColor(getResources().getColor(R.color.backgroundColor2))
            true
        }

        dragView02.setOnClickListener {
            object2 = table.getIndex(table.getCard(1))
            dragView02.setBackgroundColor(getResources().getColor(R.color.backgroundColor2))
            true
        }

        dragView03.setOnClickListener {
            object2 = table.getIndex(table.getCard(2))
            dragView03.setBackgroundColor(getResources().getColor(R.color.backgroundColor2))
            true
        }
    }

    // This method is called when PopUpKartenauswahlActivity finishes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        p1hand = HandClass(deck, "Null")
        p2hand = HandClass(deck, "Null")

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            val dragView1: ImageView = findViewById(R.id.player_card_01)!!
            val dragView2: ImageView = findViewById(R.id.player_card_02)!!
            val dragView3: ImageView = findViewById(R.id.player_card_03)!!

            val dragView01: ImageView = findViewById(R.id.table_card_01)!!
            val dragView02: ImageView = findViewById(R.id.table_card_02)!!
            val dragView03: ImageView = findViewById(R.id.table_card_03)!!

            if (playerStart == player1Name) {
                p2hand = HandClass(deck, "Schwimmen")
                if (resultCode == RESULT_CANCELED) {
                    p1hand.add(startHand1.getCard(0))
                    p1hand.add(startHand1.getCard(1))
                    p1hand.add(startHand1.getCard(2))

                    table.add(startHand2.getCard(0))
                    table.add(startHand2.getCard(1))
                    table.add(startHand2.getCard(2))
                } else {
                    p1hand.add(startHand2.getCard(0))
                    p1hand.add(startHand2.getCard(1))
                    p1hand.add(startHand2.getCard(2))

                    table.add(startHand1.getCard(0))
                    table.add(startHand1.getCard(1))
                    table.add(startHand1.getCard(2))
                }
                hand = p1hand
            } else {
                p1hand = HandClass(deck, "Schwimmen")
                if (resultCode == RESULT_CANCELED) {
                    p2hand.add(startHand1.getCard(0))
                    p2hand.add(startHand1.getCard(1))
                    p2hand.add(startHand1.getCard(2))

                    table.add(startHand2.getCard(0))
                    table.add(startHand2.getCard(1))
                    table.add(startHand2.getCard(2))
                } else {
                    p2hand.add(startHand2.getCard(0))
                    p2hand.add(startHand2.getCard(1))
                    p2hand.add(startHand2.getCard(2))

                    table.add(startHand1.getCard(0))
                    table.add(startHand1.getCard(1))
                    table.add(startHand1.getCard(2))
                }
                hand = p2hand
            }

            thirtyOne()
            if(thirtyOne === false){
                nextPlayerMenu()
            }


            dragView1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
            dragView2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
            dragView3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))

            dragView01.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
            dragView02.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
            dragView03.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))
        }

        if (requestCode == THIRD_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                startHandView()
            }
        }
    }

    fun startHandView() {
        game = SchwimmenClass()
        game.startGame(game)
        deck = DeckClass(2)
        deck.shuffle()
        table = HandClass(deck, "Null")
        dump = HandClass(deck, "Null")

        startHand1 = HandClass(deck, "Schwimmen")
        startHand2 = HandClass(deck, "Schwimmen")
        val card1 = startHand1.getPic(startHand1.getCard(0))
        val card2 = startHand1.getPic(startHand1.getCard(1))
        val card3 = startHand1.getPic(startHand1.getCard(2))

        if (playerStart == player1Name) {
            name = player1Name
        } else {
            name = player2Name
        }

        val intent = Intent(this, PopUpKartenauswahlActivity::class.java)
        intent.putExtra("p1hand", card1)
        intent.putExtra("p1hand2", card2)
        intent.putExtra("p1hand3", card3)
        intent.putExtra("name", name)
        intent.putExtra("idPos1", p1Pos)
        intent.putExtra("idPos2", p2Pos)
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
    }

    fun nextPlayerMenu() {
        if (hand == p2hand) {
            name = player2Name
        } else {
            name = player1Name
        }

        val intent = Intent(this, PopUpSpielerwechselActivity::class.java)
        intent.putExtra("heart1", player1Hearts)
        intent.putExtra("heart2", player2Hearts)
        intent.putExtra("name", name)
        intent.putExtra("player1name", player1Name)
        intent.putExtra("player2name", player2Name)
        intent.putExtra("knock", knock)
        intent.putExtra("shove", shove)
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
        }, 500)
    }

    fun roundEnd() {

        if (player1Hearts >= 0 && player2Hearts >= 0) {
            val intent = Intent(this, PopUpRundenendeActivity::class.java)
            intent.putExtra("textWinner", textWinner)
            intent.putExtra("player1name", player1Name)
            intent.putExtra("player2name", player2Name)
            intent.putExtra("playerStart", playerStart)
            startActivityForResult(intent, THIRD_ACTIVITY_REQUEST_CODE)

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

                } else if (playerStart == player2Name) {
                    game.startHand(p1hand, table, deck)
                    p2hand = HandClass(deck, "Schwimmen")
                    hand = p1hand
                    playerStart = player1Name
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

            }, 500)

        } else {
            gameEnd()
        }
    }

    fun gameEnd() {
        var winner = ""

        if (player1Hearts < 0) {
            winner = " $player2Name"
        } else {
            winner = " $player1Name"
        }
        calculatePermille()

        db.updateData(
            player1id,
            player1Name,
            player1age,
            player1size,
            player1weight,
            player1gender,
            player1drink,
            player1permille
        )
        db.updateData(
            player2id,
            player2Name,
            player2age,
            player2size,
            player2weight,
            player2gender,
            player2drink,
            player2permille
        )

        val intent = Intent(this, PopUpSpielendeActivity::class.java)
        intent.putExtra("WinnerWinner", winner)
        intent.putExtra("p1Pos", p1Pos)
        intent.putExtra("p2Pos", p2Pos)
        intent.putExtra("player1Name", player1Name)
        intent.putExtra("player2Name", player2Name)
        intent.putExtra("p1ID", player1id)
        intent.putExtra("p2ID", player2id)
        intent.putExtra("player1permille", player1permille)
        intent.putExtra("player2permille", player2permille)
        startActivity(intent)
    }

    fun onClickChangeCards(view: View) {
        val Button: Button = findViewById(R.id.single_change_button)!!
        Button.isClickable = false

        val hand1: ImageView = findViewById(R.id.player_card_01)!!
        val table1: ImageView = findViewById(R.id.table_card_01)!!
        val hand2: ImageView = findViewById(R.id.player_card_02)!!
        val table2: ImageView = findViewById(R.id.table_card_02)!!
        val hand3: ImageView = findViewById(R.id.player_card_03)!!
        val table3: ImageView = findViewById(R.id.table_card_03)!!

        game.changeCard(object1, object2, hand, table)

        if (object1 == 0 && object2 == 0) {
            hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
            table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        } else if (object1 == 0 && object2 == 1) {
            hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
            table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        } else if (object1 == 0 && object2 == 2) {
            hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
            table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))
        } else if (object1 == 1 && object2 == 0) {
            hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
            table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        } else if (object1 == 1 && object2 == 1) {
            hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
            table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        } else if (object1 == 1 && object2 == 2) {
            hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
            table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))
        } else if (object1 == 2 && object2 == 0) {
            hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
            table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        } else if (object1 == 2 && object2 == 1) {
            hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
            table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        } else if (object1 == 2 && object2 == 2) {
            hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
            table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))
        }

        hand1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))

        thirtyOne()

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
                    textWinner = player1Name
                    player2Hearts--
                    player2ml += 100.0
                } else if (winner === 2) {
                    textWinner = player2Name
                    player1Hearts--
                    player1ml += 100.0
                } else {
                    textWinner = "Yippieh, Unentschieden!"
                }

                roundEnd()

            } else {
                Handler().postDelayed({
                    nextPlayerMenu()
                }, 1500)
            }
        }

        Handler().postDelayed({
            Button.isClickable = true
        }, 2000)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onClickSwapCards(view: View) {
        val Button: Button = findViewById(R.id.tauschen_button)!!
        Button.isClickable = false

        val z1 = table.getIndex(table.getCard(0))
        val z2 = table.getIndex(table.getCard(1))
        val z3 = table.getIndex(table.getCard(2))

        val hand1: ImageView = findViewById(R.id.player_card_01)!!
        val table1: ImageView = findViewById(R.id.table_card_01)!!
        val hand2: ImageView = findViewById(R.id.player_card_02)!!
        val table2: ImageView = findViewById(R.id.table_card_02)!!
        val hand3: ImageView = findViewById(R.id.player_card_03)!!
        val table3: ImageView = findViewById(R.id.table_card_03)!!

        game.changeCard(hand.getIndex(hand.getCard(0)), z1, hand, table)
        game.changeCard(hand.getIndex(hand.getCard(1)), z2, hand, table)
        game.changeCard(hand.getIndex(hand.getCard(2)), z3, hand, table)

        hand1.setImageDrawable(getDrawable(hand.getPic(hand.getCard(0))))
        table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
        hand2.setImageDrawable(getDrawable(hand.getPic(hand.getCard(1))))
        table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
        hand3.setImageDrawable(getDrawable(hand.getPic(hand.getCard(2))))
        table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

        hand1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))

        thirtyOne()

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
                    textWinner = player1Name
                    player2Hearts--
                    player2ml += 100.0
                } else if (winner === 2) {
                    textWinner = player2Name
                    player1Hearts--
                    player1ml += 100.0
                } else {
                    textWinner = "Yippieh, Unentschieden!"
                }

                roundEnd()

            } else {
                Handler().postDelayed({
                    nextPlayerMenu()
                }, 1500)
            }
        }

        Handler().postDelayed({
            Button.isClickable = true
        }, 2000)
    }

    fun thirtyOne() {
        var winner: Int = game.endGame(p1hand, p2hand)

        if (winner === 331) {
            textWinner = player1Name
            player2Hearts--
            player2ml += 100.0
            thirtyOne = true
            roundEnd()
        } else if (winner === 332) {
            textWinner = player2Name
            player1Hearts--
            player1ml += 100.0
            thirtyOne = true
            roundEnd()
        } else if (winner === 311) {
            textWinner = player1Name
            player2Hearts--
            player2ml += 100.0
            thirtyOne = true
            roundEnd()
        } else if (winner === 312) {
            textWinner = player2Name
            player1Hearts--
            player1ml += 100.0
            thirtyOne = true
            roundEnd()
        }
    }

    fun onClickKnockCards(view: View) {
        val Button: Button = findViewById(R.id.klopfen_button)!!
        Button.isClickable = false

        val hand1: ImageView = findViewById(R.id.player_card_01)!!
        val table1: ImageView = findViewById(R.id.table_card_01)!!
        val hand2: ImageView = findViewById(R.id.player_card_02)!!
        val table2: ImageView = findViewById(R.id.table_card_02)!!
        val hand3: ImageView = findViewById(R.id.player_card_03)!!
        val table3: ImageView = findViewById(R.id.table_card_03)!!

        if (knockStarterHand != p1hand && knockStarterHand != p2hand) {
            if (hand === p1hand) {
                knockStarterHand = p1hand
                knock = true
                Handler().postDelayed({
                    nextPlayerMenu()
                }, 1500)
            } else if (hand === p2hand) {
                knockStarterHand = p2hand
                knock = true
                Handler().postDelayed({
                    nextPlayerMenu()
                }, 1500)
            }
        } else if (knock) {
            knock = false
            knockStarterHand = HandClass(deck, "Null")
            val winner: Int = game.endGame(p1hand, p2hand)
            if (winner === 1) {
                textWinner = player1Name
                player2Hearts--
                player2ml += 100.0
            } else if (winner === 2) {
                textWinner = player2Name
                player1Hearts--
                player1ml += 100.0
            } else {
                textWinner = "Yippieh, Unentschieden!"
            }

            roundEnd()

        } else {
            Handler().postDelayed({
                nextPlayerMenu()
            }, 1500)
        }

        hand1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))

        if (shove) {
            shove = false
            shoveStarterHand = HandClass(deck, "Null")
        }

        Handler().postDelayed({
            Button.isClickable = true
        }, 2000)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onClickShoveCards(view: View) {
        val Button: Button = findViewById(R.id.schieben_button)!!
        Button.isClickable = false

        val hand1: ImageView = findViewById(R.id.player_card_01)!!
        val table1: ImageView = findViewById(R.id.table_card_01)!!
        val hand2: ImageView = findViewById(R.id.player_card_02)!!
        val table2: ImageView = findViewById(R.id.table_card_02)!!
        val hand3: ImageView = findViewById(R.id.player_card_03)!!
        val table3: ImageView = findViewById(R.id.table_card_03)!!

        if (!shove) {
            if (hand === p1hand) {
                shoveStarterHand = p1hand
                shove = true
                Handler().postDelayed({
                    nextPlayerMenu()
                }, 1500)
            } else if (hand === p2hand) {
                shoveStarterHand = p2hand
                shove = true
                Handler().postDelayed({
                    nextPlayerMenu()
                }, 1500)
            }
        } else if (shove) {
            if (hand === p2hand && shoveStarterHand == p1hand) {
                game.push(table, dump, deck, true)

                val table1: ImageView = findViewById(R.id.table_card_01)!!
                val table2: ImageView = findViewById(R.id.table_card_02)!!
                val table3: ImageView = findViewById(R.id.table_card_03)!!

                table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
                table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
                table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

                shove = false
                shoveStarterHand = HandClass(deck, "Null")

                Handler().postDelayed({
                    nextPlayerMenu()
                }, 1500)
            } else if (hand === p1hand && shoveStarterHand == p2hand) {
                game.push(table, dump, deck, true)

                val table1: ImageView = findViewById(R.id.table_card_01)!!
                val table2: ImageView = findViewById(R.id.table_card_02)!!
                val table3: ImageView = findViewById(R.id.table_card_03)!!

                table1.setImageDrawable(getDrawable(table.getPic(table.getCard(0))))
                table2.setImageDrawable(getDrawable(table.getPic(table.getCard(1))))
                table3.setImageDrawable(getDrawable(table.getPic(table.getCard(2))))

                shove = false
                shoveStarterHand = HandClass(deck, "Null")

                Handler().postDelayed({
                    nextPlayerMenu()
                }, 1500)
            }
        }

        hand1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        hand3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table1.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table2.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))
        table3.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor))

        if (knock) {
            knock = false
            knockStarterHand = HandClass(deck, "Null")

            val winner: Int = game.endGame(p1hand, p2hand)
            if (winner === 1) {
                textWinner = player1Name
                player2Hearts--
                player2ml += 100.0
            } else if (winner === 2) {
                textWinner = player2Name
                player1Hearts--
                player1ml += 100.0
            } else {
                textWinner = "Yippieh, Unentschieden!"
            }

            roundEnd()
        }

        Handler().postDelayed({
            Button.isClickable = true
        }, 2000)
    }

    fun promilleAnzeige(view: View) {
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

}




