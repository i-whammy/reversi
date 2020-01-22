package com.whammy.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StoneTest {
    @Test
    fun testOpposite() {
        assertEquals(Stone.WHITE, Stone.BLACK.opposite())
        assertEquals(Stone.BLACK, Stone.WHITE.opposite())
        assertEquals(Stone.NONE, Stone.NONE.opposite())
    }
}