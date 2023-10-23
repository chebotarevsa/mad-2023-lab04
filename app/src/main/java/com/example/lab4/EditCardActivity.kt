package com.example.lab4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.databinding.ActivityEditCardBinding

class EditCardActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val question = intent.getStringExtra("question")
        val example = intent.getStringExtra("example")
        val answer = intent.getStringExtra("answer")
        val translation = intent.getStringExtra("translation")

        binding.questionField.setText(question)
        binding.exampleField.setText(example)
        binding.answerField.setText(answer)
        binding.translationField.setText(translation)

        binding.saveButton.setOnClickListener {
            Intent(this, SeeCardActivity::class.java).apply {
                putExtra("question", binding.questionField.text.toString())
                putExtra("example", binding.exampleField.text.toString())
                putExtra("answer", binding.answerField.text.toString())
                putExtra("translation", binding.translationField.text.toString())
            }.also {
                startActivity(it)
            }
        }
    }
}