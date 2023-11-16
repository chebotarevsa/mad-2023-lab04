package com.example.lab4mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab4mobile.activity.AddCardActivity
import com.example.lab4mobile.activity.ViewCard
import com.example.lab4mobile.persistance.repository.CardRepository
import com.example.lab4mobile.databinding.ActivityMainBinding
import com.example.lab4mobile.persistance.CallbackFun
import com.example.lab4mobile.persistance.CardAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CardAdapter(adapterCallBack)
        adapter.setItem(CardRepository.findAll())

        val layoutManager = LinearLayoutManager(this)

        binding.RecyclerView.layoutManager = layoutManager
        binding.RecyclerView.adapter = adapter

        val button = binding.button

        button.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.setItem(CardRepository.findAll())
    }

    private val adapterCallBack = object : CallbackFun {
        override fun deleteCard(id: String) {
            CardRepository.deleteById(id)
        }

        override fun showCard(index: Int) {
            val intent = Intent(this@MainActivity, ViewCard::class.java)
            intent.putExtra("index", index)
            this@MainActivity.startActivity(intent)
        }
    }
}
