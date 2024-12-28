package com.kuro.mdp.features.home.data

import com.kuro.mdp.features.home.data.data_source.api.FeatureCategoryLocalDataSource
import com.kuro.mdp.features.home.data.data_source.api.FeatureScheduleLocalDataSource
import com.kuro.mdp.features.home.data.data_source.impl.FeatureCategoryLocalDataSourceImpl
import com.kuro.mdp.features.home.data.data_source.impl.FeatureScheduleLocalDataSourceImpl
import com.kuro.mdp.features.home.data.mapper.schedules.SchedulesHomeToUiMapperImpl
import com.kuro.mdp.features.home.data.mapper.schedules.TimeTaskHomeToUiMapperImpl
import com.kuro.mdp.features.home.data.repository.common.TimeTaskStatusControllerImpl
import com.kuro.mdp.features.home.data.repository.feature.FeatureCategoryRepositoryImpl
import com.kuro.mdp.features.home.data.repository.feature.FeatureScheduleRepositoryImpl
import com.kuro.mdp.features.home.data.repository.home.HomeCategoryRepositoryImpl
import com.kuro.mdp.features.home.data.repository.home.HomeRepeatTaskRepositoryImpl
import com.kuro.mdp.features.home.data.repository.home.HomeScheduleRepositoryImpl
import com.kuro.mdp.features.home.data.repository.home.HomeSettingsRepositoryImpl
import com.kuro.mdp.features.home.data.repository.home.HomeSubCategoriesRepositoryImpl
import com.kuro.mdp.features.home.data.repository.home.HomeTemplatesRepositoryImpl
import com.kuro.mdp.features.home.data.repository.home.HomeTimeShiftRepositoryImpl
import com.kuro.mdp.features.home.data.repository.home.HomeUndefinedTasksRepositoryImpl
import com.kuro.mdp.features.home.domain.api.ScheduleHomeToUiMapper
import com.kuro.mdp.features.home.domain.api.TimeTaskHomeToUiMapper
import com.kuro.mdp.features.home.domain.repository.common.TimeTaskStatusController
import com.kuro.mdp.features.home.domain.repository.feature.FeatureCategoryRepository
import com.kuro.mdp.features.home.domain.repository.feature.FeatureScheduleRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeCategoryRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeRepeatTaskRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeScheduleRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeSettingsRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeSubCategoriesRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeTemplatesRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeTimeShiftRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeUndefinedTasksRepository
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
val homeDataModule = module {
    single<FeatureCategoryLocalDataSource> { FeatureCategoryLocalDataSourceImpl() }
    single<FeatureScheduleLocalDataSource> { FeatureScheduleLocalDataSourceImpl() }
    single<FeatureCategoryRepository> { FeatureCategoryRepositoryImpl(get()) }
    single<FeatureScheduleRepository> { FeatureScheduleRepositoryImpl(get()) }

    single<TimeTaskStatusController> { TimeTaskStatusControllerImpl(get(), get()) }
    single<TimeTaskHomeToUiMapper> { TimeTaskHomeToUiMapperImpl(get(), get()) }
    single<ScheduleHomeToUiMapper> { SchedulesHomeToUiMapperImpl(get(), get()) }

    single<HomeCategoryRepository> { HomeCategoryRepositoryImpl(get(), get()) }
    single<HomeRepeatTaskRepository> { HomeRepeatTaskRepositoryImpl(get(), get(), get(), get()) }
    single<HomeScheduleRepository> { HomeScheduleRepositoryImpl(get(), get(), get(), get(), get(), get()) }
    single<HomeSettingsRepository> { HomeSettingsRepositoryImpl(get()) }
    single<HomeSubCategoriesRepository> { HomeSubCategoriesRepositoryImpl(get()) }
    single<HomeTemplatesRepository> { HomeTemplatesRepositoryImpl(get()) }
    single<HomeTimeShiftRepository> { HomeTimeShiftRepositoryImpl(get(), get(), get()) }
    single<HomeUndefinedTasksRepository> { HomeUndefinedTasksRepositoryImpl(get()) }
}