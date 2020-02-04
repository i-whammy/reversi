package com.whammy.domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TargetBoardStonesTest {

    @Nested
    inner class CanTurnsOverTest {

        @Test
        fun cannotTurnStonesOverWhenNoCounterStoneExists() {
            val targetBoardStones = TargetBoardStones(
                listOf(
                    BoardStone(Point.at(4, 4), Stone.WHITE),
                    BoardStone(Point.at(4, 3), Stone.WHITE),
                    BoardStone(Point.at(4, 2), Stone.NONE),
                    BoardStone(Point.at(4, 1), Stone.NONE)
                )
            )
            assertFalse(targetBoardStones.canTurnStonesOver(BoardStone(Point.at(4, 5), Stone.BLACK)))
        }

        @Test
        fun cannotTurnStonesOverWhenNoTargetStoneExistsBeforeCounterStone() {
            val targetBoardStones = TargetBoardStones(
                listOf(
                    BoardStone(Point.at(4, 4), Stone.BLACK),
                    BoardStone(Point.at(4, 3), Stone.NONE),
                    BoardStone(Point.at(4, 2), Stone.NONE),
                    BoardStone(Point.at(4, 1), Stone.NONE)
                )
            )
            assertFalse(targetBoardStones.canTurnStonesOver(BoardStone(Point.at(4, 5), Stone.BLACK)))
        }

        @Test
        fun cannotTurnStonesOverWhenBlankExistsBeforeCounterStone() {
            val targetBoardStones = TargetBoardStones(
                listOf(
                    BoardStone(Point.at(4, 4), Stone.NONE),
                    BoardStone(Point.at(4, 3), Stone.WHITE),
                    BoardStone(Point.at(4, 2), Stone.BLACK),
                    BoardStone(Point.at(4, 1), Stone.NONE)
                )
            )
            assertFalse(targetBoardStones.canTurnStonesOver(BoardStone(Point.at(4, 5), Stone.BLACK)))
        }

        @Test
        fun canTurnStonesOver() {
            val targetBoardStones = TargetBoardStones(
                listOf(
                    BoardStone(Point.at(4, 4), Stone.WHITE),
                    BoardStone(Point.at(4, 3), Stone.BLACK),
                    BoardStone(Point.at(4, 2), Stone.NONE),
                    BoardStone(Point.at(4, 1), Stone.NONE)
                )
            )
            assertTrue(targetBoardStones.canTurnStonesOver(BoardStone(Point.at(4, 5), Stone.BLACK)))
        }
    }

    @Test
    fun testGetCounterStoneOf() {
        val targetBoardStones = TargetBoardStones(
            listOf(
                BoardStone(Point.at(4, 4), Stone.WHITE),
                BoardStone(Point.at(4, 3), Stone.BLACK),
                BoardStone(Point.at(4, 2), Stone.NONE),
                BoardStone(Point.at(4, 1), Stone.NONE)
            )
        )
        assertEquals(BoardStone(Point.at(4,3), Stone.BLACK), targetBoardStones.getCounterStoneOf(BoardStone(Point.at(4,5), Stone.BLACK)))
    }

    @Test
    fun testGetTurnoverTargetStones() {
        val targetBoardStones = TargetBoardStones(
            listOf(
                BoardStone(Point.at(4, 4), Stone.WHITE),
                BoardStone(Point.at(4, 3), Stone.BLACK),
                BoardStone(Point.at(4, 2), Stone.NONE),
                BoardStone(Point.at(4, 1), Stone.NONE)
            )
        )
        val turnoverTargetStones = TargetBoardStones(
            listOf(
                BoardStone(Point.at(4, 4), Stone.WHITE)
            )
        )
        assertEquals(turnoverTargetStones, targetBoardStones.getTurnoverTargetStones(BoardStone(Point.at(4,5), Stone.BLACK)))
    }
}