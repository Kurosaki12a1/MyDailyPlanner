package com.kuro.mdp.shared.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue.Companion.mainQueue
import platform.Foundation.NSUserDefaults
import platform.Foundation.currentLocale
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDidBecomeActiveNotification
import platform.UIKit.UIApplicationWillResignActiveNotification
import platform.UIKit.UIColor
import platform.UIKit.UIScreen
import platform.UIKit.UIView
import platform.darwin.NSObjectProtocol


// Tag used to identify the screen shield view.
private const val TAG_SHIELD = 999L

// Observers for UIApplicationWillResignActiveNotification and UIApplicationDidBecomeActiveNotification notifications.
private var resignActiveObserver: NSObjectProtocol? = null
private var becomeActiveObserver: NSObjectProtocol? = null

/**
 * Composable function to handle screen protection based on enable flag.
 *
 * @param enable A boolean flag to enable or disable screen protection.
 */
@Composable
actual fun ScreenProtection(enable: Boolean) {
    // LaunchedEffect to observe changes in the enable parameter.
    LaunchedEffect(enable) {
        if (enable) {
            // Add observers for UIApplicationWillResignActiveNotification and UIApplicationDidBecomeActiveNotification.
            resignActiveObserver = NSNotificationCenter.defaultCenter.addObserverForName(
                name = UIApplicationWillResignActiveNotification,
                null,
                queue = mainQueue()
            ) {
                addScreenShield()
            }

            becomeActiveObserver = NSNotificationCenter.defaultCenter.addObserverForName(
                name = UIApplicationDidBecomeActiveNotification,
                null,
                queue = mainQueue()
            ) {
                removeScreenShield()
            }
        } else {
            // Remove observers and remove screen shield if enable is false.
            resignActiveObserver?.let {
                NSNotificationCenter.defaultCenter.removeObserver(it)
                resignActiveObserver = null
            }

            becomeActiveObserver?.let {
                NSNotificationCenter.defaultCenter.removeObserver(it)
                becomeActiveObserver = null
            }
            removeScreenShield()
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun addScreenShield() {
    // Get the key window of the application.
    val window = UIApplication.sharedApplication.keyWindow()
    if (window != null) {
        // Create a new UIView with the same bounds as the window.
        val shieldView = UIView(frame = window.bounds)
        // Assign a unique tag to identify the shield view.
        shieldView.tag = TAG_SHIELD
        // Set the background color of the shield view to black.
        shieldView.backgroundColor = UIColor.blackColor
        // Disable user interaction with the shield view.
        shieldView.userInteractionEnabled = false
        // Add the shield view as a subview of the window.
        window.addSubview(shieldView)
    }
}

/**
 * Removes the screen shield from the window.
 */
private fun removeScreenShield() {
    // Get the key window of the application.
    val window = UIApplication.sharedApplication.keyWindow
    // Find the shield view using its unique tag.
    val shieldView = window?.viewWithTag(TAG_SHIELD)
    // Remove the shield view from its superview.
    shieldView?.removeFromSuperview()
}

actual fun changeLanguage(appLanguage: LanguageUiType) {
    NSUserDefaults.standardUserDefaults.setObject(arrayListOf(appLanguage.code ?: LanguageUiType.EN.code), "AppleLanguages")
}

@Composable
actual fun is24HourFormat(): Boolean {
    // Get the current locale from iOS.
    val locale = NSLocale.currentLocale
    // Get the date format string using template "j" (time format symbol).
    val dateFormat = NSDateFormatter.dateFormatFromTemplate("j", 0u, locale)
    // If dateFormat contains "a", it uses AM/PM (12-hour). Otherwise, 24-hour.
    return dateFormat?.contains("a")?.not() ?: true
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenWidthDp(): Dp = LocalWindowInfo.current.containerSize.width.pxToPoint().dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenHeightDp(): Dp = LocalWindowInfo.current.containerSize.height.pxToPoint().dp

fun Int.pxToPoint(): Double = this.toDouble() / UIScreen.mainScreen.scale
