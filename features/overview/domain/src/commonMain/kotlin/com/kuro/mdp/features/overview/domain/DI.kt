package com.kuro.mdp.features.overview.domain

import com.kuro.mdp.features.overview.domain.use_case.details.DetailsUseCase
import com.kuro.mdp.features.overview.domain.use_case.details.LoadAllSchedulesUseCase
import com.kuro.mdp.features.overview.domain.use_case.over_view.CreateOrUpdateTaskUseCase
import com.kuro.mdp.features.overview.domain.use_case.over_view.DeleteUndefinedTaskUseCase
import com.kuro.mdp.features.overview.domain.use_case.over_view.ExecuteUndefinedTaskUseCase
import com.kuro.mdp.features.overview.domain.use_case.over_view.LoadScheduleUseCase
import com.kuro.mdp.features.overview.domain.use_case.over_view.OverViewUseCase
import com.kuro.mdp.features.overview.domain.use_case.over_view.UpdateCategoriesUseCase
import com.kuro.mdp.features.overview.domain.use_case.over_view.UpdateUndefinedTasksUseCase
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
val overViewDomainModule = module {
    factory<OverViewUseCase> {
        OverViewUseCase(
            loadScheduleUseCase = LoadScheduleUseCase(get(), get(), get()),
            updateUndefinedTasksUseCase = UpdateUndefinedTasksUseCase(get()),
            updateCategoriesUseCase = UpdateCategoriesUseCase(get()),
            createOrUpdateTaskUseCase = CreateOrUpdateTaskUseCase(get()),
            executeUndefinedTaskUseCase = ExecuteUndefinedTaskUseCase(get(), get()),
            deleteUndefinedTaskUseCase = DeleteUndefinedTaskUseCase(get())
        )
    }
    factory<DetailsUseCase> {
        DetailsUseCase(
            loadAllSchedulesUseCase = LoadAllSchedulesUseCase(get(), get(), get())
        )
    }
}