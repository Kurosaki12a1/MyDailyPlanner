package com.kuro.mdp.features.overview.presentation.ui.overview.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
internal fun TimeTaskTitles(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color,
    subTitle: String?,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            color = titleColor,
            style = MaterialTheme.typography.titleMedium,
        )
        if (subTitle != null) {
            Text(
                text = subTitle,
                modifier = Modifier.padding(top = 2.dp),
                color = titleColor,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
