package com.kuro.mdp.shared.presentation.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.domain.model.schedules.TaskNotificationType
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.presentation.mappers.mapToIcon
import com.kuro.mdp.shared.presentation.mappers.mapToString
import com.kuro.mdp.shared.presentation.model.AlarmReceiverIntent
import com.kuro.mdp.shared.presentation.model.NotificationTimeType
import com.kuro.mdp.shared.presentation.model.toTimeType
import com.kuro.mdp.shared.presentation.provider.IconProvider
import com.kuro.mdp.shared.presentation.theme.resources.baseAppStrings
import com.kuro.mdp.shared.presentation.theme.resources.fetchAppIcons
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.Constants
import com.kuro.mdp.shared.utils.managers.DateManager
import extensions.getId
import extensions.getString
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
class TimeTaskAlarmManagerImpl(
    private val context: Context, private val receiverProvider: AlarmReceiverProvider, private val dateManager: DateManager
) : TimeTaskAlarmManager {
    private val alarmManager: AlarmManager
        get() = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    private val currentTime: LocalDateTime
        get() = dateManager.fetchCurrentDate()

    override fun addOrUpdateNotifyAlarm(timeTask: TimeTask) {
        timeTask.taskNotifications.toTypes(timeTask.isEnableNotification).forEach { type ->
            val alarmIntent = createAlarmIntent(timeTask.category, timeTask.subCategory, type.toTimeType())
            val id = timeTask.key + type.idAmount
            val pendingAlarmIntent = createPendingAlarmIntent(alarmIntent, id.toInt())
            val triggerTime = type.fetchNotifyTrigger(timeTask.timeRange).toEpochMillis()
            if (triggerTime > currentTime.toEpochMillis()) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingAlarmIntent)
            }
        }
    }

    override fun deleteNotifyAlarm(timeTask: TimeTask) {
        TaskNotificationType.entries.forEach { type ->
            val alarmIntent = createAlarmIntent(timeTask.category, timeTask.subCategory, type.toTimeType())
            val id = timeTask.key + type.idAmount
            val pendingAlarmIntent = createPendingAlarmIntent(alarmIntent, id.toInt())
            alarmManager.cancel(pendingAlarmIntent)
            pendingAlarmIntent.cancel()
        }
    }

    private fun createAlarmIntent(
        category: MainCategory,
        subCategory: SubCategory?,
        timeType: NotificationTimeType = NotificationTimeType.START_TASK,
    ): Intent {
        val categoryName = category.let { context.getString(resource = it.default?.mapToString(baseAppStrings), defaultName = Constants.App.NAME) }
        val subCategoryName = subCategory?.name ?: ""
        val categoryIcon = category.default?.mapToIcon(fetchAppIcons())?.getId(context) { IconProvider.get(it) }
        val appIcon = fetchAppIcons().logo.getId(context) { IconProvider.get(it) }

        return receiverProvider.provideReceiverIntent(
            AlarmReceiverIntent(
                category = categoryName, subCategory = subCategoryName, icon = categoryIcon, appIcon = appIcon, timeType = timeType
            )
        )
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