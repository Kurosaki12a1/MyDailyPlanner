package com.kuro.mdp.shared.presentation.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.presentation.extensions.getId
import com.kuro.mdp.shared.presentation.extensions.getString
import com.kuro.mdp.shared.presentation.mappers.mapToIcon
import com.kuro.mdp.shared.presentation.mappers.mapToString
import com.kuro.mdp.shared.presentation.model.AlarmReceiverIntent
import com.kuro.mdp.shared.presentation.model.NotificationTimeType
import com.kuro.mdp.shared.presentation.provider.IconProvider
import com.kuro.mdp.shared.presentation.theme.resources.AppIcons
import com.kuro.mdp.shared.presentation.theme.resources.baseAppStrings
import com.kuro.mdp.shared.presentation.theme.resources.fetchAppIcons
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
class TemplatesAlarmManagerImpl(
    private val context: Context, private val receiverProvider: AlarmReceiverProvider
) : TemplatesAlarmManager {

    private val alarmManager: AlarmManager
        get() = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    private val appIcons: AppIcons
        get() = fetchAppIcons()

    override fun addOrUpdateNotifyAlarm(template: Template, repeatTime: RepeatTime) = addRawNotifyAlarm(
        templateId = template.templateId,
        timeType = NotificationTimeType.START_TASK,
        repeatTime = repeatTime,
        time = template.startTime,
        category = template.category.let { context.getString(resource = it.default?.mapToString(baseAppStrings)) },
        subCategory = template.subCategory?.name,
        icon = template.category.default?.mapToIcon(appIcons)?.getId(context) { IconProvider.get(it) }
    )

    override fun addRawNotifyAlarm(
        templateId: Int,
        timeType: NotificationTimeType,
        repeatTime: RepeatTime,
        time: LocalDateTime,
        category: String,
        subCategory: String?,
        icon: Int?
    ) {
        val id = templateId + repeatTime.key
        val alarmIntent = receiverProvider.provideReceiverIntent(
            AlarmReceiverIntent(
                category = category,
                subCategory = subCategory.orEmpty(),
                icon = icon,
                appIcon = fetchAppIcons().logo.getId(context) { IconProvider.get(it) },
                time = time,
                templateId = templateId,
                repeatTime = repeatTime,
                timeType = timeType
            )
        )

        val pendingIntentAlarm = createPendingAlarmIntent(alarmIntent, id)
        val triggerTime = repeatTime.nextDate(time)

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, triggerTime.toEpochMillis(), pendingIntentAlarm
        )
    }

    override fun deleteNotifyAlarm(template: Template, repeatTime: RepeatTime) {
        val id = template.templateId + repeatTime.key
        val alarmIntent = receiverProvider.provideReceiverIntent(
            AlarmReceiverIntent(
                category = template.category.let { context.getString(resource = it.default?.mapToString(baseAppStrings)) },
                subCategory = template.subCategory?.name.orEmpty(),
                icon = template.category.default?.mapToIcon(appIcons)?.getId(context) { IconProvider.get(it) },
                appIcon = fetchAppIcons().logo.getId(context) { IconProvider.get(it) },
                time = template.startTime,
                templateId = template.templateId,
                repeatTime = repeatTime,
                timeType = NotificationTimeType.START_TASK
            )
        )
        val pendingAlarmIntent = createPendingAlarmIntent(alarmIntent, id)

        alarmManager.cancel(pendingAlarmIntent)
        pendingAlarmIntent.cancel()
    }

    private fun createPendingAlarmIntent(
        alarmIntent: Intent,
        requestId: Int,
    ) = PendingIntent.getBroadcast(
        context,
        requestId,
        alarmIntent,
        PendingIntent.FLAG_MUTABLE,
    )
}