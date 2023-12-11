package com.example.myApp

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.myApp.Data.CardsRepository
import com.example.myApp.databinding.AddCardBinding

const val NEW_CARD = -1

class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: AddCardBinding
    private var imageBIT: Bitmap? = null
    private var index = NEW_CARD

    private val getImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val image = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                    MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                } else {
                    val source = ImageDecoder.createSource(this.contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)
                }
                imageBIT = image
                binding.imageViewCard.setImageBitmap(image)
            }
        }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        index = intent.getIntExtra("index", NEW_CARD)
        loadCardData()

        binding.imageViewCard.setOnClickListener {
            getImage.launch("image/*")
        }

        binding.buttonSaveItem.setOnClickListener {
            addOrUpdateCard()
        }
    }

    private fun loadCardData() {
        if (index != NEW_CARD) {
            val card = CardsRepository.getCards().getOrNull(index)
            card?.let {
                binding.editTextQuestion.setText(it.question)
                binding.editTextHint.setText(it.example)
                binding.editTextAnswer.setText(it.answer)
                binding.editTextTranslation.setText(it.translate)
                imageBIT = it.image
                if (it.image != null) {
                    binding.imageViewCard.setImageBitmap(it.image)
                } else {
                    binding.imageViewCard.setImageDrawable(getDrawable(R.drawable.placeholder))
                }
            }
        }
    }


    private fun addOrUpdateCard() {
        val question = binding.editTextQuestion.text.toString().trim()
        val hint = binding.editTextHint.text.toString().trim()
        val answer = binding.editTextAnswer.text.toString().trim()
        val translate = binding.editTextTranslation.text.toString().trim()

        if (validateFields(question, hint, answer, translate)) {
            val newCard = CardsRepository.createNewCard(question, hint, answer, translate, imageBIT)
            if (index == NEW_CARD) {
                CardsRepository.addCard(newCard)
            } else {
                CardsRepository.replaceCard(newCard, index)
            }
            finish()
        }
    }

    private fun validateFields(
        question: String,
        hint: String,
        answer: String,
        translate: String
    ): Boolean {
        return when {
            question.isEmpty() -> showToast("Введите вопрос").let { false }
            hint.isEmpty() -> showToast("Введите подсказку").let { false }
            answer.isEmpty() -> showToast("Введите ответ").let { false }
            translate.isEmpty() -> showToast("Введите перевод").let { false }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
