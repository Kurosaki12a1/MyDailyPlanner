package extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
fun Context.openNetworkUri(uri: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    startActivity(intent)
}

fun Context.setClipboard(text: String?) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Copied Text", text)
    clipboard.setPrimaryClip(clip)
}

fun StringResource.getId(context: Context): Int = context.resources.getIdentifier(this.key, "string", context.packageName)

fun DrawableResource.getId(context: Context, convertToKey: (DrawableResource) -> String?): Int {
    return context.resources.getIdentifier(convertToKey(this), "drawable", context.packageName)
}

fun Context.getString(resource: StringResource?, defaultName: String = ""): String {
    if (resource == null) return defaultName
    return getString(resource.getId(this))
}
