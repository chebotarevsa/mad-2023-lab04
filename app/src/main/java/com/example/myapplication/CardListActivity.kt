package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityCardListBinding

class CardListActivity : AppCompatActivity() {
    lateinit var binding: ActivityCardListBinding
    lateinit var adapter: Recycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cards = Cards.cards

        val recyclerView: RecyclerView = binding.recyclerid
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Recycler(cards, this){card->
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Удаление карточки?")
                .setMessage("Вы действительно хотите удалить карточку:\n ${card.translation}")
                .setPositiveButton("Да") { _, _ ->
                    Cards.removeCard(card.id)
                    adapter.setCards(Cards.cards)
                }.setNegativeButton("Нет") { _, _ ->
                }.create()

            alertDialog.setOnShowListener {
                val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                positiveButton.setTextColor(ContextCompat.getColor(this, R.color.red))

                val negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                negativeButton.setTextColor(ContextCompat.getColor(this, R.color.green))
            }

            alertDialog.show()
        }

        recyclerView.adapter = adapter

        adapter.enableSwipeToDelete(recyclerView)

        binding.addButton.setOnClickListener {
            Intent(this, AddCardActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.refreshCardsViewWith(Cards.cards)
    }
}
