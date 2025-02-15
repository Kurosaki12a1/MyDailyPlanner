package com.kuro.mdp.features.home.domain

import com.kuro.mdp.features.home.domain.use_case.editor.CreateTemplateUseCase
import com.kuro.mdp.features.home.domain.use_case.editor.DeleteModelUseCase
import com.kuro.mdp.features.home.domain.use_case.editor.EditorUseCase
import com.kuro.mdp.features.home.domain.use_case.editor.LoadSendModelUseCase
import com.kuro.mdp.features.home.domain.use_case.editor.LoadTemplatesUseCase
import com.kuro.mdp.features.home.domain.use_case.editor.LoadUndefinedTasksUseCase
import com.kuro.mdp.features.home.domain.use_case.editor.SaveModelUseCase
import com.kuro.mdp.features.home.domain.use_case.home.AddTimeTaskUseCase
import com.kuro.mdp.features.home.domain.use_case.home.ChangeTaskDoneStateWorkUseCase
import com.kuro.mdp.features.home.domain.use_case.home.ChangeTaskViewStatusUseCase
import com.kuro.mdp.features.home.domain.use_case.home.CreateScheduleUseCase
import com.kuro.mdp.features.home.domain.use_case.home.EditTimeTaskUseCase
import com.kuro.mdp.features.home.domain.use_case.home.HomeUseCase
import com.kuro.mdp.features.home.domain.use_case.home.InitHomeUseCase
import com.kuro.mdp.features.home.domain.use_case.home.LoadScheduleUseCase
import com.kuro.mdp.features.home.domain.use_case.home.ShiftDownTimeWorkUseCase
import com.kuro.mdp.features.home.domain.use_case.home.ShiftUpTimeWorkUseCase
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
            changeTaskDoneStateWorkUseCase = ChangeTaskDoneStateWorkUseCase(get()),
            addTimeTaskUseCase = AddTimeTaskUseCase(get()),
            editTimeTaskUseCase = EditTimeTaskUseCase(get())
        )
    }
    factory<EditorUseCase> {
        EditorUseCase(
            loadTemplatesUseCase = LoadTemplatesUseCase(get()),
            loadUndefinedTasksUseCase = LoadUndefinedTasksUseCase(get()),
            loadSendModelUseCase = LoadSendModelUseCase(get(), get()),
            createTemplateUseCase = CreateTemplateUseCase(get()),
            saveModelUseCase = SaveModelUseCase(get(), get(), get(), get(), get(), get()),
            deleteModelUseCase = DeleteModelUseCase(get(), get(), get())
        )
    }
}