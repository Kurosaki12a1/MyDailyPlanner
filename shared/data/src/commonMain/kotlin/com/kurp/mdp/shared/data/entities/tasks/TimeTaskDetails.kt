package com.kurp.mdp.shared.data.entities.tasks

import androidx.room.Embedded
import androidx.room.Relation
import com.kurp.mdp.shared.data.entities.categories.MainCategoryDetails
import com.kurp.mdp.shared.data.entities.categories.MainCategoryEntity
import com.kurp.mdp.shared.data.entities.categories.SubCategoryEntity

data class TimeTaskDetails(
    @Embedded
    val timeTask: TimeTaskEntity,
    @Relation(
        parentColumn = "main_category_id",
        entityColumn = "id",
        entity = MainCategoryEntity::class,
    )
    val mainCategory: MainCategoryDetails,
    @Relation(
        parentColumn = "sub_category_id",
        entityColumn = "id",
        entity = SubCategoryEntity::class,
    )
    val subCategory: SubCategoryEntity? = null,
)
