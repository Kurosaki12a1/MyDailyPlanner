package com.kurp.mdp.shared.data.entities.template

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kurp.mdp.shared.data.entities.categories.MainCategoryEntity
import com.kurp.mdp.shared.data.entities.categories.SubCategoryEntity

@Entity(
    tableName = "timeTaskTemplates",
    foreignKeys = [
        ForeignKey(
            entity = MainCategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("main_category_id"),
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = SubCategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("sub_category_id"),
            onDelete = ForeignKey.SET_NULL,
        ),
    ],
)
data class TemplateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("start_time") val startTime: Long,
    @ColumnInfo("end_time") val endTime: Long,
    @ColumnInfo("main_category_id", index = true) val categoryId: Int,
    @ColumnInfo("sub_category_id", index = true) val subCategoryId: Int? = null,
    @ColumnInfo("is_important") val isImportantMax: Boolean,
    @ColumnInfo("is_medium_important", defaultValue = "0") val isImportantMedium: Boolean,
    @ColumnInfo("is_enable_notification") val isEnableNotification: Boolean,
    @ColumnInfo("is_consider_in_statistics") val isConsiderInStatistics: Boolean,
    @ColumnInfo("repeat_enabled", defaultValue = "0") val repeatEnabled: Boolean = false,
)
