package com.example.asktbg.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.asktbg.model.Meal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealCarouselSection(
    // This modifier parameter will RECEIVE the weight from MealAppScreenContent
    modifier: Modifier = Modifier,
    mealsToShow: List<Meal>,
    pagerState: PagerState,
    scope: CoroutineScope
) {
    // Log the input parameters for debugging.
    Log.d("MealCarouselSection", "MealCarouselSection called with mealsToShow: ${mealsToShow.size}")

    // Apply the incoming modifier (which contains the weight) to this Column
    Column(
        modifier = modifier, // <<< REMOVED .weight() HERE
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the title of the carousel.
        Text("Meal Suggestions", style = MaterialTheme.typography.headlineSmall)
        // Add a spacer for vertical spacing.
        Spacer(modifier = Modifier.height(8.dp))

        // Create a Box composable to hold the carousel content.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight() // Box fills the height Column is given by its modifier (inc. weight)
                .padding(bottom = 20.dp)
                .border(BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)), RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Check if there are meals to show.
            if (mealsToShow.isNotEmpty()) {
                // Create a HorizontalPager composable to display the meal suggestions.
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    // Get the meal for the current page.
                    val meal = mealsToShow[page]
                    // Create a Column composable to display the meal details.
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White.copy(alpha = 0.5f))
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Display the meal image.
                        Image(
                            painter = painterResource(id = meal.imageResId),
                            contentDescription = meal.name,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .aspectRatio(1.5f)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        // Add a spacer for vertical spacing.
                        Spacer(modifier = Modifier.height(12.dp))
                        // Display the meal name.
                        Text(meal.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        // Add a spacer for vertical spacing.
                        Spacer(modifier = Modifier.height(4.dp))
                        // Display the meal description.
                        Text(meal.description, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                    }
                }

                // Arrow Buttons Overlay
                // Create a Row composable to display the arrow buttons.
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Check if the previous button should be enabled.
                    val prevButtonEnabled = pagerState.currentPage > 0
                    // Check if the next button should be enabled.
                    val nextButtonEnabled = pagerState.currentPage < mealsToShow.size - 1

                    // Create the previous button.
                    IconButton(
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) } },
                        enabled = prevButtonEnabled,
                        modifier = Modifier.background(Color.Black.copy(alpha = if (prevButtonEnabled) 0.3f else 0.1f), CircleShape)
                    ) {
                        // Display the previous arrow icon.
                        Icon(Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "Previous", tint = Color.White.copy(alpha = if(prevButtonEnabled) 1.0f else 0.4f))
                    }
                    // Create the next button.
                    IconButton(
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                        enabled = nextButtonEnabled,
                        modifier = Modifier.background(Color.Black.copy(alpha = if (nextButtonEnabled) 0.3f else 0.1f), CircleShape)
                    ) {
                        // Display the next arrow icon.
                        Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = "Next", tint = Color.White.copy(alpha = if(nextButtonEnabled) 1.0f else 0.4f))
                    }
                }

                // Pager Indicators
                // Create a Row composable to display the pager indicators.
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                        .height(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Create a pager indicator for each meal.
                    repeat(mealsToShow.size) { iteration ->
                        // Determine the color of the indicator based on the current page.
                        val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray.copy(alpha = 0.7f)
                        // Create a Box composable to represent the indicator.
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }

            } else {
                // Display a placeholder text when no meals are available.
                Text("Select date & press 'Suggest Meal'", modifier = Modifier.padding(16.dp))
            }
        }
    }
}