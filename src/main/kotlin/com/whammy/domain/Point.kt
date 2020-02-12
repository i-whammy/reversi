package com.whammy.domain

import java.lang.Exception

data class Point(val verticalCoordinate: VerticalCoordinate, val horizontalCoordinate: HorizontalCoordinate) {
    companion object {
        fun at(v: Int, h: Int) = Point(VerticalCoordinate(v), HorizontalCoordinate(h))
    }

    fun isEdge() = verticalCoordinate.isTopEdge() || verticalCoordinate.isBottomEdge()
            || horizontalCoordinate.isLeftEdge() || horizontalCoordinate.isRightEdge()

    fun isEdgeOf(direction: Direction): Boolean {
        try {
            getAdjacentAt(direction)
        } catch (e: OutOfRangeException) {
            return true
        }
        return false
    }

    fun getAdjacentAt(direction: Direction): Point {
        return when (direction) {
            Direction.TopLeft -> at(verticalCoordinate.value-1,horizontalCoordinate.value-1)
            Direction.Top -> at(verticalCoordinate.value-1,horizontalCoordinate.value)
            Direction.TopRight -> at(verticalCoordinate.value-1,horizontalCoordinate.value+1)
            Direction.Right -> at(verticalCoordinate.value,horizontalCoordinate.value+1)
            Direction.BottomRight -> at(verticalCoordinate.value+1,horizontalCoordinate.value+1)
            Direction.Bottom -> at(verticalCoordinate.value+1,horizontalCoordinate.value)
            Direction.BottomLeft -> at(verticalCoordinate.value+1,horizontalCoordinate.value-1)
            Direction.Left -> at(verticalCoordinate.value,horizontalCoordinate.value-1)
        }
    }
}

data class VerticalCoordinate(val value: Int) {
    init {
        if (1 > value || 8 < value) throw OutOfRangeException("You must input number between 1 and 8.")
    }
    fun isTopEdge() = value == 1
    fun isBottomEdge() = value == 8
}

data class HorizontalCoordinate(val value: Int) {
    init {
        if (1 > value || 8 < value) throw OutOfRangeException("You must input number between 1 and 8.")
    }
    fun isLeftEdge() = value == 1
    fun isRightEdge() = value == 8
}

class OutOfRangeException(override val message: String): Exception()