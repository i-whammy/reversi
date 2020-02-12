package com.whammy

import com.whammy.handler.CLIGameHandler

fun main(args: Array<String>) {
    val handler = CLIGameHandler()
    val board = handler.init()
    handler.handleGame(board)
}
