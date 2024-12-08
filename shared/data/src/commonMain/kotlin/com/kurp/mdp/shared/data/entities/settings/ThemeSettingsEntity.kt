package com.kurp.mdp.shared.data.entities.settings

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kuro.mdp.shared.domain.model.settings.ColorsType
import com.kuro.mdp.shared.domain.model.settings.LanguageType
import com.kuro.mdp.shared.domain.model.settings.ThemeType

@Entity(tableName = "ThemeSettings")
data class ThemeSettingsEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo("language") val language: LanguageType = LanguageType.DEFAULT,
    @ColumnInfo("theme_colors") val themeColors: ThemeType = ThemeType.DEFAULT,
    @ColumnInfo("colors_type") val colorsType: ColorsType = ColorsType.PINK,
    @ColumnInfo("dynamic_color") val isDynamicColorEnable: Boolean = false,
)
