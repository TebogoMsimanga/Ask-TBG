package com.example.asktbg.ui.components

import android.util.Log // Android Logcat for debug logging [7].
import androidx.compose.foundation.BorderStroke // For adding border styles to components [8].
import androidx.compose.foundation.layout.* // Layout components like Column, Padding etc. [8].
import androidx.compose.foundation.shape.RoundedCornerShape // For rounded card corners [8].
import androidx.compose.material3.Card // Material 3 Card UI component [9].
import androidx.compose.material3.CardDefaults // For default card color configurations [9].
import androidx.compose.material3.Text // Composable for displaying text [9].
import androidx.compose.runtime.Composable // Annotation for declaring composables [8].
import androidx.compose.ui.Alignment // Alignment options for composables [8].
import androidx.compose.ui.Modifier // Modifiers to apply layout or styling [8].
import androidx.compose.ui.graphics.Color // Color values [8].
import androidx.compose.ui.text.font.FontWeight // Font weight styling [8].
import androidx.compose.ui.text.style.TextAlign // Text alignment options [8].
import androidx.compose.ui.unit.dp // Density-independent pixels for layout [8].
import androidx.compose.ui.unit.sp // Scale-independent pixels for text sizing [8].

@Composable
fun ClockCard(
    modifier: Modifier = Modifier,
    text: String,
    // Make subtext optional based on usage
    subTextLine1: String? = null,
    subTextLine2: String? = null
) {
    // Log the input parameters for debugging [7].
    Log.d("ClockCard", "ClockCard called with text: $text, subTextLine1: $subTextLine1, subTextLine2: $subTextLine2")

    // Create a Card composable to represent the clock card [9].
    Card(
        modifier = modifier.fillMaxHeight(), // Fill height [8].
        shape = RoundedCornerShape(24.dp), // Rounded corners [8].
        border = BorderStroke(1.5.dp, Color.Gray.copy(alpha = 0.6f)), // Styled border [8].
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)) // Light transparent background [9].
    ) {
        // Vertical arrangement of elements [8].
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (subTextLine1 != null || subTextLine2 != null)
                Arrangement.SpaceAround else Arrangement.Center
        ) {
            // Main time text [9].
            Text(
                text = text,
                fontSize = 110.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                lineHeight = 110.sp,
                maxLines = 1,
                color = Color.Black
            )

            // Display subtexts if available [9].
            if (subTextLine1 != null || subTextLine2 != null) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    subTextLine1?.let {
                        Text(
                            text = it,
                            fontSize = 36.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }
                    subTextLine2?.let {
                        Text(
                            text = it,
                            fontSize = 36.sp,
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