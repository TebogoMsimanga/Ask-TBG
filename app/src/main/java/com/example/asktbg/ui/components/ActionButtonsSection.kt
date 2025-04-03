package com.example.asktbg.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.asktbg.model.Meal

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActionButtonsSection(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    mealsToShow: List<Meal>, // Renamed parameter
    onShareClick: (Meal) -> Unit,
    onResetClick: () -> Unit
) {
    // Obtain the current context for displaying Toast messages.
    val context = LocalContext.current

    // Log the current pager state page for debugging purposes using Log.d.
    Log.d("ActionButtonsSection", "Current pager page is ${pagerState.currentPage}")

    // Log the number of meals to show for debugging.
    Log.d("ActionButtonsSection", "Number of meals to show: ${mealsToShow.size}")

    // Create a Row composable to arrange the action buttons horizontally.
    Row(
        modifier = modifier.fillMaxWidth(), // Fill the available width.
        horizontalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between the buttons.
    ) {
        // Create the "Share" button using the GlassButton composable.
        GlassButton(
            onClick = {
                // Log the click event for debugging using Log.d.
                Log.d("ActionButtonsSection", "Share button clicked")

                // Check if there are meals to show and if the current page is within the valid range.
                if (mealsToShow.isNotEmpty() && pagerState.currentPage < mealsToShow.size) {
                    // If valid, call the onShareClick lambda with the meal at the current page.
                    onShareClick(mealsToShow[pagerState.currentPage])
                    // Log the meal being shared.
                    Log.d("ActionButtonsSection", "Sharing meal: ${mealsToShow[pagerState.currentPage]}")
                } else {
                    // If no meal is available or the page is out of range, display a Toast message.
                    Toast.makeText(context, "No meal suggested to share", Toast.LENGTH_SHORT).show()
                    // Log that no meal was available to share.
                    Log.d("ActionButtonsSection", "No meal available to share")
                }
            },
            text = "Share", // Set the button text.
            modifier = Modifier.weight(1f), // Distribute the available width evenly.
            gradient = Brush.horizontalGradient(listOf(Color(0xAA00E676), Color(0xAA69F0AE))) // Set the button gradient.
        )
        // Create the "Reset" button using the GlassButton composable.
        GlassButton(
            onClick = {
                // Log the click event for debugging using Log.d.
                Log.d("ActionButtonsSection", "Reset button clicked")
                // Call the onResetClick lambda.
                onResetClick()
            },
            text = "Reset", // Set the button text.
            modifier = Modifier.weight(1f), // Distribute the available width evenly.
            gradient = Brush.horizontalGradient(listOf(Color(0xAAFF5252), Color(0xAAFF8A80))) // Set the button gradient.
        )
    }
}