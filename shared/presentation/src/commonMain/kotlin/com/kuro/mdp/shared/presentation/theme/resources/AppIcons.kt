package com.kuro.mdp.shared.presentation.theme.resources

import androidx.compose.runtime.staticCompositionLocalOf
import org.jetbrains.compose.resources.DrawableResource
import shared.resources.Res
import shared.resources.ic_add
import shared.resources.ic_affairs
import shared.resources.ic_analytics
import shared.resources.ic_analytics_outline
import shared.resources.ic_arrow_drop_down
import shared.resources.ic_arrow_drop_up
import shared.resources.ic_arrow_right
import shared.resources.ic_cancel
import shared.resources.ic_car
import shared.resources.ic_categories
import shared.resources.ic_category
import shared.resources.ic_check
import shared.resources.ic_close
import shared.resources.ic_compact_view
import shared.resources.ic_culture
import shared.resources.ic_dashboard
import shared.resources.ic_eat
import shared.resources.ic_end_time
import shared.resources.ic_entertainments
import shared.resources.ic_expanded_view
import shared.resources.ic_face_retouching
import shared.resources.ic_home
import shared.resources.ic_home_outlined
import shared.resources.ic_info
import shared.resources.ic_interests
import shared.resources.ic_keyboard_outline
import shared.resources.ic_medicine
import shared.resources.ic_my_daily_planner
import shared.resources.ic_my_daily_planner_circular
import shared.resources.ic_notification
import shared.resources.ic_planned_task
import shared.resources.ic_priority_high
import shared.resources.ic_reset
import shared.resources.ic_rest
import shared.resources.ic_schedule
import shared.resources.ic_settings
import shared.resources.ic_settings_outline
import shared.resources.ic_sleep
import shared.resources.ic_splash
import shared.resources.ic_sport
import shared.resources.ic_start_time
import shared.resources.ic_store
import shared.resources.ic_study
import shared.resources.ic_sub_category
import shared.resources.ic_subcategory
import shared.resources.ic_template
import shared.resources.ic_time
import shared.resources.ic_work

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */

data class AppIcons(
    val logo: DrawableResource,
    val logoCircular: DrawableResource,
    val splashIcon: DrawableResource,
    val category : DrawableResource,
    val subCategory : DrawableResource,
    val categoryWorkIcon: DrawableResource,
    val categoryRestIcon: DrawableResource,
    val categorySportIcon: DrawableResource,
    val categoryCultureIcon: DrawableResource,
    val categorySleepIcon: DrawableResource,
    val categoryAffairsIcon: DrawableResource,
    val categoryTransportIcon: DrawableResource,
    val categoryStudyIcon: DrawableResource,
    val categoryEatIcon: DrawableResource,
    val categoryShopping: DrawableResource,
    val categoryHealth: DrawableResource,
    val categoryEntertainmentsIcon: DrawableResource,
    val categoryOtherIcon: DrawableResource,
    val arrowUp: DrawableResource,
    val arrowDown: DrawableResource,
    val categoryEmptyIcon: DrawableResource,
    val compactViewIcon: DrawableResource,
    val expandedViewIcon: DrawableResource,
    val schedulerIcon: DrawableResource,
    val categoriesIcon: DrawableResource,
    val template: DrawableResource,
    val enabledHomeIcon: DrawableResource,
    val disabledHomeIcon: DrawableResource,
    val enabledSettingsIcon: DrawableResource,
    val disabledSettingsIcon: DrawableResource,
    val enabledAnalyticsIcon: DrawableResource,
    val disabledAnalyticsIcon: DrawableResource,
    val categoryHygiene: DrawableResource,
    val time: DrawableResource,
    val reset: DrawableResource,
    val overview: DrawableResource,
    val plannedTask: DrawableResource,
    val menuNavArrow: DrawableResource,
    val cancel: DrawableResource,
    val check: DrawableResource,
    val add: DrawableResource,
    val keyboard: DrawableResource,
    val priority: DrawableResource,
    val startTime : DrawableResource,
    val endTime : DrawableResource,
    val notification: DrawableResource,
    val info : DrawableResource
)

internal val baseAppIcons = AppIcons(
    logo = Res.drawable.ic_my_daily_planner,
    logoCircular = Res.drawable.ic_my_daily_planner_circular,
    splashIcon = Res.drawable.ic_splash,
    category = Res.drawable.ic_category,
    subCategory = Res.drawable.ic_subcategory,
    categoryWorkIcon = Res.drawable.ic_work,
    categoryRestIcon = Res.drawable.ic_rest,
    categorySportIcon = Res.drawable.ic_sport,
    categoryCultureIcon = Res.drawable.ic_culture,
    categorySleepIcon = Res.drawable.ic_sleep,
    categoryAffairsIcon = Res.drawable.ic_affairs,
    categoryTransportIcon = Res.drawable.ic_car,
    categoryStudyIcon = Res.drawable.ic_study,
    categoryEatIcon = Res.drawable.ic_eat,
    categoryHealth = Res.drawable.ic_medicine,
    categoryEntertainmentsIcon = Res.drawable.ic_entertainments,
    categoryShopping = Res.drawable.ic_store,
    categoryOtherIcon = Res.drawable.ic_interests,
    arrowUp = Res.drawable.ic_arrow_drop_up,
    arrowDown = Res.drawable.ic_arrow_drop_down,
    categoryEmptyIcon = Res.drawable.ic_close,
    compactViewIcon = Res.drawable.ic_compact_view,
    expandedViewIcon = Res.drawable.ic_expanded_view,
    schedulerIcon = Res.drawable.ic_schedule,
    categoriesIcon = Res.drawable.ic_categories,
    template = Res.drawable.ic_template,
    enabledHomeIcon = Res.drawable.ic_home,
    disabledHomeIcon = Res.drawable.ic_home_outlined,
    enabledSettingsIcon = Res.drawable.ic_settings,
    disabledSettingsIcon = Res.drawable.ic_settings_outline,
    enabledAnalyticsIcon = Res.drawable.ic_analytics,
    disabledAnalyticsIcon = Res.drawable.ic_analytics_outline,
    categoryHygiene = Res.drawable.ic_face_retouching,
    time = Res.drawable.ic_time,
    reset = Res.drawable.ic_reset,
    overview = Res.drawable.ic_dashboard,
    plannedTask = Res.drawable.ic_planned_task,
    menuNavArrow = Res.drawable.ic_arrow_right,
    cancel = Res.drawable.ic_cancel,
    check = Res.drawable.ic_check,
    add = Res.drawable.ic_add,
    keyboard = Res.drawable.ic_keyboard_outline,
    priority = Res.drawable.ic_priority_high,
    startTime = Res.drawable.ic_start_time,
    endTime = Res.drawable.ic_end_time,
    notification = Res.drawable.ic_notification,
    info = Res.drawable.ic_info
)


/*data class AppIcons(
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
)*/

val LocalAppIcons = staticCompositionLocalOf<AppIcons> {
    error("Core Icons is not provided")
}

fun fetchAppIcons() = baseAppIcons