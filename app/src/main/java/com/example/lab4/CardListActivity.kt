package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.databinding.ActivityListCardBinding

class CardListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListCardBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cards = listOf<Card>(
            Card(
                1,
                "to choose a small number of things, or to choose by making careful decisions",
                "here was a choice of four prizes, and the winner could ... one of them",
                "Select",
                "Выбрать"
            ),
            Card(
                2,
                "to make something more modern or suitable for use now by adding new information or changing its design",
                "an ...ed version of the software",
                "Update",
                "Обновить"
            ),
            Card(
                3,
                "to take something away",
                "An operation was needed to ... the bullets from his chest",
                "Remove",
                "Удалить"
            ),
            Card(
                4,
                "to make something happen or exist",
                "The project will ... more than 500 jobs",
                "Create",
                "Создать"
            )
        )


        val recyclerView: RecyclerView = binding.recyclerid
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter(cards)

    }


}

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
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView.text = cards[position].answer
        holder.smallTextView.text = cards[position].translation
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, SeeCardActivity::class.java);
            startActivity(it.context, intent, Bundle())
        }
    }
}