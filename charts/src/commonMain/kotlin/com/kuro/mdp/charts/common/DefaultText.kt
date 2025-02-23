package com.kuro.mdp.charts.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
@Composable
internal fun DefaultText(text: AnnotatedString?) {
    if (text != null)
        Text(text = text)
}
