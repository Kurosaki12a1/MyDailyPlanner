package com.kuro.mdp.features.settings.domain.use_case

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
data class SettingsUseCase(
    val loadAllSettingsUseCase: LoadAllSettingsUseCase,
    val updateTasksSettingsUseCase: UpdateTasksSettingsUseCase,
    val updateThemeSettingsUseCase: UpdateThemeSettingsUseCase,
    val resetToDefaultUseCase: ResetToDefaultUseCase,
    val pressDonateButtonUseCase: PressDonateButtonUseCase,
    val clearFailureUseCase: ClearFailureUseCase
)