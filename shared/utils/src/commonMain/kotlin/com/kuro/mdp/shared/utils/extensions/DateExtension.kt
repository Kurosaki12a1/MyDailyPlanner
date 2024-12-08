package com.kuro.mdp.shared.utils.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit


/**
 * Extension function to shift the current LocalDateTime by a specific number of minutes.
 * @param amount The number of minutes to shift (can be positive or negative).
 * @return The new LocalDateTime after the shift.
 */
fun LocalDateTime.shiftMinutes(amount: Int): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    val instant = this.toInstant(timeZone)

    return instant.plus(amount, DateTimeUnit.MINUTE, timeZone).toLocalDateTime(timeZone)
}

/**
 * Extension function to shift the current LocalDateTime by a specific number of hours.
 * @param amount The number of hours to shift (can be positive or negative).
 * @return The new LocalDateTime after the shift.
 */
fun LocalDateTime.shiftHours(amount: Int): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    val instant = this.toInstant(timeZone)

    return instant.plus(amount, DateTimeUnit.HOUR, timeZone).toLocalDateTime(timeZone)
}

/**
 * Extension function to shift the current LocalDateTime by a specific number of days.
 * @param amount The number of days to shift (can be positive or negative).
 * @return The new LocalDateTime after the shift.
 */
fun LocalDateTime.shiftDays(amount: Int): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    return date.atStartOfDayIn(timeZone).plus(amount, DateTimeUnit.DAY, timeZone)
        .toLocalDateTime(timeZone)
}


/**
 * Extension function to shift the current LocalDateTime by a specific number of milliseconds.
 * @param amount The number of milliseconds to shift (can be positive or negative).
 * @return The new LocalDateTime after the shift.
 */
fun LocalDateTime.shiftMillis(amount: Int): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    val instant = this.toInstant(timeZone)

    return instant.plus(amount, DateTimeUnit.MILLISECOND, timeZone).toLocalDateTime(timeZone)
}

/**
 * Function to calculate the current day number adjusted by a specified value,
 * considering the number of days in the previous month.
 * @param dayNumber The adjustment value for the day number.
 * @return The adjusted day number.
 */
fun LocalDateTime.fetchDayNumberByMax(dayNumber: Int): Int {
    val currentDayNumber = this.dayOfMonth
    val previousMonth = this.date.minus(DatePeriod(months = 1))
    val previousMonthDaysCount = previousMonth.lengthOfMonth()
    return if (dayNumber > previousMonthDaysCount) {
        currentDayNumber + dayNumber
    } else {
        currentDayNumber
    }
}

/**
 * Function to calculate the number of days in the current month.
 * It determines the last day of the current month by calculating the first day of the next month
 * and subtracting one day from it.
 */
fun LocalDate.lengthOfMonth(): Int {
    // Determine the first day of the next month
    val firstDayOfNextMonth = when (this.month) {
        // If it's December, move to January of the next year
        Month.DECEMBER -> LocalDate(this.year + 1, Month.JANUARY, 1)
        // For other months, move to the next month
        else -> LocalDate(this.year, this.monthNumber + 1, 1)
    }
    // Subtract one day to find the last day of the current month
    val lastDayOfThisMonth = firstDayOfNextMonth.minus(DatePeriod(days = 1))
    // Return the day of the last day of the current month
    return lastDayOfThisMonth.dayOfMonth
}

fun LocalDateTime.compareByHoursAndMinutes(compareDate: LocalDateTime): Boolean {
    // Compare hour and minute
    return this.hour == compareDate.hour && this.minute == compareDate.minute
}

/**
 * Extension function to calculate the priority of a day of the week
 * based on the first day of the week where SUNDAY has the lowest priority
 * and MONDAY is considered the highest.
 * @param startDay The starting day of the week (e.g., MONDAY).
 * @return The priority of the current day based on the starting day.
 */
fun DayOfWeek.priorityByFirstDayOfWeek(startDay: DayOfWeek): Int {
    // DayOfWeek.ordinal gives 0 for MONDAY, 6 for SUNDAY
    val dayNumber = this.ordinal
    val startNumber = startDay.ordinal
    return if (dayNumber >= startNumber) {
        dayNumber - startNumber
    } else {
        7 - (startNumber - dayNumber)
    }
}

fun LocalDateTime.getFirstDayOfWeek(): LocalDate {
    // Get the current day of the week (0-based: Monday = 0, Sunday = 6)
    val dayOfWeek = this.dayOfWeek.ordinal // Monday = 0, Sunday = 6
    // Calculate the offset to Monday
    val offsetToMonday = -dayOfWeek
    // Adjust the current date to the Monday
    return this.date.plus(DatePeriod(days = offsetToMonday))
}

fun LocalDate.withDayOfWeek(dayOfWeek: DayOfWeek): LocalDate {
    val currentDayOfWeek = this.dayOfWeek.ordinal
    val targetDayOfWeek = dayOfWeek.ordinal
    val difference = (targetDayOfWeek - currentDayOfWeek + 7) % 7
    return this.plus(DatePeriod(days = difference))
}

fun LocalDate.withDayOfMonth(day: Int): LocalDate = LocalDate(this.year, this.month, day)

fun LocalDate.withMonth(month: Month): LocalDate = LocalDate(this.year, month, this.dayOfMonth)

fun LocalDate.fetchWeekNumber(): Int {
    val offset = (this.dayOfMonth - 1) / 7
    return offset + 1
}

fun LocalDate.withWeekDayInMonth(weekNumber: Int, dayOfWeek: DayOfWeek): LocalDate {
    val firstDayOfMonth = this.withDayOfMonth(1)
    val firstDayOffset = (dayOfWeek.ordinal - firstDayOfMonth.dayOfWeek.ordinal + 7) % 7
    val targetDay = 1 + firstDayOffset + (weekNumber - 1) * 7
    return if (targetDay <= this.lengthOfMonth()) {
        this.withDayOfMonth(targetDay)
    } else {
        throw IllegalArgumentException("Invalid weekNumber or dayOfWeek for the given month")
    }
}

fun LocalDateTime.isCurrentDay(date: LocalDateTime): Boolean {
    return this.date == date.date
}

fun Long.toSeconds(): Long {
    return this.milliseconds.toDouble(DurationUnit.SECONDS).toLong()
}

fun Long.toMinutes(): Long {
    return this.milliseconds.toDouble(DurationUnit.MINUTES).toLong()
}

fun Long.toHours(): Long {
    return this.milliseconds.toDouble(DurationUnit.HOURS).toLong()
}

fun Long.toDays(): Long {
    return this.milliseconds.toDouble(DurationUnit.DAYS).toLong()
}

fun LocalDateTime.toEpochMillis(timeZone: TimeZone = TimeZone.currentSystemDefault()): Long {
    return this.toInstant(timeZone).toEpochMilliseconds()
}

fun Long.mapToDate(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
    return Instant.fromEpochMilliseconds(this).toLocalDateTime(timeZone)
}

fun getLocalDateTimeNow() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())