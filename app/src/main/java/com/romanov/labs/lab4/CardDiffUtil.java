package com.romanov.labs.lab4;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class CardDiffUtil extends DiffUtil.Callback {

    private final List<Card> oldList;
    private final List<Card> newList;

    public CardDiffUtil(List<Card> oldList, List<Card> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Card oldCard = oldList.get(oldItemPosition);
        Card newCard = newList.get(newItemPosition);
        return oldCard.getId() == newCard.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Card oldCard = oldList.get(oldItemPosition);
        Card newCard = newList.get(newItemPosition);
        return oldCard.equals(newCard);
    }
}

