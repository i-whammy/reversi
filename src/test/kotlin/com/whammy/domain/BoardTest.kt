package com.whammy.domain

import org.junit.Ignore
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BoardTest {

    @Test
    fun testAdd() {
        val board = Board()
        val expected = board.add(Move(Point(VerticalCoordinate(5), HorizontalCoordinate(6)), Stone.BLACK))
        assertEquals(Stone.BLACK, expected.getAt(Point(VerticalCoordinate(5), HorizontalCoordinate(5))))
        assertEquals(Stone.BLACK, expected.getAt(Point(VerticalCoordinate(5), HorizontalCoordinate(6))))
    }

    @Ignore
    @Test
    fun testAddWithSeveralStonesTurning() {
        val board = Board()
        board.add(Move(Point(VerticalCoordinate(5), HorizontalCoordinate(6)), Stone.BLACK))
        board.add(Move(Point(VerticalCoordinate(4), HorizontalCoordinate(6)), Stone.WHITE))
        val expected = board.add(Move(Point(VerticalCoordinate(3), HorizontalCoordinate(6)), Stone.BLACK))
        assertEquals(Stone.BLACK, expected.getAt(Point(VerticalCoordinate(4), HorizontalCoordinate(5))))
        assertEquals(Stone.BLACK, expected.getAt(Point(VerticalCoordinate(4), HorizontalCoordinate(6))))
    }

    @Test
    fun testGetTurnableDirections() {
        val board = Board()
        val expected = listOf(Direction.Left)
        assertEquals(expected, board.getTurnableDirections(Move(Point(VerticalCoordinate(5), HorizontalCoordinate(6)), Stone.BLACK)))
    }

    @Test
    fun testGetSeveralTurnableDirections() {
        val board = Board()
        board.add(Move(Point(VerticalCoordinate(5), HorizontalCoordinate(6)), Stone.BLACK))
        board.add(Move(Point(VerticalCoordinate(4), HorizontalCoordinate(6)), Stone.WHITE))
        val expected = listOf(Direction.Bottom, Direction.BottomLeft)
        assertEquals(expected, board.getTurnableDirections(Move(Point(VerticalCoordinate(3),HorizontalCoordinate(6)),Stone.BLACK)))
    }

    @Test
    fun testGetEmptyTurnableDirections() {
        assertEquals(emptyList(), Board().getTurnableDirections(Move(Point(VerticalCoordinate(1), HorizontalCoordinate(1)),Stone.BLACK)))
    }

    @Test
    fun testGetAt() {
        val board = Board()
        val black = Stone.BLACK
        val white = Stone.WHITE
        assertEquals(white,board.getAt(Point(VerticalCoordinate(4), HorizontalCoordinate(4))))
        assertEquals(black,board.getAt(Point(VerticalCoordinate(4), HorizontalCoordinate(5))))
        assertEquals(black,board.getAt(Point(VerticalCoordinate(5), HorizontalCoordinate(4))))
        assertEquals(white,board.getAt(Point(VerticalCoordinate(5), HorizontalCoordinate(5))))
    }
}