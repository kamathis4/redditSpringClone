package com.sample.redditbackend.utils

object ExceptionUtils {

    const val NOT_EMPTY = "Property should not be empty"

    const val MAX_LENGTH_EXCEEDED = "Max length for property exceeded"

    const val REGEXP_PASSWORD_MATCHER = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$"

    const val MAX_LENGTH = 25L

    const val MAX_LENGTH_DESC = 200L
}