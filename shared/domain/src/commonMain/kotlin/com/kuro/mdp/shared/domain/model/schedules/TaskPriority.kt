package com.kuro.mdp.shared.domain.model.schedules

import kotlinx.serialization.Serializable

@Serializable
enum class TaskPriority {
    STANDARD, MEDIUM, MAX;

    fun isImportant() = this != STANDARD
}