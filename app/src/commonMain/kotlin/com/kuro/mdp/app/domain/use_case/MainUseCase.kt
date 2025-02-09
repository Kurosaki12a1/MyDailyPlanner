package com.kuro.mdp.app.domain.use_case

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
data class MainUseCase(
    val fetchSettingsUseCase: FetchSettingsUseCase,
    val updateMainCategoryUseCase: UpdateMainCategoryUseCase,
    val updateScheduleDateUseCase: UpdateScheduleDateUseCase,
    val updateEditorUseCase: UpdateEditorUseCase
)