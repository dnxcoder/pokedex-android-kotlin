package com.example.tibiaclone.utils

fun convertFeetToMetersAndCentimeters(feet: Int): Float {
    val totalMeters = feet * 0.3048

    val meters = totalMeters.toInt()
    val centimeters = ((totalMeters - meters) * 100).toInt()


    return meters + (centimeters / 100f)
}
