package com.whammy.domain

import java.lang.Exception

class Board(private val state: State) {

    fun add(move: Move) : Board {
        val turnableDirections = this.getTurnableDirections(BoardStone(move.point, move.stone))
        if (turnableDirections.isEmpty()) throw NoTurnableStoneException("No turnable stones found.")
        state.update(move.point, move.stone)
        turnableDirections.forEach {direction ->
            val targetBoardStones = getBoardStones(move.point, direction).getTurnoverTargetStones(BoardStone(move.point, move.stone))
            targetBoardStones.stones.forEach {
                state.update(it.point, move.stone)
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

    fun getStoneAt(point: Point) = state.getAt(point)

    fun getAdjacentStoneAt(point: Point, direction: Direction): BoardStone  {
        val targetPoint = point.getAdjacentAt(direction)
        return BoardStone(targetPoint, this.getStoneAt(targetPoint))
    }

    fun getBoardStones(fromPoint: Point, toDirection: Direction): TargetBoardStones {
        val stones = mutableListOf<BoardStone>()
        var currentPoint = fromPoint
        while (!currentPoint.isEdgeOf(toDirection)) {
            stones.add(getAdjacentStoneAt(currentPoint, toDirection))
            currentPoint = currentPoint.getAdjacentAt(toDirection)
        }
        return TargetBoardStones(stones)
    }

    fun getValidPoints(stone: Stone): List<Point> {
        return state.getAllStones()
            .map { BoardStone(it.point, stone) }
            .filter { getTurnableDirections(it).isNotEmpty() }
            .filter { this.getStoneAt(it.point) == Stone.NONE }
            .map { it.point }
    }

    fun isGameEnded(): Boolean {
        return state.getAllStones().none { it.stone == Stone.NONE }
    }

    fun count(stone: Stone): Int {
        return state.getAllStones().count { it.stone == stone }
    }

    fun getAllStonesPerLines(): List<List<BoardStone>> = state.getAllStonesPerLines()
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