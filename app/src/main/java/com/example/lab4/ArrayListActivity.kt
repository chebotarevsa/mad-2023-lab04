package com.example.lab4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.databinding.ActivityArraylistBinding

class ArrayListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArraylistBinding
    private lateinit var adapter: CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArraylistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cards = CardService.cards
        val recyclerView: RecyclerView = binding.recyclerid
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomRecyclerAdapter(cards)
        recyclerView.adapter = adapter
        binding.addbuttonid.setOnClickListener {
            Intent(this, AddActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.refreshCardsViewWith(CardService.cards)
    }
}