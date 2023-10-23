package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(private val cards: List<Card>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageThumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        val largeTextView: TextView = itemView.findViewById(R.id.textViewLarge)
        val smallTextView: TextView = itemView.findViewById(R.id.textViewSmall)
        val imageDeleter: ImageView = itemView.findViewById(R.id.deleter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView.text = cards[position].answer
        holder.smallTextView.text = cards[position].translation
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, SeeCardActivity::class.java)
            intent.putExtra("id", cards[position].id)
            intent.putExtra("question", cards[position].question)
            intent.putExtra("example", cards[position].example)
            intent.putExtra("answer", cards[position].answer)
            intent.putExtra("translation", cards[position].translation)
            ContextCompat.startActivity(it.context, intent, Bundle())
        }
    }
}