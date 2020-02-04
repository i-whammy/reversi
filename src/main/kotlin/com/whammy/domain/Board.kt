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
        // TODO ひっくり返せる石が一つもなければ指し直しを要求する
        this.lines[move.point.verticalCoordinate.value - 1].stones[move.point.horizontalCoordinate.value - 1] = move.stone
        val turnableDirections = this.getTurnableDirections(BoardStone(move.point, move.stone))
        turnableDirections.forEach {
            when (it) {
                Direction.Left -> turnAtLeft(move)
                Direction.Right -> turnAtRight(move)
                // TODO 縦の実装
                Direction.Top -> turnAtTop(move)
                Direction.Bottom -> turnAtBottom(move)
                // TODO 斜めの実装
            }
        }
        return this
    }

    fun getTurnableDirections(boardStone: BoardStone): List<Direction> {
        val directions = mutableListOf<Direction>()
        Direction.values().forEach { direction ->
            val targetBoardStones = getBoardStones(boardStone.point, direction)
            if (targetBoardStones.stones.isNotEmpty() && targetBoardStones.canTurnStonesOver(boardStone)) directions.add(direction)
        }
        return directions
    }

    fun getAt(point: Point): Stone {
        return this.lines[point.verticalCoordinate.value-1].stones[point.horizontalCoordinate.value-1]
    }

    fun getAt(point: Point, direction: Direction): BoardStone  {
        val targetPoint = point.getAdjacentAt(direction)
        return BoardStone(targetPoint, this.getAt(targetPoint))
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

    fun getBoardStones(fromPoint: Point, toDirection: Direction): TargetBoardStones {
        val stones = mutableListOf<BoardStone>()
        var currentPoint = fromPoint
        while (!currentPoint.isEdge()) {
            stones.add(getAt(currentPoint, toDirection))
            currentPoint = currentPoint.getAdjacentAt(toDirection)
        }
        return TargetBoardStones(stones)
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