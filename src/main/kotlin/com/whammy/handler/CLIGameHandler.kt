package com.whammy.handler

import com.whammy.domain.*
import kotlin.system.exitProcess

class CLIGameHandler() : GameHandler {
    override fun init(): Board {
        println("Hello, reversi!")
        return Board()
    }

    override fun handleMoves(board: Board) {
        println("Your turn.")
        println("Input number like 3 5.")
        val (v, h) = readLine()!!.split(" ").map { it.toInt() }
        try {
            val move = Move(Point(VerticalCoordinate(v), HorizontalCoordinate(h)), Stone.BLACK)
            println("Accepted. Vertical : $v, horizontal : $h")
            board.add(move).printBoard()
            exitProcess(0)
        } catch (e: OutOfRangeException) {
            println(e.message)
            handleMoves(board)
        } catch (e: NoTurnableStoneException) {
            println(e.message)
            handleMoves(board)
        }
    }
}

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