package com.example.myApp

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myApp.Data.CardsRepository
import com.example.myApp.databinding.ViewCardBinding


class ViewCard : AppCompatActivity() {
    private lateinit var binding: ViewCardBinding
    private var index = NEW_CARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        index = intent.getIntExtra("index", NEW_CARD)

        binding.buttonEditItem.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView() {
        val card = CardsRepository.getCards().getOrNull(index)

        if (card != null) {
            binding.textViewQuestion.text = card.question
            binding.textViewHint.text = card.example
            binding.textViewAnswer.text = card.answer
            binding.textViewTranslation.text = card.translate
            if (card.image != null) {
                binding.imageViewCard.setImageBitmap(card.image)
            } else {
                binding.imageViewCard.setImageDrawable(getDrawable(R.drawable.placeholder))
            }
        } else {
            Toast.makeText(this, "Карточка не найдена", Toast.LENGTH_LONG).show()
            finish()
        }
    }

}
