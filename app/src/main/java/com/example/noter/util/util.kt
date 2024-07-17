package com.example.noter.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("d MMM hh:mm a", Locale.getDefault())
    return format.format(date).toString()
}