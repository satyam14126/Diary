package com.diaryvault.mvp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class EntryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_list)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val listView = findViewById<ListView>(R.id.listView)

        val date = intent.getStringExtra("date") ?: ""
        val dirPath = intent.getStringExtra("dirPath") ?: ""

        tvTitle.text = "Entries: $date"

        val dir = File(dirPath)
        val files = dir.listFiles()
            ?.filter { it.name.endsWith(".txt") }
            ?.sortedByDescending { it.lastModified() }
            ?: emptyList()

        val fileNames = files.map { it.name }
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileNames)

        listView.setOnItemClickListener { _, _, position, _ ->
            val clickedFile = files[position]
            val i = Intent(this, ViewEntryActivity::class.java)
            i.putExtra("filePath", clickedFile.absolutePath)
            startActivity(i)
        }
    }
}
