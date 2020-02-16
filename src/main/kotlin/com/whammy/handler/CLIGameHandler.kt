package com.whammy.handler

import com.whammy.domain.*

class CLIGameHandler() : GameHandler {
    override fun init(): Board {
        println("Hello, reversi!")
        val state = BoardState()
        return Board(state)
    }

    override fun handleGame(board: Board) {
        while (!board.isGameEnded()) {
            if (board.getValidPoints(Stone.BLACK).isNotEmpty()) {
                handleUserInput(board)
            } else {
                println("Skip your turn because you cannot put any stone.")
            }
            if (board.getValidPoints(Stone.WHITE).isNotEmpty()) {
                handleCPUInput(board)
            } else {
                println("Skip CPU turn because CPU cannot put any stone.")
            }
        }
        println(if (board.count(Stone.BLACK) > board.count(Stone.WHITE)) "You win!!" else "You lose")
        println("Game ended.")
    }

    private fun handleUserInput(board: Board) {
        println("Your turn.")
        println("Input number like 3 5.")
        board.printBoard()
        try {
            val (v, h) = readLine()!!.split(" ").map { it.toInt() }
            val move = Move(Point(Vertical(v), Horizontal(h)), Stone.BLACK)
            board.add(move).printBoard()
        } catch (e: OutOfRangeException) {
            println(e.message)
            handleUserInput(board)
        } catch (e: NoTurnableStoneException) {
            println(e.message)
            handleUserInput(board)
        } catch (e: Exception) {
            println("Invalid input. Input number like 3 5.")
            handleUserInput(board)
        }
    }

    private fun handleCPUInput(board: Board) {
        val (verticalCoordinate, horizontalCoordinate) = board.getValidPoints(Stone.WHITE)[0]
        board.add(Move(Point(verticalCoordinate, horizontalCoordinate), Stone.WHITE))
    }
}

private fun Board.printBoard() {
    this.getAllStonesPerLines().map {
        var str = "|"
        it.map {boardStone ->
            str += when (boardStone.stone) {
                Stone.BLACK -> "x"
                Stone.WHITE -> "o"
                else -> "-"
            }
            str += "|"
        }
        println(str)
    }
}