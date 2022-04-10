package com.tikhonovks.mireaproject.ui.history;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tikhonovks.mireaproject.R;
import com.tikhonovks.mireaproject.ui.player.Player;
import com.tikhonovks.mireaproject.ui.player.PlayerFragment;

import java.util.ArrayList;
import java.util.List;


public class StoriesFragment extends Fragment {

    static int num =0;
    private List<Item> items;
    private RecyclerView recyclerView;
    private Button newStory;
    private ItemDao itemDao;
    private AppDatebase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = App.getInstance().getDatabase();

        itemDao = db.itemDao();
        items = itemDao.getAll();

        Log.d("xyq", String.valueOf(items.size()));
        View view = inflater.inflate(R.layout.fragment_stories, container, false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        for (Item it: items){
            Log.d("StoriesFragment", it.number);
        }
        newStory = view.findViewById(R.id.button);
        newStory.setOnClickListener(this::onClick);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        ItemAdapter adapter = new ItemAdapter(items);
        recyclerView.setAdapter(adapter);

        return view;
    }


    public void onClick(View view) {
       Intent intent = new Intent(view.getContext(), StoryView.class);
       startActivity(intent);

    }
}