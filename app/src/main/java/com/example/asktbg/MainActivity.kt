package com.example.asktbg

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.asktbg.ui.mealscreen.MealAppScreenContainer
import com.example.asktbg.ui.theme.AskTBGTheme // Replace with your actual theme package

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskTBGTheme { // Apply your theme
                MealAppScreenContainer() // Call the state holder composable
            }
        }
    }
}

// --- Preview Function ---
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, name = "Main App Screen Preview") // Add the Preview annotation
@Composable
fun DefaultPreview() {
    // Wrap the preview content in your theme for consistent styling
    AskTBGTheme {
        // Call the main composable you want to preview
        MealAppScreenContainer()
    }
}