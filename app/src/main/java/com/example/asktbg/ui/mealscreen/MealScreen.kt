package com.example.asktbg.ui.mealscreen

import android.content.Context // [52]
import android.content.Intent // [52]
import android.os.Build // [52]
import android.util.Log // [52]
import android.widget.Toast // [52]
import androidx.annotation.RequiresApi // [53]
import androidx.compose.foundation.ExperimentalFoundationApi // [54]
import androidx.compose.foundation.background // [54]
import androidx.compose.foundation.layout.Box // [55]
import androidx.compose.foundation.layout.fillMaxSize // [55]
import androidx.compose.foundation.layout.padding // [55]
import androidx.compose.foundation.pager.rememberPagerState // [56]
import androidx.compose.material3.Button // [57]
import androidx.compose.material3.DatePicker // [57]
import androidx.compose.material3.DatePickerDialog // [57]
import androidx.compose.material3.ExperimentalMaterial3Api // [57]
import androidx.compose.material3.MaterialTheme // [57]
import androidx.compose.material3.Text // [57]
import androidx.compose.material3.rememberDatePickerState // [57]
import androidx.compose.runtime.Composable // [58]
import androidx.compose.runtime.LaunchedEffect // [58]
import androidx.compose.runtime.derivedStateOf // [58]
import androidx.compose.runtime.getValue // [58]
import androidx.compose.runtime.mutableStateOf // [58]
import androidx.compose.runtime.remember // [58]
import androidx.compose.runtime.rememberCoroutineScope // [58]
import androidx.compose.runtime.setValue // [58]
import androidx.compose.ui.Modifier // [59]
import androidx.compose.ui.graphics.Brush // [60]
import androidx.compose.ui.platform.LocalContext // [61]
import androidx.compose.ui.unit.dp // [62]
import com.example.asktbg.R // [63]
import com.example.asktbg.model.Meal // [64]
import com.example.asktbg.ui.components.MealAppScreenContent // [65]
import kotlinx.coroutines.delay // [66]
import kotlinx.coroutines.launch // [66]
import java.time.Instant // [67]
import java.time.LocalDate // [67]
import java.time.LocalTime // [67]
import java.time.ZoneId // [67]
import java.time.format.DateTimeFormatter // [67]
import java.time.format.FormatStyle // [67]

@RequiresApi(Build.VERSION_CODES.O) // [53]
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class) // [54, 57]
@Composable // [58]
fun MealAppScreenContainer() {
    // Log entry into the composable.
    Log.d("MealAppScreenContainer", "MealAppScreenContainer composable called") // [52]

    // State variables for time, date, date picker, suggestions, and meals.
    var currentTime by remember { mutableStateOf(LocalTime.now()) } // [58, 67]
    var currentDay by remember { mutableStateOf(LocalDate.now()) } // [58, 67]
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) } // [58]
    var showDatePicker by remember { mutableStateOf(false) } // [58]
    var showSuggestions by remember { mutableStateOf(false) } // [58]
    var mealsToShow by remember { mutableStateOf(emptyList<Meal>()) } // [58, 64]

    // Derived state for selected date and time.
    val selectedDateTime = remember(selectedDateMillis) { // [58]
        selectedDateMillis?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()) // [67]
        } ?: Instant.now().atZone(ZoneId.systemDefault()) // [67]
    }
    val selectedTime = selectedDateTime.toLocalTime() // [67]
    val selectedDate = selectedDateTime.toLocalDate() // [67]

    // Derived state for potential meals based on selected time.
    val potentialMeals by remember(selectedTime) { // [58]
        derivedStateOf { suggestMealsForTime(selectedTime) } // [58, 67]
    }

    // Context and coroutine scope.
    val context = LocalContext.current // [61]
    val scope = rememberCoroutineScope() // [58]

    // Pager state for meal carousel.
    val pagerState = rememberPagerState(pageCount = { mealsToShow.size }) // [56, 58]

    // Effect to reset pager if the list shown changes drastically.
    LaunchedEffect(mealsToShow) { // [58]
        if (pagerState.currentPage >= mealsToShow.size && mealsToShow.isNotEmpty()) { // [56]
            pagerState.animateScrollToPage(0) // [56]
        } else if (mealsToShow.isEmpty() && pagerState.currentPage != 0) { // [56]
            pagerState.animateScrollToPage(0) // [56]
        }
    }

    // Effect to update current time and day every second.
    LaunchedEffect(Unit) { // [58]
        while (true) {
            currentTime = LocalTime.now() // [67]
            currentDay = LocalDate.now() // [67]
            delay(1000) // [66]
        }
    }

    // Background gradient brush.
    val backgroundBrush = Brush.verticalGradient( // [60]
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f), // [57]
            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f), // [57]
            MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.9f) // [57]
        )
    )

    // Main UI composable.
    Box(modifier = Modifier.fillMaxSize().background(backgroundBrush)) { // [59, 55, 54, 60]
        MealAppScreenContent( // [65]
            modifier = Modifier.padding(16.dp), // [59, 55, 62]
            currentTime = currentTime,
            currentDay = currentDay,
            selectedDate = selectedDate,
            selectedTime = selectedTime,
            mealsToShow = mealsToShow,
            showSuggestions = showSuggestions,
            pagerState = pagerState,
            scope = scope,
            onDateSelectClick = {
                Log.d("MealAppScreenContainer", "Date picker triggered") // [52]
                showDatePicker = true
            },
            onSuggestClick = {
                Log.d("MealAppScreenContainer", "Suggest meals clicked") // [52]
                mealsToShow = potentialMeals
                showSuggestions = true
                scope.launch { pagerState.animateScrollToPage(0) } // [66, 56]
                Toast.makeText(context, "Suggesting meals!", Toast.LENGTH_SHORT).show() // [52]
            },
            onResetClick = {
                Log.d("MealAppScreenContainer", "Reset clicked") // [52]
                selectedDateMillis = null
                showSuggestions = false
                mealsToShow = emptyList()
                Toast.makeText(context, "Selection Reset", Toast.LENGTH_SHORT).show() // [52]
            },
            onShareClick = { mealToShare ->
                Log.d("MealAppScreenContainer", "Share clicked for meal: $mealToShare") // [52]
                shareMeal(context, mealToShare, selectedTime) // [52]
            }
        )
    }

    // Date picker dialog.
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState( // [57, 58]
            initialSelectedDateMillis = selectedDateMillis ?: System.currentTimeMillis()
        )
        DatePickerDialog( // [57]
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = { // [57]
                    val newlySelectedMillis = datePickerState.selectedDateMillis // [57]
                    selectedDateMillis = newlySelectedMillis
                    showSuggestions = false
                    mealsToShow = emptyList()
                    showDatePicker = false
                    Toast.makeText(context, "Date Selected. Press 'Suggest Meal'.", Toast.LENGTH_LONG).show() // [52]
                }) { Text("OK") } // [57]
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) { Text("Cancel") } // [57]
            }
        ) {
            DatePicker(state = datePickerState) // [57]
        }
    }
}

// Helper function to suggest meals based on time.
fun suggestMealsForTime(time: LocalTime): List<Meal> { // [67, 64]
    Log.d("MealAppScreenContainer", "Suggesting meals for time: $time") // [52, 67]
    val placeholder = R.drawable.ic_launcher_foreground // [63]

    val breakfastMeals = listOf( // [64]
        Meal("Pancakes", "Fluffy pancakes with syrup", R.drawable.pizza), // [64, 63]
        Meal("Oatmeal", "Healthy oats with fruits", R.drawable.jungle_oats) // [64, 63]
    )
    val lunchMeals = listOf( // [64]
        Meal("Chicken Salad", "Grilled chicken breast salad", R.drawable.salad), // [64, 63]
        Meal("Veggie Wrap", "Hummus and veggie wrap", R.drawable.fruit_salad) // [64, 63]
    )
    val dinnerMeals = listOf( // [64]
        Meal("Spaghetti Bolognese", "Rich meat sauce pasta", R.drawable.pasta), // [64, 63]
        Meal("Salmon & Asparagus", "Baked salmon", R.drawable.sandwich) // [64, 63]
    )

    return when (time.hour) { // [67]
        in 5..10 -> breakfastMeals
        in 11..15 -> lunchMeals
        in 16..21 -> dinnerMeals
        else -> lunchMeals
    }.also {
        Log.d("MealAppScreenContainer", "Suggested meals: $it") // [52]
    }
}

// Helper function to share a meal.
fun shareMeal(context: Context, meal: Meal, time: LocalTime) { // [52, 64, 67]
    Log.d("MealAppScreenContainer", "Sharing meal: $meal at time: $time") // [52, 64, 67]
    val timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT) // [67]
    val shareText = "How about having ${meal.name} around ${time.format(timeFormatter)}? Sounds delicious!" // [64, 67]

    val intent = Intent(Intent.ACTION_SEND).apply { // [52]
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Meal Suggestion") // [52]
        putExtra(Intent.EXTRA_TEXT, shareText) // [52]
    }
    try {
        context.startActivity(Intent.createChooser(intent, "Share Meal Suggestion")) // [52]
    } catch (e: Exception) {
        Log.e("MealAppScreenContainer", "Error sharing meal: ${e.message}") // [52]
        Toast.makeText(context, "Cannot share: No app found.", Toast.LENGTH_SHORT).show() // [52]
    }
}