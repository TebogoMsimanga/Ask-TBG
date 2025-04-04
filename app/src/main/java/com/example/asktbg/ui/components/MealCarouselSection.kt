package com.example.asktbg.ui.components

import android.util.Log // [27]
import androidx.compose.foundation.BorderStroke // [28]
import androidx.compose.foundation.ExperimentalFoundationApi // [28]
import androidx.compose.foundation.Image // [28]
import androidx.compose.foundation.background // [28]
import androidx.compose.foundation.border // [28]
import androidx.compose.foundation.layout.Arrangement // [29]
import androidx.compose.foundation.layout.Box // [29]
import androidx.compose.foundation.layout.Column // [29]
import androidx.compose.foundation.layout.Row // [29]
import androidx.compose.foundation.layout.Spacer // [29]
import androidx.compose.foundation.layout.aspectRatio // [29]
import androidx.compose.foundation.layout.fillMaxHeight // [29]
import androidx.compose.foundation.layout.fillMaxSize // [29]
import androidx.compose.foundation.layout.fillMaxWidth // [29]
import androidx.compose.foundation.layout.height // [29]
import androidx.compose.foundation.layout.padding // [29]
import androidx.compose.foundation.layout.size // [29]
import androidx.compose.foundation.pager.HorizontalPager // [30]
import androidx.compose.foundation.pager.PagerState // [30]
import androidx.compose.foundation.shape.CircleShape // [28]
import androidx.compose.foundation.shape.RoundedCornerShape // [28]
import androidx.compose.material.icons.Icons // [31]
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos // [31]
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos // [31]
import androidx.compose.material3.Icon // [32]
import androidx.compose.material3.IconButton // [32]
import androidx.compose.material3.MaterialTheme // [32]
import androidx.compose.material3.Text // [32]
import androidx.compose.runtime.Composable // [33]
import androidx.compose.ui.Alignment // [34]
import androidx.compose.ui.Modifier // [34]
import androidx.compose.ui.draw.clip // [34]
import androidx.compose.ui.graphics.Color // [35]
import androidx.compose.ui.layout.ContentScale // [36]
import androidx.compose.ui.res.painterResource // [34]
import androidx.compose.ui.text.font.FontWeight // [37]
import androidx.compose.ui.text.style.TextAlign // [37]
import androidx.compose.ui.unit.dp // [38]
import com.example.asktbg.model.Meal // [39]
import kotlinx.coroutines.CoroutineScope // [40]
import kotlinx.coroutines.launch // [40]

@OptIn(ExperimentalFoundationApi::class) // [28]
@Composable // [33]
fun MealCarouselSection(
    // This modifier parameter will RECEIVE the weight from MealAppScreenContent [1]
    modifier: Modifier = Modifier,
    mealsToShow: List<Meal>,
    pagerState: PagerState,
    scope: CoroutineScope
) {
    // Log the input parameters for debugging [2].
    Log.d("MealCarouselSection", "MealCarouselSection called with mealsToShow: ${mealsToShow.size}") // [27]

    // Apply the incoming modifier (which contains the weight) to this Column [3]
    Column(
        modifier = modifier, // <<< REMOVED .weight() HERE [4]
        horizontalAlignment = Alignment.CenterHorizontally // [34]
    ) {
        // Display the title of the carousel [5].
        Text("Meal Suggestions", style = MaterialTheme.typography.headlineSmall) // [32]
        // Add a spacer for vertical spacing [6].
        Spacer(modifier = Modifier.height(8.dp)) // [29]

        // Create a Box composable to hold the carousel content [7].
        Box(
            modifier = Modifier
                .fillMaxWidth() // [29]
                .fillMaxHeight() // Box fills the height Column is given by its modifier (inc. weight) [8] // [29]
                .padding(bottom = 20.dp) // [29]
                .border(BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)), RoundedCornerShape(12.dp)) // [28, 35, 28]
                .clip(RoundedCornerShape(12.dp)), // [34, 28]
            contentAlignment = Alignment.Center // [34]
        ) {
            // Check if there are meals to show [9].
            if (mealsToShow.isNotEmpty()) {
                // Create a HorizontalPager composable to display the meal suggestions [10].
                HorizontalPager(
                    state = pagerState, // [30]
                    modifier = Modifier.fillMaxSize() // [29]
                ) { page ->
                    // Get the meal for the current page [11].
                    val meal = mealsToShow[page] // [39]
                    // Create a Column composable to display the meal details [12].
                    Column(
                        modifier = Modifier
                            .fillMaxSize() // [29]
                            .background(Color.White.copy(alpha = 0.5f)) // [28, 35]
                            .padding(16.dp), // [29]
                        horizontalAlignment = Alignment.CenterHorizontally, // [34]
                        verticalArrangement = Arrangement.Center // [29]
                    ) {
                        // Display the meal image [13].
                        Image(
                            painter = painterResource(id = meal.imageResId), // [34, 39]
                            contentDescription = meal.name, // [39]
                            modifier = Modifier
                                .fillMaxWidth(0.8f) // [29]
                                .aspectRatio(1.5f) // [29]
                                .clip(RoundedCornerShape(8.dp)), // [34, 28]
                            contentScale = ContentScale.Crop // [36]
                        )
                        // Add a spacer for vertical spacing [14].
                        Spacer(modifier = Modifier.height(12.dp)) // [29]
                        // Display the meal name [15].
                        Text(meal.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) // [32, 39, 37]
                        // Add a spacer for vertical spacing [16].
                        Spacer(modifier = Modifier.height(4.dp)) // [29]
                        // Display the meal description [17].
                        Text(meal.description, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center) // [32, 39, 37]
                    }
                }

                // Arrow Buttons Overlay [18]
                // Create a Row composable to display the arrow buttons [19].
                Row(
                    modifier = Modifier
                        .fillMaxWidth() // [29]
                        .padding(horizontal = 8.dp) // [29]
                        .align(Alignment.Center), // [34]
                    horizontalArrangement = Arrangement.SpaceBetween, // [29]
                    verticalAlignment = Alignment.CenterVertically // [34]
                ) {
                    // Check if the previous button should be enabled [20].
                    val prevButtonEnabled = pagerState.currentPage > 0 // [30]
                    // Check if the next button should be enabled [21].
                    val nextButtonEnabled = pagerState.currentPage < mealsToShow.size - 1 // [30, 39]

                    // Create the previous button [22].
                    IconButton(
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) } }, // [40, 30]
                        enabled = prevButtonEnabled,
                        modifier = Modifier.background(Color.Black.copy(alpha = if (prevButtonEnabled) 0.3f else 0.1f), CircleShape) // [34, 35, 28]
                    ) {
                        // Display the previous arrow icon [23].
                        Icon(Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "Previous", tint = Color.White.copy(alpha = if(prevButtonEnabled) 1.0f else 0.4f)) // [31, 35]
                    }
                    // Create the next button [24].
                    IconButton(
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }, // [40, 30]
                        enabled = nextButtonEnabled,
                        modifier = Modifier.background(Color.Black.copy(alpha = if (nextButtonEnabled) 0.3f else 0.1f), CircleShape) // [34, 35, 28]
                    ) {
                        // Display the next arrow icon [25].
                        Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = "Next", tint = Color.White.copy(alpha = if(nextButtonEnabled) 1.0f else 0.4f)) // [31, 35]
                    }
                }

                // Pager Indicators [26]
                // Create a Row composable to display the pager indicators [27].
                Row(
                    Modifier
                        .align(Alignment.BottomCenter) // [34]
                        .padding(bottom = 8.dp) // [29]
                        .height(20.dp) // [29]
                        .fillMaxWidth(), // [29]
                    horizontalArrangement = Arrangement.Center // [29]
                ) {
                    // Create a pager indicator for each meal [28].
                    repeat(mealsToShow.size) { iteration -> // [39]
                        // Determine the color of the indicator based on the current page [29].
                        val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray.copy(alpha = 0.7f) // [30, 32, 35]
                        // Create a Box composable to represent the indicator [30].
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp) // [29]
                                .clip(CircleShape) // [34, 28]
                                .background(color) // [28]
                                .size(8.dp) // [29]
                        )
                    }
                }

            } else {
                // Display a placeholder text when no meals are available [31].
                Text("Select date & press 'Suggest Meal'", modifier = Modifier.padding(16.dp)) // [32, 29]
            }
        }
    }
}