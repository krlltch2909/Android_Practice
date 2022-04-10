package com.tikhonovks.mireaproject.ui.options;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tikhonovks.mireaproject.R;


public class SettingsFragment extends Fragment {

    private Button updateButoon;
    private EditText ipAddress;
    private EditText macAddress;
    private EditText gateWay;
    private SharedPreferences preferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        updateButoon = view.findViewById(R.id.update);
        updateButoon.setOnClickListener(this::updateButoonClick);

        ipAddress = view.findViewById(R.id.ipAddressEdit);
        macAddress = view.findViewById(R.id.macAddressEdit);
        gateWay = view.findViewById(R.id.gateWayEdit);

        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);;

        try {
            ipAddress.setText(preferences.getString("ip", null));
            macAddress.setText(preferences.getString("mac", null));
            gateWay.setText(preferences.getString("geteway", null));
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return view;
    }


    private void updateButoonClick(View view) {
        Log.d("TAG", "button clicked");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ip", ipAddress.getText().toString());
        editor.putString("mac", macAddress.getText().toString());
        editor.putString("geteway", gateWay.getText().toString());
        editor.apply();
        Toast.makeText(getActivity(), "Update saved", Toast.LENGTH_SHORT).show();
    }
}