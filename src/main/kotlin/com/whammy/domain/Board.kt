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
        this.lines[move.vertical.v - 1].stones[move.horizontal.v - 1] = move.stone
        val turnables = this.getTurnableDirections(move)
        turnables.forEach {
            when (it) {
                // TODO 斜めの実装
                Direction.Left -> turnAtLeft(move)
                Direction.Right -> turnAtRight(move)
                Direction.Up -> turnAtUp(move)
                Direction.Low -> turnAtLow(move)
            }
        }
        return this
    }

    fun getTurnableDirections(move: Move): List<Direction> {
        val directions = mutableListOf<Direction>()
        if (!move.vertical.isUpperEdge() && !move.horizontal.isLeftEdge() &&
            this.getAt(Vertical(move.vertical.v-1), Horizontal(move.horizontal.v-1)) == move.stone.opposite()) directions.add(Direction.UpperLeft)
        if (!move.vertical.isUpperEdge() &&
            this.getAt(Vertical(move.vertical.v-1), move.horizontal) == move.stone.opposite()) directions.add(Direction.Up)
        if (!move.vertical.isUpperEdge() && !move.horizontal.isRightEdge() &&
            this.getAt(Vertical(move.vertical.v-1), Horizontal(move.horizontal.v+1)) == move.stone.opposite()) directions.add(Direction.UpperRight)
        if (!move.horizontal.isRightEdge() &&
            this.getAt(move.vertical, Horizontal(move.horizontal.v+1)) == move.stone.opposite()) directions.add(Direction.Right)
        if (!move.horizontal.isRightEdge() && !move.vertical.isLowerEdge() &&
            this.getAt(Vertical(move.vertical.v+1), Horizontal(move.horizontal.v+1)) == move.stone.opposite()) directions.add(Direction.LowerRight)
        if (!move.vertical.isLowerEdge() &&
            this.getAt(Vertical(move.vertical.v+1), move.horizontal) == move.stone.opposite()) directions.add(Direction.Low)
        if (!move.vertical.isLowerEdge() && !move.horizontal.isLeftEdge() &&
            this.getAt(Vertical(move.vertical.v+1), Horizontal(move.horizontal.v-1)) == move.stone.opposite()) directions.add(Direction.LowerLeft)
        if (!move.horizontal.isLeftEdge() &&
            this.getAt(move.vertical, Horizontal(move.horizontal.v-1)) == move.stone.opposite()) directions.add(Direction.Left)
        return directions
    }

    fun getAt(vertical: Vertical, horizontal: Horizontal): Stone {
        return this.lines[vertical.v-1].stones[horizontal.v-1]
    }

    private fun turnAtLeft(move: Move) {
        val targetStones = this.lines[move.vertical.v - 1].stones.subList(0, move.horizontal.v - 1)
        if (targetStones.contains(move.stone)
            && !targetStones.subList(targetStones.lastIndexOf(move.stone), targetStones.size).contains(Stone.NONE)) {
            for (i in move.horizontal.v - 2 downTo 0) {
                if (this.lines[move.vertical.v - 1].stones[i] == move.stone) break
                this.lines[move.vertical.v - 1].stones[i] = move.stone
            }
        }
    }

    private fun turnAtRight(move: Move) {
        val targetStones = this.lines[move.vertical.v - 1].stones.subList(move.horizontal.v, 8)
        if (targetStones.contains(move.stone)
            && !targetStones.subList(0, targetStones.indexOf(move.stone)).contains(Stone.NONE)) {
            for (i in move.horizontal.v .. 7) {
                if (this.lines[move.vertical.v - 1].stones[i] == move.stone) break
                this.lines[move.vertical.v - 1].stones[i] = move.stone
            }
        }
    }

    private fun turnAtUp(move: Move) {
        if (this.lines[move.vertical.v - 1].stones.subList(0,move.horizontal.v-1).contains(move.stone)
            && !this.lines[move.vertical.v-1].stones.subList(0,move.horizontal.v-1).contains(Stone.NONE)) {
            for (i in move.horizontal.v - 2 downTo 0) {
                if (this.lines[move.vertical.v - 1].stones[i] == move.stone) break
                this.lines[move.vertical.v - 1].stones[i] = move.stone
            }
        }
    }

    private fun turnAtLow(move: Move) {
        if (this.lines[move.vertical.v - 1].stones.subList(0,move.horizontal.v-1).contains(move.stone)
            && !this.lines[move.vertical.v-1].stones.subList(0,move.horizontal.v-1).contains(Stone.NONE)) {
            for (i in move.horizontal.v - 2 downTo 0) {
                if (this.lines[move.vertical.v - 1].stones[i] == move.stone) break
                this.lines[move.vertical.v - 1].stones[i] = move.stone
            }
        }
    }

}

class Line {
    val stones: MutableList<Stone> = MutableList(8) { Stone.NONE }
}

enum class Stone {
    NONE, BLACK, WHITE;
    fun opposite(): Stone {
        return when (this) {
            BLACK -> WHITE
            WHITE -> BLACK
            else -> NONE
        }
    }
}