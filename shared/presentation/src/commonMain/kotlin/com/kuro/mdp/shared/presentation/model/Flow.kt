package com.kuro.mdp.shared.presentation.model

import androidx.compose.foundation.layout.Arrangement

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
/**
 * Used to specify the alignment of a layout's children, in cross axis direction.
 */
enum class FlowCrossAxisAlignment {
    /**
     * Place children such that their center is in the middle of the cross axis.
     */
    Center,

    /**
     * Place children such that their start edge is aligned to the start edge of the cross axis.
     */
    Start,

    /**
     * Place children such that their end edge is aligned to the end edge of the cross axis.
     */
    End,
}

typealias FlowMainAxisAlignment = MainAxisAlignment

enum class SizeMode {
    /**
     * Minimize the amount of free space by wrapping the children,
     * subject to the incoming layout constraints.
     */
    Wrap,

    /**
     * Maximize the amount of free space by expanding to fill the available space,
     * subject to the incoming layout constraints.
     */
    Expand
}

/**
 * Used to specify the alignment of a layout's children, in main axis direction.
 */
enum class MainAxisAlignment(internal val arrangement: Arrangement.Vertical) {
    // workaround for now - use Arrangement that equals to previous Arrangement
    /**
     * Place children such that they are as close as possible to the middle of the main axis.
     */
    Center(Arrangement.Center),

    /**
     * Place children such that they are as close as possible to the start of the main axis.
     */
    Start(Arrangement.Top),

    /**
     * Place children such that they are as close as possible to the end of the main axis.
     */
    End(Arrangement.Bottom),

    /**
     * Place children such that they are spaced evenly across the main axis, including free
     * space before the first child and after the last child.
     */
    SpaceEvenly(Arrangement.SpaceEvenly),

    /**
     * Place children such that they are spaced evenly across the main axis, without free
     * space before the first child or after the last child.
     */
    SpaceBetween(Arrangement.SpaceBetween),

    /**
     * Place children such that they are spaced evenly across the main axis, including free
     * space before the first child and after the last child, but half the amount of space
     * existing otherwise between two consecutive children.
     */
    SpaceAround(Arrangement.SpaceAround);
}