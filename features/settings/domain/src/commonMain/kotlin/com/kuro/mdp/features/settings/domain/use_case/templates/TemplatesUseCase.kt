package com.kuro.mdp.features.settings.domain.use_case.templates

/**
 * Created by: minhthinh.h on 2/4/2025
 *
 * Description:
 */
data class TemplatesUseCase(
    val loadCategoriesTemplateUseCase: LoadCategoriesTemplateUseCase,
    val loadTemplatesUseCase: LoadTemplatesUseCase,
    val addTemplatesUseCase: AddTemplatesUseCase,
    val addRepeatTemplatesUseCase: AddRepeatTemplatesUseCase,
    val updateTemplatesUseCase: UpdateTemplatesUseCase,
    val deleteTemplatesUseCase: DeleteTemplatesUseCase,
    val deleteRepeatTemplatesUseCase: DeleteRepeatTemplatesUseCase,
    val restartTemplateRepeatUseCase: RestartTemplatesRepeatUseCase,
    val stopTemplatesRepeatUseCase: StopTemplatesRepeatUseCase
)