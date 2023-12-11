package com.example.myApp.Data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myApp.R
import com.example.myApp.databinding.ItemCardBinding

interface CallbackFun {
    fun showCard(index: Int)
    fun deleteCard(card: TermCard)
}

class CardsAdapter(private val callback: CallbackFun) :
    RecyclerView.Adapter<CardsAdapter.CardHolder>() {

    private var _list: List<TermCard> = emptyList()

    class CardHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    fun setItem(list: List<TermCard>) {
        _list = list
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): TermCard {
        return _list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return CardHolder(binding)
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val item = _list[position]
        holder.binding.textViewItemPrimary.text = item.answer
        holder.binding.textViewItemPrimary.text = item.translate

        if (item.image != null) {
            holder.binding.imageViewItem.setImageBitmap(item.image)
        } else {
            holder.binding.imageViewItem.setImageResource(R.drawable.placeholder)
        }


        holder.itemView.setOnClickListener {
            callback.showCard(position)
        }


        holder.binding.buttonDeleteItem.setOnClickListener {
            callback.deleteCard(item)
            notifyDataSetChanged()
        }
    }
}
