package com.kuro.mdp.shared.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.shared.domain.model.categories.DefaultCategoryType
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.theme.resources.AppIcons
import com.kuro.mdp.shared.presentation.theme.resources.AppStrings
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
fun DefaultCategoryType.mapToIcon(icons: AppIcons): DrawableResource = when (this) {
    DefaultCategoryType.WORK -> icons.categoriesIcon
    DefaultCategoryType.REST -> icons.categoryRestIcon
    DefaultCategoryType.AFFAIRS -> icons.categoryAffairsIcon
    DefaultCategoryType.TRANSPORT -> icons.categoryTransportIcon
    DefaultCategoryType.STUDY -> icons.categoryStudyIcon
    DefaultCategoryType.EAT -> icons.categoryEatIcon
    DefaultCategoryType.ENTERTAINMENTS -> icons.categoryEntertainmentsIcon
    DefaultCategoryType.SPORT -> icons.categorySportIcon
    DefaultCategoryType.SLEEP -> icons.categorySleepIcon
    DefaultCategoryType.CULTURE -> icons.categoryCultureIcon
    DefaultCategoryType.OTHER -> icons.categoryOtherIcon
    DefaultCategoryType.EMPTY -> icons.categoryEmptyIcon
    DefaultCategoryType.HYGIENE -> icons.categoryHygiene
    DefaultCategoryType.HEALTH -> icons.categoryHealth
    DefaultCategoryType.SHOPPING -> icons.categoryShopping
}

fun DefaultCategoryType.mapToString(strings: AppStrings): StringResource = when (this) {
    DefaultCategoryType.WORK -> strings.categoryWorkTitle
    DefaultCategoryType.REST -> strings.categoryRestTitle
    DefaultCategoryType.AFFAIRS -> strings.categoryChoresTitle
    DefaultCategoryType.TRANSPORT -> strings.categoryTransportTitle
    DefaultCategoryType.STUDY -> strings.categoryStudyTitle
    DefaultCategoryType.EAT -> strings.categoryEatTitle
    DefaultCategoryType.ENTERTAINMENTS -> strings.categoryEntertainmentsTitle
    DefaultCategoryType.SPORT -> strings.categorySportTitle
    DefaultCategoryType.SLEEP -> strings.categorySleepTitle
    DefaultCategoryType.CULTURE -> strings.categoryCultureTitle
    DefaultCategoryType.OTHER -> strings.categoryOtherTitle
    DefaultCategoryType.EMPTY -> strings.categoryEmptyTitle
    DefaultCategoryType.HYGIENE -> strings.categoryHygieneTitle
    DefaultCategoryType.HEALTH -> strings.categoryHealthTitle
    DefaultCategoryType.SHOPPING -> strings.categoryShoppingTitle
}

@Composable
fun DefaultCategoryType.mapToKey(): String = mapToString(AppTheme.strings).key

@Composable
fun DefaultCategoryType.mapToIconPainter() = painterResource(resource = mapToIcon(AppTheme.icons))
