package com.example.asktbg.ui.components

import android.os.Build // [41]
import android.util.Log // [42]
import androidx.annotation.RequiresApi // [43]
import androidx.compose.foundation.BorderStroke // [44]
import androidx.compose.foundation.background // [44]
import androidx.compose.foundation.border // [44]
import androidx.compose.foundation.layout.Row // [45]
import androidx.compose.foundation.layout.Spacer // [45]
import androidx.compose.foundation.layout.fillMaxWidth // [45]
import androidx.compose.foundation.layout.size // [45]
import androidx.compose.foundation.layout.width // [45]
import androidx.compose.foundation.shape.CircleShape // [44]
import androidx.compose.foundation.shape.RoundedCornerShape // [44]
import androidx.compose.material.icons.Icons // [46]
import androidx.compose.material.icons.filled.CalendarMonth // [46]
import androidx.compose.material3.Icon // [47]
import androidx.compose.material3.IconButton // [47]
import androidx.compose.material3.MaterialTheme // [47]
import androidx.compose.material3.OutlinedTextField // [47]
import androidx.compose.material3.OutlinedTextFieldDefaults // [47]
import androidx.compose.material3.Text // [47]
import androidx.compose.runtime.Composable // [48]
import androidx.compose.runtime.remember // [48]
import androidx.compose.ui.Alignment // [49]
import androidx.compose.ui.Modifier // [49]
import androidx.compose.ui.unit.dp // [50]
import java.time.LocalDate // [51]
import java.time.LocalTime // [51]
import java.time.format.DateTimeFormatter // [51]
import java.time.format.FormatStyle // [51]

@RequiresApi(Build.VERSION_CODES.O) // [43]
@Composable // [48]
fun TimeSelectionSection(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    selectedTime: LocalTime,
    onDateSelectClick: () -> Unit
) {
    // Log the input parameters for debugging.
    Log.d("TimeSelectionSection", "TimeSelectionSection called with selectedDate: $selectedDate, selectedTime: $selectedTime") // [42]

    // Create formatters for date and time.
    val dateFormatter = remember { DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM) } // [48, 51]
    val timeFormatter = remember { DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT) } // [48, 51]

    // Create a Row composable to arrange the date and time selection components horizontally.
    Row(
        modifier = modifier.fillMaxWidth(), // [49, 45]
        verticalAlignment = Alignment.CenterVertically // [49]
    ) {
        // Create an OutlinedTextField to display the selected date and time.
        OutlinedTextField(
            value = "${selectedDate.format(dateFormatter)} ${selectedTime.format(timeFormatter)}", // [51]
            onValueChange = {}, // No direct editing
            label = { Text("Selected Time for Suggestion") }, // [47]
            modifier = Modifier.weight(1f), // [49]
            readOnly = true, // Make the text field read-only.
            shape = RoundedCornerShape(8.dp), // [44, 50]
            colors = OutlinedTextFieldDefaults.colors( // Custom colors for better contrast on gradient // [47]
                focusedTextColor = MaterialTheme.colorScheme.onSurface, // [47]
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface, // [47]
                focusedBorderColor = MaterialTheme.colorScheme.primary, // [47]
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), // [47]
                focusedLabelColor = MaterialTheme.colorScheme.primary, // [47]
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f), // [47]
                cursorColor = MaterialTheme.colorScheme.primary // [47]
            )
        )
        // Add a spacer for horizontal spacing.
        Spacer(modifier = Modifier.width(8.dp)) // [45, 50]
        // Create an IconButton to trigger the date picker.
        IconButton(
            onClick = {
                // Log the click event of the date picker button.
                Log.d("TimeSelectionSection", "Date picker button clicked") // [42]
                onDateSelectClick()
            },
            modifier = Modifier
                .size(56.dp) // Match OutlinedTextField height approx // [49, 45, 50]
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), CircleShape) // [44, 47, 44, 50]
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), CircleShape) // [44, 47, 44]
        ) {
            // Display the calendar icon.
            Icon(
                imageVector = Icons.Filled.CalendarMonth, // [46]
                contentDescription = "Select Date",
                tint = MaterialTheme.colorScheme.primary // [47]
            )
        }
    }
}