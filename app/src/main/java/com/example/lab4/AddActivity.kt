package com.example.lab4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardImage.setOnClickListener {
            getSystemContent.launch("image/*")
        }

        binding.addButton.setOnClickListener {
            val question = when {
                binding.enterQuestion.text.toString()
                    .isNotEmpty() -> binding.enterQuestion.text.toString()

                else -> getString(R.string.question_field_empty)
            }
            val example = when {
                binding.enterExample.text.toString()
                    .isNotEmpty() -> binding.enterExample.text.toString()

                else -> getString(R.string.example_field_empty)
            }
            val answer = when {
                binding.enterAnswer.text.toString()
                    .isNotEmpty() -> binding.enterAnswer.text.toString()

                else -> getString(R.string.answer_field_empty)
            }
            val translation = when {
                binding.enterTranslation.text.toString()
                    .isNotEmpty() -> binding.enterTranslation.text.toString()

                else -> getString(R.string.translation_field_empty)
            }
            val newCard = CardService.createNewCard(
                question, example, answer, translation, imageUri
            )
            CardService.addCard(newCard)
            Intent(this, ArrayListActivity::class.java).also {
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