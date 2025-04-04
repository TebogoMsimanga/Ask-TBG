package com.example.asktbg.ui.components

import android.util.Log // For debug logging during composable rendering [17].
import androidx.compose.foundation.BorderStroke // Used to define the border style [18].
import androidx.compose.foundation.border // Applies a border to a Composable [18].
import androidx.compose.foundation.layout.Box // Used to position children [18].
import androidx.compose.foundation.layout.fillMaxWidth // Modifier to stretch width [18].
import androidx.compose.foundation.layout.height // Modifier to define fixed height [18].
import androidx.compose.foundation.shape.RoundedCornerShape // Rounded corners [18].
import androidx.compose.material3.MaterialTheme // Theming support for Material Design 3 [19].
import androidx.compose.material3.Surface // Container that respects Material Design specs [19].
import androidx.compose.material3.Text // Displays text with styling [19].
import androidx.compose.runtime.Composable // Marks function as Composable [18].
import androidx.compose.ui.Alignment // For centering content inside containers [18].
import androidx.compose.ui.Modifier // Chains multiple layout instructions [18].
import androidx.compose.ui.graphics.Color // Used to define UI color [18].
import androidx.compose.ui.unit.dp // Density-independent pixels [18].

@Composable
fun LogoSection(modifier: Modifier = Modifier) {
    // Log that the LogoSection composable has been called [17].
    Log.d("LogoSection", "LogoSection composable called")

    // A Surface composable with white translucent background and rounded corners [19].
    Surface(
        modifier = modifier
            .fillMaxWidth() // Occupies full horizontal space [18].
            .height(50.dp) // Fixed height for logo container [18].
            .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(8.dp)), // Adds border with rounded edges [18].
        shape = RoundedCornerShape(8.dp), // Applies rounding to the surface itself [18].
        color = Color.White.copy(alpha = 0.8f) // Slightly transparent white background [18].
    ) {
        // Centers the content (logo text) using Box [18].
        Box(contentAlignment = Alignment.Center) {
            Text("ASK TBG", style = MaterialTheme.typography.bodyLarge) // Renders styled text [19].
            Log.d("LogoSection", "Logo text 'ASK TBG' displayed") // Debug log [17].
        }
    }
}