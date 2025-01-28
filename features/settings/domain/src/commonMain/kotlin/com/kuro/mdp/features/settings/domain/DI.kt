package com.kuro.mdp.features.settings.domain

import com.kuro.mdp.features.settings.domain.use_case.categories.AddMainCategoryUseCase
import com.kuro.mdp.features.settings.domain.use_case.categories.AddSubCategoryUseCase
import com.kuro.mdp.features.settings.domain.use_case.categories.CategoriesUseCase
import com.kuro.mdp.features.settings.domain.use_case.categories.LoadCategoriesUseCase
import com.kuro.mdp.features.settings.domain.use_case.donate.PressDonateButtonUseCase
import com.kuro.mdp.features.settings.domain.use_case.settings.LoadAllSettingsUseCase
import com.kuro.mdp.features.settings.domain.use_case.settings.ResetToDefaultUseCase
import com.kuro.mdp.features.settings.domain.use_case.settings.SettingsUseCase
import com.kuro.mdp.features.settings.domain.use_case.settings.UpdateTasksSettingsUseCase
import com.kuro.mdp.features.settings.domain.use_case.settings.UpdateThemeSettingsUseCase
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
val settingsDomainModule = module {
    factory<SettingsUseCase> {
        SettingsUseCase(
            updateTasksSettingsUseCase = UpdateTasksSettingsUseCase(get()),
            updateThemeSettingsUseCase = UpdateThemeSettingsUseCase(get()),
            loadAllSettingsUseCase = LoadAllSettingsUseCase(get()),
            resetToDefaultUseCase = ResetToDefaultUseCase(get()),
            pressDonateButtonUseCase = PressDonateButtonUseCase()
        )
    }
    factory<CategoriesUseCase> {
        CategoriesUseCase(
            loadCategoriesUseCase = LoadCategoriesUseCase(get()),
            addMainCategoryUseCase = AddMainCategoryUseCase((get())),
            addSubCategoryUseCase = AddSubCategoryUseCase(get())
        )
    }
}