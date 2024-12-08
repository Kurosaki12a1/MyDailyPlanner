package com.kurp.mdp.shared.data.data_sources.dao.templates

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kurp.mdp.shared.data.entities.template.RepeatTimeEntity
import com.kurp.mdp.shared.data.entities.template.TemplateCompound
import com.kurp.mdp.shared.data.entities.template.TemplateDetails
import com.kurp.mdp.shared.data.entities.template.TemplateEntity
import com.kurp.mdp.shared.data.entities.template.allRepeatTimes
import com.kurp.mdp.shared.data.entities.template.allTemplatesId
import kotlinx.coroutines.flow.Flow

@Dao
interface TemplatesDao {

    @Insert(entity = TemplateEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrUpdateTemplates(templates: List<TemplateEntity>): List<Long>

    @Insert(entity = RepeatTimeEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrUpdateRepeatTimes(repeatTimes: List<RepeatTimeEntity>): List<Long>

    @Transaction
    suspend fun addOrUpdateCompoundTemplates(templates: List<TemplateCompound>): List<Long> {
        deleteRepeatTimesByTemplates(templates.allTemplatesId())
        addOrUpdateRepeatTimes(templates.allRepeatTimes())
        return addOrUpdateTemplates(templates.map { it.template })
    }

    @Transaction
    @Query("SELECT * FROM timeTaskTemplates")
    fun fetchAllTemplates(): Flow<List<TemplateDetails>>

    @Transaction
    @Query("SELECT * FROM timeTaskTemplates WHERE id = :templateId")
    fun fetchTemplateById(templateId: Int): TemplateDetails?

    @Query("DELETE FROM timeTaskTemplates WHERE id = :id")
    suspend fun deleteTemplate(id: Int)

    @Query("DELETE FROM timeTaskTemplates")
    suspend fun deleteAllTemplates()

    @Query("DELETE FROM repeatTimes WHERE template_id IN (:templatesId)")
    suspend fun deleteRepeatTimesByTemplates(templatesId: List<Int>)

    @Query("DELETE FROM repeatTimes")
    suspend fun deleteAllRepeatTimes()
}
