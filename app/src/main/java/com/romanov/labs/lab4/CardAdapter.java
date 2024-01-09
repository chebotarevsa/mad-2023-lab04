package com.romanov.labs.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Card> cardList;
    private final OnSwipeListener swipeListener;

    public interface OnSwipeListener {
        void onSwipe(int position);
    }

    CardAdapter(Context context, List<Card> cards, OnSwipeListener swipeListener) {
        this.cardList = cards;
        this.inflater = LayoutInflater.from(context);
        this.swipeListener = swipeListener;
    }
    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                holder.imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                // Теперь imageView.getWidth() и imageView.getHeight() содержат корректные размеры
                Picasso.get()
                        .load(card.getImageURI())
                        .resize(holder.imageView.getWidth(), holder.imageView.getHeight())
                        .centerCrop()
                        .into(holder.imageView);
                return true;
            }
        });
        holder.answerView.setText(card.getAnswer());
        holder.translateView.setText(card.getTranslate());
    }
    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView answerView, translateView;
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.imageView);
            answerView = view.findViewById(R.id.answerTextView);
            translateView = view.findViewById(R.id.translateTextView);
        }
    }

    // Метод для удаления элемента по позиции
    public void removeItem(int position) {
        // ... удаление элемента из списка ...
        notifyItemRemoved(position);
    }

}