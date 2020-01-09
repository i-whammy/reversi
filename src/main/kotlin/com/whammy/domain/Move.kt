package com.whammy.domain

import java.lang.Exception

class Move(vertical: Int, horizontal: Int) {
    init {
        if (1 > vertical || 8 < vertical ||
            1 > horizontal || 8 < horizontal) {
            throw OutOfRangeException("You must input number between 1 and 8.")
        }
    }
}

class OutOfRangeException(override val message: String): Exception()