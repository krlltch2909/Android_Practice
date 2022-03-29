package com.tikhonovks.mireaproject.ui.calculator;

import android.media.VolumeShaper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tikhonovks.mireaproject.R;

public class CalculatorFragment extends Fragment {

    private TextView result;

    private int firstNum = 0;
    private int secondNum = 0;
    private String operation = "+";

    private String prevNumber = "";
    private String currentNumber = "";
    private VolumeShaper.Operation prevOperation;
    private boolean operationFinished = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        result = (TextView) view.findViewById(R.id.textView);

        view.findViewById(R.id.button1).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button2).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button3).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button4).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button5).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button6).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button7).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button8).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button9).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button0).setOnClickListener(this::onNumberButtonClick);

        view.findViewById(R.id.buttonDevision).setOnClickListener(this::onButtonOperationClick);
        view.findViewById(R.id.buttonMinus).setOnClickListener(this::onButtonOperationClick);
        view.findViewById(R.id.buttonPlus).setOnClickListener(this::onButtonOperationClick);
        view.findViewById(R.id.buttonMult).setOnClickListener(this::onButtonOperationClick);

        view.findViewById(R.id.buttonClear).setOnClickListener(this::onClearButtonClick);

        view.findViewById(R.id.buttonEnd).setOnClickListener(this::onEqualsButtonClick);

        return view;
    }

    private void onNumberButtonClick(View view) {

        String curText = result.getText().toString();
        Button button = (Button) view;
        curText += button.getText().toString();

        result.setText(curText);
    }

    private void onClearButtonClick(View view){
        result.setText(currentNumber);
    }

    private void onEqualsButtonClick(View view){

        secondNum = Integer.parseInt(result.getText().toString());

        double res = 0;
        switch (operation){
            case "+":
                res = firstNum + secondNum;
                break;
            case "-":
                res = firstNum - secondNum;
                break;
            case "/":
                res = firstNum / secondNum;
                break;
            case "*":
                res = firstNum * secondNum;
                break;
        }

        result.setText(firstNum+" "+operation+" "+secondNum+"="+res);
    }

       // performOperation();
        ///currentNumber = result.getText().toString();

    public void onButtonOperationClick(View view){
        firstNum = Integer.parseInt(result.getText().toString());

        operation = ((Button) view).getText().toString();

        result.setText("");
    }

}