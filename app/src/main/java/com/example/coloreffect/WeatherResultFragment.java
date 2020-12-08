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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class WeatherResultFragment extends Fragment implements View.OnClickListener {

    private static final String INNER_FRAGMENT_TAG = "inner_fragment_tag";
    private int[] ID = new int[]{R.drawable.sun, R.drawable.cloud, R.drawable.snow};

    private TextView weatherText;
    private Button shareButton;
    private Intent intentShare;
    private String message;
    private int photoWeatherInt;
    private ImageView photoWeather;
    public static final String MESSAGE_TAG = "message tag";
    public static final String PRESSURE_TAG = "pressure tag";
    public static final String TOMORROW_TAG = "tomorrow tag";
    public static final String WEEK_TAG = "week tag";
    public static final String PHOTO_WEATHER_TAG = "photo weather tag";
    public static final String BUNDLE = "bundle";
    public static final String BUNDLE_EXTRA_MESSAGE = "bundle extra message";

    public static WeatherResultFragment init(Bundle bundle) {
        WeatherResultFragment fragment = new WeatherResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_result, container, false);

        Bundle bundle = getArguments();
        photoWeather = view.findViewById(R.id.photoWeather);
        weatherText = view.findViewById(R.id.textview_weather);

        if (savedInstanceState != null) {
            weatherText.setText(savedInstanceState.getString(BUNDLE_EXTRA_MESSAGE));
        }
        if (bundle != null) {
            message = bundle.getString(MESSAGE_TAG);
            photoWeatherInt = bundle.getInt(PHOTO_WEATHER_TAG, -1);
        }else throw new RuntimeException("Bundle is empty");
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
        if(checkBoxWeatherResultFragment==null){
            if(bundle.getString(PRESSURE_TAG)!=null||bundle.getString(TOMORROW_TAG)!=null||bundle.getString(WEEK_TAG)!=null){
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                checkBoxWeatherResultFragment = CheckBoxWeatherResultFragment.init(bundle);
                fragmentTransaction.replace(R.id.inner_fragment_container, checkBoxWeatherResultFragment, INNER_FRAGMENT_TAG);//здесь мы благодаря третьему аргументу присваиваем фрагменту тэг
                // и можем в коде выше проверять по этому тэгу, создан ли фрагмент
                fragmentTransaction.commit();
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
            if (!packageManager.queryIntentActivities(intentShare, 0).isEmpty()) {
//            if (intentShare.resolveActivity(getPackageManager()) != null) { // мой вариант
                startActivity(intentShare);
                shareButton.setBackgroundColor(Color.GREEN);


            } else {
                shareButton.setBackgroundColor(Color.RED);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {// Bundle  это посылка, в которую мы кладём данные; onSaveInstanceState вызывается перед onStop
        if (message != null) {
            outState.putString(BUNDLE_EXTRA_MESSAGE, message);
        }

        super.onSaveInstanceState(outState);
    }
}