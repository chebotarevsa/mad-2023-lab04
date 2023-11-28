package com.example.lab4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.databinding.ActivitySeeBinding

class SeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)
        val card = CardService.cards.get(position)

        binding.cardQuestion.text = getString(R.string.question_field, card.question)
        binding.cardExample.text = getString(R.string.example_field, card.example)
        binding.cardAnswer.text = getString(R.string.answer_field, card.answer)
        binding.cardTranslation.text = getString(R.string.translation_field, card.translation)
        binding.cardThumbnail.setImageURI(card.imageURI)

        binding.editButton.setOnClickListener {
            Intent(this, EditActivity::class.java).apply {
                putExtra("position", position)
            }.also {
                startActivity(it)
            }
        }
    }
}