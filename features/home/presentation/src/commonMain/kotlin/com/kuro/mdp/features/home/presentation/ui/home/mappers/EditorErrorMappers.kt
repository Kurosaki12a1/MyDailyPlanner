package com.kuro.mdp.features.home.presentation.ui.home.mappers

import com.kuro.mdp.features.home.presentation.ui.home.theme.resources.HomeStrings
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.contract.EditorError
import org.jetbrains.compose.resources.StringResource

/**
 * Created by: minhthinh.h on 2/13/2025
 *
 * Description:
 */
internal fun EditorError.mapToMessage(homeString: HomeStrings): StringResource = when (this) {
    is EditorError.ShowError -> homeString.otherError
    is EditorError.ShowOverLayError -> {
        if (this.startOverlay != null && this.endOverlay != null) {
            homeString.fullOverlayError
        } else if (startOverlay != null) {
            homeString.startOverlayError
        } else homeString.endOverlayError
    }
}