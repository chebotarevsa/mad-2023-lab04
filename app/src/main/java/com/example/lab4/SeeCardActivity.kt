package com.example.lab4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.databinding.ActivitySeeCardBinding

class SeeCardActivity : AppCompatActivity() {

    lateinit var binding: ActivitySeeCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val question = intent.getStringExtra("question")
        val example = intent.getStringExtra("example")
        val answer = intent.getStringExtra("answer")
        val translation = intent.getStringExtra("translation")

        binding.cardQuestion.text =
            getString(R.string.questionField, question)
        binding.cardExample.text =
            getString(R.string.exampleField, example)
        binding.cardAnswer.text = getString(R.string.answerField, answer)
        binding.cardTranslation.text =
            getString(R.string.translationField, translation)

        binding.editButton.setOnClickListener {
            Intent(this, EditCardActivity::class.java).apply {
                putExtra("question", question)
                putExtra("example", example)
                putExtra("answer", answer)
                putExtra("translation", translation)
            }.also {
                startActivity(it)
            }
        }
    }
}