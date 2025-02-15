package com.kuro.mdp.shared.presentation.extensions

import androidx.annotation.FloatRange
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.basicMarquee
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.node.Ref
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.LayoutDirection
import com.kuro.mdp.shared.presentation.model.Fade
import com.kuro.mdp.shared.presentation.model.PlaceHolderHighlight
import com.kuro.mdp.shared.presentation.model.Shimmer
import com.kuro.mdp.shared.utils.functional.Constants.Delay.MARQUEE_LOADING_TEXT
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
fun Modifier.alphaByEnabled(enabled: Boolean, disabledAlpha: Float = 0.6F): Modifier {
    val value by animateFloatAsState(
        targetValue = if (enabled) 1f else disabledAlpha,
        animationSpec = tween(),
    )
    return alpha(alpha = value)
}

/**
 * Draws some skeleton UI which is typically used whilst content is 'loading'.
 *
 * A version of this modifier which uses appropriate values for Material themed apps is available
 * in the 'Placeholder Material' library.
 *
 * You can provide a [PlaceHolderHighlight] which runs an highlight animation on the placeholder.
 * The [shimmer] and [fade] implementations are provided for easy usage.
 *
 * A cross-fade transition will be applied to the content and placeholder UI when the [visible]
 * value changes. The transition can be customized via the [contentFadeTransitionSpec] and
 * [placeholderFadeTransitionSpec] parameters.
 *
 * You can find more information on the pattern at the Material Theming
 * [Placeholder UI](https://material.io/design/communication/launch-screen.html#placeholder-ui)
 * guidelines.
 *
 * @param visible whether the placeholder should be visible or not.
 * @param color the color used to draw the placeholder UI.
 * @param shape desired shape of the placeholder. Defaults to [RectangleShape].
 * @param highlight optional highlight animation.
 * @param placeholderFadeTransitionSpec The transition spec to use when fading the placeholder
 * on/off screen. The boolean parameter defined for the transition is [visible].
 * @param contentFadeTransitionSpec The transition spec to use when fading the content
 * on/off screen. The boolean parameter defined for the transition is [visible].
 */
fun Modifier.placeholder(
    visible: Boolean,
    color: Color,
    shape: Shape = RectangleShape,
    highlight: PlaceHolderHighlight? = null,
    placeholderFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
    contentFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "placeholder"
        value = visible
        properties["visible"] = visible
        properties["color"] = color
        properties["highlight"] = highlight
        properties["shape"] = shape
    }
) {
    // Values used for caching purposes
    val lastSize = remember { Ref<Size>() }
    val lastLayoutDirection = remember { Ref<LayoutDirection>() }
    val lastOutline = remember { Ref<Outline>() }

    // The current highlight animation progress
    var highlightProgress: Float by remember { mutableFloatStateOf(0f) }

    // This is our crossfade transition
    val transitionState = remember { MutableTransitionState(visible) }.apply {
        targetState = visible
    }
    val transition = rememberTransition(transitionState, "placeholder_crossfade")

    val placeholderAlpha by transition.animateFloat(
        transitionSpec = placeholderFadeTransitionSpec,
        label = "placeholder_fade",
        targetValueByState = { placeholderVisible -> if (placeholderVisible) 1f else 0f }
    )
    val contentAlpha by transition.animateFloat(
        transitionSpec = contentFadeTransitionSpec,
        label = "content_fade",
        targetValueByState = { placeholderVisible -> if (placeholderVisible) 0f else 1f }
    )

    // Run the optional animation spec and update the progress if the placeholder is visible
    val animationSpec = highlight?.animationSpec
    if (animationSpec != null && (visible || placeholderAlpha >= 0.01f)) {
        val infiniteTransition = rememberInfiniteTransition(label = "")
        highlightProgress = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = animationSpec,
            label = "",
        ).value
    }

    val paint = remember { Paint() }
    remember(color, shape, highlight) {
        drawWithContent {
            // Draw the composable content first
            if (contentAlpha in 0.01f..0.99f) {
                // If the content alpha is between 1% and 99%, draw it in a layer with
                // the alpha applied
                paint.alpha = contentAlpha
                withLayer(paint) {
                    with(this@drawWithContent) {
                        drawContent()
                    }
                }
            } else if (contentAlpha >= 0.99f) {
                // If the content alpha is > 99%, draw it with no alpha
                drawContent()
            }

            if (placeholderAlpha in 0.01f..0.99f) {
                // If the placeholder alpha is between 1% and 99%, draw it in a layer with
                // the alpha applied
                paint.alpha = placeholderAlpha
                withLayer(paint) {
                    lastOutline.value = drawPlaceholder(
                        shape = shape,
                        color = color,
                        highlight = highlight,
                        progress = highlightProgress,
                        lastOutline = lastOutline.value,
                        lastLayoutDirection = lastLayoutDirection.value,
                        lastSize = lastSize.value,
                    )
                }
            } else if (placeholderAlpha >= 0.99f) {
                // If the placeholder alpha is > 99%, draw it with no alpha
                lastOutline.value = drawPlaceholder(
                    shape = shape,
                    color = color,
                    highlight = highlight,
                    progress = highlightProgress,
                    lastOutline = lastOutline.value,
                    lastLayoutDirection = lastLayoutDirection.value,
                    lastSize = lastSize.value,
                )
            }

            // Keep track of the last size & layout direction
            lastSize.value = size
            lastLayoutDirection.value = layoutDirection
        }
    }
}

private fun DrawScope.drawPlaceholder(
    shape: Shape,
    color: Color,
    highlight: PlaceHolderHighlight?,
    progress: Float,
    lastOutline: Outline?,
    lastLayoutDirection: LayoutDirection?,
    lastSize: Size?,
): Outline? {
    // shortcut to avoid Outline calculation and allocation
    if (shape === RectangleShape) {
        // Draw the initial background color
        drawRect(color = color)

        if (highlight != null) {
            drawRect(
                brush = highlight.brush(progress, size),
                alpha = highlight.alpha(progress),
            )
        }
        // We didn't create an outline so return null
        return null
    }

    // Otherwise we need to create an outline from the shape
    val outline = lastOutline.takeIf {
        size == lastSize && layoutDirection == lastLayoutDirection
    } ?: shape.createOutline(size, layoutDirection, this)

    // Draw the placeholder color
    drawOutline(outline = outline, color = color)

    if (highlight != null) {
        drawOutline(
            outline = outline,
            brush = highlight.brush(progress, size),
            alpha = highlight.alpha(progress),
        )
    }

    // Return the outline we used
    return outline
}

private inline fun DrawScope.withLayer(
    paint: Paint,
    drawBlock: DrawScope.() -> Unit,
) = drawIntoCanvas { canvas ->
    canvas.saveLayer(size.toRect(), paint)
    drawBlock()
    canvas.restore()
}

/**
 * Creates a [Fade] brush with the given initial and target colors.
 *
 * @param highlightColor the color of the highlight which is faded in/out.
 * @param animationSpec the [AnimationSpec] to configure the animation.
 */
fun PlaceHolderHighlight.Companion.fade(
    highlightColor: Color,
    animationSpec: InfiniteRepeatableSpec<Float> = com.kuro.mdp.shared.presentation.model.PlaceHolderDefaults.fadeAnimationSpec,
): PlaceHolderHighlight = Fade(
    highlightColor = highlightColor,
    animationSpec = animationSpec,
)


/**
 * Creates a [PlaceHolderHighlight] which 'shimmers', using the given [highlightColor].
 *
 * The highlight starts at the top-start, and then grows to the bottom-end during the animation.
 * During that time it is also faded in, from 0f..progressForMaxAlpha, and then faded out from
 * progressForMaxAlpha..1f.
 *
 * @param highlightColor the color of the highlight 'shimmer'.
 * @param animationSpec the [animationSpec] to configure the animation.
 * @param progressForMaxAlpha The progress where the shimmer should be at it's peak opacity.
 * Defaults to 0.6f.
 */
fun PlaceHolderHighlight.Companion.shimmer(
    highlightColor: Color,
    animationSpec: InfiniteRepeatableSpec<Float> = com.kuro.mdp.shared.presentation.model.PlaceHolderDefaults.shimmerAnimationSpec,
    @FloatRange(from = 0.0, to = 1.0) progressForMaxAlpha: Float = 0.6f,
): PlaceHolderHighlight = Shimmer(
    highlightColor = highlightColor,
    animationSpec = animationSpec,
    progressForMaxAlpha = progressForMaxAlpha,
)

/**
 * This function creates a [Modifier] that enables scrolling text for a Composable element.
 *
 * The `scrollText` modifier uses the `basicMarquee` function to create a marquee effect, which is
 * typically used to display text that scrolls continuously across the screen. This can be useful for
 * displaying long pieces of text in a limited space or for drawing attention to important information.
 *
 * The `iterations` parameter is set to `Int.MAX_VALUE`, which means the scrolling effect will repeat
 * indefinitely. The `repeatDelayMillis` parameter is set to `MARQUEE_LOADING_TEXT`, which likely
 * represents the delay between each iteration of the scrolling effect.
 *
 * Usage example:
 * ```
 * @Composable
 * fun ScrollingText() {
 *     Text(
 *         text = "This is a scrolling text example",
 *         modifier = Modifier.scrollText()
 *     )
 * }
 * ```
 *
 * In this example, the `Text` Composable will have its text scroll continuously across the screen.
 */
@Composable
fun Modifier.scrollText(): Modifier = this.basicMarquee(
    iterations = Int.MAX_VALUE,
    repeatDelayMillis = MARQUEE_LOADING_TEXT
)

/**
 * Updates the current [TextFieldValue] by processing a new input value to form a two-digit number.
 *
 * <p>This function extracts digits from the [newValue] text, limits it to 2 digits, and then
 * constructs a new two-digit string based on the current cursor position and the previous text.
 * It also checks whether the resulting number is within the allowed [restrict] range. If the input
 * operation reaches the limit (i.e. when the cursor is at the end and the value remains unchanged),
 * the optional [onLimit] callback is invoked with the last character of [newValue].
 *
 * @param newValue The new input [TextFieldValue].
 * @param restrict The allowed integer range for the final two-digit number.
 * @param onLimit Optional callback invoked when the limit condition is met.
 * @return A new [TextFieldValue] with the updated text and cursor position if within [restrict];
 *         otherwise, returns the original [TextFieldValue].
 */
fun TextFieldValue.changeTwoDigitNumber(
    newValue: TextFieldValue,
    restrict: IntRange = Int.MIN_VALUE..Int.MAX_VALUE,
    onLimit: ((Char) -> Unit)? = null,
): TextFieldValue {
    // Get the current cursor position (minimum index of the selection range)
    val cursor = selection.min
    val oldText = text
    // Extract up to 2 digits from the new input value
    val newText = newValue.text.filter { it.isDigit() }.take(2)
    // Compute the final text based on the length of newText vs oldText and the cursor position.
    val finalText = if (newText.length < oldText.length) {
        // Case: Deletion occurred
        if (cursor == 2) {
            // If the cursor is at the end, append "0" to maintain two digits.
            newText + "0"
        } else {
            // Otherwise, pad the string to ensure it has 2 digits.
            newText.padStart(2, '0')
        }
    } else {
        // Case: Addition occurred
        if (cursor == 0) {
            // If cursor is at start, keep the first digit from newText and last digit from oldText.
            newText.first().toString() + oldText.last().toString()
        } else {
            // Otherwise, pad the string to ensure it has 2 digits.
            newText.padStart(2, '0')
        }
    }

    // Determine the new cursor position ensuring it does not exceed finalText length.
    val newCursor = minOf(newValue.selection.start, finalText.length)
    // If cursor remains at the end and no change is observed, invoke the onLimit callback.
    if (cursor == 2 && newCursor == 2 && newText == finalText) {
        onLimit?.invoke(newValue.text.last())
    }
    return if (finalText.toInt() in restrict) {
        TextFieldValue(text = finalText, selection = TextRange(newCursor))
    } else {
        this@changeTwoDigitNumber
    }
}

/**
 * Transitions the [TextFieldValue] by prepending a character and adjusting focus accordingly.
 *
 * <p>This function requests focus via the provided [requester], then constructs a new text by
 * prepending the given [char] to the last character of the current text (if any). It checks whether
 * the resulting integer (converted from the new text) is within the allowed [restrict] range. If it
 * is within range, it updates the selection to position 1; otherwise, it resets the selection to 0.
 *
 * @param char The character to be added at the beginning of the text.
 * @param restrict The allowed integer range for the new text.
 * @param requester The [FocusRequester] used to request focus on the input field.
 * @return A new [TextFieldValue] reflecting the change, or the original value with adjusted selection.
 */
fun TextFieldValue.endLimitCharTransition(
    char: Char,
    restrict: IntRange = Int.MIN_VALUE..Int.MAX_VALUE,
    requester: FocusRequester,
): TextFieldValue {
    // Request focus to ensure the TextField is active.
    requester.requestFocus()
    // Construct new text by concatenating the new character and the last character of the current text (if available)
    val newText = char.toString() + (text.lastOrNull()?.toString() ?: "")
    // Validate the new text by converting to integer and checking against the allowed range.
    return if (newText.toIntOrNull() in restrict) {
        // If within range, set selection to the end of the new text.
        TextFieldValue(
            text = newText,
            selection = TextRange(1),
        )
    } else {
        // If out of range, revert selection to the beginning.
        TextFieldValue(
            text = text,
            selection = TextRange(0),
        )
    }
}

@Composable
fun StringResource.string() = stringResource(this)
