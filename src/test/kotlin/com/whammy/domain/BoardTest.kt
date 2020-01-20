package com.whammy.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BoardTest {

    @Test
    fun testAdd() {
        val board = Board()
        val expected = board.add(Move(5, 6, Stone.BLACK))
        assertEquals(Stone.BLACK, expected.lines[4].stones[3])
        assertEquals(Stone.BLACK, expected.lines[4].stones[4])
    }
}