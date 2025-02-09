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
import home.resources.applyTitle
import home.resources.beforeEndTitle
import home.resources.cancelButtonTitle
import home.resources.categoriesManageWarningTitle
import home.resources.categoryNotSelectedTitle
import home.resources.categoryValidateError
import home.resources.chooseCategoryTitle
import home.resources.controlTitle
import home.resources.correctOverlayTitle
import home.resources.createScheduleDesc
import home.resources.createScheduleTitle
import home.resources.currentTaskHeader
import home.resources.dateDialogPickerHeadline
import home.resources.dateDialogPickerTitle
import home.resources.dayTitleHome
import home.resources.deadlineLabel
import home.resources.deleteCategoryTitle
import home.resources.durationFormat
import home.resources.durationPickerTitle
import home.resources.emptyListTitle
import home.resources.emptyTemplatesTitle
import home.resources.endOverlayError
import home.resources.executeUndefinedTasksTitle
import home.resources.fifteenMinutesBeforeTitle
import home.resources.foundedPlannedTasksTitle
import home.resources.fullOverlayError
import home.resources.importanceError
import home.resources.mainCategoryChooserTitle
import home.resources.mainCategoryTitle
import home.resources.monthTitle
import home.resources.nameCategoryTitle
import home.resources.navToBackTitle
import home.resources.nextDateIconDesc
import home.resources.noneTitle
import home.resources.noteLabel
import home.resources.notePlaceholder
import home.resources.noteTitle
import home.resources.notificationDisabledTitle
import home.resources.notificationEnabledTitle
import home.resources.notifyParameterDesc
import home.resources.notifyParameterTitle
import home.resources.oneDayBeforeTitle
import home.resources.oneHourBeforeTitle
import home.resources.oneWeekBeforeTitle
import home.resources.otherError
import home.resources.parameterChooserSwitchIconDesc
import home.resources.plannedScheduleTitle
import home.resources.previousDateIconDesc
import home.resources.priorityParameterTitle
import home.resources.progressTitle
import home.resources.realizedScheduleTitle
import home.resources.restoreDefaultCategories
import home.resources.saveTaskButtonTitle
import home.resources.saveTemplateWarningConfirm
import home.resources.saveTemplateWarningTitle
import home.resources.saveTemplateWarningUnSave
import home.resources.schedulesHeader
import home.resources.shiftError
import home.resources.showAllSchedulesTitle
import home.resources.sortedTypeCategories
import home.resources.sortedTypeDate
import home.resources.sortedTypeDuration
import home.resources.sortedTypeTitle
import home.resources.specifyDeadlineTitle
import home.resources.startOverlayError
import home.resources.startTimeTaskTitlePlaceHolder
import home.resources.statisticsActiveTitle
import home.resources.statisticsDisabledTitle
import home.resources.statisticsParameterDesc
import home.resources.statisticsParameterTitle
import home.resources.subCategoryChooserTitle
import home.resources.subCategoryDialogAddedTitle
import home.resources.subCategoryDialogMainCategoryFormat
import home.resources.subCategoryTitle
import home.resources.taskDateChooserFieldLabel
import home.resources.taskDateChooserHeader
import home.resources.templateIconDesc
import home.resources.templatesSheetTitle
import home.resources.threeHourBeforeTitle
import home.resources.timeFieldEndLabel
import home.resources.timeFieldStartLabel
import home.resources.timePickerHeader
import home.resources.timePickerSeparator
import home.resources.timeRangeFormat
import home.resources.timeTaskAddIconDesc
import home.resources.timeTaskCheckIconDesc
import home.resources.timeTaskExpandedIconDesc
import home.resources.timeTaskIncreaseTimeTitle
import home.resources.timeTaskMoreIconDesc
import home.resources.timeTaskReduceTimeTitle
import home.resources.timeTaskRemoveIconDesc
import home.resources.topAppBarBackIconDesc
import home.resources.topAppBarCalendarIconDesc
import home.resources.topAppBarCategoriesTitle
import home.resources.topAppBarDeleteTitle
import home.resources.topAppBarEditorTitle
import home.resources.topAppBarHomeTitle
import home.resources.topAppBarMenuIconDesc
import home.resources.topAppBarMoreIconDesc
import home.resources.topAppBarOverviewTitle
import home.resources.topAppBarTemplatesTitle
import home.resources.undefinedTasksHeader
import home.resources.undefinedTasksSheetTitle
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
    val showAllSchedulesTitle: StringResource,
    val topAppBarEditorTitle: StringResource,
    val topAppBarBackIconDesc: StringResource,
    val chooseCategoryTitle: StringResource,
    val categoryNotSelectedTitle: StringResource,
    val subCategoryDialogAddedTitle: StringResource,
    val timeFieldStartLabel: StringResource,
    val timeFieldEndLabel: StringResource,
    val parameterChooserSwitchIconDesc: StringResource,
    val timePickerHeader: StringResource,
    val timePickerSeparator: StringResource,
    val notifyParameterTitle: StringResource,
    val notifyParameterDesc: StringResource,
    val statisticsParameterTitle: StringResource,
    val statisticsParameterDesc: StringResource,
    val saveTaskButtonTitle: StringResource,
    val cancelButtonTitle: StringResource,
    val templateIconDesc: StringResource,
    val topAppBarDeleteTitle: StringResource,
    val categoryValidateError: StringResource,
    val startOverlayError: StringResource,
    val endOverlayError: StringResource,
    val fullOverlayError: StringResource,
    val correctOverlayTitle: StringResource,
    val durationPickerTitle: StringResource,
    val templatesSheetTitle: StringResource,
    val controlTitle: StringResource,
    val applyTitle: StringResource,
    val timeRangeFormat: StringResource,
    val durationFormat: StringResource,
    val emptyTemplatesTitle: StringResource,
    val saveTemplateWarningTitle: StringResource,
    val saveTemplateWarningConfirm: StringResource,
    val saveTemplateWarningUnSave: StringResource,
    val categoriesManageWarningTitle: StringResource,
    val confirmNavigateTitle: StringResource,
    val priorityParameterTitle: StringResource,
    val noteLabel: StringResource,
    val notePlaceholder: StringResource,
    val undefinedTasksSheetTitle: StringResource,
    val fifteenMinutesBeforeTitle: StringResource,
    val oneHourBeforeTitle: StringResource,
    val threeHourBeforeTitle: StringResource,
    val oneDayBeforeTitle: StringResource,
    val oneWeekBeforeTitle: StringResource,
    val beforeEndTitle: StringResource
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
    confirmNavigateTitle = Res.string.timeTaskReduceTimeTitle,
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
    showAllSchedulesTitle = Res.string.showAllSchedulesTitle,
    topAppBarEditorTitle = Res.string.topAppBarEditorTitle,
    topAppBarBackIconDesc = Res.string.topAppBarBackIconDesc,
    chooseCategoryTitle = Res.string.chooseCategoryTitle,
    categoryNotSelectedTitle = Res.string.categoryNotSelectedTitle,
    subCategoryDialogAddedTitle = Res.string.subCategoryDialogAddedTitle,
    timeFieldStartLabel = Res.string.timeFieldStartLabel,
    timeFieldEndLabel = Res.string.timeFieldEndLabel,
    parameterChooserSwitchIconDesc = Res.string.parameterChooserSwitchIconDesc,
    timePickerHeader = Res.string.timePickerHeader,
    timePickerSeparator = Res.string.timePickerSeparator,
    notifyParameterTitle = Res.string.notifyParameterTitle,
    notifyParameterDesc = Res.string.notifyParameterDesc,
    statisticsParameterTitle = Res.string.statisticsParameterTitle,
    statisticsParameterDesc = Res.string.statisticsParameterDesc,
    saveTaskButtonTitle = Res.string.saveTaskButtonTitle,
    cancelButtonTitle = Res.string.cancelButtonTitle,
    templateIconDesc = Res.string.templateIconDesc,
    topAppBarDeleteTitle = Res.string.topAppBarDeleteTitle,
    categoryValidateError = Res.string.categoryValidateError,
    startOverlayError = Res.string.startOverlayError,
    endOverlayError = Res.string.endOverlayError,
    fullOverlayError = Res.string.fullOverlayError,
    correctOverlayTitle = Res.string.correctOverlayTitle,
    durationPickerTitle = Res.string.durationPickerTitle,
    templatesSheetTitle = Res.string.templatesSheetTitle,
    controlTitle = Res.string.controlTitle,
    applyTitle = Res.string.applyTitle,
    timeRangeFormat = Res.string.timeRangeFormat,
    durationFormat = Res.string.durationFormat,
    emptyTemplatesTitle = Res.string.emptyTemplatesTitle,
    saveTemplateWarningTitle = Res.string.saveTemplateWarningTitle,
    saveTemplateWarningConfirm = Res.string.saveTemplateWarningConfirm,
    saveTemplateWarningUnSave = Res.string.saveTemplateWarningUnSave,
    categoriesManageWarningTitle = Res.string.categoriesManageWarningTitle,
    priorityParameterTitle = Res.string.priorityParameterTitle,
    noteLabel = Res.string.noteLabel,
    notePlaceholder = Res.string.notePlaceholder,
    undefinedTasksSheetTitle = Res.string.undefinedTasksSheetTitle,
    fifteenMinutesBeforeTitle = Res.string.fifteenMinutesBeforeTitle,
    oneHourBeforeTitle = Res.string.oneHourBeforeTitle,
    threeHourBeforeTitle = Res.string.threeHourBeforeTitle,
    oneDayBeforeTitle = Res.string.oneDayBeforeTitle,
    oneWeekBeforeTitle = Res.string.oneWeekBeforeTitle,
    beforeEndTitle = Res.string.beforeEndTitle,
    timeTaskReduceTimeTitle = Res.string.timeTaskReduceTimeTitle
)

internal val LocalHomeStrings = staticCompositionLocalOf<HomeStrings> {
    error("Home Strings is not provided")
}

internal fun fetchHomeString() = baseHomeStrings