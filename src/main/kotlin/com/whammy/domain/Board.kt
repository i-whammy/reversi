package com.whammy.domain

import java.lang.Exception

class Board {
    val lines: MutableList<Line> = MutableList(8) { Line() }

    init {
        lines[3].stones[3] = Stone.WHITE
        lines[3].stones[4] = Stone.BLACK
        lines[4].stones[3] = Stone.BLACK
        lines[4].stones[4] = Stone.WHITE
    }

    fun add(move: Move) : Board {
        val turnableDirections = this.getTurnableDirections(BoardStone(move.point, move.stone))
        if (turnableDirections.isEmpty()) throw NoTurnableStoneException("No turnable stones found.")
        this.lines[move.point.verticalCoordinate.value - 1].stones[move.point.horizontalCoordinate.value - 1] = move.stone
        turnableDirections.forEach {direction ->
            val targetBoardStones = getBoardStones(move.point, direction).getTurnoverTargetStones(BoardStone(move.point, move.stone))
            targetBoardStones.stones.forEach {
                this.lines[it.point.verticalCoordinate.value-1].stones[it.point.horizontalCoordinate.value-1] = move.stone
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

    fun getStoneAt(point: Point): Stone {
        return this.lines[point.verticalCoordinate.value-1].stones[point.horizontalCoordinate.value-1]
    }

    fun getStoneAt(point: Point, direction: Direction): BoardStone  {
        val targetPoint = point.getAdjacentAt(direction)
        return BoardStone(targetPoint, this.getStoneAt(targetPoint))
    }

    fun getBoardStones(fromPoint: Point, toDirection: Direction): TargetBoardStones {
        val stones = mutableListOf<BoardStone>()
        var currentPoint = fromPoint
        while (!currentPoint.isEdge()) {
            stones.add(getStoneAt(currentPoint, toDirection))
            currentPoint = currentPoint.getAdjacentAt(toDirection)
        }
        return TargetBoardStones(stones)
    }

    fun getValidPoints(stone: Stone): List<Point> {
        return this.lines.mapIndexed { verticalIndex, line ->
            line.stones.mapIndexed { horizontalIndex, _ -> BoardStone(Point.at(verticalIndex + 1, horizontalIndex + 1), stone) }
                .filter { getTurnableDirections(it).isNotEmpty() }
                .map { it.point }
        }.flatten()
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

class NoTurnableStoneException(override val message: String) : Exception()