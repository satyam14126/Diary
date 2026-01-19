package com.diaryvault.mvp.utils

import android.content.Context
import java.io.File
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun diaryPath(context: Context): File {
    val now = LocalDate.now()
    val year = now.year.toString()
    val month = now.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val day = now.dayOfMonth.toString()

    return File(context.filesDir, "$year/$month/$day").apply { mkdirs() }
}
