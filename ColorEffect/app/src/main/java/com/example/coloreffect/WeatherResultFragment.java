package com.example.coloreffect;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class WeatherResultFragment extends Fragment implements View.OnClickListener {

    private int [] ID  = new int[]{R.drawable.sun, R.drawable.cloud, R.drawable.snow};

    private static final String BUNDLE_EXTRAS_MESSAGE = "bundle_extras_message";
    private TextView weatherText;
    private TextView pressureText;
    private TextView tomorrowText;
    private TextView weekText;
    private Button shareButton;
    private Intent intentShare;
    private String message;
    private String pressure;
    private String tomorrow;
    private String week;
    private int photoWeatherInt;
    private ImageView photoWeather;
    public static final String MESSAGE_TAG = "message tag";
    public static final String PRESSURE_TAG = "pressure tag";
    public static final String TOMORROW_TAG = "tomorrow tag";
    public static final String WEEK_TAG = "week tag";
    public static final String PHOTO_WEATHER_TAG = "photo weather tag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_result,container,false);

        photoWeather = view.findViewById(R.id.photoWeather);
        weatherText = view.findViewById(R.id.textview_weather);
        pressureText = view.findViewById(R.id.textview_pressure);
        tomorrowText = view.findViewById(R.id.textview_tomorrow);
        weekText = view.findViewById(R.id.textview_week);

        if (savedInstanceState != null){
            weatherText.setText(savedInstanceState.getString(BUNDLE_EXTRAS_MESSAGE));
        }
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            message = intent.getStringExtra(MESSAGE_TAG);
            pressure = intent.getStringExtra(PRESSURE_TAG);
            tomorrow = intent.getStringExtra(TOMORROW_TAG);
            week = intent.getStringExtra(WEEK_TAG);
            photoWeatherInt = intent.getIntExtra(PHOTO_WEATHER_TAG,-1);
            if(photoWeatherInt!=-1){
                photoWeather.setImageResource(ID[photoWeatherInt]);
            }
            if (message != null) {
                weatherText.setText(message);
                Intent intentResult = new Intent();
                intentResult.putExtra(CitiesListFragment.RESULT_OK_STRING, getResources().getString(R.string.repeat_choose_city));
                getActivity().setResult(Activity.RESULT_OK, intentResult);
            }
            if(pressure != null){
                pressureText.setVisibility(View.VISIBLE);
                pressureText.setText(pressure);
            }
            if(tomorrow != null){
                tomorrowText.setVisibility(View.VISIBLE);
                tomorrowText.setText(tomorrow);
            }
            if(week != null){
                weekText.setVisibility(View.VISIBLE);
                weekText.setText(week);
            }

        }

        shareButton = view.findViewById(R.id.button_share);
        shareButton.setOnClickListener(this);

        return view;


//        //Надуваем Фрагмент
//        View view = inflater.inflate(R.layout.fragment_workout_detail, container, false);
//        //Проверяем, есть ли что-то в savedInstanceState
//        if (savedInstanceState != null) {
//            workoutId = savedInstanceState.getInt(WORKOUT_ID);
//        }
//        //Создаем все наши вью
//        TextView title = (TextView) view.findViewById(R.id.textTitle);
//        Workout workout = Workout.workouts[workoutId];
//        title.setText(workout.getName());
//        TextView description = (TextView) view.findViewById(R.id.textDescription);
//        description.setText(workout.getDescription());
//        ImageView imageResourceId = (ImageView) view.findViewById(R.id.image_workout);
//        imageResourceId.setImageResource(workout.getImageResourceId());
//        return view;//Возвращаем нашей активити, в которой Фрагмент и создается
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_share) {
            intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("text/plain");
            if (message != null) {
                intentShare.putExtra(Intent.EXTRA_TEXT, message);
            }
//            Intent chooserIntent = Intent.createChooser(intentShare,"Выбор приложения:");
            PackageManager packageManager = getActivity().getPackageManager();
            if (!packageManager.queryIntentActivities(intentShare,0).isEmpty()) {
//            if (intentShare.resolveActivity(getPackageManager()) != null) { // мой вариант
                startActivity(intentShare);
                shareButton.setBackgroundColor(Color.GREEN);


            }else {
                shareButton.setBackgroundColor(Color.RED);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {// Bundle  это посылка, в которую мы кладём данные; onSaveInstanceState вызывается перед onStop
        if(message != null){
            outState.putString(BUNDLE_EXTRAS_MESSAGE,message);
        }

        super.onSaveInstanceState(outState);
    }
}