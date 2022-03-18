package com.tikhonovks.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        }

        public void onButtonClick(View view){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Здравствуй MIREA Тихонов Кирилл Сергеевич",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_banner_foreground);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            toast.setView(imageView);
            toast.show();
        }
}