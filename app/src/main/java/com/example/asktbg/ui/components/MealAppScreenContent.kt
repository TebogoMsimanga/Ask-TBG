package com.example.asktbg.ui.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.asktbg.model.Meal
import kotlinx.coroutines.CoroutineScope
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealAppScreenContent(
    modifier: Modifier = Modifier,
    currentTime: LocalTime,
    currentDay: LocalDate,
    selectedDate: LocalDate,
    selectedTime: LocalTime,
    mealsToShow: List<Meal>, // New parameter
    showSuggestions: Boolean, // New parameter
    pagerState: PagerState,
    scope: CoroutineScope,
    onDateSelectClick: () -> Unit,
    onSuggestClick: () -> Unit, // New parameter
    onResetClick: () -> Unit,
    onShareClick: (Meal) -> Unit
) {
    // Log the input parameters for debugging.
    Log.d("MealAppScreenContent", "MealAppScreenContent called with currentTime: $currentTime, currentDay: $currentDay, selectedDate: $selectedDate, selectedTime: $selectedTime, showSuggestions: $showSuggestions")

    // Create a Column composable to arrange the content vertically.
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        // Changed arrangement: Let weight push buttons down
        verticalArrangement = Arrangement.Top // Align top content first
    ) {
        // --- Top Fixed Content ---
        // Display the Logo Section
        LogoSection()
        // Add a spacer for vertical spacing.
        Spacer(modifier = Modifier.height(20.dp)) // Slightly less space
        // Display the Live Clock Section.
        LiveClockSection(currentTime = currentTime, currentDay = currentDay)
        // Add a spacer for vertical spacing.
        Spacer(modifier = Modifier.height(20.dp))
        // Display the Time Selection Section.
        TimeSelectionSection(
            selectedDate = selectedDate,
            selectedTime = selectedTime,
            onDateSelectClick = onDateSelectClick
        )
        // Add a spacer for vertical spacing.
        Spacer(modifier = Modifier.height(16.dp))

        // --- New Suggest Button ---
        // Display the Suggest Meal Button.
        Button(
            onClick = {
                // Log the click event of the Suggest button.
                Log.d("MealAppScreenContent", "Suggest Meal button clicked")
                onSuggestClick()
            },
            modifier = Modifier.fillMaxWidth(0.7f), // Make it reasonably wide
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9C27B0) // Purple color
            )
        ) {
            // Display the text "Suggest Meal".
            Text("Suggest Meal", color = Color.White)
        }
        // Add a spacer for vertical spacing.
        Spacer(modifier = Modifier.height(16.dp))

        // --- Carousel Section (Takes remaining space) ---
        // Only show the carousel if suggestions are explicitly shown
        if (showSuggestions) {
            // Display the Meal Carousel Section.
            MealCarouselSection(
                // Apply weight modifier here to make this section expand
                modifier = Modifier.weight(1f, fill = true),
                mealsToShow = mealsToShow, // Pass the list to show
                pagerState = pagerState,
                scope = scope
            )
        } else {
            // Placeholder when suggestions are hidden
            // Display a text placeholder when suggestions are hidden.
            Box(modifier = Modifier.weight(1f, fill = true), contentAlignment = Alignment.Center) {
                Text("Select a date and press 'Suggest Meal'", textAlign = TextAlign.Center)
            }
        }

        // --- Bottom Buttons (Pushed down by the weighted Spacer/Carousel) ---
        // Add a spacer for vertical spacing before the bottom buttons.
        Spacer(modifier = Modifier.height(24.dp)) // Space before bottom buttons
        // Display the Action Buttons Section.
        ActionButtonsSection(
            pagerState = pagerState,
            mealsToShow = mealsToShow, // Pass the list being shown
            onShareClick = { meal ->
                // Log the share click event.
                Log.d("MealAppScreenContent", "Share button clicked for meal: $meal")
                onShareClick(meal)
            },
            onResetClick = {
                // Log the reset click event.
                Log.d("MealAppScreenContent", "Reset button clicked")
                onResetClick()
            },
            modifier = Modifier.padding(bottom = 8.dp) // Less bottom padding maybe
        )
    }
}