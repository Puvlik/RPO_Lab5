package com.example.focused

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var game = Game()
    var buttons: Array<Button> = arrayOf()
    var numbers: Array<Int> = arrayOf()
    var flipCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12)
        numbers = arrayOf(1, 2, 3, 4, 5, 6)
        initialSetup()
    }

    fun initialSetup() {
        flipCount = 0
        textView.text = "Flip Count: " + flipCount
        for (i in 0..11) {
            buttons[i].setOnClickListener {
                this.flipCount++
                textView.text = "Flip Count: " + flipCount
                game.onClicked(i)
                update()
                checkIfEnd()
            }
        }
        update()
    }

    fun update() {
        for (i in 0..11) {
            val button = buttons[i]
            val card = game.cards[i]
            if (card.isFlipedUp) {
                button.text = numbers[card.id].toString()
                button.setBackgroundResource(R.color.colorPrimary)
            } else {
                button.text = ""
                if (card.isMatched) {
                    button.setBackgroundResource(R.color.white)
                    button.visibility = View.INVISIBLE
                } else {
                    button.setBackgroundResource(R.color.colorPrimaryDark)
                }
            }
        }
    }

    fun checkIfEnd() {
        if (game.isTheEnd()) {
            buttons.forEach {
                it.visibility = View.VISIBLE
                it.setBackgroundResource(R.color.colorPrimaryDark)
            }
            game = Game()
            initialSetup()
        }
    }
}
