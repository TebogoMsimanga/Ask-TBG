package com.example.asktbg.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LogoSection(modifier: Modifier = Modifier) {
    // Log that the LogoSection composable has been called.
    Log.d("LogoSection", "LogoSection composable called")

    // Create a Surface composable to represent the logo section.
    Surface(
        modifier = modifier
            .fillMaxWidth() // Fill the available width.
            .height(50.dp) // Set the height of the surface.
            .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(8.dp)), // Add a border with rounded corners.
        shape = RoundedCornerShape(8.dp), // Set the shape of the surface to rounded corners.
        color = Color.White.copy(alpha = 0.8f) // Set the background color to slightly transparent white.
    ) {
        // Create a Box composable to center the logo text.
        Box(contentAlignment = Alignment.Center) {
            // Create a Text composable to display the logo text.
            Text("ASK TBG", style = MaterialTheme.typography.bodyLarge)
            // Log the logo text being displayed.
            Log.d("LogoSection", "Logo text 'ASK TBG' displayed")
        }
    }
}