package com.whammy

import com.whammy.domain.*
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
    // TODO Handle null, non-Integer v
    val (v, h) = readLine()!!.split(" ").map { it.toInt() }
    try {
        val move = Move(Point(VerticalCoordinate(v), HorizontalCoordinate(h)), Stone.BLACK)
        println("Accepted. Vertical : $v, horizontal : $h")
        board.add(move).printBoard()
    } catch (e: OutOfRangeException) {
        println(e.message)
        handleMove(board)
    }
}

