package com.example.asktbg.ui.mealscreen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.asktbg.R
import com.example.asktbg.model.Meal
import com.example.asktbg.ui.components.MealAppScreenContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MealAppScreenContainer() {
    // Log entry into the composable.
    Log.d("MealAppScreenContainer", "MealAppScreenContainer composable called")

    // State variables for time, date, date picker, suggestions, and meals.
    var currentTime by remember { mutableStateOf(LocalTime.now()) }
    var currentDay by remember { mutableStateOf(LocalDate.now()) }
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showSuggestions by remember { mutableStateOf(false) }
    var mealsToShow by remember { mutableStateOf(emptyList<Meal>()) }

    // Derived state for selected date and time.
    val selectedDateTime = remember(selectedDateMillis) {
        selectedDateMillis?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault())
        } ?: Instant.now().atZone(ZoneId.systemDefault())
    }
    val selectedTime = selectedDateTime.toLocalTime()
    val selectedDate = selectedDateTime.toLocalDate()

    // Derived state for potential meals based on selected time.
    val potentialMeals by remember(selectedTime) {
        derivedStateOf { suggestMealsForTime(selectedTime) }
    }

    // Context and coroutine scope.
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Pager state for meal carousel.
    val pagerState = rememberPagerState(pageCount = { mealsToShow.size })

    // Effect to reset pager if the list shown changes drastically.
    LaunchedEffect(mealsToShow) {
        if (pagerState.currentPage >= mealsToShow.size && mealsToShow.isNotEmpty()) {
            pagerState.animateScrollToPage(0)
        } else if (mealsToShow.isEmpty() && pagerState.currentPage != 0) {
            pagerState.animateScrollToPage(0)
        }
    }

    // Effect to update current time and day every second.
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            currentDay = LocalDate.now()
            delay(1000)
        }
    }

    // Background gradient brush.
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
            MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.9f)
        )
    )

    // Main UI composable.
    Box(modifier = Modifier.fillMaxSize().background(backgroundBrush)) {
        MealAppScreenContent(
            modifier = Modifier.padding(16.dp),
            currentTime = currentTime,
            currentDay = currentDay,
            selectedDate = selectedDate,
            selectedTime = selectedTime,
            mealsToShow = mealsToShow,
            showSuggestions = showSuggestions,
            pagerState = pagerState,
            scope = scope,
            onDateSelectClick = {
                Log.d("MealAppScreenContainer", "Date picker triggered")
                showDatePicker = true
            },
            onSuggestClick = {
                Log.d("MealAppScreenContainer", "Suggest meals clicked")
                mealsToShow = potentialMeals
                showSuggestions = true
                scope.launch { pagerState.animateScrollToPage(0) }
                Toast.makeText(context, "Suggesting meals!", Toast.LENGTH_SHORT).show()
            },
            onResetClick = {
                Log.d("MealAppScreenContainer", "Reset clicked")
                selectedDateMillis = null
                showSuggestions = false
                mealsToShow = emptyList()
                Toast.makeText(context, "Selection Reset", Toast.LENGTH_SHORT).show()
            },
            onShareClick = { mealToShare ->
                Log.d("MealAppScreenContainer", "Share clicked for meal: $mealToShare")
                shareMeal(context, mealToShare, selectedTime)
            }
        )
    }

    // Date picker dialog.
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDateMillis ?: System.currentTimeMillis()
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = {
                    val newlySelectedMillis = datePickerState.selectedDateMillis
                    selectedDateMillis = newlySelectedMillis
                    showSuggestions = false
                    mealsToShow = emptyList()
                    showDatePicker = false
                    Toast.makeText(context, "Date Selected. Press 'Suggest Meal'.", Toast.LENGTH_LONG).show()
                }) { Text("OK") }
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

// Helper function to suggest meals based on time.
fun suggestMealsForTime(time: LocalTime): List<Meal> {
    Log.d("MealAppScreenContainer", "Suggesting meals for time: $time")
    val placeholder = R.drawable.ic_launcher_foreground

    val breakfastMeals = listOf(
        Meal("Pancakes", "Fluffy pancakes with syrup", R.drawable.pizza),
        Meal("Oatmeal", "Healthy oats with fruits", R.drawable.jungle_oats)
    )
    val lunchMeals = listOf(
        Meal("Chicken Salad", "Grilled chicken breast salad", R.drawable.salad),
        Meal("Veggie Wrap", "Hummus and veggie wrap", R.drawable.fruit_salad)
    )
    val dinnerMeals = listOf(
        Meal("Spaghetti Bolognese", "Rich meat sauce pasta", R.drawable.pasta),
        Meal("Salmon & Asparagus", "Baked salmon", R.drawable.sandwich)
    )

    return when (time.hour) {
        in 5..10 -> breakfastMeals
        in 11..15 -> lunchMeals
        in 16..21 -> dinnerMeals
        else -> lunchMeals
    }.also {
        Log.d("MealAppScreenContainer", "Suggested meals: $it")
    }
}

// Helper function to share a meal.
fun shareMeal(context: Context, meal: Meal, time: LocalTime) {
    Log.d("MealAppScreenContainer", "Sharing meal: $meal at time: $time")
    val timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
    val shareText = "How about having ${meal.name} around ${time.format(timeFormatter)}? Sounds delicious!"

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Meal Suggestion")
        putExtra(Intent.EXTRA_TEXT, shareText)
    }
    try {
        context.startActivity(Intent.createChooser(intent, "Share Meal Suggestion"))
    } catch (e: Exception) {
        Log.e("MealAppScreenContainer", "Error sharing meal: ${e.message}")
        Toast.makeText(context, "Cannot share: No app found.", Toast.LENGTH_SHORT).show()
    }
}