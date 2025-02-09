package com.kuro.mdp.features.home.domain.repository.common

import com.kuro.mdp.features.home.domain.model.editor.error.TimeRangeError
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kuro.mdp.shared.utils.validation.Validator

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
interface TimeRangeValidator : Validator<TimeRange, TimeRangeError>