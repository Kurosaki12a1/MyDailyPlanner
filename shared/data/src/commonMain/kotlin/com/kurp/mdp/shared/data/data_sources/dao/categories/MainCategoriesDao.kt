package com.kurp.mdp.shared.data.data_sources.dao.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.kurp.mdp.shared.data.entities.categories.MainCategoryDetails
import com.kurp.mdp.shared.data.entities.categories.MainCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MainCategoriesDao {

    @Transaction
    @Query("SELECT * FROM mainCategories")
    fun fetchAllCategories(): Flow<List<MainCategoryDetails>>

    @Transaction
    @Query("SELECT * FROM mainCategories WHERE id = :id")
    suspend fun fetchCategoriesById(id: Int): MainCategoryDetails?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(entity: MainCategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategories(entities: List<MainCategoryEntity>): List<Long>

    @Query("DELETE FROM mainCategories WHERE id = :id")
    suspend fun removeCategory(id: Int)

    @Query("DELETE FROM mainCategories")
    suspend fun removeAllCategories()

    @Update
    suspend fun updateCategory(entity: MainCategoryEntity)
}
