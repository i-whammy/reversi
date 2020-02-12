package com.whammy.handler

import com.whammy.domain.Board

interface GameHandler {
    fun init(): Board
    fun handleGame(board: Board)
}