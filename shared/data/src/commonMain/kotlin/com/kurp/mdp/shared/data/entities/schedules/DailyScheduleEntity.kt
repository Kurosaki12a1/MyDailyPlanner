package com.kurp.mdp.shared.data.entities.schedules

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dailySchedules")
data class DailyScheduleEntity(
    @PrimaryKey val date: Long,
)
