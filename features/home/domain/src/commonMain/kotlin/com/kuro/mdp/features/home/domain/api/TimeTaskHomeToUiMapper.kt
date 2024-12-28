package com.kuro.mdp.features.home.domain.api

import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.functional.ParameterizedMapper

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
interface TimeTaskHomeToUiMapper : ParameterizedMapper<TimeTask, TimeTaskHome, Boolean>
