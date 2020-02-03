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
        this.lines[move.point.verticalCoordinate.value - 1].stones[move.point.horizontalCoordinate.value - 1] = move.stone
        val turnables = this.getTurnableDirections(move)
        turnables.forEach {
            when (it) {
                // TODO 斜めの実装
                Direction.Left -> turnAtLeft(move)
                Direction.Right -> turnAtRight(move)
                Direction.Top -> turnAtTop(move)
                Direction.Bottom -> turnAtBottom(move)
            }
        }
        return this
    }

    fun getTurnableDirections(move: Move): List<Direction> {
        val directions = mutableListOf<Direction>()
        val point = move.point
        val stone = move.stone
        if (!point.verticalCoordinate.isTopEdge() && !point.horizontalCoordinate.isLeftEdge() &&
            this.getAt(Point(VerticalCoordinate(point.verticalCoordinate.value-1), HorizontalCoordinate(point.horizontalCoordinate.value-1))) == stone.opposite()) directions.add(Direction.TopLeft)
        if (!point.verticalCoordinate.isTopEdge() &&
            this.getAt(Point(VerticalCoordinate(point.verticalCoordinate.value-1), point.horizontalCoordinate)) == move.stone.opposite()) directions.add(Direction.Top)
        if (!point.verticalCoordinate.isTopEdge() && !point.horizontalCoordinate.isRightEdge() &&
            this.getAt(Point(VerticalCoordinate(point.verticalCoordinate.value-1), HorizontalCoordinate(point.horizontalCoordinate.value+1))) == stone.opposite()) directions.add(Direction.TopRight)
        if (!point.horizontalCoordinate.isRightEdge() &&
            this.getAt(Point(point.verticalCoordinate, HorizontalCoordinate(point.horizontalCoordinate.value+1))) == stone.opposite()) directions.add(Direction.Right)
        if (!point.horizontalCoordinate.isRightEdge() && !point.verticalCoordinate.isBottomEdge() &&
            this.getAt(Point(VerticalCoordinate(point.verticalCoordinate.value+1), HorizontalCoordinate(point.horizontalCoordinate.value+1))) == stone.opposite()) directions.add(Direction.BottomRight)
        if (!point.verticalCoordinate.isBottomEdge() &&
            this.getAt(Point(VerticalCoordinate(point.verticalCoordinate.value+1), point.horizontalCoordinate)) == stone.opposite()) directions.add(Direction.Bottom)
        if (!point.verticalCoordinate.isBottomEdge() && !point.horizontalCoordinate.isLeftEdge() &&
            this.getAt(Point(VerticalCoordinate(point.verticalCoordinate.value+1), HorizontalCoordinate(point.horizontalCoordinate.value-1))) == stone.opposite()) directions.add(Direction.BottomLeft)
        if (!point.horizontalCoordinate.isLeftEdge() &&
            this.getAt(Point(point.verticalCoordinate, HorizontalCoordinate(point.horizontalCoordinate.value-1))) == stone.opposite()) directions.add(Direction.Left)
        return directions
    }

    fun getAt(point: Point): Stone {
        return this.lines[point.verticalCoordinate.value-1].stones[point.horizontalCoordinate.value-1]
    }

    private fun turnAtLeft(move: Move) {
        val verticalCoordinate = move.point.verticalCoordinate
        val horizontalCoordinate = move.point.horizontalCoordinate

        val targetStones = this.lines[verticalCoordinate.value-1].stones.subList(0, horizontalCoordinate.value-1)
        if (targetStones.contains(move.stone)
            && !targetStones.subList(targetStones.lastIndexOf(move.stone), targetStones.size).contains(Stone.NONE)) {
            for (i in horizontalCoordinate.value-2 downTo 0) {
                if (this.lines[verticalCoordinate.value-1].stones[i] == move.stone) break
                this.lines[verticalCoordinate.value-1].stones[i] = move.stone
            }
        }
    }

    private fun turnAtRight(move: Move) {
        val verticalCoordinate = move.point.verticalCoordinate
        val horizontalCoordinate = move.point.horizontalCoordinate

        val targetStones = this.lines[verticalCoordinate.value- 1].stones.subList(horizontalCoordinate.value, 8)
        if (targetStones.contains(move.stone)
            && !targetStones.subList(0, targetStones.indexOf(move.stone)).contains(Stone.NONE)) {
            for (i in horizontalCoordinate.value.. 7) {
                if (this.lines[verticalCoordinate.value- 1].stones[i] == move.stone) break
                this.lines[verticalCoordinate.value- 1].stones[i] = move.stone
            }
        }
    }

    private fun turnAtTop(move: Move) {
        //TODO
    }

    private fun turnAtBottom(move: Move) {
        //TODO
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