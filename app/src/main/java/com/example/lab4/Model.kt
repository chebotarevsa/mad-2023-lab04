package com.example.lab4

//import android.net.Uri

object Model {
    private val _cards = mutableListOf( //Создание списка mutableListOf
        Card(
            0,
            "what we usually say when we meet",
            "... Ben, how are you?",
            "Hello",
            "Привет"
        ), Card(
            1,
            "what surrounds us, what includes each person",
            "this news has spread all over the ...",
            "World",
            "Мир"
        )
    )
    val cards get() = _cards.toList() //Создаётся переменная cards, которая содержит в себе весь список элементов, что-то типа статической переменной


}