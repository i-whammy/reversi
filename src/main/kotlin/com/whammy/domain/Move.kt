package com.whammy.domain

class Move(val point: Point, val stone: Stone)

data class BoardStones(val stones: List<BoardStone>)

data class BoardStone(val point: Point, val stone: Stone)