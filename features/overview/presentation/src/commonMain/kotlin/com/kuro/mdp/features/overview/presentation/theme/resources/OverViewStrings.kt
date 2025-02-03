package com.kuro.mdp.features.overview.presentation.theme.resources

import androidx.compose.runtime.staticCompositionLocalOf
import org.jetbrains.compose.resources.StringResource
import overview.resources.Res
import overview.resources.accomplishmentScheduleTitle
import overview.resources.addCategoryTitle
import overview.resources.addFreeTimeTaskTitle
import overview.resources.addSubCategoryTitle
import overview.resources.addTaskTitle
import overview.resources.addTemplatesFabTitle
import overview.resources.addTimeTaskIconsDesc
import overview.resources.categoryFieldLabel
import overview.resources.createScheduleDesc
import overview.resources.createScheduleTitle
import overview.resources.currentTaskHeader
import overview.resources.dateDialogPickerHeadline
import overview.resources.dateDialogPickerTitle
import overview.resources.dayTitleHome
import overview.resources.deadlineLabel
import overview.resources.deleteCategoryTitle
import overview.resources.emptyListTitle
import overview.resources.executeUndefinedTasksTitle
import overview.resources.foundedPlannedTasksTitle
import overview.resources.importanceError
import overview.resources.mainCategoryChooserTitle
import overview.resources.mainCategoryTitle
import overview.resources.monthTitle
import overview.resources.nameCategoryTitle
import overview.resources.navToBackTitle
import overview.resources.nextDateIconDesc
import overview.resources.noneTitle
import overview.resources.noteTitle
import overview.resources.notificationDisabledTitle
import overview.resources.notificationEnabledTitle
import overview.resources.otherError
import overview.resources.plannedScheduleTitle
import overview.resources.previousDateIconDesc
import overview.resources.progressTitle
import overview.resources.realizedScheduleTitle
import overview.resources.restoreDefaultCategories
import overview.resources.schedulesHeader
import overview.resources.shiftError
import overview.resources.showAllSchedulesTitle
import overview.resources.sortedTypeCategories
import overview.resources.sortedTypeDate
import overview.resources.sortedTypeDuration
import overview.resources.sortedTypeTitle
import overview.resources.specifyDeadlineTitle
import overview.resources.startTimeTaskTitlePlaceHolder
import overview.resources.statisticsActiveTitle
import overview.resources.statisticsDisabledTitle
import overview.resources.subCategoryChooserTitle
import overview.resources.subCategoryDialogMainCategoryFormat
import overview.resources.subCategoryTitle
import overview.resources.taskDateChooserFieldLabel
import overview.resources.taskDateChooserHeader
import overview.resources.timeTaskAddIconDesc
import overview.resources.timeTaskCheckIconDesc
import overview.resources.timeTaskExpandedIconDesc
import overview.resources.timeTaskIncreaseTimeTitle
import overview.resources.timeTaskMoreIconDesc
import overview.resources.timeTaskReduceTimeTitle
import overview.resources.timeTaskRemoveIconDesc
import overview.resources.topAppBarCalendarIconDesc
import overview.resources.topAppBarCategoriesTitle
import overview.resources.topAppBarHomeTitle
import overview.resources.topAppBarMenuIconDesc
import overview.resources.topAppBarMoreIconDesc
import overview.resources.topAppBarOverviewTitle
import overview.resources.topAppBarTemplatesTitle
import overview.resources.undefinedTasksHeader
import overview.resources.updateCategoryTitle
import overview.resources.warningDeleteCategoryText
import overview.resources.weekDayTitle
import overview.resources.weekNumberTitle

/**
 * Created by: minhthinh.h on 12/18/2024
 *
 * Description:
 */
internal data class OverViewStrings(
    val topAppBarHomeTitle: StringResource,
    val topAppBarCategoriesTitle: StringResource,
    val topAppBarOverviewTitle: StringResource,
    val mainCategoryTitle: StringResource,
    val topAppBarCalendarIconDesc: StringResource,
    val topAppBarMenuIconDesc: StringResource,
    val nextDateIconDesc: StringResource,
    val previousDateIconDesc: StringResource,
    val dateDialogPickerHeadline: StringResource,
    val dateDialogPickerTitle: StringResource,
    val timeTaskExpandedIconDesc: StringResource,
    val timeTaskCheckIconDesc: StringResource,
    val timeTaskMoreIconDesc: StringResource,
    val timeTaskAddIconDesc: StringResource,
    val timeTaskRemoveIconDesc: StringResource,
    val timeTaskIncreaseTimeTitle: StringResource,
    val timeTaskReduceTimeTitle: StringResource,
    val startTimeTaskTitlePlaceHolder: StringResource,
    val addTimeTaskIconsDesc: StringResource,
    val addFreeTimeTaskTitle: StringResource,
    val addTaskTitle: StringResource,
    val navToBackTitle: StringResource,
    val topAppBarMoreIconDesc: StringResource,
    val topAppBarTemplatesTitle: StringResource,
    val mainCategoryChooserTitle: StringResource,
    val subCategoryChooserTitle: StringResource,
    val subCategoryDialogMainCategoryFormat: StringResource,
    val createScheduleTitle: StringResource,
    val createScheduleDesc: StringResource,
    val shiftError: StringResource,
    val importanceError: StringResource,
    val otherError: StringResource,
    val addSubCategoryTitle: StringResource,
    val categoryFieldLabel: StringResource,
    val emptyListTitle: StringResource,
    val updateCategoryTitle: StringResource,
    val deleteCategoryTitle: StringResource,
    val subCategoryTitle: StringResource,
    val warningDeleteCategoryText: StringResource,
    val sortedTypeTitle: StringResource,
    val sortedTypeDate: StringResource,
    val sortedTypeCategories: StringResource,
    val sortedTypeDuration: StringResource,
    val notificationEnabledTitle: StringResource,
    val notificationDisabledTitle: StringResource,
    val statisticsActiveTitle: StringResource,
    val statisticsDisabledTitle: StringResource,
    val addTemplatesFabTitle: StringResource,
    val addCategoryTitle: StringResource,
    val nameCategoryTitle: StringResource,
    val foundedPlannedTasksTitle: StringResource,
    val monthTitle: StringResource,
    val dayTitle: StringResource,
    val weekNumberTitle: StringResource,
    val weekDayTitle: StringResource,
    val noteTitle: StringResource,
    val restoreDefaultCategories: StringResource,
    val schedulesHeader: StringResource,
    val realizedScheduleTitle: StringResource,
    val accomplishmentScheduleTitle: StringResource,
    val plannedScheduleTitle: StringResource,
    val undefinedTasksHeader: StringResource,
    val executeUndefinedTasksTitle: StringResource,
    val currentTaskHeader: StringResource,
    val noneTitle: StringResource,
    val progressTitle: StringResource,
    val taskDateChooserHeader: StringResource,
    val taskDateChooserFieldLabel: StringResource,
    val specifyDeadlineTitle: StringResource,
    val deadlineLabel: StringResource,
    val showAllSchedulesTitle: StringResource
)

internal val baseOverViewStrings = OverViewStrings(
    topAppBarHomeTitle = Res.string.topAppBarHomeTitle,
    topAppBarCategoriesTitle = Res.string.topAppBarCategoriesTitle,
    topAppBarOverviewTitle = Res.string.topAppBarOverviewTitle,
    mainCategoryTitle = Res.string.mainCategoryTitle,
    topAppBarCalendarIconDesc = Res.string.topAppBarCalendarIconDesc,
    topAppBarMenuIconDesc = Res.string.topAppBarMenuIconDesc,
    nextDateIconDesc = Res.string.nextDateIconDesc,
    previousDateIconDesc = Res.string.previousDateIconDesc,
    dateDialogPickerHeadline = Res.string.dateDialogPickerHeadline,
    dateDialogPickerTitle = Res.string.dateDialogPickerTitle,
    timeTaskExpandedIconDesc = Res.string.timeTaskExpandedIconDesc,
    timeTaskCheckIconDesc = Res.string.timeTaskCheckIconDesc,
    timeTaskMoreIconDesc = Res.string.timeTaskMoreIconDesc,
    timeTaskAddIconDesc = Res.string.timeTaskAddIconDesc,
    timeTaskRemoveIconDesc = Res.string.timeTaskRemoveIconDesc,
    timeTaskIncreaseTimeTitle = Res.string.timeTaskIncreaseTimeTitle,
    timeTaskReduceTimeTitle = Res.string.timeTaskReduceTimeTitle,
    startTimeTaskTitlePlaceHolder = Res.string.startTimeTaskTitlePlaceHolder,
    addTimeTaskIconsDesc = Res.string.addTimeTaskIconsDesc,
    addFreeTimeTaskTitle = Res.string.addFreeTimeTaskTitle,
    addTaskTitle = Res.string.addTaskTitle,
    navToBackTitle = Res.string.navToBackTitle,
    topAppBarMoreIconDesc = Res.string.topAppBarMoreIconDesc,
    topAppBarTemplatesTitle = Res.string.topAppBarTemplatesTitle,
    mainCategoryChooserTitle = Res.string.mainCategoryChooserTitle,
    subCategoryChooserTitle = Res.string.subCategoryChooserTitle,
    subCategoryDialogMainCategoryFormat = Res.string.subCategoryDialogMainCategoryFormat,
    createScheduleTitle = Res.string.createScheduleTitle,
    createScheduleDesc = Res.string.createScheduleDesc,
    shiftError = Res.string.shiftError,
    importanceError = Res.string.importanceError,
    otherError = Res.string.otherError,
    addSubCategoryTitle = Res.string.addSubCategoryTitle,
    categoryFieldLabel = Res.string.categoryFieldLabel,
    emptyListTitle = Res.string.emptyListTitle,
    updateCategoryTitle = Res.string.updateCategoryTitle,
    deleteCategoryTitle = Res.string.deleteCategoryTitle,
    subCategoryTitle = Res.string.subCategoryTitle,
    warningDeleteCategoryText = Res.string.warningDeleteCategoryText,
    sortedTypeTitle = Res.string.sortedTypeTitle,
    sortedTypeDate = Res.string.sortedTypeDate,
    sortedTypeCategories = Res.string.sortedTypeCategories,
    sortedTypeDuration = Res.string.sortedTypeDuration,
    notificationEnabledTitle = Res.string.notificationEnabledTitle,
    notificationDisabledTitle = Res.string.notificationDisabledTitle,
    statisticsActiveTitle = Res.string.statisticsActiveTitle,
    statisticsDisabledTitle = Res.string.statisticsDisabledTitle,
    addTemplatesFabTitle = Res.string.addTemplatesFabTitle,
    addCategoryTitle = Res.string.addCategoryTitle,
    nameCategoryTitle = Res.string.nameCategoryTitle,
    foundedPlannedTasksTitle = Res.string.foundedPlannedTasksTitle,
    monthTitle = Res.string.monthTitle,
    dayTitle = Res.string.dayTitleHome,
    weekNumberTitle = Res.string.weekNumberTitle,
    weekDayTitle = Res.string.weekDayTitle,
    noteTitle = Res.string.noteTitle,
    restoreDefaultCategories = Res.string.restoreDefaultCategories,
    schedulesHeader = Res.string.schedulesHeader,
    realizedScheduleTitle = Res.string.realizedScheduleTitle,
    accomplishmentScheduleTitle = Res.string.accomplishmentScheduleTitle,
    plannedScheduleTitle = Res.string.plannedScheduleTitle,
    undefinedTasksHeader = Res.string.undefinedTasksHeader,
    executeUndefinedTasksTitle = Res.string.executeUndefinedTasksTitle,
    currentTaskHeader = Res.string.currentTaskHeader,
    noneTitle = Res.string.noneTitle,
    progressTitle = Res.string.progressTitle,
    taskDateChooserHeader = Res.string.taskDateChooserHeader,
    taskDateChooserFieldLabel = Res.string.taskDateChooserFieldLabel,
    specifyDeadlineTitle = Res.string.specifyDeadlineTitle,
    deadlineLabel = Res.string.deadlineLabel,
    showAllSchedulesTitle = Res.string.showAllSchedulesTitle
)

internal val LocalOverViewStrings = staticCompositionLocalOf<OverViewStrings> {
    error("Home Strings is not provided")
}

internal fun fetchOverViewStrings() = baseOverViewStrings