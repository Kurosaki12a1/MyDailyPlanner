package com.kuro.mdp.shared.utils.managers

import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
interface TimeOverlayManager {
    fun isOverlay(current: TimeRange, allTimeRanges: List<TimeRange>): TimeOverlayResult
}

class TimeOverLayManagerImpl : TimeOverlayManager {
    override fun isOverlay(current: TimeRange, allTimeRanges: List<TimeRange>): TimeOverlayResult {
        var leftBorder: LocalDateTime? = null
        var rightBorder: LocalDateTime? = null
        var startError = false
        var endError = false
        allTimeRanges.forEach { newRange ->
            if (newRange.to < current.to && (leftBorder == null || newRange.to > leftBorder!!)) {
                if (newRange.to > current.from) {
                    leftBorder = newRange.to
                    startError = true
                }
            }
            if (newRange.from >= current.from && (rightBorder == null || newRange.from > rightBorder!!)) {
                if (newRange.from < current.to) {
                    rightBorder = newRange.from
                    endError = true
                }
            }
            if (current.from > newRange.from && current.to < newRange.to) {
                startError = true
                endError = true
            }
        }
        if (leftBorder != null && rightBorder != null) {
            if (leftBorder!!.time > rightBorder!!.time) {
                rightBorder = current.to
            } else if (rightBorder!!.time > current.to.time) {
                rightBorder = current.to
            }
        }
        return TimeOverlayResult(
            isOverlay = startError || endError,
            leftTimeBorder = leftBorder,
            rightTimeBorder = rightBorder,
        )
    }
}

data class TimeOverlayResult(
    val isOverlay: Boolean,
    val leftTimeBorder: LocalDateTime?,
    val rightTimeBorder: LocalDateTime?,
)

data class TimeOverlayException(val startOverlay: LocalDateTime?, val endOverlay: LocalDateTime?) : Exception()

