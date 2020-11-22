package com.fiuba.bookbnb.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val DATE_SEPARATOR = "-"
    private const val DATE_FORMAT = "dd${DATE_SEPARATOR}MM${DATE_SEPARATOR}yyyy"
    private const val DATE_FORMAT_OUTPUT = "yyyy${DATE_SEPARATOR}MM${DATE_SEPARATOR}dd"

    fun getDateFormat() = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    fun getDateOutputFormat() = SimpleDateFormat(DATE_FORMAT_OUTPUT, Locale.getDefault())
}