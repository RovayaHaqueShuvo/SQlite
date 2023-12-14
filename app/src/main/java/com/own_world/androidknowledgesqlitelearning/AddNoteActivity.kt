package com.own_world.androidknowledgesqlitelearning

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.own_world.androidknowledgesqlitelearning.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNoteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        db = MyDatabaseHelper(this)

        binding.DoneBtn.setOnClickListener {
            val title = binding.TitleEdtxt.text.toString()
            val description = binding.ContentEdtxt.text.toString()
            val note = DataClass(0,title, description)
            db.insertNote(note)
            finish()
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
        }

    }
}