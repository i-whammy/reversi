package com.whammy.domain

import java.lang.Exception

class Move(val point: Point, val stone: Stone)

data class Point(val verticalCoordinate: VerticalCoordinate, val horizontalCoordinate: HorizontalCoordinate)

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