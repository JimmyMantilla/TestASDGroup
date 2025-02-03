package com.example.testasdgroup.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Long.toFormattedDate(): String {
    val date = Date(this * 1000)
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return dateFormat.format(date)
}

fun String.extractLaunchInfo(): String? {
    val startIndex = this.indexOf("launch from", ignoreCase = true)
    if (startIndex == -1) return null  // Return null if "launch from" is not found

    val endIndex = this.indexOf(",", startIndex)
    if (endIndex == -1) return null  // Return null if no comma is found

    return this.substring(startIndex, endIndex).trim()  // Extract the substring and trim any extra spaces
}

fun String.extractMissionName(): String {
    return if (this.contains("Mission", ignoreCase = true)) {
        val parts = this.split(" ")

        // Check if the string has at least two parts and contains "Mission"
        if (parts.size >= 2) {
            parts.takeLast(2).joinToString(" ")
        } else {
            this  // If fewer than two parts, return the original string
        }
    } else {
        "No mission information"
    }
}