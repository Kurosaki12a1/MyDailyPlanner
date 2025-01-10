package com.kuro.mdp.shared.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue.Companion.mainQueue
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDidBecomeActiveNotification
import platform.UIKit.UIApplicationWillResignActiveNotification
import platform.UIKit.UIColor
import platform.UIKit.UIDevice
import platform.UIKit.UIView
import platform.darwin.NSObjectProtocol

// Returns the current platform of the device.
actual fun getPlatform(): DevicePlatform = DevicePlatform.IOS

// Returns a string containing the device's system name and version.
actual fun getDeviceInfo(): String = "${UIDevice.currentDevice.systemName()} ${UIDevice.currentDevice.systemVersion}"

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