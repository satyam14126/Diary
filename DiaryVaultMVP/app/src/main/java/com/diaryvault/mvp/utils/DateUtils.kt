
package com.diaryvault.mvp.utils
import android.content.Context
import java.io.File
import java.time.LocalDate

fun diaryPath(context: Context): File {
    val now = LocalDate.now()
    return File(context.filesDir, "${now.year}/${now.month}/${now.dayOfMonth}")
        .apply { mkdirs() }
}
