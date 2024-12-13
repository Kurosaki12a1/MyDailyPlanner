package com.kuro.mdp.shared.presentation.theme.resources

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
data class AppIcons(
    val logo: String,
    val logoCircular: String,
    val splashIcon: String,
    val categoryWorkIcon: String,
    val categoryRestIcon: String,
    val categorySportIcon: String,
    val categoryCultureIcon: String,
    val categorySleepIcon: String,
    val categoryAffairsIcon: String,
    val categoryTransportIcon: String,
    val categoryStudyIcon: String,
    val categoryEatIcon: String,
    val categoryShopping: String,
    val categoryHealth: String,
    val categoryEntertainmentsIcon: String,
    val categoryOtherIcon: String,
    val arrowUp: String,
    val arrowDown: String,
    val categoryEmptyIcon: String,
    val compactViewIcon: String,
    val expandedViewIcon: String,
    val schedulerIcon: String,
    val categoriesIcon: String,
    val template: String,
    val enabledHomeIcon: String,
    val disabledHomeIcon: String,
    val enabledSettingsIcon: String,
    val disabledSettingsIcon: String,
    val enabledAnalyticsIcon: String,
    val disabledAnalyticsIcon: String,
    val categoryHygiene: String,
    val time: String,
    val reset: String,
    val overview: String,
    val plannedTask: String,
    val menuNavArrow: String,
    val cancel: String,
    val check: String,
    val add: String,
    val keyboard: String,
)

internal val baseTimePlannerIcons = AppIcons(
    logo = "ic_my_daily_planner",
    logoCircular = "ic_my_daily_planner_circular",
    splashIcon = "ic_splash",
    categoryWorkIcon = "ic_work",
    categoryRestIcon = "ic_rest",
    categorySportIcon = "ic_sport",
    categoryCultureIcon = "ic_culture",
    categorySleepIcon = "ic_sleep",
    categoryAffairsIcon = "ic_affairs",
    categoryTransportIcon = "ic_car",
    categoryStudyIcon = "ic_study",
    categoryEatIcon = "ic_eat",
    categoryHealth = "ic_medicine",
    categoryEntertainmentsIcon = "ic_entertainments",
    categoryShopping = "ic_store",
    categoryOtherIcon = "ic_interests",
    arrowUp = "ic_arrow_drop_up",
    arrowDown = "ic_arrow_drop_down",
    categoryEmptyIcon = "ic_close",
    compactViewIcon = "ic_compact_view",
    expandedViewIcon = "ic_expanded_view",
    schedulerIcon = "ic_schedule",
    categoriesIcon = "ic_categories",
    template = "ic_template",
    enabledHomeIcon = "ic_home",
    disabledHomeIcon = "ic_home_outlined",
    enabledSettingsIcon = "ic_settings",
    disabledSettingsIcon = "ic_settings_outline",
    enabledAnalyticsIcon = "ic_analytics",
    disabledAnalyticsIcon = "ic_analytics_outline",
    categoryHygiene = "ic_face_retouching",
    time = "ic_time",
    reset = "ic_reset",
    overview = "ic_dashboard",
    plannedTask = "ic_planned_task",
    menuNavArrow = "ic_arrow_right",
    cancel = "ic_cancel",
    check = "ic_check",
    add = "ic_add",
    keyboard = "ic_keyboard_outline",
)

val LocalAppIcons = staticCompositionLocalOf<AppIcons> {
    error("Core Icons is not provided")
}

fun fetchAppIcons() = baseTimePlannerIcons