package com.example.lab4mobile.persistance.repository

import com.example.lab4mobile.persistance.model.Card

typealias CardsListener = (cards: List<Card>) -> Unit

object CardRepository {
    private var cards = mutableListOf<Card>()
    private val listeners = mutableListOf<CardsListener>()

    fun findAll(): List<Card> {
        return cards
    }

    fun deleteById(id: String) {
        cards.removeIf { i -> i.id == id}
    }


    fun save(card: Card) {
        cards.add(card)
    }

    fun replace(card: Card, index: Int){
        cards.removeAt(index)
        cards.add(index, card)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(cards) }
    }
}
