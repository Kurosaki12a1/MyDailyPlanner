package com.kuro.mdp.app.domain.use_case

import com.kuro.mdp.app.domain.models.MainAction
import com.kuro.mdp.features.settings.domain.repository.SettingsMenuRepository
import com.kuro.mdp.features.settings.presentation.mappers.mapToUi
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
class FetchSettingsUseCase(
    private val settingsMenuRepository: SettingsMenuRepository
) {

    operator fun invoke(): Flow<ResultState<MainAction>> = flow {
        settingsMenuRepository.fetchAllSettings().collectAndHandle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = {
                emit(
                    ResultState.Success(
                        MainAction.UpdateSettings(
                            secureMode = it.tasksSettings.secureMode,
                            isEnableDynamicColors = it.themeSettings.isDynamicColorEnable,
                            language = it.themeSettings.language.mapToUi(),
                            theme = it.themeSettings.themeColors.mapToUi(),
                            colors = it.themeSettings.colorsType.mapToUi()
                        )
                    )
                )
            }
        )
    }
}