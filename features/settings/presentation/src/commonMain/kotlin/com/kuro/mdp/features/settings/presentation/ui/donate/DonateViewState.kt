package com.kuro.mdp.features.settings.presentation.ui.donate

import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
@Serializable
internal sealed class DonateViewState : BaseViewState {
    data object Default : DonateViewState()
}