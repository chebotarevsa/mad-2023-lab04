package com.example.lab4

import android.net.Uri

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

    fun removeCard(id: Int) { //Всё просто - удаление по id
        _cards.removeIf {
            it.id == id
        }
    }

    fun addCard(card: Card) { //Всё просто - добавления нового элемента
        _cards.add(card)
    }
    fun createNewCard(
        question: String, example: String, answer: String, translation: String, imageURI: Uri?
    ): Card {
        val nextId = _cards.maxBy { it.id }.id + 1 //Ищется максимальный id среди всех элементов списка, найденное присваивается nextId, после +1
        val card = Card(nextId, question, example, answer, translation, imageURI)
        return card
    }
}