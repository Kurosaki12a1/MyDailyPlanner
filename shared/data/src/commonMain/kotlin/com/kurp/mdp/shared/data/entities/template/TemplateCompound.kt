package com.kurp.mdp.shared.data.entities.template

data class TemplateCompound(
    val template: TemplateEntity,
    val repeatTimes: List<RepeatTimeEntity>,
)

fun List<TemplateCompound>.allRepeatTimes() = mutableListOf<RepeatTimeEntity>().apply {
    this@allRepeatTimes.map { it.repeatTimes }.forEach { addAll(it) }
}

fun List<TemplateCompound>.allTemplatesId() = map { it.template.id }