package extensions

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
fun String.toResInt(context: Context): Int =
    context.resources.getIdentifier(this, "drawable", context.packageName)

fun Context.openNetworkUri(uri: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    startActivity(intent)
}

fun setClipboard(context: Context, text: String?) {
    val clipboard =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = ClipData.newPlainText("Copied Text", text)
    clipboard.setPrimaryClip(clip)
}
