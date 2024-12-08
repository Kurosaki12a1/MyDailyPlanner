package com.kurp.mdp.shared.data.data_sources.dao.subcategories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kurp.mdp.shared.data.entities.categories.SubCategoryEntity

@Dao
interface SubCategoriesDao {

    @Query("SELECT * FROM subCategories")
    suspend fun fetchAllSubCategories(): List<SubCategoryEntity>

    @Query("SELECT * FROM subCategories WHERE main_category_id = :id")
    suspend fun fetchSubCategoriesByTypeId(id: Int): List<SubCategoryEntity>

    @Insert(entity = SubCategoryEntity::class)
    suspend fun addSubCategories(entity: List<SubCategoryEntity>)

    @Query("DELETE FROM subCategories WHERE id = :id")
    suspend fun removeSubCategory(id: Int)

    @Query("DELETE FROM subCategories")
    suspend fun removeAllSubCategories()

    @Update
    suspend fun updateSubCategory(entity: SubCategoryEntity)
}
