package com.example.lab4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.databinding.ActivityListCardBinding

class CardListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListCardBinding
    lateinit var adapter: CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cards = Model.cards

        val recyclerView: RecyclerView = binding.recyclerid
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomRecyclerAdapter(cards)
        recyclerView.adapter = adapter
        /*
                contentURI:URI
                 imageView.setImageURI(contentURI);*/


        binding.addbuttonid.setOnClickListener {
            Intent(this, AddCardActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.setCards(Model.cards)
    }
}

