package com.whammy.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BoardStateTest {
    @Test
    fun testGetAt() {
        val state = BoardState()
        assertEquals(Stone.WHITE, state.getAt(Point(Vertical(4), Horizontal(4))))
        assertEquals(Stone.BLACK, state.getAt(Point(Vertical(4), Horizontal(5))))
        assertEquals(Stone.BLACK, state.getAt(Point(Vertical(5), Horizontal(4))))
        assertEquals(Stone.WHITE, state.getAt(Point(Vertical(5), Horizontal(5))))
    }

    @Test
    fun testUpdate() {
        val state = BoardState()
        state.update(Point(Vertical(5), Horizontal(6)), Stone.BLACK)
        assertEquals(Stone.BLACK, state.getAt(Point(Vertical(5), Horizontal(6))))
    }

    @Test
    fun testGetAllStones() {
        val state = BoardState()
        val stones = listOf(
            BoardStone(Point(Vertical(1), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(8)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(8)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(8)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(4)), Stone.WHITE),
            BoardStone(Point(Vertical(4), Horizontal(5)), Stone.BLACK),
            BoardStone(Point(Vertical(4), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(8)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(4)), Stone.BLACK),
            BoardStone(Point(Vertical(5), Horizontal(5)), Stone.WHITE),
            BoardStone(Point(Vertical(5), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(8)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(8)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(8)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(8)), Stone.NONE))
        assertEquals(stones, state.getAllStones())
    }

    @Test
    fun testGetAllStonesPerLines() {
        val state = BoardState()
        val stones = listOf(
            listOf(
            BoardStone(Point(Vertical(1), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(8)), Stone.NONE)),
            listOf(
            BoardStone(Point(Vertical(2), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(8)), Stone.NONE)),
            listOf(BoardStone(Point(Vertical(3), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(8)), Stone.NONE)),
            listOf(BoardStone(Point(Vertical(4), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(4)), Stone.WHITE),
            BoardStone(Point(Vertical(4), Horizontal(5)), Stone.BLACK),
            BoardStone(Point(Vertical(4), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(8)), Stone.NONE)),
            listOf(BoardStone(Point(Vertical(5), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(4)), Stone.BLACK),
            BoardStone(Point(Vertical(5), Horizontal(5)), Stone.WHITE),
            BoardStone(Point(Vertical(5), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(5), Horizontal(8)), Stone.NONE)),
            listOf(BoardStone(Point(Vertical(6), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(6), Horizontal(8)), Stone.NONE)),
            listOf(BoardStone(Point(Vertical(7), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(7), Horizontal(8)), Stone.NONE)),
            listOf(BoardStone(Point(Vertical(8), Horizontal(1)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(8), Horizontal(8)), Stone.NONE)))
        assertEquals(stones, state.getAllStonesPerLines())
    }
}