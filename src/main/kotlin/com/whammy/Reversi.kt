package com.whammy

import com.whammy.domain.Move
import com.whammy.domain.OutOfRangeException
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Hello, reversi!")

    println("|-|-|-|-|-|-|-|-|")
    println("|-|-|-|-|-|-|-|-|")
    println("|-|-|-|-|-|-|-|-|")
    println("|-|-|-|x|o|-|-|-|")
    println("|-|-|-|o|x|-|-|-|")
    println("|-|-|-|-|-|-|-|-|")
    println("|-|-|-|-|-|-|-|-|")
    println("|-|-|-|-|-|-|-|-|")

    handleMove()

    exitProcess(0)
}

private fun handleMove() {
    println("Your turn.")
    println("Input number like 3 5.")
    // TODO Handle null, non-Integer value
    val (v, h) = readLine()!!.split(" ").map { it.toInt() }
    try {
        Move(v, h)
        println("Accepted. Vertical : $v, horizontal : $h")
    } catch (e: OutOfRangeException) {
        println(e.message)
        handleMove()
    }
}

