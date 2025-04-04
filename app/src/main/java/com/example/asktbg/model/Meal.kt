package com.example.asktbg.model // This defines the package the class belongs to

import androidx.annotation.DrawableRes // [1] Import for annotation that marks a parameter as a drawable resource ID

// Data class representing a Meal with a name, description, and image resource
data class Meal(
    val name: String, // Name of the meal
    val description: String, // Description of the meal
    @DrawableRes val imageResId: Int // [1] Ensures that only drawable resource IDs are passed to this parameter
)