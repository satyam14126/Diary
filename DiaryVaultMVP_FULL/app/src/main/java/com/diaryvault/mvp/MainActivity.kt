package com.diaryvault.mvp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.diaryvault.mvp.security.CryptoManager
import com.diaryvault.mvp.speech.SpeechToTextManager
import com.diaryvault.mvp.utils.diaryPath
import java.io.File
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var entryText: EditText
    private lateinit var btnSpeak: Button
    private lateinit var btnSave: Button

    private val crypto = CryptoManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        entryText = findViewById(R.id.entryText)
        btnSpeak = findViewById(R.id.btnSpeak)
        btnSave = findViewById(R.id.btnSave)

        btnSpeak.setOnClickListener {
            val stt = SpeechToTextManager(this) { text ->
                val current = entryText.text.toString()
                val appended = if (current.isBlank()) text else "$current\n$text"
                entryText.setText(appended)
                entryText.setSelection(appended.length)
            }
            stt.start()
        }

        btnSave.setOnClickListener {
            val plain = entryText.text.toString().trim()
            if (plain.isBlank()) return@setOnClickListener

            val encrypted = crypto.encrypt(plain)

            val dir = diaryPath(this)
            val file = File(dir, "entry_${LocalDate.now()}.txt")
            file.writeText(encrypted)

            entryText.setText("")
        }
    }
}
