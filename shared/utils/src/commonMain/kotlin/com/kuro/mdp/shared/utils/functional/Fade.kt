package com.kuro.mdp.shared.utils.functional

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
data class Fade(
    private val highlightColor: Color,
    override val animationSpec: InfiniteRepeatableSpec<Float>,
) : PlaceHolderHighlight {
    private val brush = SolidColor(highlightColor)

    override fun brush(progress: Float, size: Size): Brush = brush
    override fun alpha(progress: Float): Float = progress
}
