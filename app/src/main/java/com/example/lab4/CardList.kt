package com.example.lab4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.databinding.ListBinding

class CardList : AppCompatActivity() {
    lateinit var binding: ListBinding  //Будет инициализированно позже - lateinit
    lateinit var adapter: AdapterRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListBinding.inflate(layoutInflater) //Всё по стандарту: раздуваем хмл во вью
        setContentView(binding.root)

        val cards = Model.cards //Получаем лист всех карточек

        val recyclerView: RecyclerView = binding.recyclerId //Получение по id из надутого хмл через биндинг
        recyclerView.layoutManager = LinearLayoutManager(this)//layoutManager отвечает за расположение элементов, а
        //LinearLayoutManager отвечает за расположение относительно параметров элемента, т.е. в качестве layoutManager
        //будет выставлен LinearLayoutManager
        adapter = AdapterRecyclerView(cards) //Установка адаптера
        recyclerView.adapter = adapter

//        binding.addbuttonid.setOnClickListener {//Кнопка добавления нового элемента
//            Intent(this, AddCard::class.java).also {
//                startActivity(it)
//            }
//        }
    }

    override fun onResume() { //Обновление списка
        super.onResume()
        adapter.refreshCardsViewWith(Model.cards)
    }
}

