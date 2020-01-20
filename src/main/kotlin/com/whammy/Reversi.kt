package com.whammy

import com.whammy.domain.Board
import com.whammy.domain.Move
import com.whammy.domain.OutOfRangeException
import com.whammy.domain.Stone
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Hello, reversi!")
    val board = Board()

    board.printBoard()

    handleMove(board)

    exitProcess(0)
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

private fun handleMove(board: Board) {
    println("Your turn.")
    println("Input number like 3 5.")
    // TODO Handle null, non-Integer value
    val (v, h) = readLine()!!.split(" ").map { it.toInt() }
    try {
        val move = Move(v, h, Stone.BLACK)
        board.add(move)
        println("Accepted. Vertical : $v, horizontal : $h")
    } catch (e: OutOfRangeException) {
        println(e.message)
        handleMove(board)
    }
}

