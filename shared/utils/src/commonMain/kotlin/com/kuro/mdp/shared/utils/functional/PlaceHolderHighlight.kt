package com.kuro.mdp.shared.utils.functional

import androidx.annotation.FloatRange
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description: A class which provides a brush to paint placeholder based on progress.
 */
interface PlaceHolderHighlight {
    /**
     * The optional [AnimationSpec] to use when running the animation for this highlight.
     */
    val animationSpec: InfiniteRepeatableSpec<Float>?

    /**
     * Return a [Brush] to draw for the given [progress] and [size].
     *
     * @param progress the current animated progress in the range of 0f..1f.
     * @param size The size of the current layout to draw in.
     */
    fun brush(
        @FloatRange(from = 0.0, to = 1.0) progress: Float,
        size: Size
    ): Brush

    /**
     * Return the desired alpha value used for drawing the [Brush] returned from [brush].
     *
     * @param progress the current animated progress in the range of 0f..1f.
     */
    @FloatRange(from = 0.0, to = 1.0)
    fun alpha(progress: Float): Float

    companion object
}

