package com.example.asktbg.ui.components

import android.util.Log // [1] Used for logging debug messages
import android.widget.Toast // [2] For showing quick messages on the screen
import androidx.compose.foundation.ExperimentalFoundationApi // [3] Marks use of experimental Compose APIs
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState // [4] Used for tracking the page index
import androidx.compose.runtime.Composable // [5] Marks a composable function in Jetpack Compose
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext // [6] Access the current context in Compose
import androidx.compose.ui.unit.dp
import com.example.asktbg.model.Meal // Your custom data class

@OptIn(ExperimentalFoundationApi::class) // [3]
@Composable // [5]
fun ActionButtonsSection(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    mealsToShow: List<Meal>, // List of meals to display
    onShareClick: (Meal) -> Unit,
    onResetClick: () -> Unit
) {
    val context = LocalContext.current // [6]

    Log.d("ActionButtonsSection", "Current pager page is ${pagerState.currentPage}") // [1]
    Log.d("ActionButtonsSection", "Number of meals to show: ${mealsToShow.size}") // [1]

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GlassButton(
            onClick = {
                Log.d("ActionButtonsSection", "Share button clicked") // [1]

                if (mealsToShow.isNotEmpty() && pagerState.currentPage < mealsToShow.size) {
                    onShareClick(mealsToShow[pagerState.currentPage])
                    Log.d("ActionButtonsSection", "Sharing meal: ${mealsToShow[pagerState.currentPage]}") // [1]
                } else {
                    Toast.makeText(context, "No meal suggested to share", Toast.LENGTH_SHORT).show() // [2]
                    Log.d("ActionButtonsSection", "No meal available to share") // [1]
                }
            },
            text = "Share",
            modifier = Modifier.weight(1f),
            gradient = Brush.horizontalGradient(listOf(Color(0xAA00E676), Color(0xAA69F0AE)))
        )
        GlassButton(
            onClick = {
                Log.d("ActionButtonsSection", "Reset button clicked") // [1]
                onResetClick()
            },
            text = "Reset",
            modifier = Modifier.weight(1f),
            gradient = Brush.horizontalGradient(listOf(Color(0xAAFF5252), Color(0xAAFF8A80)))
        )
    }
}