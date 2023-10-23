package com.example.lab4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.databinding.ActivityListCardBinding

class CardListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cards = listOf<Card>(
            Card(
                1,
                "to choose a small number of things, or to choose by making careful decisions",
                "here was a choice of four prizes, and the winner could ... one of them",
                "Select",
                "Выбрать"
            ),
            Card(
                2,
                "to make something more modern or suitable for use now by adding new information or changing its design",
                "an ...ed version of the software",
                "Update",
                "Обновить"
            ),
            Card(
                3,
                "to take something away",
                "An operation was needed to ... the bullets from his chest",
                "Remove",
                "Удалить"
            ),
            Card(
                4,
                "to make something happen or exist",
                "The project will ... more than 500 jobs",
                "Create",
                "Создать"
            )
        )


        val recyclerView: RecyclerView = binding.recyclerid
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter(cards)

        binding.addbuttonid.setOnClickListener {
            Intent(this, AddCardActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}

