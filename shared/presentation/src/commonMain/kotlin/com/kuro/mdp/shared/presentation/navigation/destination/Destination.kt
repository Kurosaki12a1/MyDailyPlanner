package com.kuro.mdp.shared.presentation.navigation.destination

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.Template
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
@Serializable
sealed class Destination {
    @Serializable
    data object Splash : Destination()

    @Serializable
    data class Home(val scheduleDate: Long? = null) : Destination()

    @Serializable
    data object Overview : Destination()

    @Serializable
    data object Analytics : Destination()

    @Serializable
    data object Settings : Destination()

    @Serializable
    data object Donate : Destination()

    @Serializable
    data object Templates : Destination()

    @Serializable
    data object Categories : Destination()

    @Serializable
    data class Editor(
        val timeTask: TimeTask,
        val template: Template?,
        val undefinedTaskId: Long? = null
    ) : Destination()
}