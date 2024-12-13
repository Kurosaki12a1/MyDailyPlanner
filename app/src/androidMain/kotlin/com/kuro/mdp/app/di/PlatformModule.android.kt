package com.kuro.mdp.app.di

import com.kuro.mdp.app.framework.receiver.AlarmReceiverProviderImpl
import com.kuro.mdp.shared.presentation.notifications.AlarmReceiverProvider
import com.kuro.mdp.shared.presentation.notifications.TemplatesAlarmManager
import com.kuro.mdp.shared.presentation.notifications.TemplatesAlarmManagerImpl
import com.kuro.mdp.shared.presentation.notifications.TimeTaskAlarmManager
import com.kuro.mdp.shared.presentation.notifications.TimeTaskAlarmManagerImpl
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
actual fun appPlatformModule(): Module = module {
    single<AlarmReceiverProvider> { AlarmReceiverProviderImpl(get()) }
    single<TemplatesAlarmManager> { TemplatesAlarmManagerImpl(get(), get()) }
    single<TimeTaskAlarmManager> { TimeTaskAlarmManagerImpl(get(), get(), get()) }
}