package com.example.asktbg

import android.os.Build // [68]
import android.os.Bundle // [68]
import androidx.activity.ComponentActivity // [69]
import androidx.activity.compose.setContent // [70]
import androidx.annotation.RequiresApi // [71]
import androidx.compose.runtime.Composable // [72]
import androidx.compose.ui.tooling.preview.Preview // [73]
import com.example.asktbg.ui.mealscreen.MealAppScreenContainer // [74]
import com.example.asktbg.ui.theme.AskTBGTheme // Replace with your actual theme package // [75]

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O) // [71]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskTBGTheme { // Apply your theme // [75]
                MealAppScreenContainer() // Call the state holder composable // [74]
            }
        }
    }
}

// --- Preview Function ---
@RequiresApi(Build.VERSION_CODES.O) // [71]
@Preview(showBackground = true, name = "Main App Screen Preview") // Add the Preview annotation // [73]
@Composable // [72]
fun DefaultPreview() {
    // Wrap the preview content in your theme for consistent styling
    AskTBGTheme { // [75]
        // Call the main composable you want to preview
        MealAppScreenContainer() // [74]
    }
}