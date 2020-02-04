package com.whammy.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PointTest {

    @Test
    fun testAdjacentAt() {
        val point = Point.at(4,4)

        assertEquals(Point.at(3,3), point.getAdjacentAt(Direction.TopLeft))
        assertEquals(Point.at(3,4), point.getAdjacentAt(Direction.Top))
        assertEquals(Point.at(3,5), point.getAdjacentAt(Direction.TopRight))
        assertEquals(Point.at(4,5), point.getAdjacentAt(Direction.Right))
        assertEquals(Point.at(5,5), point.getAdjacentAt(Direction.BottomRight))
        assertEquals(Point.at(5,4), point.getAdjacentAt(Direction.Bottom))
        assertEquals(Point.at(5,3), point.getAdjacentAt(Direction.BottomLeft))
        assertEquals(Point.at(4,3), point.getAdjacentAt(Direction.Left))
    }
}