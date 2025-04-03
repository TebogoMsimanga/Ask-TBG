package com.example.asktbg.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GlassButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    gradient: Brush,
    shape: Shape = RoundedCornerShape(12.dp),
    borderStroke: BorderStroke? = BorderStroke(1.dp, Color.White.copy(alpha = 0.6f))
) {
    // Log the input parameters for debugging.
    Log.d("GlassButton", "GlassButton called with text: $text, gradient: $gradient, shape: $shape, borderStroke: $borderStroke")

    // Create a Button composable with the specified parameters.
    Button(
        onClick = {
            // Log the click event.
            Log.d("GlassButton", "Button clicked")
            onClick()
        },
        modifier = modifier.height(48.dp), // Set the height of the button.
        shape = shape, // Set the shape of the button.
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent // Make the button's background transparent.
        ),
        contentPadding = PaddingValues(), // Remove default padding.
        border = borderStroke // Set the border stroke of the button.
    ) {
        // Create a Box composable to hold the button content.
        Box(
            modifier = Modifier
                .fillMaxSize() // Fill the button's size.
                .background(gradient, shape = shape) // Apply the gradient background and shape.
                .padding(horizontal = 16.dp, vertical = 8.dp), // Add padding to the content.
            contentAlignment = Alignment.Center // Center the content within the Box.
        ) {
            // Create a Text composable to display the button text.
            Text(
                text = text, // Set the text to display.
                color = Color.White, // Set the text color to white.
                fontWeight = FontWeight.Bold // Set the text font weight to bold.
            )
        }
    }
}