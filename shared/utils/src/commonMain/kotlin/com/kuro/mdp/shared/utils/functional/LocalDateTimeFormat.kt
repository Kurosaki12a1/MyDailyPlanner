package com.kuro.mdp.shared.utils.functional

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

/**
 * Created by: minhthinh.h on 2/17/2025
 *
 * Description:
 */

/**
 * Returns a `DateTimeFormat` for a full date-time format, including day of the week,
 * month name, day of the month, and year.
 *
 * @param listDayOfWeekName List of day of the week names to be used in the format.
 * @param listOfMonthName List of month names to be used in the format.
 * @return A `DateTimeFormat` for the specified date-time format.
 */
fun getTimeFormatOfWeek(
    listDayOfWeekName: List<String> = DayOfWeekNames.ENGLISH_FULL.names,
    listOfMonthName: List<String> = MonthNames.ENGLISH_FULL.names
): DateTimeFormat<LocalDateTime> {
    return LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames(listDayOfWeekName))
        chars(", ")
        monthName(MonthNames(listOfMonthName))
        char(' ')
        dayOfMonth()
        chars(", ''")
        yearTwoDigits(2000)
    }
}

/**
 * Returns a `DateTimeFormat` for a date-time format that includes AM/PM markers.
 *
 * @param amFormat The string to be used for AM marker.
 * @param pmFormat The string to be used for PM marker.
 * @return A `DateTimeFormat` for the specified date-time format.
 */
fun getTimeFormatOfDay(
    amFormat: String,
    pmFormat: String
): DateTimeFormat<LocalDateTime> {
    return LocalDateTime.Format {
        monthNumber()
        char('/')
        dayOfMonth()
        char('/')
        yearTwoDigits(2000)
        chars(", ")
        amPmHour()
        char(':')
        minute()
        char(' ')
        amPmMarker(am = amFormat, pm = pmFormat)
    }
}

/**
 * Returns a `DateTimeFormat` for a month-based date-time format, including day of the week,
 * day of the month, and month name.
 *
 * @param listDayOfWeekName List of day of the week names to be used in the format.
 * @param listOfMonthName List of month names to be used in the format.
 * @return A `DateTimeFormat` for the specified date-time format.
 */
fun getTimeFormatOfMonth(
    listDayOfWeekName: List<String> = DayOfWeekNames.ENGLISH_FULL.names,
    listOfMonthName: List<String> = MonthNames.ENGLISH_FULL.names
): DateTimeFormat<LocalDateTime> {
    return LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames(listDayOfWeekName))
        chars(", ")
        dayOfMonth()
        char(' ')
        monthName(MonthNames(listOfMonthName))
    }
}

/**
 * Returns a `DateTimeFormat` for a short date-time format, including only hour and minute.
 *
 * @return A `DateTimeFormat` for the specified short date-time format.
 */
fun getTimeFormatShort(): DateTimeFormat<LocalDateTime> {
    return LocalDateTime.Format {
        hour()
        char('-')
        minute()
    }
}

fun getDateFormatMedium() : DateTimeFormat<LocalDateTime> {
    return LocalDateTime.Format {
        dayOfMonth()
        char('-')
        monthNumber()
        char('-')
        yearTwoDigits(2000)
    }
}

fun getDateFormatShort() : DateTimeFormat<LocalDateTime> {
    return LocalDateTime.Format {
        dayOfMonth()
        char('-')
        monthNumber()
    }
}