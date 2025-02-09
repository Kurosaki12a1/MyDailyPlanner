package com.kuro.mdp.features.home.presentation.ui.home.theme.resources

import androidx.compose.runtime.staticCompositionLocalOf
import org.jetbrains.compose.resources.DrawableResource
import shared.resources.Res
import shared.resources.ic_add
import shared.resources.ic_bell_off
import shared.resources.ic_calendar
import shared.resources.ic_cancel
import shared.resources.ic_category
import shared.resources.ic_charts
import shared.resources.ic_check
import shared.resources.ic_complete_task
import shared.resources.ic_delete
import shared.resources.ic_end_time
import shared.resources.ic_info
import shared.resources.ic_menu
import shared.resources.ic_more
import shared.resources.ic_next
import shared.resources.ic_not_complete_task
import shared.resources.ic_not_found
import shared.resources.ic_notes
import shared.resources.ic_notification
import shared.resources.ic_off_repeat
import shared.resources.ic_play
import shared.resources.ic_previous
import shared.resources.ic_remove
import shared.resources.ic_repeat
import shared.resources.ic_repeat_variant
import shared.resources.ic_schedule
import shared.resources.ic_start_time
import shared.resources.ic_stop
import shared.resources.ic_subcategory
import shared.resources.ic_template
import shared.resources.ic_time
import shared.resources.ic_timer
import shared.resources.ic_update_repeat

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
internal data class HomeIcons(
    val nextDate: DrawableResource,
    val previousDate: DrawableResource,
    val more: DrawableResource,
    val add: DrawableResource,
    val remove: DrawableResource,
    val calendar: DrawableResource,
    val menu: DrawableResource,
    val check: DrawableResource,
    val notFound: DrawableResource,
    val category: DrawableResource,
    val subCategory: DrawableResource,
    val startTime: DrawableResource,
    val endTime: DrawableResource,
    val notification: DrawableResource,
    val info: DrawableResource,
    val cancel: DrawableResource,
    val statistics: DrawableResource,
    val duration: DrawableResource,
    val time: DrawableResource,
    val repeat: DrawableResource,
    val updateRepeat: DrawableResource,
    val repeatVariant: DrawableResource,
    val turnOffRepeat: DrawableResource,
    val stop: DrawableResource,
    val start: DrawableResource,
    val notes: DrawableResource,
    val schedule: DrawableResource,
    val completedTask: DrawableResource,
    val unExecutedTask: DrawableResource,
    val offNotifications: DrawableResource,
    val templates: DrawableResource,
    val delete: DrawableResource,
    val deadline: DrawableResource
)

internal val baseHomeIcons = HomeIcons(
    nextDate = Res.drawable.ic_next,
    previousDate = Res.drawable.ic_previous,
    more = Res.drawable.ic_more,
    add = Res.drawable.ic_add,
    remove = Res.drawable.ic_remove,
    calendar = Res.drawable.ic_calendar,
    menu = Res.drawable.ic_menu,
    check = Res.drawable.ic_check,
    notFound = Res.drawable.ic_not_found,
    category = Res.drawable.ic_category,
    subCategory = Res.drawable.ic_subcategory,
    startTime = Res.drawable.ic_start_time,
    endTime = Res.drawable.ic_end_time,
    notification = Res.drawable.ic_notification,
    info = Res.drawable.ic_info,
    cancel = Res.drawable.ic_cancel,
    statistics = Res.drawable.ic_charts,
    duration = Res.drawable.ic_timer,
    time = Res.drawable.ic_time,
    repeat = Res.drawable.ic_repeat,
    updateRepeat = Res.drawable.ic_update_repeat,
    repeatVariant = Res.drawable.ic_repeat_variant,
    turnOffRepeat = Res.drawable.ic_off_repeat,
    stop = Res.drawable.ic_stop,
    start = Res.drawable.ic_play,
    notes = Res.drawable.ic_notes,
    schedule = Res.drawable.ic_schedule,
    completedTask = Res.drawable.ic_complete_task,
    unExecutedTask = Res.drawable.ic_not_complete_task,
    offNotifications = Res.drawable.ic_bell_off,
    templates = Res.drawable.ic_template,
    delete = Res.drawable.ic_delete,
    deadline = Res.drawable.ic_timer
)

internal val LocalHomeIcons = staticCompositionLocalOf<HomeIcons> {
    error("Home Icons is not provided")
}

internal fun fetchHomeIcons() = baseHomeIcons
