package com.whammy.handler

import com.whammy.domain.Board

interface GameHandler {
    fun init(): Board
    fun handleMoves(board: Board)
}