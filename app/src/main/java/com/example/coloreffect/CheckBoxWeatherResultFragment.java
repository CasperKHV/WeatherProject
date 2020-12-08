package com.example.coloreffect;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;




public class CheckBoxWeatherResultFragment extends Fragment {

    private TextView pressureText;
    private TextView tomorrowText;
    private TextView weekText;
    private String pressure;
    private String tomorrow;
    private String week;
    public static final String PRESSURE_TAG = "pressure tag";
    public static final String TOMORROW_TAG = "tomorrow tag";
    public static final String WEEK_TAG = "week tag";

    public static CheckBoxWeatherResultFragment init(Bundle bundle) {
        CheckBoxWeatherResultFragment fragment = new CheckBoxWeatherResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_box_weather_result, container, false);

        Bundle bundle = getArguments();
        pressureText = view.findViewById(R.id.textview_pressure);
        tomorrowText = view.findViewById(R.id.textview_tomorrow);
        weekText = view.findViewById(R.id.textview_week);

        if (bundle != null) {
            pressure = bundle.getString(PRESSURE_TAG);
            tomorrow = bundle.getString(TOMORROW_TAG);
            week = bundle.getString(WEEK_TAG);
        }else throw new RuntimeException("Bundle is empty");
        if (pressure != null) {
            pressureText.setVisibility(View.VISIBLE);
            pressureText.setText(pressure);
        }
        if (tomorrow != null) {
            tomorrowText.setVisibility(View.VISIBLE);
            tomorrowText.setText(tomorrow);
        }
        if (week != null) {
            weekText.setVisibility(View.VISIBLE);
            weekText.setText(week);
        }

        return view;
    }



}