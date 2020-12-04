package com.example.coloreffect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WeatherResult extends AppCompatActivity {

    private static final String TAG = "### WeatherResult";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate savedInstanceState" + savedInstanceState);
        setContentView(R.layout.activity_weather_result);

        WeatherResultFragment resultFragment = new WeatherResultFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,resultFragment);
        transaction.commit();


        //Инстантируем фрагмент
//        WorkoutDetailFragment detailFragment = new WorkoutDetailFragment();
//        //Сеттим то упражнение, которое хотим показать
//        detailFragment.setWorkout(id);
//        //Начинаем Транзакцию Фрагмента через SupportFragmentManager
//        // (класс, который управляет созданием и хранением фрагментов в системе)
//        // именно фрагмент менеджер создаёт фрагменты, хранит у себя в памяти ссылки на фрагменты и когда нужно
//        // (например, при пересоздании активити при повороте экрана) приаттачивает (привязывает) их к активити
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        //Указываем, какой фрагмент и в какой контейнер хотим поместить
//        transaction.add(R.id.fragment_container,detailFragment);
//        //transaction.replace() заменяет в бекстеке предыдущий фрагмент, а transaction.add добавляет новый фрагмент в бекстек после предудущего
//        //Запускаем транзакцию (остальное берёт на себя система)
//        transaction.commit();






    }



    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }


}
