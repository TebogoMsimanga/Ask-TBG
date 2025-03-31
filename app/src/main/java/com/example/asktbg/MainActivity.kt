package com.example.asktbg

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.asktbg.ui.theme.AskTBGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskTBGTheme{
                MealSuggestionScreen()
            }
        }
    }
}

// Mean suggestion screen composable that defines the UI for the meal suggestions
@Composable
fun MealSuggestionScreen() {
    //state variables to store the user's input and meal suggestion
    var timeOfDay by remember { mutableStateOf("") }
    var mealSuggestion by remember { mutableStateOf("") }
    var context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = timeOfDay,
            onValueChange = { timeOfDay = it},
            label = { Text("Enter time of day (e.g, Breakfast)")},
            modifier = Modifier.fillMaxWidth()
        )

        // vertical space between text field and button
        Spacer(modifier = Modifier.height(16.dp))

        // button to trigger the meal suggestion generation
        Button(
            onClick = {
                // trim and lowercase the user input for case insensitive comparison
                var trimmedTime = timeOfDay.trim().lowercase()
                // is for checking if input is empty
                if (trimmedTime.isEmpty()) {
                    // display a toast massage if the input is empty
                    Toast.makeText(context, "Please the time of day.", Toast.LENGTH_SHORT).show()
                    // clear the meal suggestion
                    mealSuggestion=""
                } else {
                    // generate the meal suggestion based on the time of day using a when expression
                    mealSuggestion = when (trimmedTime) {
                        "morning" -> "jungle oats"
                        "mid morning" -> "toast"
                        "afternoon" -> "fruit salad"
                        "mid afternoon" -> "pizza"
                        "dinner" -> "milk and cookies"
                        else -> {
                            //display a toast massage for invalid input
                            Toast.makeText(
                                context,
                                "invalid time of day. please enter 'Morning','Mid morning','Afternoon','Mid afternoon','Dinner'",
                                Toast.LENGTH_SHORT
                            ).show()
                            // clear the meal suggestion
                            ""
                        }
                    }

                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Suggest Meal")
        }

        // Text to display the generated meal suggestion
        Text(text = if (mealSuggestion.isNotEmpty()) "Meal Suggestion: $mealSuggestion" else "")

// vertical space between meal suggestion text and reset button
        Spacer(modifier = Modifier.height(16.dp))

        //button to reset the input and meal suggestion
        Button(
            onClick = {
                //Clear input and meal suggestion
                timeOfDay=""
                mealSuggestion=""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset")
        }

    }
}

// meal suggestion preview composable function to preview the meal suggestion screen
@Preview(showBackground = true)
@Composable
fun MealSuggestionPreview(){
    AskTBGTheme {
        MealSuggestionScreen()
    }
}