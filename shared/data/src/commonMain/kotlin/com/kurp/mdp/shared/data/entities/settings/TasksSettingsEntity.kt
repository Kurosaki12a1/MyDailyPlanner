package com.kurp.mdp.shared.data.entities.settings

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TasksSettings")
data class TasksSettingsEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo("task_view_status") val taskViewStatus: String = "COMPACT",
    @ColumnInfo("task_analytics_range") val taskAnalyticsRange: String = "WEEK",
    @ColumnInfo("calendar_button_behavior") val calendarButtonBehavior: String = "SET_CURRENT_DATE",
    @ColumnInfo("secure_mode") val secureMode: Boolean = false,
)
