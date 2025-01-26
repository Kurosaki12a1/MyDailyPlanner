package com.kuro.mdp.shared.presentation.navigation.destination

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.utils.functional.Constants
import com.kuro.mdp.shared.utils.functional.Constants.NavigationArguments.DATE
import com.kuro.mdp.shared.utils.functional.Constants.NavigationArguments.ID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
@Serializable
sealed class Destination {
    @Serializable
    @SerialName(Constants.NavigationGraph.SPLASH)
    data object Splash : Destination()

    @Serializable
    @SerialName(Constants.NavigationGraph.HOME)
    data class Home(@SerialName(DATE) val scheduleDate: Long? = null) : Destination()

    @Serializable
    @SerialName(Constants.NavigationGraph.OVERVIEW)
    data object Overview : Destination()

    @Serializable
    @SerialName(Constants.NavigationGraph.ANALYTICS)
    data object Analytics : Destination()

    @Serializable
    @SerialName(Constants.NavigationGraph.SETTINGS)
    data object Settings : Destination()

    @Serializable
    data object Donate : Destination()

    @Serializable
    data object Templates : Destination()

    @Serializable
    data class Categories(@SerialName(ID) val mainCategoryId: Int? = null) : Destination()

    @Serializable
    data class Editor(
        val timeTask: TimeTask,
        val template: Template?,
        val undefinedTaskId: Long? = null
    ) : Destination()
}