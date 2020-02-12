package com.whammy.domain

import org.junit.Ignore
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BoardTest {

    @Test
    fun testAdd() {
        val board = Board()
        val expected = board.add(Move(Point(VerticalCoordinate(5), HorizontalCoordinate(6)), Stone.BLACK))
        assertEquals(Stone.BLACK, expected.getStoneAt(Point(VerticalCoordinate(5), HorizontalCoordinate(5))))
        assertEquals(Stone.BLACK, expected.getStoneAt(Point(VerticalCoordinate(5), HorizontalCoordinate(6))))
    }

    @Test
    fun testThrowsNoTurnableStoneExceptionWhenAdd() {
        val board = Board()
        assertThrows<NoTurnableStoneException> { board.add(Move(Point.at(5,6), Stone.WHITE)) }
    }

    @Ignore
    @Test
    fun testAddWithSeveralStonesTurning() {
        val board = Board()
        board.add(Move(Point(VerticalCoordinate(5), HorizontalCoordinate(6)), Stone.BLACK))
        board.add(Move(Point(VerticalCoordinate(4), HorizontalCoordinate(6)), Stone.WHITE))
        val expected = board.add(Move(Point(VerticalCoordinate(3), HorizontalCoordinate(6)), Stone.BLACK))
        assertEquals(Stone.BLACK, expected.getStoneAt(Point(VerticalCoordinate(4), HorizontalCoordinate(5))))
        assertEquals(Stone.BLACK, expected.getStoneAt(Point(VerticalCoordinate(4), HorizontalCoordinate(6))))
    }

    @Test
    fun testGetTurnableDirections() {
        val board = Board()
        val expected = listOf(Direction.Left)
        assertEquals(expected, board.getTurnableDirections(BoardStone(Point(VerticalCoordinate(5), HorizontalCoordinate(6)), Stone.BLACK)))
    }

    @Test
    fun testGetSeveralTurnableDirections() {
        val board = Board()
        board.add(Move(Point(VerticalCoordinate(5), HorizontalCoordinate(6)), Stone.BLACK))
        board.add(Move(Point(VerticalCoordinate(4), HorizontalCoordinate(6)), Stone.WHITE))
        val expected = listOf(Direction.Bottom, Direction.BottomLeft)
        assertEquals(expected, board.getTurnableDirections(BoardStone(Point(VerticalCoordinate(3),HorizontalCoordinate(6)),Stone.BLACK)))
    }

    @Test
    fun testGetTurnableDirectionsInParticularCase() {
        val board = Board()
        board.add(Move(Point(VerticalCoordinate(4), HorizontalCoordinate(3)), Stone.BLACK))
        board.add(Move(Point(VerticalCoordinate(3), HorizontalCoordinate(3)), Stone.WHITE))
        board.add(Move(Point(VerticalCoordinate(2), HorizontalCoordinate(3)), Stone.BLACK))
        board.add(Move(Point(VerticalCoordinate(2), HorizontalCoordinate(2)), Stone.WHITE))
        val expected = listOf(Direction.Right)
        assertEquals(expected, board.getTurnableDirections(BoardStone(Point(VerticalCoordinate(2),HorizontalCoordinate(1)),Stone.BLACK)))
    }

    @Test
    fun testGetTurnableDirectionsInParticularCase2() {
        val board = Board()
        board.add(Move(Point(VerticalCoordinate(6), HorizontalCoordinate(5)), Stone.BLACK))
        board.add(Move(Point(VerticalCoordinate(4), HorizontalCoordinate(6)), Stone.WHITE))
        board.add(Move(Point(VerticalCoordinate(3), HorizontalCoordinate(4)), Stone.BLACK))
        board.add(Move(Point(VerticalCoordinate(2), HorizontalCoordinate(3)), Stone.WHITE))
        board.add(Move(Point(VerticalCoordinate(2), HorizontalCoordinate(4)), Stone.BLACK))
        assertTrue { !board.getValidPoints(Stone.WHITE).contains(Point.at(3,4))}
    }

    @Test
    fun testGetEmptyTurnableDirections() {
        assertEquals(emptyList(), Board().getTurnableDirections(BoardStone(Point(VerticalCoordinate(1), HorizontalCoordinate(1)),Stone.BLACK)))
    }

    @Test
    fun testGetAt() {
        val board = Board()
        val black = Stone.BLACK
        val white = Stone.WHITE
        assertEquals(white,board.getStoneAt(Point(VerticalCoordinate(4), HorizontalCoordinate(4))))
        assertEquals(black,board.getStoneAt(Point(VerticalCoordinate(4), HorizontalCoordinate(5))))
        assertEquals(black,board.getStoneAt(Point(VerticalCoordinate(5), HorizontalCoordinate(4))))
        assertEquals(white,board.getStoneAt(Point(VerticalCoordinate(5), HorizontalCoordinate(5))))
    }

    @Test
    fun testGetAtWithDirection() {
        val board = Board()
        assertEquals(BoardStone(Point.at(3,3), Stone.NONE),board.getStoneAt(Point.at(4,4), Direction.TopLeft))
        assertEquals(BoardStone(Point.at(3,4), Stone.NONE),board.getStoneAt(Point.at(4,4), Direction.Top))
        assertEquals(BoardStone(Point.at(3,5), Stone.NONE),board.getStoneAt(Point.at(4,4), Direction.TopRight))
        assertEquals(BoardStone(Point.at(4,5), Stone.BLACK),board.getStoneAt(Point.at(4,4), Direction.Right))
        assertEquals(BoardStone(Point.at(5,5), Stone.WHITE),board.getStoneAt(Point.at(4,4), Direction.BottomRight))
        assertEquals(BoardStone(Point.at(5,4), Stone.BLACK),board.getStoneAt(Point.at(4,4), Direction.Bottom))
        assertEquals(BoardStone(Point.at(5,3), Stone.NONE),board.getStoneAt(Point.at(4,4), Direction.BottomLeft))
        assertEquals(BoardStone(Point.at(4,3), Stone.NONE),board.getStoneAt(Point.at(4,4), Direction.Left))
    }

    @Test
    fun testGetBoardStones() {
        val board = Board()
        val stones = TargetBoardStones(listOf(
            BoardStone(Point(VerticalCoordinate(4), HorizontalCoordinate(5)), Stone.BLACK),
            BoardStone(Point(VerticalCoordinate(4), HorizontalCoordinate(4)), Stone.WHITE),
            BoardStone(Point(VerticalCoordinate(4), HorizontalCoordinate(3)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(4), HorizontalCoordinate(2)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(4), HorizontalCoordinate(1)), Stone.NONE)))
        assertEquals(stones,board.getBoardStones(Point.at(4,6), Direction.Left))
    }

    @Test
    fun testGetBoardStonesToTopRight() {
        val board = Board()
        val stones = TargetBoardStones(listOf(
            BoardStone(Point(VerticalCoordinate(5), HorizontalCoordinate(5)), Stone.WHITE),
            BoardStone(Point(VerticalCoordinate(4), HorizontalCoordinate(6)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(3), HorizontalCoordinate(7)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(2), HorizontalCoordinate(8)), Stone.NONE)))
        assertEquals(stones,board.getBoardStones(Point.at(6,4), Direction.TopRight))
    }

    @Test
    fun testGetBoardStonesFromLeftEdgeToRight() {
        val board = Board()
        val stones = TargetBoardStones(listOf(
            BoardStone(Point(VerticalCoordinate(1), HorizontalCoordinate(2)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(1), HorizontalCoordinate(3)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(1), HorizontalCoordinate(4)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(1), HorizontalCoordinate(5)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(1), HorizontalCoordinate(6)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(1), HorizontalCoordinate(7)), Stone.NONE),
            BoardStone(Point(VerticalCoordinate(1), HorizontalCoordinate(8)), Stone.NONE)))
        assertEquals(stones,board.getBoardStones(Point.at(1,1), Direction.Right))
    }

    @Test
    fun testGetValidPoints() {
        val board = Board()
        val validBlackPoints = listOf(
            Point.at(3,4),
            Point.at(4,3),
            Point.at(5,6),
            Point.at(6,5)
        )
        assertEquals(validBlackPoints, board.getValidPoints(Stone.BLACK))
    }

    @Test
    fun testIsGameEnded() {
        val board = Board()
        board.lines.forEachIndexed { verticalIndex, line ->
            line.stones.forEachIndexed { horizontalIndex, _ ->
                board.lines[verticalIndex].stones[horizontalIndex] = Stone.WHITE
            }
        }
        assertTrue { board.isGameEnded() }
    }

    @Test
    fun testCount() {
        val board = Board()
        assertEquals(2, board.count(Stone.BLACK))
    }
}