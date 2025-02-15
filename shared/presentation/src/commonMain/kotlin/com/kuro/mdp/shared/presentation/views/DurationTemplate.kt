package com.kuro.mdp.shared.presentation.views

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
enum class DurationTemplate(val hours: Int, val minutes: Int) {
    TEN_MINUTES(0, 10),
    THIRTY_MINUTES(0, 30),
    ONE_HOUR(1, 0),
    FOUR_HOUR(4, 0),
}
