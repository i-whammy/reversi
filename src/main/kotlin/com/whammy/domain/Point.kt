package com.whammy.domain

import java.lang.Exception

data class Point(val vertical: Vertical, val horizontal: Horizontal) {
    companion object {
        fun at(v: Int, h: Int) = Point(Vertical(v), Horizontal(h))
    }

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
            Direction.TopLeft -> at(vertical.value-1,horizontal.value-1)
            Direction.Top -> at(vertical.value-1,horizontal.value)
            Direction.TopRight -> at(vertical.value-1,horizontal.value+1)
            Direction.Right -> at(vertical.value,horizontal.value+1)
            Direction.BottomRight -> at(vertical.value+1,horizontal.value+1)
            Direction.Bottom -> at(vertical.value+1,horizontal.value)
            Direction.BottomLeft -> at(vertical.value+1,horizontal.value-1)
            Direction.Left -> at(vertical.value,horizontal.value-1)
        }
    }
}

data class Vertical(val value: Int) {
    init {
        if (1 > value || 8 < value) throw OutOfRangeException("You must input number between 1 and 8.")
    }
}

data class Horizontal(val value: Int) {
    init {
        if (1 > value || 8 < value) throw OutOfRangeException("You must input number between 1 and 8.")
    }
}

class OutOfRangeException(override val message: String): Exception()