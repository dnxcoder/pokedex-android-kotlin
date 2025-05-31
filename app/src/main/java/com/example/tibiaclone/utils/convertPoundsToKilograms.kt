package com.example.tibiaclone.utils

fun convertPoundsToKilograms(pounds: Int): Float {
    val kg = pounds * 0.453592f
    return String.format("%.2f", kg).toFloat()
}
