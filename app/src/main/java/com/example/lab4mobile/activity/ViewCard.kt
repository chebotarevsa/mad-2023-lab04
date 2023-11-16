package com.example.lab4mobile.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab4mobile.R
import com.example.lab4mobile.persistance.repository.CardRepository
import com.example.lab4mobile.databinding.ViewCardBinding

class ViewCard : AppCompatActivity() {
    private lateinit var binding: ViewCardBinding
    private var index = NEW_CARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //получение данных из интента
        index = intent.getIntExtra("index", NEW_CARD)
        //initView()
    }

    override fun onResume() {
        super.onResume()
        initView()
    }


    private fun initView() {
        val card = CardRepository.findAll()[index]

        binding.questionField.text = card.question
        binding.exampleField.text = card.example
        binding.answerView.text = card.answer
        binding.translateView.text = card.translate

        if (card.image != null) {
            binding.imageView3.setImageBitmap(card.image)
        } else {
            binding.imageView3.setImageResource(R.drawable.ic_image)
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }
    }
}
