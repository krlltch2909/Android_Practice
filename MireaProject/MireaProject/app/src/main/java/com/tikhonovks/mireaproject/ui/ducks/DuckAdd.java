package com.tikhonovks.mireaproject.ui.ducks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tikhonovks.mireaproject.MainActivity;
import com.tikhonovks.mireaproject.R;
import com.tikhonovks.mireaproject.ui.history.App;
import com.tikhonovks.mireaproject.ui.history.AppDatebase;

public class DuckAdd extends AppCompatActivity {

    private EditText duckName;
    private EditText duckLearnedPhrase;
    private EditText duckAge;
    private Button button;

    private AppDatebase db;
    private DuckDao duckDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duck_view);

        db = App.getInstance().getDatabase();
        duckDao = db.duckDao();

        duckName = findViewById(R.id.ducknameEdit);
        duckLearnedPhrase = findViewById(R.id.duck_learned_phraseEdit1);
        duckAge = findViewById(R.id.duckAgeEdit1);

        button = findViewById(R.id.btnDuckSave);
        button.setOnClickListener(this::saveBtn);
    }

    public void saveBtn(View view) {

        Duck duck = new Duck();
        duck.name = duckName.getText().toString();
        duck.learned_phrase = duckLearnedPhrase.getText().toString();
        duck.age = duckAge.getText().toString();

        duckDao.insert(duck);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
