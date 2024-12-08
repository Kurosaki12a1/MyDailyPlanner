package com.kuro.mdp.shared.utils.managers

import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.DurationUnit

interface DateManager {
    fun fetchCurrentDate(): LocalDateTime
    fun fetchBeginningCurrentDay(): LocalDateTime
    fun fetchEndCurrentDay(): LocalDateTime
    fun calculateLeftTime(endTime: LocalDateTime): Long
    fun calculateProgress(startTime: LocalDateTime, endTime: LocalDateTime): Float
    fun setCurrentHMS(date: LocalDateTime): LocalDateTime
}

class DateManagerImpl : DateManager {
    private val timeZone = TimeZone.currentSystemDefault()

    override fun fetchCurrentDate(): LocalDateTime = getLocalDateTimeNow()

    override fun fetchBeginningCurrentDay(): LocalDateTime {
        val now = fetchCurrentDate()
        return now.date.atStartOfDayIn(timeZone).toLocalDateTime(timeZone)
    }

    override fun fetchEndCurrentDay(): LocalDateTime {
        val now = fetchCurrentDate()
        return now.date.atStartOfDayIn(timeZone)
            .plus(1, DateTimeUnit.DAY, timeZone)
            .minus(1, DateTimeUnit.SECOND, timeZone)
            .toLocalDateTime(timeZone)
    }

    override fun calculateLeftTime(endTime: LocalDateTime): Long {
        val currentDateTime = fetchCurrentDate()
        val currentInstant = currentDateTime.toInstant(timeZone)
        val endInstant = endTime.toInstant(timeZone)
        return (endInstant - currentInstant).inWholeMilliseconds
    }

    override fun calculateProgress(startTime: LocalDateTime, endTime: LocalDateTime): Float {
        val currentInstant = fetchCurrentDate().toInstant(timeZone)
        val startInstant = startTime.toInstant(timeZone)
        val endInstant = endTime.toInstant(timeZone)

        val totalDuration = (endInstant - startInstant).toDouble(DurationUnit.MILLISECONDS)
        val elapsedDuration = (currentInstant - startInstant).toDouble(DurationUnit.MILLISECONDS)

        return when {
            elapsedDuration < 0 -> 0f
            elapsedDuration > totalDuration -> 1f
            else -> (elapsedDuration / totalDuration).toFloat()
        }
    }

    override fun setCurrentHMS(date: LocalDateTime): LocalDateTime {
        val current = fetchCurrentDate()
        return LocalDateTime(
            year = date.year,
            monthNumber = date.monthNumber,
            dayOfMonth = date.dayOfMonth,
            hour = current.hour,
            minute = current.minute,
            second = current.second,
            nanosecond = current.nanosecond
        )
    }

}