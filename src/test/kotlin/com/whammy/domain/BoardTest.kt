package com.whammy.domain

import org.junit.Ignore
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BoardTest {

    @Test
    fun testAdd() {
        val board = Board(BoardState())
        val expected = board.add(Move(Point(Vertical(5), Horizontal(6)), Stone.BLACK))
        assertEquals(Stone.BLACK, expected.getStoneAt(Point(Vertical(5), Horizontal(5))))
        assertEquals(Stone.BLACK, expected.getStoneAt(Point(Vertical(5), Horizontal(6))))
    }

    @Test
    fun testThrowsNoTurnableStoneExceptionWhenAdd() {
        val board = Board(BoardState())
        assertThrows<NoTurnableStoneException> { board.add(Move(Point.at(5,6), Stone.WHITE)) }
    }

    @Ignore
    @Test
    fun testAddWithSeveralStonesTurning() {
        val board = Board(BoardState())
        board.add(Move(Point(Vertical(5), Horizontal(6)), Stone.BLACK))
        board.add(Move(Point(Vertical(4), Horizontal(6)), Stone.WHITE))
        val expected = board.add(Move(Point(Vertical(3), Horizontal(6)), Stone.BLACK))
        assertEquals(Stone.BLACK, expected.getStoneAt(Point(Vertical(4), Horizontal(5))))
        assertEquals(Stone.BLACK, expected.getStoneAt(Point(Vertical(4), Horizontal(6))))
    }

    @Test
    fun testGetTurnableDirections() {
        val board = Board(BoardState())
        val expected = listOf(Direction.Left)
        assertEquals(expected, board.getTurnableDirections(BoardStone(Point(Vertical(5), Horizontal(6)), Stone.BLACK)))
    }

    @Test
    fun testGetSeveralTurnableDirections() {
        val board = Board(BoardState())
        board.add(Move(Point(Vertical(5), Horizontal(6)), Stone.BLACK))
        board.add(Move(Point(Vertical(4), Horizontal(6)), Stone.WHITE))
        val expected = listOf(Direction.Bottom, Direction.BottomLeft)
        assertEquals(expected, board.getTurnableDirections(BoardStone(Point(Vertical(3),Horizontal(6)),Stone.BLACK)))
    }

    @Test
    fun testGetTurnableDirectionsInParticularCase() {
        val board = Board(BoardState())
        board.add(Move(Point(Vertical(4), Horizontal(3)), Stone.BLACK))
        board.add(Move(Point(Vertical(3), Horizontal(3)), Stone.WHITE))
        board.add(Move(Point(Vertical(2), Horizontal(3)), Stone.BLACK))
        board.add(Move(Point(Vertical(2), Horizontal(2)), Stone.WHITE))
        val expected = listOf(Direction.Right)
        assertEquals(expected, board.getTurnableDirections(BoardStone(Point(Vertical(2),Horizontal(1)),Stone.BLACK)))
    }

    @Test
    fun testGetTurnableDirectionsInParticularCase2() {
        val board = Board(BoardState())
        board.add(Move(Point(Vertical(6), Horizontal(5)), Stone.BLACK))
        board.add(Move(Point(Vertical(4), Horizontal(6)), Stone.WHITE))
        board.add(Move(Point(Vertical(3), Horizontal(4)), Stone.BLACK))
        board.add(Move(Point(Vertical(2), Horizontal(3)), Stone.WHITE))
        board.add(Move(Point(Vertical(2), Horizontal(4)), Stone.BLACK))
        assertTrue { !board.getValidPoints(Stone.WHITE).contains(Point.at(3,4))}
    }

    @Test
    fun testGetEmptyTurnableDirections() {
        val board = Board(BoardState())
        assertEquals(emptyList(), board.getTurnableDirections(BoardStone(Point(Vertical(1), Horizontal(1)),Stone.BLACK)))
    }

    @Test
    fun testGetAt() {
        val board = Board(BoardState())
        val black = Stone.BLACK
        val white = Stone.WHITE
        assertEquals(white,board.getStoneAt(Point(Vertical(4), Horizontal(4))))
        assertEquals(black,board.getStoneAt(Point(Vertical(4), Horizontal(5))))
        assertEquals(black,board.getStoneAt(Point(Vertical(5), Horizontal(4))))
        assertEquals(white,board.getStoneAt(Point(Vertical(5), Horizontal(5))))
    }

    @Test
    fun testGetAtWithDirection() {
        val board = Board(BoardState())
        assertEquals(BoardStone(Point.at(3,3), Stone.NONE),board.getAdjacentStoneAt(Point.at(4,4), Direction.TopLeft))
        assertEquals(BoardStone(Point.at(3,4), Stone.NONE),board.getAdjacentStoneAt(Point.at(4,4), Direction.Top))
        assertEquals(BoardStone(Point.at(3,5), Stone.NONE),board.getAdjacentStoneAt(Point.at(4,4), Direction.TopRight))
        assertEquals(BoardStone(Point.at(4,5), Stone.BLACK),board.getAdjacentStoneAt(Point.at(4,4), Direction.Right))
        assertEquals(BoardStone(Point.at(5,5), Stone.WHITE),board.getAdjacentStoneAt(Point.at(4,4), Direction.BottomRight))
        assertEquals(BoardStone(Point.at(5,4), Stone.BLACK),board.getAdjacentStoneAt(Point.at(4,4), Direction.Bottom))
        assertEquals(BoardStone(Point.at(5,3), Stone.NONE),board.getAdjacentStoneAt(Point.at(4,4), Direction.BottomLeft))
        assertEquals(BoardStone(Point.at(4,3), Stone.NONE),board.getAdjacentStoneAt(Point.at(4,4), Direction.Left))
    }

    @Test
    fun testGetBoardStones() {
        val board = Board(BoardState())
        val stones = TargetBoardStones(listOf(
            BoardStone(Point(Vertical(4), Horizontal(5)), Stone.BLACK),
            BoardStone(Point(Vertical(4), Horizontal(4)), Stone.WHITE),
            BoardStone(Point(Vertical(4), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(4), Horizontal(1)), Stone.NONE)))
        assertEquals(stones,board.getBoardStones(Point.at(4,6), Direction.Left))
    }

    @Test
    fun testGetBoardStonesToTopRight() {
        val board = Board(BoardState())
        val stones = TargetBoardStones(listOf(
            BoardStone(Point(Vertical(5), Horizontal(5)), Stone.WHITE),
            BoardStone(Point(Vertical(4), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(3), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(2), Horizontal(8)), Stone.NONE)))
        assertEquals(stones,board.getBoardStones(Point.at(6,4), Direction.TopRight))
    }

    @Test
    fun testGetBoardStonesFromLeftEdgeToRight() {
        val board = Board(BoardState())
        val stones = TargetBoardStones(listOf(
            BoardStone(Point(Vertical(1), Horizontal(2)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(3)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(4)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(5)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(6)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(7)), Stone.NONE),
            BoardStone(Point(Vertical(1), Horizontal(8)), Stone.NONE)))
        assertEquals(stones,board.getBoardStones(Point.at(1,1), Direction.Right))
    }

    @Test
    fun testGetValidPoints() {
        val board = Board(BoardState())
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
        val board = Board(MockState())
        assertTrue { board.isGameEnded() }
    }

    @Test
    fun testCount() {
        val board = Board(MockState())
        assertEquals(2, board.count(Stone.BLACK))
        assertEquals(1, board.count(Stone.WHITE))
    }

    class MockState: State {
        override fun getAt(point: Point): Stone {
            TODO("Implement when needed")
        }

        override fun update(point: Point, stone: Stone) {
            TODO("Implement when needed")
        }

        override fun getAllStones(): List<BoardStone> {
            return listOf(
                BoardStone(Point(Vertical(1), Horizontal(1)), Stone.BLACK),
                BoardStone(Point(Vertical(1), Horizontal(2)), Stone.BLACK),
                BoardStone(Point(Vertical(1), Horizontal(3)), Stone.WHITE)
            )
        }

        override fun getAllStonesPerLines(): List<List<BoardStone>> {
            TODO("Implement when needed")
        }
    }
}