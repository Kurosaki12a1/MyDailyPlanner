package com.kuro.mdp.shared.utils.functional

object Constants {

    object App {
        const val SPLASH_NAME = "MY DAILY\nPLANNER"
        const val NAME = "DailyPlanner"
        const val DEVELOPER = "Huynh Minh Thinh"
        const val LICENCE = "Apache Licence v2.0"
        const val EDITOR_DEEP_LINK = "app://daily_planner.com/openEditor"
        const val PERMISSION_TAG = "Notification_Permission"
        const val GITHUB_URI = "https://github.com/Kurosaki12a1/MyDailyPlanner"
        const val ISSUES_URI = "https://github.com/Kurosaki12a1/MyDailyPlanner/issues"
    }

    object Text {
        const val MAX_NOTE_LENGTH = 160
        const val MAX_LENGTH = 50
    }

    object DATABASE {
        const val SCHEDULES_DB_NAME = "schedules_db"
        const val SETTINGS_DB_NAME = "settings_db"
        const val INIT_TASK_SETTINGS_DB = """
            INSERT INTO TasksSettings (id, task_view_status, task_analytics_range, calendar_button_behavior, secure_mode) 
            VALUES (0, 'COMPACT', 'WEEK', 'SET_CURRENT_DATE', 0)
        """
        const val INIT_THEME_SETTINGS_DB = """
            INSERT INTO ThemeSettings (id, language, theme_colors, colors_type, dynamic_color) 
            VALUES (0, 'DEFAULT', 'DEFAULT', 'PINK', 0)
        """
    }

    object NavigationGraph {
        const val HOME = "HomeGraph"
        const val MAIN = "MainGraph"
        const val ANALYTICS = "AnalyticsGraph"
        const val SETTINGS = "SettingsGraph"
        const val OVERVIEW = "OverviewGraph"
        const val CATEGORIES = "CategoriesGraph"
    }

    object Destination {
        const val HOME = "HomeDestination"
        const val SPLASH = "SplashDestination"
        const val ANALYTICS = "AnalyticsDestination"
        const val SETTINGS = "SettingsDestination"
        const val OVERVIEW = "OverviewDestination"
        const val CATEGORIES = "CategoriesDestination"
    }

    object NavigationArguments {
        const val DATE = "date"
        const val ID = "id"
    }

    object Arguments {
        const val SCHEDULE_DATE = "scheduleDate"
    }

    object Placeholder {
        const val ITEMS = 6
        const val MANY_ITEMS = 15
    }

    object Delay {
        const val MARQUEE_LOADING_TEXT = 1500
        const val LOAD_ANIMATION = 400L
        const val SPLASH_BEFORE_NAVIGATE = 900L
        const val SPLASH_LOGO = 300L
        const val SPLASH_TEXT = 600L
        const val CHECK_STATUS = 5000L
        const val NAVIGATION_FLOW = 250L
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

        const val MINUTES_FORMAT = "%s %s"
        const val HOURS_FORMAT = "%s %s"
        const val HOURS_AND_MINUTES_FORMAT = "%s %s %s %s"

        const val SHIFT_MINUTE_VALUE = 5
    }

    object Alarm {
        const val ALARM_NOTIFICATION_ACTION = "kuro.mdp.ALARM_NOTIFICATION_ACTION"
        const val NOTIFICATION_TIME_TYPE = "ALARM_DATA_TIME_TYPE"
        const val NOTIFICATION_CATEGORY = "ALARM_DATA_CATEGORY"
        const val NOTIFICATION_SUBCATEGORY = "ALARM_DATA_SUBCATEGORY"
        const val NOTIFICATION_ICON = "ALARM_DATA_ICON"
        const val APP_ICON = "ALARM_DATA_APP_ICON"
        const val REPEAT_TIME = "REPEAT_TIME"
        const val REPEAT_TYPE = "REPEAT_TYPE"
        const val TEMPLATE_ID = "REPEAT_TEMPLATE_ID"
        const val DAY_OF_MONTH = "REPEAT_DAY_OF_MONTH"
        const val WEEK_DAY = "REPEAT_WEEK_DAY"
        const val WEEK_NUMBER = "REPEAT_WEEK_NUMBER"
        const val MONTH = "REPEAT_MONTH"
    }

    object State {
        const val LOADING = "LOADING"
        const val DEFAULT = "DEFAULT"
    }
}