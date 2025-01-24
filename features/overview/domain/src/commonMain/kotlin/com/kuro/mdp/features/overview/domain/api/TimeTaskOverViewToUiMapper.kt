package com.kuro.mdp.features.overview.domain.api

import com.kuro.mdp.features.overview.domain.model.schedules.TimeTaskOverView
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.functional.ParameterizedMapper

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
interface TimeTaskOverViewToUiMapper : ParameterizedMapper<TimeTask, TimeTaskOverView, Boolean>
