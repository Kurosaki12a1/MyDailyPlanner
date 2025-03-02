package com.kuro.mdp.charts.common

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import com.kuro.mdp.charts.ChartShape
import com.kuro.mdp.charts.legend.data.LegendEntry
import com.kuro.mdp.charts.line.data.LineChartData
import com.kuro.mdp.charts.pie.data.PieChartData
import com.kuro.mdp.shared.utils.functional.Constants
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
internal fun <T> List<T>.safeGet(idx: Int): T = when {
    idx in 0..lastIndex -> this[idx]
    idx > lastIndex -> this[idx - size]
    else -> error("Can't get a color at $idx")
}

internal const val DEG2RAD = Constants.Math.PI / 180.0
internal const val FDEG2RAD = Constants.Math.PI.toFloat() / 180f
internal val FLOAT_EPSILON = Float.fromBits(1)

internal fun LineChartData.createLegendEntries(
    shapeSize: Dp,
): List<LegendEntry> =
    series.map { item ->
        LegendEntry(
            text = AnnotatedString(item.title),
            shape = ChartShape(
                color = item.color,
                shape = legendShape,
                size = shapeSize,
            )
        )
    }

internal fun PieChartData.createLegendEntries(
    shapeSize: Dp,
): List<LegendEntry> =
    entries.mapIndexed { index, item ->
        LegendEntry(
            text = item.label,
            value = item.value,
            percent = item.value * 100f / entries.map { it.value }.reduce { acc, i -> acc + i },
            shape = ChartShape(
                color = item.color ?: colors.safeGet(index),
                shape = legendShape,
                size = shapeSize,
            )
        )
    }

internal fun PieChartData.calculateFractions(
    minAngle: Float = 16f,
    maxAngle: Float = 360f
): List<Float> {
    val total = entries.sumOf { it.value.toDouble() }.toFloat()
    val entryCount = entries.size

    val hasMinAngle = minAngle != 0f && entryCount * minAngle <= maxAngle
    val minAngles = MutableList(entryCount) { 0f }

    val fractions = entries
        .map { it.value / total }
        .map { it * 360f }

    var offset = 0f
    var diff = 0f

    if (hasMinAngle) {
        fractions.forEachIndexed { idx, angle ->
            val temp = angle - minAngle

            if (temp <= 0) {
                offset += -temp
                minAngles[idx] = minAngle
            } else {
                minAngles[idx] = angle
                diff += temp
            }
        }

        fractions.forEachIndexed { idx, _ ->
            minAngles[idx] -= (minAngles[idx] - minAngle) / diff * offset
        }

        return minAngles
    }

    return fractions
}

internal fun calculateMinimumRadiusForSpacedSlice(
    center: Offset,
    radius: Float,
    angle: Float,
    arcStartPointX: Float,
    arcStartPointY: Float,
    startAngle: Float,
    sweepAngle: Float
): Float {
    val angleMiddle = startAngle + sweepAngle / 2f

    // Other point of the arc
    val arcEndPointX: Float = center.x + radius * cos((startAngle + sweepAngle) * FDEG2RAD)
    val arcEndPointY: Float = center.y + radius * sin((startAngle + sweepAngle) * FDEG2RAD)

    // Middle point on the arc
    val arcMidPointX: Float = center.x + radius * cos(angleMiddle * FDEG2RAD)
    val arcMidPointY: Float = center.y + radius * sin(angleMiddle * FDEG2RAD)

    // This is the base of the contained triangle
    val basePointsDistance = sqrt(
        (arcEndPointX - arcStartPointX).toDouble().pow(2.0) +
                (arcEndPointY - arcStartPointY).toDouble().pow(2.0)
    )

    // After reducing space from both sides of the "slice",
    //   the angle of the contained triangle should stay the same.
    // So let's find out the height of that triangle.
    val containedTriangleHeight =
        (basePointsDistance / 2.0 * tan((180.0 - angle) / 2.0 * DEG2RAD)).toFloat()

    // Now we subtract that from the radius
    var spacedRadius = radius - containedTriangleHeight

    // And now subtract the height of the arc that's between the triangle and the outer circle
    spacedRadius -= sqrt(
        (arcMidPointX - (arcEndPointX + arcStartPointX) / 2f).toDouble().pow(2.0) +
                (arcMidPointY - (arcEndPointY + arcStartPointY) / 2f).toDouble().pow(2.0)
    ).toFloat()

    return spacedRadius
}


internal enum class EntryDrawShape {
    // Rounded on all sides
    Single,

    // Rounded left side
    First,

    // Not rounded
    Middle,

    // Rounded right side
    Last;
}

// TODO: make this customisable
internal fun createBarEntryShape(shape: EntryDrawShape, size: Size): Path {
    val rect = Rect(Offset(0f, 0f), Offset(size.width, size.height))
    return Path().apply {
        when (shape) {
            EntryDrawShape.Single -> addRoundRect(
                RoundRect(
                    rect = rect,
                    cornerRadius = CornerRadius(size.height)
                )
            )

            EntryDrawShape.First -> addRoundRect(
                RoundRect(
                    rect = rect,
                    topRight = CornerRadius.Zero,
                    bottomRight = CornerRadius.Zero,
                    topLeft = CornerRadius(size.height),
                    bottomLeft = CornerRadius(size.height),
                )
            )

            EntryDrawShape.Middle -> addRect(rect = rect)
            EntryDrawShape.Last -> addRoundRect(
                RoundRect(
                    rect = rect,
                    topLeft = CornerRadius.Zero,
                    bottomLeft = CornerRadius.Zero,
                    topRight = CornerRadius(size.height),
                    bottomRight = CornerRadius(size.height),
                )
            )
        }
    }
}
