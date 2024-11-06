package com.fondstore.core.domain.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.format.DateTimeComponents

fun String.isInvalidNumber(): Boolean = length in 1..10

fun String.isValidNumber(): Boolean = isNotEmpty() && !isInvalidNumber()

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

fun String.properCapitalize(): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

fun Int.formatDateTimeValue() = if (this in 0..9) "0$this" else this
