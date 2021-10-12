package com.example.tictactoe

/*
        board values description
      0 - empty space
      1 - "x" symbol
      2 - "o" symbol
*/

class Board {
    private var board: Array<Array<Int>> = arrayOf()

    init {
        //fill array 3x3 with 0
        for (i in 0..2) {
            var array = arrayOf<Int>()
            for (j in 0..2) {
                array += 0
            }
            board += array
        }
    }

    fun getValue(row: Int, col: Int) = board[row][col]

    fun playerMakeMove(row: Int, col: Int) {
        board[row][col] = 1
    }

    fun computerMakeMove() {
        var row: Int
        var col: Int
        do {
            row = (0..2).random()
            col = (0..2).random()
        } while (!checkForAvailableSpace(row, col))
        board[row][col] = 2
    }

    fun checkForAvailableSpace(row: Int, col: Int): Boolean {
        return board[row][col] == 0
    }

    fun checkForAvailableSpace(): Boolean {
        var value = false
        for (i in 0..2) for (j in 0..2) if (board[i][j] == 0) value = true
        return value
    }


    fun checkPlayerWon(): Boolean {
        var value = false

        if (board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1) value = true
        if (board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1) value = true
        if (board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1) value = true

        if (board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1) value = true
        if (board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1) value = true
        if (board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1) value = true

        if (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) value = true
        if (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1) value = true

        var v = ""
        for (i in 0..2) {
            for (j in 0..2) {
                v += board[i][j].toString() + " "
            }
            v += "\n"
        }

        return value
    }

    fun checkComputerWon(): Boolean {
        var value = false

        if (board[0][0] == 2 && board[0][1] == 2 && board[0][2] == 2) value = true
        if (board[1][0] == 2 && board[1][1] == 2 && board[1][2] == 2) value = true
        if (board[2][0] == 2 && board[2][1] == 2 && board[2][2] == 2) value = true

        if (board[0][0] == 2 && board[1][0] == 2 && board[2][0] == 2) value = true
        if (board[0][1] == 2 && board[1][1] == 2 && board[2][1] == 2) value = true
        if (board[2][0] == 2 && board[2][1] == 2 && board[2][2] == 2) value = true

        if (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2) value = true
        if (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2) value = true

        return value
    }

}
