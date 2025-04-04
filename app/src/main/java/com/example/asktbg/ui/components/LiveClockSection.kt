package com.example.asktbg.ui.components

import android.util.Log // For debug logging [13].
import androidx.compose.animation.core.animateFloatAsState // For smooth animation handling [14].
import androidx.compose.animation.core.tween // Animation specs [14].
import androidx.compose.foundation.layout.* // Layout-related Composables [15].
import androidx.compose.runtime.* // Composable state handling [15].
import androidx.compose.ui.Alignment // Alignment options for layouts [15].
import androidx.compose.ui.Modifier // Modifier chain for styling [15].
import androidx.compose.ui.graphics.graphicsLayer // For applying transformations like rotation [14].
import androidx.compose.ui.unit.dp // Density-independent pixels [15].
import java.time.LocalDate // Java 8+ time API for dates [16].
import java.time.LocalTime // Java 8+ time API for time [16].
import java.time.format.DateTimeFormatter // For formatting date/time output [16].

@Composable
fun LiveClockSection(
    modifier: Modifier = Modifier,
    currentTime: LocalTime,
    currentDay: LocalDate
) {
    // Log the input parameters for debugging [13].
    Log.d("LiveClockSection", "LiveClockSection called with currentTime: $currentTime, currentDay: $currentDay")

    // Create formatters for displaying hour-minute, AM/PM, and weekday [16].
    val timeFormatter = remember { DateTimeFormatter.ofPattern("hh mm") }
    val amPmFormatter = remember { DateTimeFormatter.ofPattern("a") }
    val dayFormatter = remember { DateTimeFormatter.ofPattern("EEEE") }

    // Track minute changes for triggering animations.
    val currentMinute = currentTime.minute
    var previousMinute by remember { mutableStateOf(currentMinute) }
    var rotationState by remember { mutableStateOf(0f) }

    // Animate the rotation value for the minute card [14].
    val rotationY by animateFloatAsState(
        targetValue = rotationState,
        animationSpec = tween(durationMillis = 600),
        label = "minuteFlipAnimation"
    )

    // When the minute updates, increment rotation to animate the flip [14].
    LaunchedEffect(currentMinute) {
        if (currentMinute != previousMinute) {
            Log.d("LiveClockSection", "Minute changed from $previousMinute to $currentMinute") // [13]
            rotationState += 360f
            previousMinute = currentMinute
        }
    }

    // Row layout: places hour and minute cards side-by-side [15].
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        // Extract hour and minute parts from formatted time.
        val timeParts = currentTime.format(timeFormatter).split(" ")
        val hourText = timeParts.getOrElse(0) { "??" }
        val minuteText = timeParts.getOrElse(1) { "??" }

        // Left: Hour card [15].
        ClockCard(
            text = hourText,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        Spacer(modifier = Modifier.width(12.dp)) // Spacer between cards [15].

        // Right: Minute card with animated flip effect [14].
        ClockCard(
            text = minuteText,
            subTextLine1 = currentTime.format(amPmFormatter),
            subTextLine2 = currentDay.format(dayFormatter).uppercase(),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .graphicsLayer {
                    this.rotationY = rotationY
                    cameraDistance = 12f * density // Improves 3D perspective
                }
        )
    }
}