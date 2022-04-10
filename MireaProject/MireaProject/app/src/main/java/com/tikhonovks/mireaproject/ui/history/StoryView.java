package com.tikhonovks.mireaproject.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tikhonovks.mireaproject.MainActivity;
import com.tikhonovks.mireaproject.R;

public class StoryView extends AppCompatActivity {

    private EditText number;
    private EditText ipAdd;
    private EditText macAdd;
    private Button button;
    private AppDatebase db;
    private ItemDao itemDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_view);
        db = App.getInstance().getDatabase();
        itemDao = db.itemDao();

        number = findViewById(R.id.nameIdHistory);
        ipAdd = findViewById(R.id.ipAddressEdit1);
        macAdd = findViewById(R.id.macAddressEdit1);

        button = findViewById(R.id.btnSave);
        button.setOnClickListener(this::saveBtn);
    }

    public void saveBtn(View view) {

        Item item =new Item();



        item.number = number.getText().toString();
        item.ip = ipAdd.getText().toString();
        item.mac = macAdd.getText().toString();

        itemDao.insert(item);


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
