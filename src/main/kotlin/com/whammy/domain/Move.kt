package com.whammy.domain

import java.lang.Exception

class Move(val vertical: Vertical, val horizontal: Horizontal, val stone: Stone)

data class Vertical(val v: Int): Point(v) {
    fun isTopEdge() = v == 1
    fun isBottomEdge() = v == 8
}

data class Horizontal(val v: Int): Point(v) {
    fun isLeftEdge() = v == 1
    fun isRightEdge() = v == 8
}

open class Point(value: Int) {
    init {
        if (1 > value || 8 < value) throw OutOfRangeException("You must input number between 1 and 8.")
    }
}

class OutOfRangeException(override val message: String): Exception()