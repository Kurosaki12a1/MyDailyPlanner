package com.kuro.mdp.app.di

import com.kurp.mdp.shared.data.sharedDataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Created by: minhthinh.h on 12/11/2024
 *
 * Description:
 */

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(listOf(sharedModule, featureModules))
    }

// Called by iOS?
fun initKoin() = initKoin { }