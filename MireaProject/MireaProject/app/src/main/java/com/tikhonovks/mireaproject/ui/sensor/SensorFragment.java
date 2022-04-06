package com.tikhonovks.mireaproject.ui.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tikhonovks.mireaproject.R;


public class SensorFragment extends Fragment implements SensorEventListener {
    private TextView accelerometerSensorValueX;
    private TextView accelerometerSensorValueY;
    private TextView accelerometerSensorValueZ;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    private TextView temperaturelabel;
    private Sensor mTemperature;
    private final static String NOT_SUPPORTED_MESSAGE = "Sorry, sensor not available for this device.";

    private TextView lightSensorValue;
    private Sensor lightSensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sensor, container, false);

        accelerometerSensorValueX = view.findViewById(R.id.accelerometer_sensor_value_x);
        accelerometerSensorValueY = view.findViewById(R.id.accelerometer_sensor_value_y);
        accelerometerSensorValueZ = view.findViewById(R.id.accelerometer_sensor_value_z);

        temperaturelabel = view.findViewById(R.id.temperaturelabel);

        lightSensorValue = view.findViewById(R.id.lightSensor);

        if (getActivity() != null){
            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mTemperature,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEventevent) {
        if (sensorEventevent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float valueAzimuth = sensorEventevent.values[0];
            float valuePitch = sensorEventevent.values[1];
            float valueRoll = sensorEventevent.values[2];
            accelerometerSensorValueX.setText("Azimuth: " + valueAzimuth);
            accelerometerSensorValueY.setText("Pitch: " + valuePitch);
            accelerometerSensorValueZ.setText("Roll: " + valueRoll);
        }
        if(sensorEventevent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            float ambient_temperature = sensorEventevent.values[0];
            temperaturelabel.setText("Ambient Temperature:\n " + String.valueOf(ambient_temperature));
        }
        if (mTemperature == null) {
            temperaturelabel.setText(NOT_SUPPORTED_MESSAGE);
        }
        if(sensorEventevent.sensor.getType() == Sensor.TYPE_LIGHT){
            String newValue = sensorEventevent.values[0] + " лк";
            lightSensorValue.setText(newValue);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}