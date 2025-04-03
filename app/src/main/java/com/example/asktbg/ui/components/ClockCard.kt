package com.example.asktbg.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClockCard(
    modifier: Modifier = Modifier,
    text: String,
    // Make subtext optional based on usage
    subTextLine1: String? = null,
    subTextLine2: String? = null
) {
    // Log the input parameters for debugging.
    Log.d("ClockCard", "ClockCard called with text: $text, subTextLine1: $subTextLine1, subTextLine2: $subTextLine2")

    // Create a Card composable to represent the clock card.
    Card(
        modifier = modifier.fillMaxHeight(), // Ensure card tries to fill height.
        shape = RoundedCornerShape(24.dp), // More rounded corners.
        border = BorderStroke(1.5.dp, Color.Gray.copy(alpha = 0.6f)), // Slightly thicker border.
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
    ) {
        // Create a Column composable to arrange the content vertically inside the card.
        Column(
            modifier = Modifier
                .fillMaxSize() // Fill the card
                .padding(horizontal = 8.dp, vertical = 8.dp), // Minimal padding
            horizontalAlignment = Alignment.CenterHorizontally,
            // Distribute space vertically if subtext exists
            verticalArrangement = if (subTextLine1 != null || subTextLine2 != null) Arrangement.SpaceAround else Arrangement.Center
        ) {
            // Create a Text composable to display the main clock text.
            Text(
                text = text,
                fontSize = 110.sp, // <<< MUCH BIGGER FONT SIZE
                fontWeight = FontWeight.Normal, // Match example style (adjust if needed)
                textAlign = TextAlign.Center,
                lineHeight = 110.sp, // Try to match fontSize to prevent extra spacing
                maxLines = 1,
                color = Color.Black
                // Consider adding Modifier.weight(1f) if vertical space is tight
            )

            // Only show subtext if provided
            if (subTextLine1 != null || subTextLine2 != null) {
                // Create a Column composable to arrange the subtext vertically.
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Check if subTextLine1 is not null and display it.
                    subTextLine1?.let {
                        Text(
                            text = it, // e.g., PM
                            fontSize = 36.sp, // <<< Bigger subtext
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }
                    // Check if subTextLine2 is not null and display it.
                    subTextLine2?.let {
                        Text(
                            text = it, // e.g., MONDAY
                            fontSize = 36.sp, // <<< Bigger subtext
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}