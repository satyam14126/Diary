package com.diaryvault.mvp.utils

import android.content.Context
import java.io.File
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun diaryPathFor(context: Context, date: LocalDate): File {
    val year = date.year.toString()
    val month = date.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val day = date.dayOfMonth.toString()
    return File(context.filesDir, "$year/$month/$day").apply { mkdirs() }
}
