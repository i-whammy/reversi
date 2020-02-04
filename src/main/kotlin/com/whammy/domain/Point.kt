package com.whammy.domain

import java.lang.Exception

data class Point(val verticalCoordinate: VerticalCoordinate, val horizontalCoordinate: HorizontalCoordinate) {
    companion object {
        fun at(v: Int, h: Int) = Point(VerticalCoordinate(v), HorizontalCoordinate(h))
    }

    fun isEdge() = verticalCoordinate.isTopEdge() || verticalCoordinate.isBottomEdge()
            || horizontalCoordinate.isLeftEdge() || horizontalCoordinate.isRightEdge()

    fun getAdjacentAt(direction: Direction): Point {
        return when (direction) {
            Direction.TopLeft -> Point.at(verticalCoordinate.value-1,horizontalCoordinate.value-1)
            Direction.Top -> Point.at(verticalCoordinate.value-1,horizontalCoordinate.value)
            Direction.TopRight -> Point.at(verticalCoordinate.value-1,horizontalCoordinate.value+1)
            Direction.Right -> Point.at(verticalCoordinate.value,horizontalCoordinate.value+1)
            Direction.BottomRight -> Point.at(verticalCoordinate.value+1,horizontalCoordinate.value+1)
            Direction.Bottom -> Point.at(verticalCoordinate.value+1,horizontalCoordinate.value)
            Direction.BottomLeft -> Point.at(verticalCoordinate.value+1,horizontalCoordinate.value-1)
            Direction.Left -> Point.at(verticalCoordinate.value,horizontalCoordinate.value-1)
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