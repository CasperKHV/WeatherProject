package com.example.coloreffect;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;


public class WeatherResultFragment extends Fragment implements View.OnClickListener {

    private static final String INNER_FRAGMENT_TAG = "inner_fragment_tag";
    private int[] ID = new int[]{R.drawable.sun, R.drawable.cloud, R.drawable.snow};

    private DataForBundle dataForBundle;
    private TextView weatherText;
    private Button shareButton;
    private String message;
    private ImageView photoWeather;
    public static final String DATA_FOR_BUNDLE = "data for bundle";

    public static WeatherResultFragment newInstance(Serializable dataForBundle) {
        WeatherResultFragment fragment = new WeatherResultFragment();
        fragment.dataForBundle = (DataForBundle) dataForBundle;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_result, container, false);
        photoWeather = view.findViewById(R.id.photoWeather);
        weatherText = view.findViewById(R.id.textview_weather);
        shareButton = view.findViewById(R.id.button_share);
        shareButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            dataForBundle = (DataForBundle) savedInstanceState.getSerializable(DATA_FOR_BUNDLE);
        }

        int photoWeatherInt;
        if (dataForBundle != null) {
            message = dataForBundle.getMessage();
            photoWeatherInt = dataForBundle.getPhotoWeather();
        } else {
            throw new RuntimeException("DataForBundle is empty");
        }

        if (photoWeatherInt != -1) {
            photoWeather.setImageResource(ID[photoWeatherInt]);
        }

        if (message != null) {
            weatherText.setText(message);
            Intent intentResult = new Intent();
            intentResult.putExtra(CitiesListFragment.RESULT_OK_STRING, getResources().getString(R.string.repeat_choose_city));
            getActivity().setResult(Activity.RESULT_OK, intentResult);
        }

        FragmentManager fragmentManager = getChildFragmentManager();
        CheckBoxWeatherResultFragment checkBoxWeatherResultFragment = (CheckBoxWeatherResultFragment) fragmentManager.findFragmentByTag(INNER_FRAGMENT_TAG);
        if (checkBoxWeatherResultFragment == null) {
            String pressure = dataForBundle.getResultPressure();
            String tomorrow = dataForBundle.getResultTomorrow();
            String week = dataForBundle.getResultWeek();
            if (pressure != null || tomorrow != null || week != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                checkBoxWeatherResultFragment = CheckBoxWeatherResultFragment.newInstance(pressure, tomorrow, week);
                fragmentTransaction.replace(R.id.inner_fragment_container, checkBoxWeatherResultFragment, INNER_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }
        }

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_share) {
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("text/plain");

            if (message != null) {
                intentShare.putExtra(Intent.EXTRA_TEXT, message);
            }
            PackageManager packageManager = getActivity().getPackageManager();

            if (!packageManager.queryIntentActivities(intentShare, 0).isEmpty()) {
                startActivity(intentShare);
                shareButton.setBackgroundColor(Color.GREEN);
            } else {
                shareButton.setBackgroundColor(Color.RED);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (dataForBundle != null) {
            outState.putSerializable(DATA_FOR_BUNDLE, dataForBundle);
        }
        super.onSaveInstanceState(outState);
    }
}