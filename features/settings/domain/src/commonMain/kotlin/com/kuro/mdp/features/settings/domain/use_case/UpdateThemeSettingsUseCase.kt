package com.kuro.mdp.features.settings.domain.use_case

import com.kuro.mdp.features.settings.domain.repository.MenuSettingsRepository
import com.kuro.mdp.shared.domain.model.settings.ThemeSettings
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
class UpdateThemeSettingsUseCase(
    private val menuSettingsRepository: MenuSettingsRepository
) {
    operator fun invoke(themeString: ThemeSettings): Flow<ResultState<Unit>> = flow {
        menuSettingsRepository.updateThemeSettings(themeString).handle(
            onFailure = { e -> emit(ResultState.Failure(e)) },
            onSuccess = {}
        )
    }
}