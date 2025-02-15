package com.kuro.mdp.shared.presentation.extensions

import android.content.Context
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
fun StringResource.getId(context: Context): Int = context.resources.getIdentifier(this.key, "string", context.packageName)

fun DrawableResource.getId(context: Context, convertToKey: (DrawableResource) -> String?): Int {
    return context.resources.getIdentifier(convertToKey(this), "drawable", context.packageName)
}

fun Context.getString(resource: StringResource?, defaultName: String = ""): String {
    if (resource == null) return defaultName
    return getString(resource.getId(this))
}
