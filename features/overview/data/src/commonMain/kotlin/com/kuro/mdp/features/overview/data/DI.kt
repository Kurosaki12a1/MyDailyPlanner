package com.kuro.mdp.features.overview.data

import com.kuro.mdp.features.overview.data.data_source.api.FeatureCategoryLocalDataSource
import com.kuro.mdp.features.overview.data.data_source.api.FeatureScheduleLocalDataSource
import com.kuro.mdp.features.overview.data.data_source.impl.FeatureCategoryLocalDataSourceImpl
import com.kuro.mdp.features.overview.data.data_source.impl.FeatureScheduleLocalDataSourceImpl
import com.kuro.mdp.features.overview.data.mapper.schedule.ScheduleOverViewToUiMapperImpl
import com.kuro.mdp.features.overview.data.mapper.schedule.TimeTaskOverViewToUiMapperImpl
import com.kuro.mdp.features.overview.data.repository.common.TimeTaskStatusControllerImpl
import com.kuro.mdp.features.overview.data.repository.over_view.OverViewCategoryRepositoryImpl
import com.kuro.mdp.features.overview.data.repository.over_view.OverViewFeatureCategoryRepositoryImpl
import com.kuro.mdp.features.overview.data.repository.over_view.OverViewFeatureScheduleRepositoryImpl
import com.kuro.mdp.features.overview.data.repository.over_view.OverViewRepeatTaskRepositoryImpl
import com.kuro.mdp.features.overview.data.repository.over_view.OverViewScheduleRepositoryImpl
import com.kuro.mdp.features.overview.data.repository.over_view.OverViewSubCategoriesRepositoryImpl
import com.kuro.mdp.features.overview.data.repository.over_view.OverViewTimeShiftRepositoryImpl
import com.kuro.mdp.features.overview.data.repository.over_view.OverViewUndefinedTasksRepositoryImpl
import com.kuro.mdp.features.overview.domain.api.ScheduleOverViewToUiMapper
import com.kuro.mdp.features.overview.domain.api.TimeTaskOverViewToUiMapper
import com.kuro.mdp.features.overview.domain.repository.common.TimeTaskStatusController
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewCategoryRepository
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewFeatureCategoryRepository
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewFeatureScheduleRepository
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewRepeatTaskRepository
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewScheduleRepository
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewSubCategoriesRepository
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewTimeShiftRepository
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewUndefinedTasksRepository
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
val overViewDataModule = module {
    single<FeatureCategoryLocalDataSource> { FeatureCategoryLocalDataSourceImpl() }
    single<FeatureScheduleLocalDataSource> { FeatureScheduleLocalDataSourceImpl() }
    single<OverViewFeatureCategoryRepository> { OverViewFeatureCategoryRepositoryImpl(get()) }
    single<OverViewFeatureScheduleRepository> { OverViewFeatureScheduleRepositoryImpl(get()) }

    single<TimeTaskStatusController> { TimeTaskStatusControllerImpl(get(), get()) }
    single<TimeTaskOverViewToUiMapper> { TimeTaskOverViewToUiMapperImpl(get(), get()) }
    single<ScheduleOverViewToUiMapper> { ScheduleOverViewToUiMapperImpl(get(), get()) }

    single<OverViewCategoryRepository> { OverViewCategoryRepositoryImpl(get(), get()) }
    single<OverViewRepeatTaskRepository> { OverViewRepeatTaskRepositoryImpl(get(), get(), get(), get()) }
    single<OverViewScheduleRepository> { OverViewScheduleRepositoryImpl(get(), get(), get(), get(), get(), get()) }
    single<OverViewSubCategoriesRepository> { OverViewSubCategoriesRepositoryImpl(get()) }
    single<OverViewTimeShiftRepository> { OverViewTimeShiftRepositoryImpl(get(), get(), get()) }
    single<OverViewUndefinedTasksRepository> { OverViewUndefinedTasksRepositoryImpl(get()) }
}