package com.example.lab4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)
        val cards = CardService.cards
        val card = cards.get(position)

        binding.questionField.setText(card.question)
        binding.exampleField.setText(card.example)
        binding.answerField.setText(card.answer)
        binding.translationField.setText(card.translation)
        binding.cardImage.setImageURI(card.imageURI)
        imageUri = card.imageURI

        binding.cardImage.setOnClickListener {
            getSystemContent.launch("image/*")
        }

        binding.saveButton.setOnClickListener {
            val question = when {
                binding.questionField.text.toString()
                    .isNotEmpty() -> binding.questionField.text.toString()

                else -> getString(R.string.question_field_empty)
            }
            val example = when {
                binding.exampleField.text.toString()
                    .isNotEmpty() -> binding.exampleField.text.toString()

                else -> getString(R.string.example_field_empty)
            }
            val answer = when {
                binding.answerField.text.toString()
                    .isNotEmpty() -> binding.answerField.text.toString()

                else -> getString(R.string.answer_field_empty)
            }
            val translation = when {
                binding.translationField.text.toString()
                    .isNotEmpty() -> binding.translationField.text.toString()

                else -> getString(R.string.translation_field_empty)
            }
            val newCard = CardService.updateCard(
                card,
                question, example, answer, translation, imageUri
            )
            CardService.updateCardList(position, newCard)
            Intent(this, SeeActivity::class.java).apply {
                putExtra("position", position)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }.also {
                startActivity(it)
            }
        }

    }

    private val getSystemContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
        val name = this.packageName
        this.grantUriPermission(name, it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        binding.cardImage.setImageURI(it)
    }
}