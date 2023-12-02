package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAddCardBinding

class AddCardActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddCardBinding
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardImage.setOnClickListener {
            getSystemContent.launch("image/*")
        }

        binding.addButton.setOnClickListener {
            val question = when {
                binding.setQuestion.text.toString()
                    .isNotEmpty() -> binding.setQuestion.text.toString()

                else -> "Поле вопроса отсутствует"
            }
            val example = when {
                binding.setExample.text.toString()
                    .isNotEmpty() -> binding.setExample.text.toString()

                else -> "Поле примера отсутствует"
            }
            val answer = when {
                binding.setAnswer.text.toString()
                    .isNotEmpty() -> binding.setAnswer.text.toString()

                else -> "Поле ответа отсутствует"
            }
            val translation = when {
                binding.setTranslation.text.toString()
                    .isNotEmpty() -> binding.setTranslation.text.toString()

                else -> "Поле перевода отсутствует"
            }
            val newCard = Cards.createNewCard(
                question, example, answer, translation, imageUri
            )
            Cards.addCard(newCard)
            Intent(this, CardListActivity::class.java).also {
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