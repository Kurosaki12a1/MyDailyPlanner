package com.kuro.mdp.features.home.domain

import com.kuro.mdp.features.home.domain.use_case.ChangeTaskDoneStateWorkUseCase
import com.kuro.mdp.features.home.domain.use_case.ChangeTaskViewStatusUseCase
import com.kuro.mdp.features.home.domain.use_case.CreateScheduleUseCase
import com.kuro.mdp.features.home.domain.use_case.HomeUseCase
import com.kuro.mdp.features.home.domain.use_case.InitHomeUseCase
import com.kuro.mdp.features.home.domain.use_case.LoadScheduleUseCase
import com.kuro.mdp.features.home.domain.use_case.ShiftDownTimeWorkUseCase
import com.kuro.mdp.features.home.domain.use_case.ShiftUpTimeWorkUseCase
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 1/13/2025
 *
 * Description:
 */
val homeDomainModule = module {
    factory<HomeUseCase> {
        HomeUseCase(
            loadScheduleUseCase = LoadScheduleUseCase(get(), get(), get(), get()),
            initHomeUseCase = InitHomeUseCase(get()),
            createScheduleUseCase = CreateScheduleUseCase(get()),
            shiftUpTimeWorkUseCase = ShiftUpTimeWorkUseCase(get(), get()),
            shiftDownTimeWorkUseCase = ShiftDownTimeWorkUseCase(get(), get()),
            changeTaskViewStatusUseCase = ChangeTaskViewStatusUseCase(get()),
            changeTaskDoneStateWorkUseCase = ChangeTaskDoneStateWorkUseCase(get())
        )
    }
}