package com.kuro.mdp.features.home.domain.use_case

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
data class HomeUseCase(
    val loadScheduleUseCase: LoadScheduleUseCase,
    val initHomeUseCase: InitHomeUseCase,
    val createScheduleUseCase: CreateScheduleUseCase,
    val shiftUpTimeWorkUseCase: ShiftUpTimeWorkUseCase,
    val shiftDownTimeWorkUseCase: ShiftDownTimeWorkUseCase,
    val changeTaskViewStatusUseCase: ChangeTaskViewStatusUseCase,
    val changeTaskDoneStateWorkUseCase: ChangeTaskDoneStateWorkUseCase
)