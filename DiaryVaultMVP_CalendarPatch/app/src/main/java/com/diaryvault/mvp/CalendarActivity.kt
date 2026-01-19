package com.diaryvault.mvp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diaryvault.mvp.utils.diaryPathFor
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.time.LocalDate

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val cal = findViewById<MaterialCalendarView>(R.id.calendarView)

        cal.setOnDateChangedListener { _, date, _ ->
            val pickedDate = LocalDate.of(date.year, date.month + 1, date.day)
            val dir = diaryPathFor(this, pickedDate)

            val i = Intent(this, EntryListActivity::class.java)
            i.putExtra("date", pickedDate.toString())
            i.putExtra("dirPath", dir.absolutePath)
            startActivity(i)
        }
    }
}
