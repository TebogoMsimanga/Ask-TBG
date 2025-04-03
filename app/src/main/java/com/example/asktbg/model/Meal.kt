package com.example.asktbg.model

import androidx.annotation.DrawableRes

data class Meal(
    val name: String,
    val description: String,
    @DrawableRes val imageResId: Int
)