package com.whammy.domain

class Board {
    val lines: MutableList<Line> = MutableList(8) { Line() }

    init {
        lines[3].stones[3] = Stone.WHITE
        lines[3].stones[4] = Stone.BLACK
        lines[4].stones[3] = Stone.BLACK
        lines[4].stones[4] = Stone.WHITE
    }

    fun add(move: Move) : Board {
        this.lines[move.vertical - 1].stones[move.horizontal - 1] = move.stone
        if (this.lines[move.vertical - 1].stones[move.horizontal - 2] != move.stone
            && this.lines[move.vertical - 1].stones.subList(0,move.horizontal-1).contains(move.stone)) {
            for (i in move.horizontal - 2 downTo 0) {
                if (this.lines[move.vertical - 1].stones[i] == move.stone) break
                this.lines[move.vertical - 1].stones[i] = move.stone
            }
        }
        return this
    }
}

class Line {
    val stones: MutableList<Stone> = MutableList(8) { Stone.NONE }
}

enum class Stone {
    NONE, BLACK, WHITE
}