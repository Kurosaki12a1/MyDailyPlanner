package com.kuro.mdp.features.settings.presentation.ui.donate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.domain.model.CryptoAddress

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
@Composable
internal fun CryptoAddressLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
) {
    val clipBoardManager = LocalClipboardManager.current
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        items(CryptoAddress.entries.toTypedArray()) { address ->
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
                text = address.cryptoName,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                style = MaterialTheme.typography.titleSmall,
            )
            CryptoAddressItem(
                model = address,
                onCopy = { clipBoardManager.setText(annotatedString = buildAnnotatedString { append(text = it) }) },
            )
        }
        item {
            Spacer(modifier = Modifier.padding(48.dp))
        }
    }
}