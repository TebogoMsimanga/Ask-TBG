package com.example.asktbg.ui.components

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun LiveClockSection(
    modifier: Modifier = Modifier,
    currentTime: LocalTime,
    currentDay: LocalDate
) {
    // Log the input parameters for debugging.
    Log.d("LiveClockSection", "LiveClockSection called with currentTime: $currentTime, currentDay: $currentDay")

    // Create formatters for time and date.
    val timeFormatter = remember { DateTimeFormatter.ofPattern("hh mm") }
    val amPmFormatter = remember { DateTimeFormatter.ofPattern("a") }
    val dayFormatter = remember { DateTimeFormatter.ofPattern("EEEE") }

    // Get the current minute from the LocalTime.
    val currentMinute = currentTime.minute
    // Store the previous minute to detect changes.
    var previousMinute by remember { mutableStateOf(currentMinute) }
    // Store the rotation state for the minute card animation.
    var rotationState by remember { mutableStateOf(0f) }

    // Animate the rotation of the minute card.
    val rotationY by animateFloatAsState(
        targetValue = rotationState,
        animationSpec = tween(durationMillis = 600),
        label = "minuteFlipAnimation"
    )

    // Launch an effect to update the rotation state when the minute changes.
    LaunchedEffect(currentMinute) {
        if (currentMinute != previousMinute) {
            // Log the minute change.
            Log.d("LiveClockSection", "Minute changed from $previousMinute to $currentMinute")

            // Update the rotation state and previous minute.
            rotationState += 360f
            previousMinute = currentMinute
        }
    }

    // Create a Row composable to arrange the hour and minute cards horizontally.
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min), // Set height strategy for children
        horizontalArrangement = Arrangement.Center,
        // Correct Alignment: Use Bottom, Top, or CenterVertically.
        // Stretch is not valid here. Bottom worked well before.
        verticalAlignment = Alignment.Bottom
    ) {
        // Split the formatted time string into hour and minute parts.
        val timeParts = currentTime.format(timeFormatter).split(" ")
        // Get the hour and minute text, providing default values if necessary.
        val hourText = timeParts.getOrElse(0) { "??" }
        val minuteText = timeParts.getOrElse(1) { "??" }

        // Create the hour card.
        ClockCard(
            text = hourText,
            modifier = Modifier
                .weight(1f) // Correct usage: Assign weight
                .fillMaxHeight() // Let the card fill the intrinsic height
        )
        // Add a spacer between the hour and minute cards.
        Spacer(modifier = Modifier.width(12.dp))

        // Create the minute card with subtext.
        ClockCard(
            text = minuteText,
            subTextLine1 = currentTime.format(amPmFormatter),
            subTextLine2 = currentDay.format(dayFormatter).uppercase(),
            modifier = Modifier
                .weight(1f) // Correct usage: Assign weight
                .fillMaxHeight() // Let the card fill the intrinsic height
                .graphicsLayer {
                    // Apply rotation animation to the minute card.
                    this.rotationY = rotationY
                    cameraDistance = 12f * density
                }
        )
    }
}