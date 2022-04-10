package com.tikhonovks.mireaproject.ui.history;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tikhonovks.mireaproject.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.StoryViewHolder> {
    private List<Item> items;

    public ItemAdapter(List<Item> stories){
        this.items = stories;
    }

    public void addStoryToList(Item item){
        items.add(item);
    }

    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new StoryViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Item item = items.get(position);
        holder.numbers.setText(item.number);
        holder.ipAdd.setText(item.ip);
        holder.macAdd.setText(item.mac);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder{
        public TextView numbers;
        public TextView ipAdd;
        public TextView macAdd;

        public StoryViewHolder(View itemView) {
            super(itemView);
            numbers = (TextView) itemView.findViewById(R.id.numb);
            ipAdd = (TextView) itemView.findViewById(R.id.ipAdd);
            macAdd = (TextView) itemView.findViewById(R.id.macAdd);

        }
    }
}