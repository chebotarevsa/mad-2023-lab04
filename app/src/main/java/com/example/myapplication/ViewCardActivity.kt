package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityViewCardBinding

class ViewCardActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewCardBinding

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)
        val card = Cards.cards.get(position)

        binding.cardQuestion.text = getString(R.string.questionField, card.question)
        binding.cardExample.text = getString(R.string.exampleField, card.example)
        binding.cardAnswer.text = getString(R.string.answerField, card.answer)
        binding.cardTranslation.text = getString(R.string.translationField, card.translation)
        binding.cardThumbnail.setImageURI(card.imageURI)

        binding.editButton.setOnClickListener {
            Intent(this, EditCardActivity::class.java).apply {
                putExtra("position", position)
            }.also {
                startActivity(it)
            }
        }
    }
}