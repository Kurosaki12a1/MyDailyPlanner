package com.kuro.mdp.features.home.data.repository.common

import com.kuro.mdp.features.home.domain.model.editor.error.TimeRangeError
import com.kuro.mdp.features.home.domain.repository.common.TimeRangeValidator
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.functional.Constants.Date.HOURS_IN_DAY
import com.kuro.mdp.shared.utils.functional.Constants.Date.MILLIS_IN_HOUR
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kuro.mdp.shared.utils.validation.ValidateResult

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
class TimeRangeValidatorImpl : TimeRangeValidator {
    override fun validate(data: TimeRange): ValidateResult<TimeRangeError> {
        return if (duration(data) == 0L || duration(data) > MILLIS_IN_HOUR * HOURS_IN_DAY) {
            ValidateResult(false, TimeRangeError.DurationError)
        } else {
            ValidateResult(true, null)
        }
    }
}