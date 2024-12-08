package com.kurp.mdp.shared.data.entities.template

import androidx.room.Embedded
import androidx.room.Relation
import com.kurp.mdp.shared.data.entities.categories.MainCategoryEntity
import com.kurp.mdp.shared.data.entities.categories.SubCategoryEntity

data class TemplateDetails(
    @Embedded
    val template: TemplateEntity,
    @Relation(
        parentColumn = "main_category_id",
        entityColumn = "id",
        entity = MainCategoryEntity::class,
    )
    val mainCategory: MainCategoryEntity,
    @Relation(
        parentColumn = "sub_category_id",
        entityColumn = "id",
        entity = SubCategoryEntity::class,
    )
    val subCategory: SubCategoryEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "template_id",
        entity = RepeatTimeEntity::class,
    )
    val repeatTime: List<RepeatTimeEntity>,
)
