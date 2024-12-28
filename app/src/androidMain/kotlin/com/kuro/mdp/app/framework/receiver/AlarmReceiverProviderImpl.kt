package com.kuro.mdp.app.framework.receiver

import android.content.Context
import android.content.Intent
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.presentation.model.AlarmReceiverIntent
import com.kuro.mdp.shared.presentation.notifications.AlarmReceiverProvider
import com.kuro.mdp.shared.presentation.notifications.PlatformIntent
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.Constants

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
class AlarmReceiverProviderImpl(
    private val context: Context
) : AlarmReceiverProvider {
    override fun provideReceiverIntent(receiverIntent: AlarmReceiverIntent): PlatformIntent {
        return Intent(context, TimeTaskAlarmReceiver::class.java).apply {
            action = Constants.Alarm.ALARM_NOTIFICATION_ACTION
            putExtra(Constants.Alarm.NOTIFICATION_TIME_TYPE, receiverIntent.timeType.toString())
            putExtra(Constants.Alarm.NOTIFICATION_CATEGORY, receiverIntent.category)
            putExtra(Constants.Alarm.NOTIFICATION_SUBCATEGORY, receiverIntent.subCategory)
            if (receiverIntent.icon != null) {
                putExtra(Constants.Alarm.NOTIFICATION_ICON, receiverIntent.icon!!)
            }
            putExtra(Constants.Alarm.APP_ICON, receiverIntent.appIcon)
            if (receiverIntent.time != null) {
                putExtra(Constants.Alarm.REPEAT_TIME, receiverIntent.time!!.toEpochMillis())
            }
            if (receiverIntent.repeatTime != null) {
                putExtra(Constants.Alarm.REPEAT_TYPE, receiverIntent.repeatTime!!.repeatType.name)
            }
            if (receiverIntent.templateId != null) {
                putExtra(Constants.Alarm.TEMPLATE_ID, receiverIntent.templateId!!)
            }
            when (val value = receiverIntent.repeatTime) {
                null -> {}

                is RepeatTime.WeekDays -> {
                    putExtra(Constants.Alarm.WEEK_DAY, value.day.name)
                }

                is RepeatTime.MonthDay -> {
                    putExtra(Constants.Alarm.DAY_OF_MONTH, value.dayNumber)
                }

                is RepeatTime.WeekDayInMonth -> {
                    putExtra(Constants.Alarm.WEEK_DAY, value.day.name)
                    putExtra(Constants.Alarm.WEEK_NUMBER, value.weekNumber)
                }

                is RepeatTime.YearDay -> {
                    putExtra(Constants.Alarm.DAY_OF_MONTH, value.dayNumber)
                    putExtra(Constants.Alarm.MONTH, value.month.name)
                }
            }
        }
    }
}