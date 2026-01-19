package com.diaryvault.mvp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.diaryvault.mvp.security.CryptoManager
import java.io.File

class ViewEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_entry)

        val tv = findViewById<TextView>(R.id.tvEntry)
        val filePath = intent.getStringExtra("filePath") ?: return

        val encrypted = File(filePath).readText()
        val crypto = CryptoManager()

        val plain = try {
            crypto.decrypt(encrypted)
        } catch (e: Exception) {
            "❌ Unable to decrypt entry.\nIt might be from older format."
        }

        tv.text = plain
    }
}
