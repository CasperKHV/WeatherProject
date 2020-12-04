package com.example.coloreffect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


//    private final String[] ARRAY = getResources().getStringArray(R.array.cityes_selection);

    private static final String TAG = "### MainActivity";







//    Button showWeatherButton;
//    private Spinner spinnerForCityes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate savedInstanceState" + savedInstanceState);
        setContentView(R.layout.activity_main);// Связываем макет (layout) с активити








    }

//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if(v.getId()==R.id.button_show_description){//в данном случае можем не проверять, так как вью у нас один с листенером будет, но если несколько, то проверки обязательно нужны
//                String result = ColorSpec.getEffect(MainActivity.this,spinnerForColors.getSelectedItemPosition());
//                descriptionText.setText(result);
//            }
//        }
//    };







    private void customizeView(){
//        spinnerForCityes.setSelection(savedCity.getInt(CITY_SPINNER,0));
//        weatherText.setText(WeatherSpec.getWeather(MainActivity.this,spinnerForCityes.getSelectedItemPosition()));
    }






    // если кнопок много, то можно сделать, чтобы MainActivity реализовывал интерфейс View.OnClickListener и
    // в строке showDescriptionButton.setOnClickListener(this); мы передаём this
//    @Override
//    public void onClick(View v) {
//        //а впереопределённом методе через switch проверяем id нажатой кнопки
//        switch (v.getId()){
//            case R.id.button_show_description:
//                String result = ColorSpec.getEffect(MainActivity.this,spinnerForColors.getSelectedItemPosition());
//                descriptionText.setText(result);
//                break;//не забываем!!!
//        }
//    }

    // можно вынести определение всех переменных в отдельный метод:
//    private void initViews (){
//        descriptionText = (TextView) findViewById(R.id.textview_description);//в третьей версии андроид студии кастовать не надо, но препод показал как было
//        spinnerForColors = (Spinner) findViewById(R.id.spiner_for_colors);
//        Button showDescriptionButton = (Button) findViewById(R.id.button_show_description);
//        showDescriptionButton.setOnClickListener(onClickListener);
//    }
//    а в onCreate добавить строку вызова метода - initViews();

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


//    @Override
//    public void onListItemClick(int id) {
//
//
//    }
}
