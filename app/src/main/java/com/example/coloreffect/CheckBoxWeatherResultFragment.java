package com.example.coloreffect;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class CheckBoxWeatherResultFragment extends Fragment {

    TextView pressureTextView;
    TextView tomorrowTextView;
    TextView weekTextView;
    String pressure;
    String tomorrow;
    String week;

    public static CheckBoxWeatherResultFragment newInstance(String pressure, String tomorrow, String week) {
        CheckBoxWeatherResultFragment fragment = new CheckBoxWeatherResultFragment();
        fragment.pressure = pressure;
        fragment.tomorrow = tomorrow;
        fragment.week = week;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_box_weather_result, container, false);
        pressureTextView = view.findViewById(R.id.textview_pressure);
        tomorrowTextView = view.findViewById(R.id.textview_tomorrow);
        weekTextView = view.findViewById(R.id.textview_week);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (pressure != null) {
            pressureTextView.setVisibility(View.VISIBLE);
            pressureTextView.setText(pressure);
        }

        if (tomorrow != null) {
            tomorrowTextView.setVisibility(View.VISIBLE);
            tomorrowTextView.setText(tomorrow);
        }

        if (week != null) {
            weekTextView.setVisibility(View.VISIBLE);
            weekTextView.setText(week);
        }


    }


}