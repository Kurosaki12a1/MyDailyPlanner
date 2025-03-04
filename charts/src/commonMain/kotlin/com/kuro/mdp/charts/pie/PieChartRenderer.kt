package com.kuro.mdp.charts.pie

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import com.kuro.mdp.charts.common.FDEG2RAD
import com.kuro.mdp.charts.common.FLOAT_EPSILON
import com.kuro.mdp.charts.common.calculateMinimumRadiusForSpacedSlice
import com.kuro.mdp.charts.common.safeGet
import com.kuro.mdp.charts.pie.data.PieChartEntry
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
private const val StartDegree = -90f
private const val FACTOR_SELECTED_PIE = 1.2f

@Composable
internal fun PieChartRenderer(
    modifier: Modifier = Modifier,
    chartSizePx: Float,
    sliceWidthPx: Float,
    sliceSpacingPx: Float,
    fractions: List<Float>,
    data: List<PieChartEntry>,
    composeColors: List<Color>,
    animate: Boolean,
    pieSelected: Int = -1,
    clickOffset: Offset = Offset.Zero,
    onClick: (Offset) -> Unit = {}
) {
    var animationRan by rememberSaveable(fractions, animate) { mutableStateOf(false) }
    val animation = remember {
        Animatable(
            when {
                animate -> if (animationRan) 1f else 0f
                else -> 1f
            }
        )
    }
    val phase by animation.asState()

    LaunchedEffect(Unit) {
        if (!animationRan)
            animation.animateTo(1f, tween(325)) { animationRan = true }
    }

    val pathBuffer by remember { mutableStateOf(Path()) }

    val holeRadius = remember(chartSizePx, sliceWidthPx) {
        (chartSizePx - (sliceWidthPx * 2f)) / 2f
    }

    val textMeasurer = rememberTextMeasurer()
    val textColor = MaterialTheme.colorScheme.onSurface

    Canvas(modifier = modifier.pointerInput(Unit) {
        detectTapGestures { onClick(it) }
    }) {
        val circleBox = Rect(0f, 0f, size.width, size.height)

        var angle = 0f

        val radius = chartSizePx / 2f
        val drawInnerArc = sliceWidthPx > FLOAT_EPSILON && sliceWidthPx < chartSizePx / 2f
        val userInnerRadius = if (drawInnerArc) holeRadius else 0f

        fractions.fastForEachIndexed { idx, sliceAngle ->
            var innerRadius = userInnerRadius
            val isPieSelected = pieSelected == idx

            if (isPieSelected) {
                val textLayoutResult = textMeasurer.measure(
                    text = "${data[idx].label}\n${data[idx].textDisplay}",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Center
                    ),
                    overflow = TextOverflow.Clip,
                    constraints = Constraints(
                        maxWidth = (radius - sliceWidthPx).toInt() * 2,
                        maxHeight = (radius - sliceWidthPx).toInt() * 2
                    )
                )
                drawText(
                    textLayoutResult = textLayoutResult,
                    color = textColor,
                    topLeft = Offset(center.x - textLayoutResult.size.width / 2f, center.y - textLayoutResult.size.height / 2f),
                )
            }

            val accountForSliceSpacing = sliceSpacingPx > 0f && sliceAngle <= 180f

            val sliceSpaceAngleOuter = sliceSpacingPx / (FDEG2RAD * radius)
            val startAngleOuter = StartDegree + (angle + sliceSpaceAngleOuter / 2f) * phase
            val sweepAngleOuter = ((sliceAngle - sliceSpaceAngleOuter) * phase).coerceAtLeast(0f)

            pathBuffer.reset()

            val arcStartPointX = center.x + radius * cos(startAngleOuter * FDEG2RAD)
            val arcStartPointY = center.y + radius * sin(startAngleOuter * FDEG2RAD)

            if (sweepAngleOuter >= 360f && sweepAngleOuter % 360f <= FLOAT_EPSILON) {
                pathBuffer.addOval(Rect(center = Offset(center.x, center.y), radius))
            } else {
                if (!isPieSelected) {
                    pathBuffer.arcTo(
                        circleBox,
                        startAngleOuter,
                        sweepAngleOuter,
                        false
                    )
                } else {
                    pathBuffer.arcTo(
                        resizeRect(circleBox, 12f),
                        startAngleOuter,
                        sweepAngleOuter,
                        false
                    )
                }
            }

            val innerRectBuffer = Rect(
                center.x - innerRadius,
                center.y - innerRadius,
                center.x + innerRadius,
                center.y + innerRadius
            )


            if (drawInnerArc && (innerRadius > 0f || accountForSliceSpacing)) {
                if (accountForSliceSpacing) {
                    val minSpacedRadius = calculateMinimumRadiusForSpacedSlice(
                        center,
                        radius,
                        sliceAngle * phase,
                        arcStartPointX, arcStartPointY,
                        startAngleOuter,
                        sweepAngleOuter
                    ).absoluteValue

                    innerRadius = max(innerRadius, minSpacedRadius)
                }

                val sliceSpaceAngleInner = sliceSpacingPx / (FDEG2RAD * innerRadius)

                val startAngleInner = StartDegree + (angle + sliceSpaceAngleInner / 2f) * phase
                val sweepAngleInner =
                    ((sliceAngle - sliceSpaceAngleInner) * phase).coerceAtLeast(0f)

                val endAngleInner = startAngleInner + sweepAngleInner
                if (sweepAngleOuter >= 360f && sweepAngleOuter % 360f <= FLOAT_EPSILON) {
                    // Android is doing "mod 360"
                    pathBuffer.addArc(innerRectBuffer, StartDegree, sweepAngleOuter)
                } else {
                    pathBuffer.lineTo(
                        center.x + innerRadius * cos(endAngleInner * FDEG2RAD),
                        center.y + innerRadius * sin(endAngleInner * FDEG2RAD)
                    )

                    pathBuffer.arcTo(
                        innerRectBuffer,
                        endAngleInner,
                        -sweepAngleInner,
                        false
                    )
                }
            } else {
                if (sweepAngleOuter % 360f > FLOAT_EPSILON) {
                    if (accountForSliceSpacing) {
                        val angleMiddle = startAngleOuter + sweepAngleOuter / 2f
                        val sliceSpaceOffset = calculateMinimumRadiusForSpacedSlice(
                            center,
                            radius,
                            sliceAngle * phase,
                            arcStartPointX,
                            arcStartPointY,
                            startAngleOuter,
                            sweepAngleOuter
                        )
                        val arcEndPointX = center.x + sliceSpaceOffset * cos(angleMiddle * FDEG2RAD)
                        val arcEndPointY = center.y + sliceSpaceOffset * sin(angleMiddle * FDEG2RAD)
                        pathBuffer.lineTo(
                            arcEndPointX,
                            arcEndPointY
                        )
                    } else {
                        pathBuffer.lineTo(
                            center.x,
                            center.y
                        )
                    }
                }
            }

            pathBuffer.close()

            drawPath(pathBuffer, composeColors.safeGet(idx))

            angle += sliceAngle * phase
        }
    }
}

private fun resizeRect(rect: Rect, value: Float): Rect {
    return Rect(
        left = rect.left - value,
        right = rect.right + value,
        top = rect.top - value,
        bottom = rect.bottom + value
    )
}
