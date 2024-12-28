package com.kuro.mdp.shared.presentation.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Composable
fun ErrorSnackBar(snackBarData: SnackbarData) = Snackbar(
    snackbarData = snackBarData,
    containerColor = MaterialTheme.colorScheme.errorContainer,
    contentColor = MaterialTheme.colorScheme.onErrorContainer,
    actionColor = MaterialTheme.colorScheme.error,
    dismissActionContentColor = MaterialTheme.colorScheme.onSurface,
)
