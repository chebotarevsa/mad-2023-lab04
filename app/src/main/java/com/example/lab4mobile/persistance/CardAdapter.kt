package com.example.lab4mobile.persistance

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lab4mobile.R
import com.example.lab4mobile.databinding.ItemCardBinding
import com.example.lab4mobile.persistance.model.Card

interface CallbackFun {
    fun showCard(index: Int): Unit
    fun deleteCard(id: String): Unit
}

class CardAdapter(private val callback: CallbackFun) :
    RecyclerView.Adapter<CardAdapter.CardHolder>() {

    private var _list: List<Card> = emptyList()

    class CardHolder(val binding: ItemCardBinding) : ViewHolder(binding.root) {
    }

    fun setItem(list: List<Card>) {
        _list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding2 = ItemCardBinding.inflate(inflater, parent, false)
        return CardHolder(binding2)
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val item = _list[position]
        holder.binding.cardsNameTextView.text = item.answer
        holder.binding.cardsTranslateTextView.text = item.translate

        if (item.image != null)
            holder.binding.photoImageView.setImageBitmap(item.image)
        else
            holder.binding.photoImageView.setImageResource(R.drawable.ic_image)

        holder.itemView.setOnClickListener {
            callback.showCard(position)
        }

        holder.binding.photoImageViewButtonDelete.setOnTouchListener { view, motionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    holder.binding.photoImageViewButtonDelete.setBackgroundResource(R.drawable.round_background)
                }

                MotionEvent.ACTION_UP -> {
                    holder.binding.photoImageViewButtonDelete.setBackgroundResource(R.drawable.base_round_background)
                    callback.deleteCard(item.id)
                    notifyDataSetChanged()
                }
            }
            true
        }
    }
}

