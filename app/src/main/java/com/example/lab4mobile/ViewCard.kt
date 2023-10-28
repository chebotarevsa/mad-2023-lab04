package com.example.lab4mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ViewCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_card)
        // Получите данные из интента
        val question = intent.getStringExtra("question")
        val example = intent.getStringExtra("example")
        val answer = intent.getStringExtra("answer")
        val translate = intent.getStringExtra("translate")
        val image = intent.getStringExtra("image")

        val imageView = findViewById<ImageView>(R.id.imageView3)
        val questionTextView = findViewById<TextView>(R.id.questionField)
        val exampleTextView = findViewById<TextView>(R.id.exampleField)
        val answerTextView = findViewById<TextView>(R.id.answerView)
        val translateTextView = findViewById<TextView>(R.id.translateView)

        //проблема с полечением картинки! Уточнить!
        questionTextView.text = question
        exampleTextView.text = example
        answerTextView.text = answer
        translateTextView.text = translate
    }
}