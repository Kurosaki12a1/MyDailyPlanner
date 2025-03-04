package com.kuro.mdp.shared.presentation.model

import androidx.compose.ui.unit.Constraints

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
internal enum class LayoutOrientation {
    Horizontal,
    Vertical
}

internal data class OrientationIndependentConstraints(
    val mainAxisMin: Int,
    val mainAxisMax: Int,
    val crossAxisMin: Int,
    val crossAxisMax: Int
) {
    constructor(c: Constraints, orientation: LayoutOrientation) : this(
        if (orientation === LayoutOrientation.Horizontal) c.minWidth else c.minHeight,
        if (orientation === LayoutOrientation.Horizontal) c.maxWidth else c.maxHeight,
        if (orientation === LayoutOrientation.Horizontal) c.minHeight else c.minWidth,
        if (orientation === LayoutOrientation.Horizontal) c.maxHeight else c.maxWidth
    )
}
