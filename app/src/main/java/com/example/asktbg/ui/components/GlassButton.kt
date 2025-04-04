package com.example.asktbg.ui.components

import android.util.Log // For logging debug messages [10].
import androidx.compose.foundation.BorderStroke // To define border styling [11].
import androidx.compose.foundation.background // For applying background gradient [11].
import androidx.compose.foundation.layout.* // Layout components (Box, PaddingValues, etc.) [11].
import androidx.compose.foundation.shape.RoundedCornerShape // Rounded corners for shapes [11].
import androidx.compose.material3.Button // Material 3 Button composable [12].
import androidx.compose.material3.ButtonDefaults // Default styling options for Button [12].
import androidx.compose.material3.Text // Composable for text display [12].
import androidx.compose.runtime.Composable // For declaring composable functions [11].
import androidx.compose.ui.Alignment // For aligning content [11].
import androidx.compose.ui.Modifier // For styling components via chaining [11].
import androidx.compose.ui.graphics.Brush // For gradient effects [11].
import androidx.compose.ui.graphics.Color // To define color values [11].
import androidx.compose.ui.graphics.Shape // To handle shape definitions [11].
import androidx.compose.ui.text.font.FontWeight // Text font weight options [11].
import androidx.compose.ui.unit.dp // Density-independent pixels for consistent layout [11].

@Composable
fun GlassButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    gradient: Brush,
    shape: Shape = RoundedCornerShape(12.dp),
    borderStroke: BorderStroke? = BorderStroke(1.dp, Color.White.copy(alpha = 0.6f))
) {
    // Log the input parameters for debugging [10].
    Log.d("GlassButton", "GlassButton called with text: $text, gradient: $gradient, shape: $shape, borderStroke: $borderStroke")

    // Create a Button composable with the specified parameters [12].
    Button(
        onClick = {
            Log.d("GlassButton", "Button clicked") // Log click event [10].
            onClick()
        },
        modifier = modifier.height(48.dp), // Set fixed height [11].
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Transparent background [12].
        contentPadding = PaddingValues(), // No padding [11].
        border = borderStroke
    ) {
        // Content container [11].
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient, shape = shape) // Gradient background [11].
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            // Display the button text [12].
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}