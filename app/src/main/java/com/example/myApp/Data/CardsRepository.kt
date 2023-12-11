package com.example.myApp.Data

import android.graphics.Bitmap
import java.util.UUID

typealias CardsListener = (cards: List<TermCard>) -> Unit

object CardsRepository {

    private var cards = mutableListOf<TermCard>()

    private val listeners = mutableListOf<CardsListener>()

    init {
        initializeCards()
    }

    private fun initializeCards() {
        val card_1 = TermCard(
            id = "1",
            question = "in a way that gets what you want",
            example = "Teachers need to be able to communicate ideas effectively.",
            answer = "Effectively",
            translate = "Действенно",
            image = null
        )
        cards.add(card_1)

        val card_2 = TermCard(
            id = "2",
            question = "the things that you can see from a place",
            example = "There was a lovely view of the lake from the bedroom window.",
            answer = "View",
            translate = "Пейзаж",
            image = null
        )
        cards.add(card_2)

        val card_3 = TermCard(
            id = "3",
            question = "used to strongly agree with someone",
            example = "“Do you agree?” “Absolutely.”",
            answer = "Absolutely",
            translate = "Безусловно>",
            image = null
        )
        cards.add(card_3)

    }


    fun getCards(): List<TermCard> {
        return cards
    }


    fun deleteCard(cardToDelete: TermCard) {
        val indexToDelete = cards.indexOfFirst { it.id == cardToDelete.id }
        if (indexToDelete != -1) {
            cards.removeAt(indexToDelete)
            notifyChanges()
        }
    }


    fun createNewCard(
        question: String,
        example: String,
        answer: String,
        translate: String,
        image: Bitmap?
    ): TermCard {
        val id = UUID.randomUUID().toString()
        return TermCard(id, question, example, answer, translate, image)
    }


    fun addCard(card: TermCard) {
        cards.add(card)
        notifyChanges()
    }


    fun replaceCard(card: TermCard, index: Int) {
        if (index in cards.indices) {
            cards[index] = card
            notifyChanges()
        }
    }


    fun addListener(listener: CardsListener) {
        listeners.add(listener)
    }


    fun removeListener(listener: CardsListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(cards) }
    }
}
