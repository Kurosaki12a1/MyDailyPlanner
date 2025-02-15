package com.kuro.mdp.features.home.domain.use_case.editor

/**
 * Created by: minhthinh.h on 2/11/2025
 *
 * Description:
 */
data class EditorUseCase(
    val loadTemplatesUseCase: LoadTemplatesUseCase,
    val loadUndefinedTasksUseCase: LoadUndefinedTasksUseCase,
    val loadSendModelUseCase: LoadSendModelUseCase,
    val createTemplateUseCase: CreateTemplateUseCase,
    val saveModelUseCase: SaveModelUseCase,
    val deleteModelUseCase: DeleteModelUseCase
)