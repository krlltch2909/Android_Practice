package com.tikhonovks.mireaproject.ui.ducks;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tikhonovks.mireaproject.R;

import java.util.ArrayList;
import java.util.List;

public class DuckFragment extends Fragment {
    private List<Duck> ducks = new ArrayList<>();
    private DuckViewModel duckViewModel;
    private DuckAdapter duckAdapter = new DuckAdapter(ducks);
    private RecyclerView recyclerView;
    private ActivityResultLauncher<Intent> launcher;
    public static final int ADD_CAT_RESULT_CODE=1;
    public static final String NAME_LABEL="name";
    public static final String BREED_LABEL="breed";
    public static final String AGE_LABEL="age";
    public static final String IMAGE_LABEL="age";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_duck, container, false);

        if (getActivity() != null){
            duckViewModel = new ViewModelProvider(getActivity()).
                    get(DuckViewModel.class);
            duckViewModel.getDucksLiveData().observe(getActivity(), this::onChanged);
        }
        view.findViewById(R.id.addButtonDuck).setOnClickListener(this::onNewCatClicked);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.duckRecyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(duckAdapter);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result) -> {
                    if (result.getResultCode() == ADD_CAT_RESULT_CODE
                            && result.getData() != null){
                        Duck duck = new Duck();
                        duck.name = result.getData().getStringExtra(NAME_LABEL);
                        duck.learned_phrase = result.getData().getStringExtra(BREED_LABEL);
                        duck.age = result.getData().getStringExtra(AGE_LABEL);
                        duckViewModel.addDuck(duck);
                        duckAdapter.notifyDataSetChanged();
                    }
                });
        return view;
    }

    public void onChanged(List<Duck> ducksFromDb) {
        if (!ducks.isEmpty()){
            ducks.clear();
        }
        ducks.addAll(ducksFromDb);
        duckAdapter.notifyDataSetChanged();
    }

    private void onNewCatClicked(View view){
        Intent intent = new Intent(getActivity(), DuckAdd.class);
        launcher.launch(intent);
    }
}
