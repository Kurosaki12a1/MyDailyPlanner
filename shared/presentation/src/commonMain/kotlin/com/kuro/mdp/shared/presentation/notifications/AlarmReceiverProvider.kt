package com.kuro.mdp.shared.presentation.notifications

import com.kuro.mdp.shared.presentation.model.AlarmReceiverIntent

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */

interface AlarmReceiverProvider {
    fun provideReceiverIntent(receiverIntent: AlarmReceiverIntent) : PlatformIntent
}

expect class PlatformIntent
