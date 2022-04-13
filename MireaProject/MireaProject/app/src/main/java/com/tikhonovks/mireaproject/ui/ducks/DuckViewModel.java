package com.tikhonovks.mireaproject.ui.ducks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tikhonovks.mireaproject.ui.history.App;
import com.tikhonovks.mireaproject.ui.history.AppDatebase;

import java.util.List;

public class DuckViewModel extends ViewModel {
    private final LiveData<List<Duck>> ducks;
    private final AppDatebase appDatabase = App.instance.getDatabase();
    private final DuckDao duckDao = appDatabase.duckDao();

    public DuckViewModel(){
        ducks = duckDao.getAllDuck();
    }

    public void addDuck(Duck duck){
        duckDao.insert(duck);
    }

    public LiveData<List<Duck>> getDucksLiveData(){
        return ducks;
    }
    public List<Duck> getDucks(){
        return ducks.getValue();
    }
}