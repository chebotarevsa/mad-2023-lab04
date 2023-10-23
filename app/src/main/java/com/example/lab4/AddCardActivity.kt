package com.example.lab4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.databinding.ActivityAddCardBinding

class AddCardActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            Intent(this, CardListActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}