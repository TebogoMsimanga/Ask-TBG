package com.example.asktbg.ui.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeSelectionSection(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    selectedTime: LocalTime,
    onDateSelectClick: () -> Unit
) {
    // Log the input parameters for debugging.
    Log.d("TimeSelectionSection", "TimeSelectionSection called with selectedDate: $selectedDate, selectedTime: $selectedTime")

    // Create formatters for date and time.
    val dateFormatter = remember { DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM) }
    val timeFormatter = remember { DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT) }

    // Create a Row composable to arrange the date and time selection components horizontally.
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Create an OutlinedTextField to display the selected date and time.
        OutlinedTextField(
            value = "${selectedDate.format(dateFormatter)} ${selectedTime.format(timeFormatter)}",
            onValueChange = {}, // No direct editing
            label = { Text("Selected Time for Suggestion") },
            modifier = Modifier.weight(1f),
            readOnly = true, // Make the text field read-only.
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors( // Custom colors for better contrast on gradient
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
        // Add a spacer for horizontal spacing.
        Spacer(modifier = Modifier.width(8.dp))
        // Create an IconButton to trigger the date picker.
        IconButton(
            onClick = {
                // Log the click event of the date picker button.
                Log.d("TimeSelectionSection", "Date picker button clicked")
                onDateSelectClick()
            },
            modifier = Modifier
                .size(56.dp) // Match OutlinedTextField height approx
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), CircleShape)
        ) {
            // Display the calendar icon.
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = "Select Date",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}