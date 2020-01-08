package com.whammy

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

    println("Your turn.")

    val (v, h) = readLine()!!.split(" ").map { it.toInt() }

    println("Accepted. Vertical : $v, horizontal : $h")
    exitProcess(0)
}

