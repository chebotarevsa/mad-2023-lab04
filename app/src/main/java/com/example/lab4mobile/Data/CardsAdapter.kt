package com.example.lab4mobile.Data

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lab4mobile.R
import com.example.lab4mobile.ViewCard
import com.example.lab4mobile.databinding.ItemCardBinding


class CardsAdapter: RecyclerView.Adapter<CardsAdapter.CardHolder>()  {

    private var _list:List<TermCard> = emptyList()
    class CardHolder(val binding: ItemCardBinding) : ViewHolder(binding.root) {
//        val textItem = itemView.findViewById<TextView>(R.id.cardsNameTextView)
//        val textItem2 = itemView.findViewById<TextView>(R.id.cardsTranslateTextView)
    }

    fun setItem(list:List<TermCard>) {
        _list = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val  inflater = LayoutInflater.from(parent.context)
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
        if (item.image.isNotEmpty())
            holder.binding.photoImageView.setImageURI(Uri.parse(item.image))
        else
            holder.binding.photoImageView.setImageResource(R.drawable.ic_image)

        //нажатие на элемент карточки
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ViewCard::class.java)
            //передача данных в интент
            intent.putExtra("question", item.question)
            intent.putExtra("example", item.example)
            intent.putExtra("answer", item.answer)
            intent.putExtra("translate", item.translate)
            intent.putExtra("image", item.image)
            intent.putExtra("id", item.id)

            holder.itemView.context.startActivity(intent)
        }

        //установка стиля при нажатии/отпускании
            holder.binding.photoImageViewButtonDelete.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        holder.binding.photoImageViewButtonDelete.setBackgroundResource(R.drawable.round_background)
                    }
                    MotionEvent.ACTION_UP -> {
                        holder.binding.photoImageViewButtonDelete.setBackgroundResource(R.drawable.base_round_background) // Обычный фон
                    }
                }
                true
            }
    }
}

