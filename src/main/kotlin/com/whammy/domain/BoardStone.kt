package com.whammy.domain

data class TargetBoardStones(val stones: List<BoardStone>)

data class BoardStone(val point: Point, val stone: Stone)