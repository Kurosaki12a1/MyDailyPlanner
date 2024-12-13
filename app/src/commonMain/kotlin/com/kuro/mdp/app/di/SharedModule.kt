package com.kuro.mdp.app.di

import com.kuro.mdp.shared.domain.common.ScheduleStatusChecker
import com.kuro.mdp.shared.domain.common.TimeTaskStatusChecker
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.navigation.navigator.NavigatorImpl
import com.kuro.mdp.shared.utils.managers.DateManager
import com.kuro.mdp.shared.utils.managers.DateManagerImpl
import com.kurp.mdp.shared.data.mappers.schedules.ScheduleDataToDomainMapper
import com.kurp.mdp.shared.data.mappers.schedules.ScheduleDataToDomainMapperImpl
import com.kurp.mdp.shared.data.repository.common.ScheduleStatusCheckerImpl
import com.kurp.mdp.shared.data.repository.common.TimeTaskStatusCheckerImpl
import org.koin.dsl.module



val sharedModule = module {
    single<ScheduleStatusChecker> { ScheduleStatusCheckerImpl() }
    single<TimeTaskStatusChecker> { TimeTaskStatusCheckerImpl() }
    single<ScheduleDataToDomainMapper> { ScheduleDataToDomainMapperImpl(get(), get()) }
    single<DateManager> { DateManagerImpl() }
    single<Navigator> { NavigatorImpl() }
}
