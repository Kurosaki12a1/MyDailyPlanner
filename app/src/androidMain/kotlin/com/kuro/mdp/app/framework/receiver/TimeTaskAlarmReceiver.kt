package com.kuro.mdp.app.framework.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
class TimeTaskAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null || context == null) return
    }
}