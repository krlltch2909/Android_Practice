package com.tikhonovks.mireaproject.ui.ducks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tikhonovks.mireaproject.R;

import java.util.List;

public class DuckAdapter extends RecyclerView.Adapter<DuckAdapter.DuckViewHolder> {
    private List<Duck> ducks;

    public DuckAdapter(List<Duck> ducks){
        this.ducks = ducks;
    }

    @NonNull
    @Override
    public DuckAdapter.DuckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.duck_list, parent, false);

        return new DuckAdapter.DuckViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull DuckAdapter.DuckViewHolder holder, int position) {
        Duck duck = ducks.get(position);
        holder.name.setText(duck.name);
        holder.breed.setText(duck.learned_phrase);
        holder.age.setText(duck.age);
    }

    @Override
    public int getItemCount() {
        return ducks.size();
    }

    public static class DuckViewHolder extends RecyclerView.ViewHolder{
        public final TextView name;
        public final TextView breed;
        public final TextView age;

        public DuckViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.numa1Duck);
            breed = (TextView) itemView.findViewById(R.id.numa2Duck);
            age = (TextView) itemView.findViewById(R.id.numa3Duck);
        }
    }
}