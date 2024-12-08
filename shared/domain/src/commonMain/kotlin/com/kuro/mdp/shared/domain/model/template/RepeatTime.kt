package com.kuro.mdp.shared.domain.model.template

import com.kuro.mdp.shared.utils.extensions.fetchDayNumberByMax
import com.kuro.mdp.shared.utils.extensions.fetchWeekNumber
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.priorityByFirstDayOfWeek
import com.kuro.mdp.shared.utils.extensions.withDayOfMonth
import com.kuro.mdp.shared.utils.extensions.withDayOfWeek
import com.kuro.mdp.shared.utils.extensions.withMonth
import com.kuro.mdp.shared.utils.extensions.withWeekDayInMonth
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed class RepeatTime {

    abstract val repeatType: RepeatTimeType
    abstract val key: Int

    @Serializable
    data class WeekDays(val day: DayOfWeek) : RepeatTime() {

        @Transient
        override val repeatType = RepeatTimeType.WEEK_DAY

        @Transient
        override val key = day.isoDayNumber
    }

    @Serializable
    data class WeekDayInMonth(val day: DayOfWeek, val weekNumber: Int) : RepeatTime() {

        @Transient
        override val repeatType = RepeatTimeType.WEEK_DAY_IN_MONTH

        @Transient
        override val key = day.isoDayNumber + weekNumber
    }

    @Serializable
    data class MonthDay(val dayNumber: Int) : RepeatTime() {

        @Transient
        override val repeatType = RepeatTimeType.MONTH_DAY

        @Transient
        override val key = dayNumber
    }

    @Serializable
    data class YearDay(val month: Month, val dayNumber: Int) : RepeatTime() {

        @Transient
        override val repeatType = RepeatTimeType.YEAR_DAY

        @Transient
        override val key = month.number + dayNumber
    }

    fun checkDateIsRepeat(date: LocalDateTime) = when (this) {
        is WeekDays -> date.dayOfWeek == day
        is WeekDayInMonth -> date.dayOfWeek == day && date.date.fetchWeekNumber() == weekNumber
        is MonthDay -> date.fetchDayNumberByMax(dayNumber) == dayNumber
        is YearDay -> date.fetchDayNumberByMax(dayNumber) == dayNumber && date.month == month
    }

    fun nextDate(
        startTime: LocalDateTime,
        current: LocalDateTime = getLocalDateTimeNow()
    ): LocalDateTime {
        val firstDay = DayOfWeek.MONDAY // Customize based on your first day of week requirement
        var resultDate = current.date

        when (this) {
            is WeekDays -> {
                if (current.date.dayOfWeek.priorityByFirstDayOfWeek(firstDay) >=
                    day.priorityByFirstDayOfWeek(firstDay)
                ) {
                    resultDate = resultDate.plus(DatePeriod(days = 7))
                }
                resultDate = resultDate.withDayOfWeek(day)
            }

            is MonthDay -> {
                if (current.date.dayOfMonth >= dayNumber) {
                    resultDate = resultDate.plus(DatePeriod(months = 1)).withDayOfMonth(1)
                }
                resultDate = resultDate.withDayOfMonth(dayNumber)
            }

            is YearDay -> {
                if (current.date.monthNumber >= month.number && current.date.dayOfMonth > dayNumber) {
                    resultDate = resultDate.plus(DatePeriod(years = 1))
                }
                resultDate = resultDate.withMonth(month).withDayOfMonth(dayNumber)
            }

            is WeekDayInMonth -> {
                if (current.date.fetchWeekNumber() >= weekNumber &&
                    current.date.dayOfWeek
                        .priorityByFirstDayOfWeek(firstDay) > day.priorityByFirstDayOfWeek(firstDay)
                ) {
                    resultDate = resultDate.plus(DatePeriod(months = 1))
                }
                resultDate = resultDate.withWeekDayInMonth(weekNumber, day)
            }
        }
        return LocalDateTime(
            resultDate.year,
            resultDate.month,
            resultDate.dayOfMonth,
            startTime.hour,
            startTime.minute,
            startTime.second
        )
    }

}
