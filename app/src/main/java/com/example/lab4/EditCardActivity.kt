package com.example.lab4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.databinding.ActivityEditCardBinding

class EditCardActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditCardBinding
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)
        val cards = Model.cards
        val card = cards.get(position)

        binding.questionField.setText(card.question)
        binding.exampleField.setText(card.example)
        binding.answerField.setText(card.answer)
        binding.translationField.setText(card.translation)

        binding.saveButton.setOnClickListener {
            val newCard = Model.updateCard(
                card,
                binding.questionField.text.toString(),
                binding.exampleField.text.toString(),
                binding.answerField.text.toString(),
                binding.translationField.text.toString(),
                imageUri
            )
            Model.updateCardList(position, newCard)
            Intent(this, SeeCardActivity::class.java).apply {
                putExtra("position", position)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }.also {
                startActivity(it)
            }
        }

        binding.cardImage.setOnClickListener {
            getSystemContent.launch("image/*")
        }
    }

    private val getSystemContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
        val name = this.packageName
        this.grantUriPermission(name, it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        binding.cardImage.setImageURI(it)
    }
}