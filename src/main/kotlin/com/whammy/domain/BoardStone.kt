package com.whammy.domain

data class TargetBoardStones(val stones: List<BoardStone>) {
    fun canTurnStonesOver(fromBoardStone: BoardStone): Boolean {
        val counterStoneExists =  stones.map { it.stone }.contains(fromBoardStone.stone)
        return if (counterStoneExists) {
            val targetStones = stones.subList(0, stones.indexOfFirst { it.stone == fromBoardStone.stone })
            targetStones.isNotEmpty() &&
                    targetStones.all { it.stone == fromBoardStone.stone.opposite() }
        } else {
            false
        }
    }

    fun getCounterStoneOf(boardStone: BoardStone) = stones.first { it.stone == boardStone.stone }
    fun getTurnoverTargetStones(boardStone: BoardStone) =
        TargetBoardStones(stones.subList(0, stones.indexOfFirst { it.stone == boardStone.stone }))
}

data class BoardStone(val point: Point, val stone: Stone)