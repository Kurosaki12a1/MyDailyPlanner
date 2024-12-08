package com.kuro.mdp.shared.utils.functional

object Constants {

    object App {
        const val SPLASH_NAME = "MY DAILY PLANNER"
        const val NAME = "DailyPlanner"
        const val DEVELOPER = "Huynh Minh Thinh"
        const val LICENCE = "Apache Licence v2.0"
        const val EDITOR_DEEP_LINK = "app://daily_planner.com/openEditor"
        const val PERMISSION_TAG = "Notification_Permission"
    }

    object Date {
        const val DAY = 1
        const val DAYS_IN_WEEK = 7
        const val DAYS_IN_MONTH = 31
        const val DAYS_IN_HALF_YEAR = 183
        const val DAYS_IN_YEAR = 365

        const val EMPTY_DURATION = 0L
        const val MILLIS_IN_SECONDS = 1000L
        const val MILLIS_IN_MINUTE = 60000L
        const val MILLIS_IN_HOUR = 3600000L
        const val SECONDS_IN_MINUTE = 60L
        const val MINUTES_IN_MILLIS = 60000L
        const val MINUTES_IN_HOUR = 60L
        const val HOURS_IN_DAY = 24L

        const val NEXT_REPEAT_LIMIT = 100L

        const val OVERVIEW_NEXT_DAYS = 15
        const val OVERVIEW_PREVIOUS_DAYS = 14

        const val MINUTES_FORMAT = "%s%s"
        const val HOURS_FORMAT = "%s%s"
        const val HOURS_AND_MINUTES_FORMAT = "%s%s %s%s"

        const val SHIFT_MINUTE_VALUE = 5
    }
}