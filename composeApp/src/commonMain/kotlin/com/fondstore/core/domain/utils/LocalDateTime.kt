package com.fondstore.core.domain.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.format.DateTimeComponents

fun String.toLocalDateTime(): LocalDateTime {
    return DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET.parse(this)
        .toLocalDateTime()
}

fun LocalDate.formatDate(): String {
    return "${dayOfWeek.name.substring(0, 3).lowercase().properCapitalize()}, ${
        month.name.substring(0, 3).lowercase().properCapitalize()
    } ${dayOfMonth.formatDateTimeValue()} $year"
}

fun LocalDateTime.formatDateTime(): String {
    return formatTime() + " ${dayOfMonth.formatDateTimeValue()}, ${
        month.name.substring(0, 3).lowercase().properCapitalize()
    } $year"
}

fun LocalDateTime.formatTime(): String {
    return "${hour.formatDateTimeValue()}:${minute.formatDateTimeValue()}:${second.formatDateTimeValue()}"
}

fun Month.lastDay(leapYear: Boolean): Int {
    val leap = if (leapYear) 1 else 0
    return when (this) {
        Month.FEBRUARY -> 28 + leap
        Month.APRIL -> 30
        Month.JUNE -> 30
        Month.SEPTEMBER -> 30
        Month.NOVEMBER -> 30
        else -> 31
    }
}

fun String.properCapitalize(): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

fun Int.formatDateTimeValue() = if (this in 0..9) "0$this" else this
