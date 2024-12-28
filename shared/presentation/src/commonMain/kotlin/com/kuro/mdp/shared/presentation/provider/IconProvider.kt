package com.kuro.mdp.shared.presentation.provider

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import shared.resources.Res
import shared.resources.allDrawableResources

/**
 * Created by: minhthinh.h on 12/17/2024
 *
 * Description:
 */
object IconProvider {

    @OptIn(ExperimentalResourceApi::class)
    private val icons: Map<DrawableResource, String> = Res.allDrawableResources.entries.associate { (key, value) -> value to key }

    fun get(resource: DrawableResource): String? = icons[resource]
}
