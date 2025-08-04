package com.example.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
fun String.toRelativeTime(): String {
    try {
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val dateTime = ZonedDateTime.parse(this, formatter)
        val now = ZonedDateTime.now()

        val duration = ChronoUnit.SECONDS.between(dateTime, now)

        val years = TimeUnit.SECONDS.toDays(duration) / 365
        val months = TimeUnit.SECONDS.toDays(duration) / 30
        val weeks = TimeUnit.SECONDS.toDays(duration) / 7
        val days = TimeUnit.SECONDS.toDays(duration)
        val hours = TimeUnit.SECONDS.toHours(duration)
        val minutes = TimeUnit.SECONDS.toMinutes(duration)

        return when {
            years > 0 -> "$years year${if (years > 1) "s" else ""} ago"
            months > 0 -> "$months month${if (months > 1) "s" else ""} ago"
            weeks > 0 -> "$weeks week${if (weeks > 1) "s" else ""} ago"
            days > 0 -> "$days day${if (days > 1) "s" else ""} ago"
            hours > 0 -> "$hours hour${if (hours > 1) "s" else ""} ago"
            minutes > 0 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
            else -> "$duration second${if (duration > 1) "s" else ""} ago"
        }
    } catch (e: Exception) {
        return ""
    }
}