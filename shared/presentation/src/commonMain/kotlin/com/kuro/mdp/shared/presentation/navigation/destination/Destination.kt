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
    @SerialName(Constants.Destination.SPLASH)
    data object Splash : Destination()

    @Serializable
    @SerialName(Constants.Destination.HOME)
    data class Home(@SerialName(DATE) val scheduleDate: Long? = null) : Destination()

    @Serializable
    @SerialName(Constants.Destination.OVERVIEW)
    data object Overview : Destination()

    @Serializable
    @SerialName(Constants.Destination.ANALYTICS)
    data object Analytics : Destination()

    @Serializable
    @SerialName(Constants.Destination.SETTINGS)
    data object Settings : Destination()

    @Serializable
    data object Donate : Destination()

    @Serializable
    data object Templates : Destination()

    @Serializable
    data class Categories(@SerialName(ID) val mainCategoryId: Int? = null) : Destination()

    // This cannot use as route because KMP does not support deep link right now
    @Serializable
    data class Editor(
        val timeTask: TimeTask,
        val template: Template?,
        val undefinedTaskId: Long? = null
    ) : Destination()

    @Serializable
    data class EditorRoute(
        val data: String
    ) : Destination()
}