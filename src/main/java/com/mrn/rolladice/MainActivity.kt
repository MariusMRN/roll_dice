package com.mrn.rolladice

import android.content.Context
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var DiceImage1: ImageView
    lateinit var DiceImage2: ImageView
    lateinit var DiceImage3: ImageView //= findViewById(R.id.diceImage3)
    lateinit var DiceImage4: ImageView  //= findViewById(R.id.diceImage4)

    var TotalRandomValue = 0
    var Dices = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val DiceSound: MediaPlayer = MediaPlayer.create(this, R.raw.dice_sound)

        val DiceButton: Button = findViewById(R.id.b_dice)
        DiceImage1 = findViewById(R.id.diceImage)
        DiceImage2 = findViewById(R.id.diceImage2)
        DiceImage3 = findViewById(R.id.diceImage3)
        DiceImage4 = findViewById(R.id.diceImage4)

        val AddDiceButton: ImageView = findViewById(R.id.b_addDice)
        val RemoveDiceButton: ImageView = findViewById(R.id.b_removeDice)

        DiceButton.setOnClickListener{

            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(200)

                rollDice()
        }

        AddDiceButton.setOnClickListener {
            addDice()
        }

        RemoveDiceButton.setOnClickListener{
            removeDice()
        }


    }

    private fun rollDice() {
        Toast.makeText(this, "Dice rolled", Toast.LENGTH_SHORT).show()

        val ResultDice: TextView = findViewById(R.id.resultDice)

        val RandomValue1 = Random.nextInt(6) + 1
        val RandomValue2 = Random.nextInt(6) + 1
        val RandomValue3 = Random.nextInt(6) + 1
        val RandomValue4 = Random.nextInt(6) + 1

        if ( Dices == 1 ){
            TotalRandomValue = RandomValue1;
        }
        if ( Dices == 2 ){
            TotalRandomValue = RandomValue1 + RandomValue2
        }
        if ( Dices == 3 ){
            TotalRandomValue = RandomValue1 + RandomValue2 + RandomValue3
        }
        if ( Dices == 4 ){
            TotalRandomValue = RandomValue1 + RandomValue2 + RandomValue3 + RandomValue4
        }

        val DrawableResource = when (RandomValue1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else
                -> R.drawable.dice_6
        }

        val DrawableResource2 = when (RandomValue2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else
                -> R.drawable.dice_6
        }

        val DrawableResource3 = when (RandomValue3){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else
            -> R.drawable.dice_6
        }

        val DrawableResource4 = when (RandomValue4){

            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else
            -> R.drawable.dice_6
        }

        DiceImage1.setImageResource(DrawableResource)
        DiceImage2.setImageResource(DrawableResource2)
        DiceImage3.setImageResource(DrawableResource3)
        DiceImage4.setImageResource(DrawableResource4)

        ResultDice.text = "You rolled: " + TotalRandomValue.toString()

    }

    private fun addDice(){

        Dices++

        Toast.makeText(this, "Dice added" , Toast.LENGTH_SHORT).show()

        if ( Dices == 2 ){
            DiceImage2.visibility = View.VISIBLE
        }

        if ( Dices == 3 ){
            DiceImage3.visibility = View.VISIBLE
        }
        if ( Dices == 4 ){
            DiceImage4.visibility = View.VISIBLE
        }
        if ( Dices == 5 ){
            Toast.makeText(this,"You reached the maximum number of Dies", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeDice(){

        Toast.makeText(this,"Dice removed", Toast.LENGTH_SHORT).show()

        if ( Dices == 4 )
            DiceImage4.visibility = View.INVISIBLE
        if ( Dices == 3 )
            DiceImage3.visibility = View.INVISIBLE
        if ( Dices == 2 )
            DiceImage2.visibility = View.INVISIBLE

        Dices--

        if ( Dices == 1 )
            Toast.makeText(this, "You can't remove more Dices", Toast.LENGTH_SHORT).show()
    }



}
