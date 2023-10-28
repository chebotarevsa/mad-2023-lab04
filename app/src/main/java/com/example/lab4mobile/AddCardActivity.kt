package com.example.lab4mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab4mobile.Data.CardsRepository
import com.example.lab4mobile.Data.TermCard
import com.example.lab4mobile.databinding.AddCardBinding
import java.util.UUID

class AddCardActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_PICK = 1
    private lateinit var binding: AddCardBinding
    private var imageURI = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddCardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageView2.setOnClickListener {
            openImagePicker()
        }

        binding.button.setOnClickListener {
            addTermCard()
        }

    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            val selectedImageUri = data?.data
            imageURI = selectedImageUri.toString()
            binding.imageView2.setImageURI(selectedImageUri)
        }
    }

    //добавление карточки
    private fun addTermCard() {
        val question = binding.editTextText.text.toString()
        val hint = binding.editTextText2.text.toString()
        val answer = binding.editTextText3.text.toString()
        val translate = binding.editTextText4.text.toString()

        val  newCard = CardsRepository.createNewCard(question,hint,answer,translate,imageURI)
        CardsRepository.addCard(newCard)
        finish()
    }
}