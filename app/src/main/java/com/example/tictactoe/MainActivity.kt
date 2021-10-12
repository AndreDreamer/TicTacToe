package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tictactoe.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TIME_TO_THINK_MIN = 2
    private val TIME_TO_THINK_MAX = 10

    lateinit var binding: ActivityMainBinding

    private var board1 = Board()
    private var board2 = Board()

    private var buttonsBoard1: Array<Array<Button>> = arrayOf()
    private var buttonsBoard2: Array<Array<Button>> = arrayOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBoards()
        setupViews()
        refreshBoardUI()
    }

    private fun setupViews() {
        with(binding) {
            restartBtn.setOnClickListener {
                board1 = Board()
                board2 = Board()
                refreshBoardUI()
                textView.text = resources.getString(R.string.your_turn_text)
                textView2.text = resources.getString(R.string.your_turn_text)
                unlockButtons(buttonsBoard1)
                unlockButtons(buttonsBoard2)
            }
        }

        //buttons for 1 board
        for (i in 0..2) for (j in 0..2)
            buttonsBoard1[i][j].setOnClickListener {
                if (board1.checkForAvailableSpace(i, j)) {
                    board1.playerMakeMove(i, j)
                    refreshBoardUI()
                    if (!board1.checkPlayerWon()) {
                        if (!board1.checkForAvailableSpace()) {
                            binding.textView.text = resources.getString(R.string.tie_text)
                        } else {
                            computerTurn(board1, binding.textView, buttonsBoard1)
                            if (board1.checkComputerWon()) {
                                binding.textView.text =
                                    resources.getString(R.string.computer_won_text)
                                lockButtons(buttonsBoard1)
                            }
                        }
                    } else {
                        binding.textView.text = resources.getString(R.string.player_won_text)
                        lockButtons(buttonsBoard1)
                    }
                }
            }
        //buttons for 2 board
        for (i in 0..2) for (j in 0..2)
            buttonsBoard2[i][j].setOnClickListener {
                if (board2.checkForAvailableSpace(i, j)) {
                    board2.playerMakeMove(i, j)
                    refreshBoardUI()
                    if (!board2.checkPlayerWon()) {
                        if (!board2.checkForAvailableSpace()) {
                            binding.textView2.text = resources.getString(R.string.tie_text)
                        } else {
                            computerTurn(board2, binding.textView2, buttonsBoard2)
                            if (board2.checkComputerWon()) {
                                binding.textView2.text =
                                    resources.getString(R.string.computer_won_text)
                                lockButtons(buttonsBoard2)
                            }
                        }
                    } else {
                        binding.textView2.text = resources.getString(R.string.player_won_text)
                        lockButtons(buttonsBoard2)
                    }
                }
            }
    }


    private fun computerTurn(board: Board, label: TextView, buttonBoard: Array<Array<Button>>) =
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {

                runOnUiThread {
                    label.text = resources.getString(R.string.computer_turn_text)
                    lockButtons(buttonBoard)

                }

                delay((TIME_TO_THINK_MIN..TIME_TO_THINK_MAX).random() * 1000L)
                board.computerMakeMove()
                refreshBoardUI()

                runOnUiThread {
                    label.text = resources.getString(R.string.your_turn_text)
                    unlockButtons(buttonBoard)
                }
            }
        }

    private fun setupBoards() {
        with(binding) {
            var array = arrayOf(button1, button2, button3)
            buttonsBoard1 += array
            array = arrayOf(button4, button5, button6)
            buttonsBoard1 += array
            array = arrayOf(button7, button8, button9)
            buttonsBoard1 += array

            array = arrayOf(button10, button11, button12)
            buttonsBoard2 += array
            array = arrayOf(button13, button14, button15)
            buttonsBoard2 += array
            array = arrayOf(button16, button17, button18)
            buttonsBoard2 += array

        }
    }

    private fun lockButtons(buttonBoard: Array<Array<Button>>) {
        for (i in 0..2) for (j in 0..2) buttonBoard[i][j].isClickable = false
    }

    private fun unlockButtons(buttonBoard: Array<Array<Button>>) {
        for (i in 0..2) for (j in 0..2) buttonBoard[i][j].isClickable = true
    }

    private fun refreshBoardUI() {
        for (i in 0..2) for (j in 0..2)
            when (board1.getValue(i, j)) {
                0 -> buttonsBoard1[i][j].text = " "
                1 -> buttonsBoard1[i][j].text = "X"
                2 -> buttonsBoard1[i][j].text = "O"
            }

        for (i in 0..2) for (j in 0..2)
            when (board2.getValue(i, j)) {
                0 -> buttonsBoard2[i][j].text = " "
                1 -> buttonsBoard2[i][j].text = "X"
                2 -> buttonsBoard2[i][j].text = "O"
            }
    }
}