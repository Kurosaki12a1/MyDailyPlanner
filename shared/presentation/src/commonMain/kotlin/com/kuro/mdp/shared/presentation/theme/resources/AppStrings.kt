package com.kuro.mdp.shared.presentation.theme.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.kuro.mdp.shared.presentation.extensions.string
import org.jetbrains.compose.resources.StringResource
import shared.resources.Res
import shared.resources.afterTaskNotifyText
import shared.resources.amFormatTitle
import shared.resources.analyticsTabTitle
import shared.resources.app_name
import shared.resources.aprilTitle
import shared.resources.augustTitle
import shared.resources.beforeTaskNotifyText
import shared.resources.cancelTitle
import shared.resources.categoriesDrawerTitle
import shared.resources.categoryChoresTitle
import shared.resources.categoryCultureTitle
import shared.resources.categoryEatTitle
import shared.resources.categoryEmptyTitle
import shared.resources.categoryEntertainmentsTitle
import shared.resources.categoryHealthTitle
import shared.resources.categoryHygieneTitle
import shared.resources.categoryOtherTitle
import shared.resources.categoryRestTitle
import shared.resources.categoryShoppingTitle
import shared.resources.categorySleepTitle
import shared.resources.categorySportTitle
import shared.resources.categoryStudyTitle
import shared.resources.categoryTransportTitle
import shared.resources.categoryWorkTitle
import shared.resources.compactViewToggleTittle
import shared.resources.confirmTitle
import shared.resources.dayTitle
import shared.resources.decemberTitle
import shared.resources.dialogCreateTitle
import shared.resources.drawerMainSection
import shared.resources.emptyScheduleTitle
import shared.resources.endTimeLabel
import shared.resources.expandViewToggleTittle
import shared.resources.februaryTitle
import shared.resources.fridayTitle
import shared.resources.homeTabTitle
import shared.resources.hoursSymbol
import shared.resources.hoursTitle
import shared.resources.januaryTitle
import shared.resources.julyTitle
import shared.resources.juneTitle
import shared.resources.mainCategoryLabel
import shared.resources.mainDrawerTitle
import shared.resources.marchTitle
import shared.resources.mayTitle
import shared.resources.minutesSymbol
import shared.resources.minutesTitle
import shared.resources.mondayTitle
import shared.resources.notificationLabel
import shared.resources.novemberTitle
import shared.resources.octoberTitle
import shared.resources.okConfirmTitle
import shared.resources.overviewDrawerTitle
import shared.resources.pmFormatTitle
import shared.resources.priorityLabel
import shared.resources.priorityMax
import shared.resources.priorityMedium
import shared.resources.priorityStandard
import shared.resources.repeatTimeDayInMonthTitle
import shared.resources.repeatTimeDayInWeekTitle
import shared.resources.repeatTimeDayInYearTitle
import shared.resources.repeatTimeWeekDayInMonthTitle
import shared.resources.saturdayTitle
import shared.resources.separator
import shared.resources.septemberTitle
import shared.resources.settingsTabTitle
import shared.resources.splitFormat
import shared.resources.startTaskNotifyText
import shared.resources.startTimeLabel
import shared.resources.statisticsLabel
import shared.resources.subCategoryEmptyTitle
import shared.resources.subCategoryLabel
import shared.resources.sundayTitle
import shared.resources.templateDrawerTitle
import shared.resources.templateEditorHeader
import shared.resources.thursdayTitle
import shared.resources.timeTaskChannelName
import shared.resources.tuesdayTitle
import shared.resources.warningDeleteConfirmTitle
import shared.resources.warningDialogTitle
import shared.resources.wednesdayTitle

/**
 * Created by: minhthinh.h on 12/17/2024
 *
 * Description:
 */
data class AppStrings(
    val appName: StringResource,
    val startTaskNotifyText: StringResource,
    val timeTaskChannelName: StringResource,
    val categoryWorkTitle: StringResource,
    val categoryRestTitle: StringResource,
    val categorySportTitle: StringResource,
    val categorySleepTitle: StringResource,
    val categoryCultureTitle: StringResource,
    val categoryChoresTitle: StringResource,
    val categoryTransportTitle: StringResource,
    val categoryStudyTitle: StringResource,
    val categoryEatTitle: StringResource,
    val categoryEntertainmentsTitle: StringResource,
    val categoryHygieneTitle: StringResource,
    val categoryHealthTitle: StringResource,
    val categoryShoppingTitle: StringResource,
    val categoryOtherTitle: StringResource,
    val mainCategoryLabel: StringResource,
    val subCategoryLabel: StringResource,
    val subCategoryEmptyTitle: StringResource,
    val minutesSymbol: StringResource,
    val hoursSymbol: StringResource,
    val separator: StringResource,
    val cancelTitle: StringResource,
    val confirmTitle: StringResource,
    val okConfirmTitle: StringResource,
    val categoryEmptyTitle: StringResource,
    val expandViewToggleTittle: StringResource,
    val compactViewToggleTittle: StringResource,
    val warningDialogTitle: StringResource,
    val warningDeleteConfirmTitle: StringResource,
    val hoursTitle: StringResource,
    val minutesTitle: StringResource,
    val dayTitle: StringResource,
    val homeTabTitle: StringResource,
    val analyticsTabTitle: StringResource,
    val settingsTabTitle: StringResource,
    val mainDrawerTitle: StringResource,
    val overviewDrawerTitle: StringResource,
    val drawerMainSection: StringResource,
    val templateDrawerTitle: StringResource,
    val categoriesDrawerTitle: StringResource,
    val splitFormat: StringResource,
    val amFormatTitle: StringResource,
    val pmFormatTitle: StringResource,
    val repeatTimeDayInMonthTitle: StringResource,
    val repeatTimeDayInWeekTitle: StringResource,
    val repeatTimeWeekDayInMonthTitle: StringResource,
    val repeatTimeDayInYearTitle: StringResource,
    val sundayTitle: StringResource,
    val mondayTitle: StringResource,
    val tuesdayTitle: StringResource,
    val wednesdayTitle: StringResource,
    val thursdayTitle: StringResource,
    val fridayTitle: StringResource,
    val saturdayTitle: StringResource,
    val januaryTitle: StringResource,
    val februaryTitle: StringResource,
    val marchTitle: StringResource,
    val aprilTitle: StringResource,
    val mayTitle: StringResource,
    val juneTitle: StringResource,
    val julyTitle: StringResource,
    val augustTitle: StringResource,
    val septemberTitle: StringResource,
    val octoberTitle: StringResource,
    val novemberTitle: StringResource,
    val decemberTitle: StringResource,
    val beforeTaskNotifyText: StringResource,
    val afterTaskNotifyText: StringResource,
    val priorityStandard: StringResource,
    val priorityMedium: StringResource,
    val priorityMax: StringResource,
    val priorityLabel: StringResource,
    val emptyScheduleTitle: StringResource,
    val dialogCreateTitle: StringResource,
    val startTimeLabel: StringResource,
    val endTimeLabel: StringResource,
    val notificationLabel: StringResource,
    val statisticsLabel: StringResource,
    val templateEditorHeader: StringResource
)

internal val baseAppStrings = AppStrings(
    appName = Res.string.app_name,
    startTaskNotifyText = Res.string.startTaskNotifyText,
    timeTaskChannelName = Res.string.timeTaskChannelName,
    categoryWorkTitle = Res.string.categoryWorkTitle,
    categoryRestTitle = Res.string.categoryRestTitle,
    categorySportTitle = Res.string.categorySportTitle,
    categorySleepTitle = Res.string.categorySleepTitle,
    categoryCultureTitle = Res.string.categoryCultureTitle,
    categoryChoresTitle = Res.string.categoryChoresTitle,
    categoryTransportTitle = Res.string.categoryTransportTitle,
    categoryStudyTitle = Res.string.categoryStudyTitle,
    categoryEatTitle = Res.string.categoryEatTitle,
    categoryEntertainmentsTitle = Res.string.categoryEntertainmentsTitle,
    categoryHygieneTitle = Res.string.categoryHygieneTitle,
    categoryHealthTitle = Res.string.categoryHealthTitle,
    categoryShoppingTitle = Res.string.categoryShoppingTitle,
    categoryOtherTitle = Res.string.categoryOtherTitle,
    mainCategoryLabel = Res.string.mainCategoryLabel,
    subCategoryLabel = Res.string.subCategoryLabel,
    subCategoryEmptyTitle = Res.string.subCategoryEmptyTitle,
    minutesSymbol = Res.string.minutesSymbol,
    hoursSymbol = Res.string.hoursSymbol,
    separator = Res.string.separator,
    cancelTitle = Res.string.cancelTitle,
    confirmTitle = Res.string.confirmTitle,
    okConfirmTitle = Res.string.okConfirmTitle,
    categoryEmptyTitle = Res.string.categoryEmptyTitle,
    expandViewToggleTittle = Res.string.expandViewToggleTittle,
    compactViewToggleTittle = Res.string.compactViewToggleTittle,
    warningDialogTitle = Res.string.warningDialogTitle,
    warningDeleteConfirmTitle = Res.string.warningDeleteConfirmTitle,
    hoursTitle = Res.string.hoursTitle,
    minutesTitle = Res.string.minutesTitle,
    dayTitle = Res.string.dayTitle,
    homeTabTitle = Res.string.homeTabTitle,
    analyticsTabTitle = Res.string.analyticsTabTitle,
    settingsTabTitle = Res.string.settingsTabTitle,
    mainDrawerTitle = Res.string.mainDrawerTitle,
    overviewDrawerTitle = Res.string.overviewDrawerTitle,
    drawerMainSection = Res.string.drawerMainSection,
    templateDrawerTitle = Res.string.templateDrawerTitle,
    categoriesDrawerTitle = Res.string.categoriesDrawerTitle,
    splitFormat = Res.string.splitFormat,
    amFormatTitle = Res.string.amFormatTitle,
    pmFormatTitle = Res.string.pmFormatTitle,
    repeatTimeDayInMonthTitle = Res.string.repeatTimeDayInMonthTitle,
    repeatTimeDayInWeekTitle = Res.string.repeatTimeDayInWeekTitle,
    repeatTimeWeekDayInMonthTitle = Res.string.repeatTimeWeekDayInMonthTitle,
    repeatTimeDayInYearTitle = Res.string.repeatTimeDayInYearTitle,
    sundayTitle = Res.string.sundayTitle,
    mondayTitle = Res.string.mondayTitle,
    tuesdayTitle = Res.string.tuesdayTitle,
    wednesdayTitle = Res.string.wednesdayTitle,
    thursdayTitle = Res.string.thursdayTitle,
    fridayTitle = Res.string.fridayTitle,
    saturdayTitle = Res.string.saturdayTitle,
    januaryTitle = Res.string.januaryTitle,
    februaryTitle = Res.string.februaryTitle,
    marchTitle = Res.string.marchTitle,
    aprilTitle = Res.string.aprilTitle,
    mayTitle = Res.string.mayTitle,
    juneTitle = Res.string.juneTitle,
    julyTitle = Res.string.julyTitle,
    augustTitle = Res.string.augustTitle,
    septemberTitle = Res.string.septemberTitle,
    octoberTitle = Res.string.octoberTitle,
    novemberTitle = Res.string.novemberTitle,
    decemberTitle = Res.string.decemberTitle,
    beforeTaskNotifyText = Res.string.beforeTaskNotifyText,
    afterTaskNotifyText = Res.string.afterTaskNotifyText,
    priorityStandard = Res.string.priorityStandard,
    priorityMedium = Res.string.priorityMedium,
    priorityMax = Res.string.priorityMax,
    priorityLabel = Res.string.priorityLabel,
    emptyScheduleTitle = Res.string.emptyScheduleTitle,
    dialogCreateTitle = Res.string.dialogCreateTitle,
    startTimeLabel = Res.string.startTimeLabel,
    endTimeLabel = Res.string.endTimeLabel,
    notificationLabel = Res.string.notificationLabel,
    statisticsLabel = Res.string.statisticsLabel,
    templateEditorHeader = Res.string.templateEditorHeader
)

val LocalAppStrings = staticCompositionLocalOf { baseAppStrings }

@Composable
fun getListDayOfWeekTitle(): List<String> {
    return listOf(
        baseAppStrings.mondayTitle.string(),
        baseAppStrings.tuesdayTitle.string(),
        baseAppStrings.wednesdayTitle.string(),
        baseAppStrings.thursdayTitle.string(),
        baseAppStrings.fridayTitle.string(),
        baseAppStrings.saturdayTitle.string(),
        baseAppStrings.sundayTitle.string()
    )
}

@Composable
fun getListMonthTitle(): List<String> {
    return listOf(
        baseAppStrings.januaryTitle.string(),
        baseAppStrings.februaryTitle.string(),
        baseAppStrings.marchTitle.string(),
        baseAppStrings.aprilTitle.string(),
        baseAppStrings.mayTitle.string(),
        baseAppStrings.juneTitle.string(),
        baseAppStrings.julyTitle.string(),
        baseAppStrings.augustTitle.string(),
        baseAppStrings.septemberTitle.string(),
        baseAppStrings.octoberTitle.string(),
        baseAppStrings.novemberTitle.string(),
        baseAppStrings.decemberTitle.string(),
    )
}