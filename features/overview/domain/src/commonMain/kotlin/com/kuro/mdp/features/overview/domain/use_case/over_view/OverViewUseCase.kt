package com.kuro.mdp.features.overview.domain.use_case.over_view

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
data class OverViewUseCase(
    val loadScheduleUseCase: LoadScheduleUseCase,
    val updateUndefinedTasksUseCase: UpdateUndefinedTasksUseCase,
    val updateCategoriesUseCase: UpdateCategoriesUseCase,
    val createOrUpdateTaskUseCase: CreateOrUpdateTaskUseCase,
    val executeUndefinedTaskUseCase: ExecuteUndefinedTaskUseCase,
    val deleteUndefinedTaskUseCase: DeleteUndefinedTaskUseCase
)