package com.example.lab4mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4mobile.Data.CardsAdapter
import com.example.lab4mobile.Data.CardsListener
import com.example.lab4mobile.Data.CardsRepository
import com.example.lab4mobile.Data.TermCard
import com.example.lab4mobile.databinding.ActivityMainBinding
import com.example.lab4mobile.databinding.ItemCardBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CardsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CardsAdapter()
        adapter.setItem(CardsRepository.getCards())
        val layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.layoutManager = layoutManager
        binding.RecyclerView.adapter = adapter
        //binding.RecyclerView.seton


    val button = binding.root.findViewById<Button>(R.id.button)
    button.setOnClickListener {
        val intent = Intent(this, AddCardActivity::class.java)
        startActivity(intent)
        }
    }

    //удаление карточки
//    val delete = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_delete)


    //нажатие на карточку
    fun onCardClicked(termCard: TermCard) {
        val intent = Intent(this, ViewCard::class.java)
        intent.putExtra("cardId", termCard.id)
        startActivity(intent)
    }


    override fun onResume() {
        super.onResume()
        adapter.setItem(CardsRepository.getCards())
    }
}