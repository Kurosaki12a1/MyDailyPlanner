package com.kuro.mdp.app.di

import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.navigation.navigator.NavigatorImpl
import com.kuro.mdp.shared.utils.managers.DateManager
import com.kuro.mdp.shared.utils.managers.DateManagerImpl
import com.kuro.mdp.shared.utils.managers.TimeOverLayManagerImpl
import com.kuro.mdp.shared.utils.managers.TimeOverlayManager
import com.kurp.mdp.shared.data.sharedDataModule
import org.koin.dsl.module


val sharedModule = module {
    single<DateManager> { DateManagerImpl() }
    single<TimeOverlayManager> { TimeOverLayManagerImpl() }
    single<Navigator> { NavigatorImpl() }

    includes(sharedDataModule)
}
