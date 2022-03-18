package com.tikhonovks.clickbuttons;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvOut;
    private Button buttonOn;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = (TextView) findViewById(R.id.tvOut);
        buttonOn = (Button) findViewById(R.id.buttonOk);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);


        View.OnClickListener onClButOk = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvOut.setText(" Нажата кнопка Ок");
            }
        };
        buttonOn.setOnClickListener(onClButOk);

        View.OnClickListener onClButCancel = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvOut.setText(" Нажата кнопка Cancel");
            }
        };


        buttonCancel.setOnClickListener(onClButCancel);

    }

    public void onMyButtonClick(View view) {
        Toast.makeText(this, "Ещё один способ!", Toast.LENGTH_SHORT).show();
    }
}