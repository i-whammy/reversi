package com.whammy.domain

import org.junit.Ignore
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BoardTest {

    @Test
    fun testAdd() {
        val board = Board()
        val expected = board.add(Move(Vertical(5), Horizontal(6), Stone.BLACK))
        assertEquals(Stone.BLACK, expected.getAt(Vertical(5), Horizontal(5)))
        assertEquals(Stone.BLACK, expected.getAt(Vertical(5), Horizontal(6)))
    }

    @Ignore
    @Test
    fun testAddWithSeveralStonesTurning() {
        val board = Board()
        board.add(Move(Vertical(5), Horizontal(6), Stone.BLACK))
        board.add(Move(Vertical(4), Horizontal(6), Stone.WHITE))
        val expected = board.add(Move(Vertical(3), Horizontal(6), Stone.BLACK))
        assertEquals(Stone.BLACK, expected.getAt(Vertical(4), Horizontal(5)))
        assertEquals(Stone.BLACK, expected.getAt(Vertical(4), Horizontal(6)))
    }

    @Test
    fun testGetTurnableDirections() {
        val board = Board()
        val expected = listOf(Direction.Left)
        assertEquals(expected, board.getTurnableDirections(Move(Vertical(5),Horizontal(6),Stone.BLACK)))
    }

    @Test
    fun testGetSeveralTurnableDirections() {
        val board = Board()
        board.add(Move(Vertical(5), Horizontal(6), Stone.BLACK))
        board.add(Move(Vertical(4), Horizontal(6), Stone.WHITE))
        val expected = listOf(Direction.Bottom, Direction.BottomLeft)
        assertEquals(expected, board.getTurnableDirections(Move(Vertical(3),Horizontal(6),Stone.BLACK)))
    }

    @Test
    fun testGetEmptyTurnableDirections() {
        assertEquals(emptyList(), Board().getTurnableDirections(Move(Vertical(1),Horizontal(1),Stone.BLACK)))
    }

    @Test
    fun testGetAt() {
        val board = Board()
        val black = Stone.BLACK
        val white = Stone.WHITE
        assertEquals(white,board.getAt(Vertical(4), Horizontal(4)))
        assertEquals(black,board.getAt(Vertical(4), Horizontal(5)))
        assertEquals(black,board.getAt(Vertical(5), Horizontal(4)))
        assertEquals(white,board.getAt(Vertical(5), Horizontal(5)))
    }
}