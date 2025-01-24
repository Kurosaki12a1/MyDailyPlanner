package com.kuro.mdp.features.settings.presentation.ui.donate.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.domain.model.donate.CryptoAddress
import com.kuro.mdp.features.settings.presentation.mappers.mapToIcon
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.utils.extensions.string
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
@Composable
internal fun CryptoAddressItem(
    modifier: Modifier = Modifier,
    model: CryptoAddress,
    onCopy: (String) -> Unit,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier.size(38.dp),
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = MaterialTheme.shapes.medium,
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(model.mapToIcon()),
                        contentDescription = model.cryptoName,
                    )
                }
            }
            Text(
                modifier = Modifier.weight(1f),
                text = model.address,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
            )
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = { onCopy(model.address) },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ),
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(SettingsTheme.icons.copy),
                    contentDescription =
                    SettingsTheme.strings.copyTitle.string(),
                )
            }
        }
    }
}
