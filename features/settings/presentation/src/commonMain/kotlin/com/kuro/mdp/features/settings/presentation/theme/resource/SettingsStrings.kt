package com.kuro.mdp.features.settings.presentation.theme.resource

import androidx.compose.runtime.staticCompositionLocalOf
import org.jetbrains.compose.resources.StringResource
import settings.resources.Res
import settings.resources.aboutAppHeader
import settings.resources.addCategoryTitle
import settings.resources.addTemplatesFabTitle
import settings.resources.askQuestionTitle
import settings.resources.backIconDesc
import settings.resources.backupDataTitle
import settings.resources.braporLanguageTitle
import settings.resources.calendarButtonBehaviorTitle
import settings.resources.categoryFieldLabel
import settings.resources.clearDataButtonTitle
import settings.resources.clearDataTitle
import settings.resources.clearDataWarning
import settings.resources.copyTitle
import settings.resources.currentDayCalendarBehavior
import settings.resources.darkThemeTitle
import settings.resources.dataSectionHeader
import settings.resources.defaultLanguageTitle
import settings.resources.deleteCategoryTitle
import settings.resources.developerTitle
import settings.resources.donateHeader
import settings.resources.donateTitle
import settings.resources.emptyListTitle
import settings.resources.engLanguageTitle
import settings.resources.errorBackupFileMessage
import settings.resources.errorBackupMessage
import settings.resources.freLanguageTitle
import settings.resources.gerLanguageTitle
import settings.resources.githubTitle
import settings.resources.interfaceSectionHeader
import settings.resources.licenseTitle
import settings.resources.lightThemeTitle
import settings.resources.mainCategoryChooserTitle
import settings.resources.mainCategoryTitle
import settings.resources.mainSettingsColorsTitle
import settings.resources.mainSettingsDynamicColorTitle
import settings.resources.mainSettingsLanguageTitle
import settings.resources.mainSettingsThemeTitle
import settings.resources.mainSettingsTitle
import settings.resources.menuIconDesc
import settings.resources.moreIconDesc
import settings.resources.nameCategoryTitle
import settings.resources.navToBackTitle
import settings.resources.notificationDisabledTitle
import settings.resources.notificationEnabledTitle
import settings.resources.otherError
import settings.resources.perLanguageTitle
import settings.resources.polLanguageTitle
import settings.resources.resetToDefaultTitle
import settings.resources.restoreDataButtonTitle
import settings.resources.restoreDefaultCategories
import settings.resources.rusLanguageTitle
import settings.resources.secureModeTitle
import settings.resources.secureSectionHeader
import settings.resources.selectDayCalendarBehavior
import settings.resources.settingsTitle
import settings.resources.sortedTypeCategories
import settings.resources.sortedTypeDate
import settings.resources.sortedTypeDuration
import settings.resources.sortedTypeTitle
import settings.resources.spaLanguageTitle
import settings.resources.statisticsActiveTitle
import settings.resources.statisticsDisabledTitle
import settings.resources.subCategoryChooserTitle
import settings.resources.subCategoryDialogMainCategoryFormat
import settings.resources.subCategoryTitle
import settings.resources.systemThemeTitle
import settings.resources.topAppBarCategoriesTitle
import settings.resources.topAppBarTemplatesTitle
import settings.resources.turLanguageTitle
import settings.resources.updateCategoryTitle
import settings.resources.versionCodeTitle
import settings.resources.versionNameTitle
import settings.resources.vieLanguageTitle
import settings.resources.warningDeleteCategoryText
import shared.resources.dialogCreateTitle

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
data class SettingsStrings(
    val settingsTitle: StringResource,
    val mainSettingsTitle: StringResource,
    val mainSettingsThemeTitle: StringResource,
    val topAppBarCategoriesTitle: StringResource,
    val topAppBarTemplatesTitle: StringResource,
    val mainCategoryChooserTitle: StringResource,
    val subCategoryChooserTitle: StringResource,
    val darkThemeTitle: StringResource,
    val lightThemeTitle: StringResource,
    val systemThemeTitle: StringResource,
    val mainSettingsLanguageTitle: StringResource,
    val rusLanguageTitle: StringResource,
    val engLanguageTitle: StringResource,
    val gerLanguageTitle: StringResource,
    val spaLanguageTitle: StringResource,
    val perLanguageTitle: StringResource,
    val freLanguageTitle: StringResource,
    val brazilLanguageTitle: StringResource,
    val turLanguageTitle: StringResource,
    val vieLanguageTitle: StringResource,
    val polLanguageTitle: StringResource,
    val defaultLanguageTitle: StringResource,
    val backIconDesc: StringResource,
    val moreIconDesc: StringResource,
    val resetToDefaultTitle: StringResource,
    val menuIconDesc: StringResource,
    val dataSectionHeader: StringResource,
    val clearDataTitle: StringResource,
    val clearDataButtonTitle: StringResource,
    val clearDataWarning: StringResource,
    val backupDataTitle: StringResource,
    val backupDataButtonTitle: StringResource,
    val restoreDataButtonTitle: StringResource,
    val errorBackupMessage: StringResource,
    val errorBackupFileMessage: StringResource,
    val otherError: StringResource,
    val mainSettingsDynamicColorTitle: StringResource,
    val aboutAppHeader: StringResource,
    val versionCodeTitle: StringResource,
    val versionNameTitle: StringResource,
    val developerTitle: StringResource,
    val licenseTitle: StringResource,
    val githubTitle: StringResource,
    val askQuestionTitle: StringResource,
    val donateTitle: StringResource,
    val copyTitle: StringResource,
    val mainSettingsColorsTitle: StringResource,
    val donateHeader: StringResource,
    val secureSectionHeader: StringResource,
    val secureModeTitle: StringResource,
    val interfaceSectionHeader: StringResource,
    val calendarButtonBehaviorTitle: StringResource,
    val currentDayCalendarBehavior: StringResource,
    val selectDayCalendarBehavior: StringResource,
    val restoreDefaultCategories: StringResource,
    val mainCategoryTitle: StringResource,
    val nameCategoryTitle: StringResource,
    val warningDeleteCategoryText: StringResource,
    val addCategoryTitle: StringResource,
    val updateCategoryTitle: StringResource,
    val deleteCategoryTitle: StringResource,
    val dialogCreateTitle: StringResource,
    val subCategoryTitle: StringResource,
    val subCategoryDialogMainCategoryFormat: StringResource,
    val categoryFieldLabel: StringResource,
    val navToBackTitle: StringResource,
    val statisticsActiveTitle: StringResource,
    val statisticsDisabledTitle: StringResource,
    val notificationEnabledTitle: StringResource,
    val notificationDisabledTitle: StringResource,
    val sortedTypeDate: StringResource,
    val sortedTypeCategories: StringResource,
    val sortedTypeDuration: StringResource,
    val sortedTypeTitle: StringResource,
    val addTemplatesFabTitle: StringResource,
    val emptyListTitle: StringResource
)

val baseSettingsStrings = SettingsStrings(
    settingsTitle = Res.string.settingsTitle,
    mainSettingsTitle = Res.string.mainSettingsTitle,
    mainSettingsThemeTitle = Res.string.mainSettingsThemeTitle,
    topAppBarCategoriesTitle = Res.string.topAppBarCategoriesTitle,
    topAppBarTemplatesTitle = Res.string.topAppBarTemplatesTitle,
    mainCategoryChooserTitle = Res.string.mainCategoryChooserTitle,
    subCategoryChooserTitle = Res.string.subCategoryChooserTitle,
    darkThemeTitle = Res.string.darkThemeTitle,
    lightThemeTitle = Res.string.lightThemeTitle,
    systemThemeTitle = Res.string.systemThemeTitle,
    mainSettingsLanguageTitle = Res.string.mainSettingsLanguageTitle,
    rusLanguageTitle = Res.string.rusLanguageTitle,
    engLanguageTitle = Res.string.engLanguageTitle,
    gerLanguageTitle = Res.string.gerLanguageTitle,
    spaLanguageTitle = Res.string.spaLanguageTitle,
    perLanguageTitle = Res.string.perLanguageTitle,
    freLanguageTitle = Res.string.freLanguageTitle,
    brazilLanguageTitle = Res.string.braporLanguageTitle,
    turLanguageTitle = Res.string.turLanguageTitle,
    vieLanguageTitle = Res.string.vieLanguageTitle,
    polLanguageTitle = Res.string.polLanguageTitle,
    defaultLanguageTitle = Res.string.defaultLanguageTitle,
    backIconDesc = Res.string.backIconDesc,
    moreIconDesc = Res.string.moreIconDesc,
    resetToDefaultTitle = Res.string.resetToDefaultTitle,
    menuIconDesc = Res.string.menuIconDesc,
    dataSectionHeader = Res.string.dataSectionHeader,
    clearDataTitle = Res.string.clearDataTitle,
    clearDataButtonTitle = Res.string.clearDataButtonTitle,
    clearDataWarning = Res.string.clearDataWarning,
    backupDataTitle = Res.string.backupDataTitle,
    backupDataButtonTitle = Res.string.backupDataTitle,
    restoreDataButtonTitle = Res.string.restoreDataButtonTitle,
    errorBackupMessage = Res.string.errorBackupMessage,
    errorBackupFileMessage = Res.string.errorBackupFileMessage,
    otherError = Res.string.otherError,
    mainSettingsDynamicColorTitle = Res.string.mainSettingsDynamicColorTitle,
    aboutAppHeader = Res.string.aboutAppHeader,
    versionCodeTitle = Res.string.versionCodeTitle,
    versionNameTitle = Res.string.versionNameTitle,
    developerTitle = Res.string.developerTitle,
    licenseTitle = Res.string.licenseTitle,
    githubTitle = Res.string.githubTitle,
    askQuestionTitle = Res.string.askQuestionTitle,
    donateTitle = Res.string.donateTitle,
    copyTitle = Res.string.copyTitle,
    mainSettingsColorsTitle = Res.string.mainSettingsColorsTitle,
    donateHeader = Res.string.donateHeader,
    secureSectionHeader = Res.string.secureSectionHeader,
    secureModeTitle = Res.string.secureModeTitle,
    interfaceSectionHeader = Res.string.interfaceSectionHeader,
    calendarButtonBehaviorTitle = Res.string.calendarButtonBehaviorTitle,
    currentDayCalendarBehavior = Res.string.currentDayCalendarBehavior,
    selectDayCalendarBehavior = Res.string.selectDayCalendarBehavior,
    restoreDefaultCategories = Res.string.restoreDefaultCategories,
    mainCategoryTitle = Res.string.mainCategoryTitle,
    nameCategoryTitle = Res.string.nameCategoryTitle,
    warningDeleteCategoryText = Res.string.warningDeleteCategoryText,
    addCategoryTitle = Res.string.addCategoryTitle,
    updateCategoryTitle = Res.string.updateCategoryTitle,
    deleteCategoryTitle = Res.string.deleteCategoryTitle,
    dialogCreateTitle = shared.resources.Res.string.dialogCreateTitle,
    subCategoryTitle = Res.string.subCategoryTitle,
    subCategoryDialogMainCategoryFormat = Res.string.subCategoryDialogMainCategoryFormat,
    categoryFieldLabel = Res.string.categoryFieldLabel,
    navToBackTitle = Res.string.navToBackTitle,
    statisticsActiveTitle = Res.string.statisticsActiveTitle,
    statisticsDisabledTitle = Res.string.statisticsDisabledTitle,
    notificationEnabledTitle = Res.string.notificationEnabledTitle,
    notificationDisabledTitle = Res.string.notificationDisabledTitle,
    sortedTypeDate = Res.string.sortedTypeDate,
    sortedTypeCategories = Res.string.sortedTypeCategories,
    sortedTypeDuration = Res.string.sortedTypeDuration,
    sortedTypeTitle = Res.string.sortedTypeTitle,
    addTemplatesFabTitle = Res.string.addTemplatesFabTitle,
    emptyListTitle = Res.string.emptyListTitle
)

internal val LocalSettingsStrings = staticCompositionLocalOf<SettingsStrings> {
    error("Settings Strings is not provided")
}

internal fun fetchSettingsStrings() = baseSettingsStrings