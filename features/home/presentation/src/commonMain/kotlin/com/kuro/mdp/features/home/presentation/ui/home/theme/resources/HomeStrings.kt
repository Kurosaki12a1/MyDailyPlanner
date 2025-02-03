package com.kuro.mdp.features.home.presentation.ui.home.theme.resources

import androidx.compose.runtime.staticCompositionLocalOf
import home.resources.Res
import home.resources.accomplishmentScheduleTitle
import home.resources.addCategoryTitle
import home.resources.addFreeTimeTaskTitle
import home.resources.addSubCategoryTitle
import home.resources.addTaskTitle
import home.resources.addTemplatesFabTitle
import home.resources.addTimeTaskIconsDesc
import home.resources.createScheduleDesc
import home.resources.createScheduleTitle
import home.resources.currentTaskHeader
import home.resources.dateDialogPickerHeadline
import home.resources.dateDialogPickerTitle
import home.resources.dayTitleHome
import home.resources.deadlineLabel
import home.resources.deleteCategoryTitle
import home.resources.emptyListTitle
import home.resources.executeUndefinedTasksTitle
import home.resources.foundedPlannedTasksTitle
import home.resources.importanceError
import home.resources.mainCategoryChooserTitle
import home.resources.mainCategoryTitle
import home.resources.monthTitle
import home.resources.nameCategoryTitle
import home.resources.navToBackTitle
import home.resources.nextDateIconDesc
import home.resources.noneTitle
import home.resources.noteTitle
import home.resources.notificationDisabledTitle
import home.resources.notificationEnabledTitle
import home.resources.otherError
import home.resources.plannedScheduleTitle
import home.resources.previousDateIconDesc
import home.resources.progressTitle
import home.resources.realizedScheduleTitle
import home.resources.restoreDefaultCategories
import home.resources.schedulesHeader
import home.resources.shiftError
import home.resources.showAllSchedulesTitle
import home.resources.sortedTypeCategories
import home.resources.sortedTypeDate
import home.resources.sortedTypeDuration
import home.resources.sortedTypeTitle
import home.resources.specifyDeadlineTitle
import home.resources.startTimeTaskTitlePlaceHolder
import home.resources.statisticsActiveTitle
import home.resources.statisticsDisabledTitle
import home.resources.subCategoryChooserTitle
import home.resources.subCategoryDialogMainCategoryFormat
import home.resources.subCategoryTitle
import home.resources.taskDateChooserFieldLabel
import home.resources.taskDateChooserHeader
import home.resources.timeTaskAddIconDesc
import home.resources.timeTaskCheckIconDesc
import home.resources.timeTaskExpandedIconDesc
import home.resources.timeTaskIncreaseTimeTitle
import home.resources.timeTaskMoreIconDesc
import home.resources.timeTaskReduceTimeTitle
import home.resources.timeTaskRemoveIconDesc
import home.resources.topAppBarCalendarIconDesc
import home.resources.topAppBarCategoriesTitle
import home.resources.topAppBarHomeTitle
import home.resources.topAppBarMenuIconDesc
import home.resources.topAppBarMoreIconDesc
import home.resources.topAppBarOverviewTitle
import home.resources.topAppBarTemplatesTitle
import home.resources.undefinedTasksHeader
import home.resources.updateCategoryTitle
import home.resources.warningDeleteCategoryText
import home.resources.weekDayTitle
import home.resources.weekNumberTitle
import org.jetbrains.compose.resources.StringResource

/**
 * Created by: minhthinh.h on 12/18/2024
 *
 * Description:
 */
internal data class HomeStrings(
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

internal val baseHomeStrings = HomeStrings(
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

internal val LocalHomeStrings = staticCompositionLocalOf<HomeStrings> {
    error("Home Strings is not provided")
}

internal fun fetchHomeString() = baseHomeStrings