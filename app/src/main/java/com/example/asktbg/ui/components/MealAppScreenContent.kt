package com.example.asktbg.ui.components

import android.os.Build // For API version checking [20].
import android.util.Log // For logging during composable rendering [21].
import androidx.annotation.RequiresApi // Ensures the API level is appropriate for specific functionality [20].
import androidx.compose.foundation.ExperimentalFoundationApi // Opting into experimental composables [22].
import androidx.compose.foundation.layout.Arrangement // Controls alignment and spacing in layouts [22].
import androidx.compose.foundation.layout.Box // Used for positioning content in a container [22].
import androidx.compose.foundation.layout.Column // Arranges children vertically [22].
import androidx.compose.foundation.layout.Spacer // Adds space between UI elements [22].
import androidx.compose.foundation.layout.fillMaxSize // Fills the available space in a layout [22].
import androidx.compose.foundation.layout.fillMaxWidth // Fills the width of the container [22].
import androidx.compose.foundation.layout.height // Defines fixed height for a composable [22].
import androidx.compose.foundation.layout.padding // Adds padding around a composable [22].
import androidx.compose.foundation.pager.PagerState // State for handling pager behavior [22].
import androidx.compose.foundation.shape.RoundedCornerShape // Creates rounded corners for UI elements [22].
import androidx.compose.material3.Button // Material 3 button implementation [23].
import androidx.compose.material3.ButtonDefaults // Defaults for button styling in Material 3 [23].
import androidx.compose.material3.Text // Displays text with styling [23].
import androidx.compose.runtime.Composable // Marks the function as composable [22].
import androidx.compose.ui.Alignment // Aligns content within a container [22].
import androidx.compose.ui.Modifier // Allows for chaining layout modifications [22].
import androidx.compose.ui.graphics.Color // Defines color for UI elements [22].
import androidx.compose.ui.text.style.TextAlign // Defines text alignment [22].
import androidx.compose.ui.unit.dp // Unit of measurement for UI spacing [22].
import com.example.asktbg.model.Meal // Meal data model for rendering [24].
import kotlinx.coroutines.CoroutineScope // Defines scope for managing coroutines [25].
import java.time.LocalDate // Represents a date without time [26].
import java.time.LocalTime // Represents a time without date [26].

@RequiresApi(Build.VERSION_CODES.O) // Ensures the function is only called on supported API versions [20].
@OptIn(ExperimentalFoundationApi::class) // Opt-in to experimental features in Jetpack Compose [22].
@Composable
fun MealAppScreenContent(
    modifier: Modifier = Modifier,
    currentTime: LocalTime, // The current time [26].
    currentDay: LocalDate, // The current day [26].
    selectedDate: LocalDate, // Date selected by the user [26].
    selectedTime: LocalTime, // Time selected by the user [26].
    mealsToShow: List<Meal>, // List of meals to display [24].
    showSuggestions: Boolean, // Flag to show meal suggestions [24].
    pagerState: PagerState, // State for controlling pager [22].
    scope: CoroutineScope, // Coroutine scope for managing side-effects [25].
    onDateSelectClick: () -> Unit, // Callback for when the date is selected [22].
    onSuggestClick: () -> Unit, // Callback for when the suggest button is clicked [22].
    onResetClick: () -> Unit, // Callback for when reset button is clicked [22].
    onShareClick: (Meal) -> Unit // Callback for sharing a selected meal [24].
) {
    // Log the input parameters for debugging [21].
    Log.d("MealAppScreenContent", "MealAppScreenContent called with currentTime: $currentTime, currentDay: $currentDay, selectedDate: $selectedDate, selectedTime: $selectedTime, showSuggestions: $showSuggestions")

    // Create a Column composable to arrange the content vertically [22].
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, // Centers the content horizontally [22].
        verticalArrangement = Arrangement.Top // Align top content first [22].
    ) {
        // --- Top Fixed Content ---
        LogoSection() // Display the logo section [22].
        Spacer(modifier = Modifier.height(20.dp)) // Add vertical spacing [22].
        LiveClockSection(currentTime = currentTime, currentDay = currentDay) // Display live clock section [22].
        Spacer(modifier = Modifier.height(20.dp)) // Add vertical spacing [22].
        TimeSelectionSection(
            selectedDate = selectedDate,
            selectedTime = selectedTime,
            onDateSelectClick = onDateSelectClick
        ) // Display time selection section [22].
        Spacer(modifier = Modifier.height(16.dp)) // Add vertical spacing [22].

        // --- New Suggest Button ---
        Button(
            onClick = {
                Log.d("MealAppScreenContent", "Suggest Meal button clicked") // Log the click event [21].
                onSuggestClick() // Call the suggest meal function [22].
            },
            modifier = Modifier.fillMaxWidth(0.7f), // Set the width of the button [22].
            shape = RoundedCornerShape(12.dp), // Round the corners of the button [22].
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9C27B0) // Set button color [23].
            )
        ) {
            Text("Suggest Meal", color = Color.White) // Display the button text [23].
        }
        Spacer(modifier = Modifier.height(16.dp)) // Add vertical spacing [22].

        // --- Carousel Section (Takes remaining space) ---
        if (showSuggestions) {
            MealCarouselSection(
                modifier = Modifier.weight(1f, fill = true), // Use remaining space [22].
                mealsToShow = mealsToShow, // Pass the meals to display [24].
                pagerState = pagerState, // Pass the pager state [22].
                scope = scope // Pass the coroutine scope [25].
            )
        } else {
            // Display placeholder if no suggestions [22].
            Box(modifier = Modifier.weight(1f, fill = true), contentAlignment = Alignment.Center) {
                Text("Select a date and press 'Suggest Meal'", textAlign = TextAlign.Center) // Placeholder text [22].
            }
        }

        // --- Bottom Buttons ---
        Spacer(modifier = Modifier.height(24.dp)) // Space before bottom buttons [22].
        ActionButtonsSection(
            pagerState = pagerState, // Pass pager state [22].
            mealsToShow = mealsToShow, // Pass meals to share [24].
            onShareClick = { meal ->
                Log.d("MealAppScreenContent", "Share button clicked for meal: $meal") // Log share click [21].
                onShareClick(meal) // Call the share function [24].
            },
            onResetClick = {
                Log.d("MealAppScreenContent", "Reset button clicked") // Log reset click [21].
                onResetClick() // Call the reset function [22].
            },
            modifier = Modifier.padding(bottom = 8.dp) // Add bottom padding [22].
        )
    }
}