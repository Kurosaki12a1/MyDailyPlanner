package com.kuro.mdp.features.settings.domain

import com.kuro.mdp.features.settings.domain.use_case.ClearFailureUseCase
import com.kuro.mdp.features.settings.domain.use_case.LoadAllSettingsUseCase
import com.kuro.mdp.features.settings.domain.use_case.PressDonateButtonUseCase
import com.kuro.mdp.features.settings.domain.use_case.ResetToDefaultUseCase
import com.kuro.mdp.features.settings.domain.use_case.SettingsUseCase
import com.kuro.mdp.features.settings.domain.use_case.UpdateTasksSettingsUseCase
import com.kuro.mdp.features.settings.domain.use_case.UpdateThemeSettingsUseCase
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
            pressDonateButtonUseCase = PressDonateButtonUseCase(),
            clearFailureUseCase = ClearFailureUseCase()
        )
    }
}