package com.whammy.domain

class BoardState: State {
    private val lines: MutableList<Line> = MutableList(8) { Line() }

    init {
        lines[3].stones[3] = Stone.WHITE
        lines[3].stones[4] = Stone.BLACK
        lines[4].stones[3] = Stone.BLACK
        lines[4].stones[4] = Stone.WHITE
    }

    override fun getAt(point: Point): Stone {
        return lines[point.vertical.value-1].stones[point.horizontal.value-1]
    }

    override fun update(point: Point, stone: Stone) {
        lines[point.vertical.value-1].stones[point.horizontal.value-1] = stone
    }

    override fun getAllStones(): List<BoardStone> {
        return lines.mapIndexed { lineIndex, line ->
            line.stones.mapIndexed { stoneIndex, stone ->
                BoardStone(Point(Vertical(lineIndex + 1), Horizontal(stoneIndex + 1)), stone)
            }
        }.flatten()
    }

    override fun getAllStonesPerLines(): List<List<BoardStone>> {
        return lines.mapIndexed { lineIndex, line ->
            line.stones.mapIndexed { stoneIndex, stone ->
                BoardStone(Point(Vertical(lineIndex + 1), Horizontal(stoneIndex + 1)), stone)
            }
        }
    }
}

interface State {
    fun getAt(point: Point): Stone
    fun update(point: Point, stone: Stone)
    fun getAllStones(): List<BoardStone>
    fun getAllStonesPerLines(): List<List<BoardStone>>
}