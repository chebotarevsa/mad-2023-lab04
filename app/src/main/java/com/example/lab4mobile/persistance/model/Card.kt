package com.example.lab4mobile.persistance.model

import android.graphics.Bitmap

data class Card(
    val id: String,
    val question: String,
    val example: String,
    val answer: String,
    val translate: String,
    val image: Bitmap?
)
