package com.example.myApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog
import com.example.myApp.Data.CallbackFun
import com.example.myApp.Data.CardsAdapter
import com.example.myApp.Data.CardsRepository
import com.example.myApp.Data.TermCard
import com.example.myApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSwipeToDelete()
        setupAddButton()
    }

    override fun onResume() {
        super.onResume()
        updateCards()
    }

    private fun setupRecyclerView() {
        adapter = CardsAdapter(adapterCallBack)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        updateCards()
    }

    private fun setupAddButton() {
        binding.buttonAddItem.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateCards() {
        adapter.setItem(CardsRepository.getCards())
        adapter.notifyDataSetChanged()
    }

    private val adapterCallBack = object : CallbackFun {
        override fun deleteCard(card: TermCard) {
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle("Подтвердите удаление")
                setMessage("Вы уверены, что хотите удалить эту карточку?")
                setPositiveButton("Удалить") { dialog, which ->
                    CardsRepository.deleteCard(card)
                    adapter.notifyDataSetChanged() // Обновляем адаптер после удаления
                }
                setNegativeButton("Отмена", null)
            }.create().show()
        }

        override fun showCard(index: Int) {
            val intent = Intent(this@MainActivity, ViewCard::class.java)
            intent.putExtra("index", index)
            this@MainActivity.startActivity(intent)
        }
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val card = adapter.getItemAtPosition(position)
                adapterCallBack.deleteCard(card)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}
