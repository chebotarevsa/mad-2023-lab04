package com.example.lab4mobile.activity

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab4mobile.R
import com.example.lab4mobile.contracts.ImageContract
import com.example.lab4mobile.persistance.repository.CardRepository
import com.example.lab4mobile.persistance.model.Card
import com.example.lab4mobile.databinding.AddCardBinding
import java.util.UUID


const val NEW_CARD = -1

class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: AddCardBinding
    private var imageBIT: Bitmap? = null
    private var index = NEW_CARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AddCardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        index = intent.getIntExtra("index", index)

        if (index != NEW_CARD) {
            val card = CardRepository.findAll()[index]
            // получение данных из интента editButton ViewCard
            binding.editTextText.setText(card.question)
            binding.editTextText2.setText(card.example)
            binding.editTextText3.setText(card.answer)
            binding.editTextText4.setText(card.translate)
            imageBIT = card.image
            if (imageBIT != null) {
                binding.imageView2.setImageBitmap(imageBIT)
            } else {
                binding.imageView2.setImageResource(R.drawable.ic_image)
            }
        }

        binding.imageView2.setOnClickListener {
            getImage.launch("image/*")
//            openImagePicker()
        }

        binding.button.setOnClickListener {
            createCard()
        }
    }

    private val getImage = registerForActivityResult(ImageContract()) { uri ->
        uri?.let {
            val image = ImageDecoder.createSource(this.contentResolver, uri)
            imageBIT = ImageDecoder.decodeBitmap(image)
            binding.imageView2.setImageBitmap(imageBIT)
        }
    }

    private fun createCard() {
        val id = UUID.randomUUID().toString()
        val question = binding.editTextText.text.toString()
        val hint = binding.editTextText2.text.toString()
        val answer = binding.editTextText3.text.toString()
        val translate = binding.editTextText4.text.toString()
        val image = imageBIT

        if (question.isEmpty() || hint.isEmpty() || answer.isEmpty() || translate.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }

        val card = Card(id, question, hint, answer, translate, image)
        if (index == NEW_CARD) {
            CardRepository.save(card)
        } else {
            CardRepository.replace(card, index)
        }

        finish()
    }
}
