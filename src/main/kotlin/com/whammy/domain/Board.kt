package com.whammy.domain

class Board {
    val lines: MutableList<Line> = mutableListOf(
        Line(),
        Line(),
        Line(),
        Line(),
        Line(),
        Line(),
        Line(),
        Line()
    )

    init {
        lines[3].stones[3] = Stone.WHITE
        lines[3].stones[4] = Stone.BLACK
        lines[4].stones[3] = Stone.BLACK
        lines[4].stones[4] = Stone.WHITE
    }
}

class Line {
    val stones: MutableList<Stone> = mutableListOf(
        Stone.NONE,
        Stone.NONE,
        Stone.NONE,
        Stone.NONE,
        Stone.NONE,
        Stone.NONE,
        Stone.NONE,
        Stone.NONE
    )
}

enum class Stone {
    NONE, BLACK, WHITE
}