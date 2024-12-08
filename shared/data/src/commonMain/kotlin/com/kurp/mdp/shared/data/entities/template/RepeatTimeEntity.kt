package com.kurp.mdp.shared.data.entities.template

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kuro.mdp.shared.domain.model.template.RepeatTimeType
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

@Entity(tableName = "repeatTimes")
data class RepeatTimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("template_id", index = true) val templateId: Int,
    @ColumnInfo("type") val type: RepeatTimeType,
    @ColumnInfo("day") val day: DayOfWeek? = null,
    @ColumnInfo("day_number") val dayNumber: Int? = null,
    @ColumnInfo("month") val month: Month? = null,
    @ColumnInfo("week_number") val weekNumber: Int? = null,
)
