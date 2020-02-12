package com.whammy.handler

import com.whammy.domain.*

class CLIGameHandler() : GameHandler {
    override fun init(): Board {
        println("Hello, reversi!")
        return Board()
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
        // TODO result
        println("Game ended.")
    }

    private fun handleUserInput(board: Board) {
        println("Your turn.")
        println("Input number like 3 5.")
        board.printBoard()
        try {
            val (v, h) = readLine()!!.split(" ").map { it.toInt() }
            val move = Move(Point(VerticalCoordinate(v), HorizontalCoordinate(h)), Stone.BLACK)
            board.add(move).printBoard()
            println("Accepted. Vertical : $v, horizontal : $h")
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

// handleGame
// 先攻：プレイヤー
// 後攻：CPU
// userInput -> CPUInput

private fun Board.printBoard() {
    this.lines.map {
        var str = "|"
        it.stones.map {stone ->
            str += when (stone) {
                Stone.BLACK -> "x"
                Stone.WHITE -> "o"
                else -> "-"
            }
            str += "|"
        }
        println(str)
    }
}