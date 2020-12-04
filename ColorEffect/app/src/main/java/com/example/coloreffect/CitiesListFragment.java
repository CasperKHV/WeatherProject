package com.example.coloreffect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class CitiesListFragment extends Fragment implements View.OnClickListener {

    public static final String CITY_SPINNER = "citySpinner";
    private static final String BUNDLE_EXTRAS_SELECTED_CITY = "bundle_extras_selected_city";
    private static final String SAVED_CITY = "savedCity";
    public static final String CITY = "City";
    private SharedPreferences savedCity;
    public static final String RESULT_OK_STRING = "Ok";

    private CheckBox checkBoxPressure;
    private CheckBox checkBoxTomorrow;
    private CheckBox checkBoxWeek;

    private static final String CHECK_BOX_PRESSURE = "checkBoxPressure";
    private static final String CHECK_BOX_TOMORROW = "checkBoxTomorrow";
    private static final String CHECK_BOX_WEEK = "checkBoxWeek";

    public static final int REQUEST_CODE = 100;
    private TextView descriptionText;


//    //Объявим инстанс интерфейса для передачи номера строки списка, нажатой пользователем
//    private CitiesListListener mainActivity;
//
//    //Создаем интерфейс, через который мы будем передавать номер строки списка, нажатой пользователем. Лучше его выносить в отдельный класс, но здесь для примера он внутренний
//    interface CitiesListListener {
//        void onListItemClick(int id);
//    }
//
//    //Инстантиируем наш интерфейс
//    //В коллбеке жизненного цикла фрагмента onAttach
//    // передаётся контекст той активити, которая этот фрагмент создаёт
//    // и содержит его (фрагмент) в себе (в активити).
//    @Override
//    public void onAttach(Context context) {
//        mainActivity = (CitiesListListener) context;
//        super.onAttach(context);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cities_list,container,false);
        RecyclerView cityesCategoriesRecyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cityesCategoriesRecyclerView.setLayoutManager(layoutManager);
        cityesCategoriesRecyclerView.setAdapter(new MyAdapter());


        initializeViews(rootView);
        setListeners();
//        if(savedInstanceState != null){
//            spinnerForCityes.setSelection(savedInstanceState.getInt(BUNDLE_EXTRAS_SELECTED_CITY));
//            checkBoxPressure.setChecked(savedInstanceState.getBoolean(CHECK_BOX_PRESSURE,false));
//            checkBoxTomorrow.setChecked(savedInstanceState.getBoolean(CHECK_BOX_TOMORROW,false));
//            checkBoxWeek.setChecked(savedInstanceState.getBoolean(CHECK_BOX_WEEK,false));
//        }
        initializePreferences();

//        customizeView();


//        spinnerForCityes.setSelection(savedCity.getInt(CITY_SPINNER,0));
        checkBoxPressure.setChecked(savedCity.getBoolean(CHECK_BOX_PRESSURE,false));
        checkBoxTomorrow.setChecked(savedCity.getBoolean(CHECK_BOX_TOMORROW,false));
        checkBoxWeek.setChecked(savedCity.getBoolean(CHECK_BOX_WEEK,false));

        return rootView;





//        View rootView = inflater.inflate(R.layout.workout_list_fragment, container, false);
//
//        RecyclerView workoutRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view); //Найдем наш RecyclerView
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()); //Создадим LinearLayoutManager. Обратите внимание, как мы теперь получаем контекст
//        layoutManager.setOrientation(RecyclerView.VERTICAL);//Обозначим ориентацию
//        workoutRecyclerView.setLayoutManager(layoutManager);//Назначим нашему RecyclerView созданный ранее layoutManager
//        workoutRecyclerView.setAdapter(new MyAdapter());//Назначим нашему RecyclerView адаптер
//        return rootView;//Вернем вью нашего фрагмента нашей Activity

    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int [] ID  = new int[]{R.drawable.khv_image, R.drawable.spb_image, R.drawable.moscow_image};
        private TextView categoryNameTextView;
        private ImageView photo;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.category_list_item, parent, false));
            itemView.setOnClickListener(this);
            categoryNameTextView = (TextView) itemView.findViewById(R.id.category_name_text_view);
            photo = itemView.findViewById(R.id.photo);
        }

        void bind(int position) {
            String category = getResources().getStringArray(R.array.cityes_selection)[position];
            categoryNameTextView.setText(category);
            photo.setImageResource(ID[position]);
        }

        @Override
        public void onClick(View view) {
            showActivity(this.getLayoutPosition());
        }
    }

    //Адаптер для RecyclerView
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new MyViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return getResources().getStringArray(R.array.cityes_selection).length;
        }
    }

    private void showActivity(int categoryId) {
        Intent intent = new Intent(getActivity(),WeatherResult.class);
        String resultWeather = WeatherSpec.getWeather(getActivity(),categoryId);
        if(checkBoxPressure.isChecked()){
            String resultPressure = WeatherSpec.getPressure(getActivity(),categoryId);
            intent.putExtra(WeatherResultFragment.PRESSURE_TAG,resultPressure);
        }
        if(checkBoxTomorrow.isChecked()){
            String resultTomorrow = WeatherSpec.getTomorrow(getActivity(),categoryId);
            intent.putExtra(WeatherResultFragment.TOMORROW_TAG,resultTomorrow);
        }
        if(checkBoxWeek.isChecked()){
            String resultWeek = WeatherSpec.getWeek(getActivity(),categoryId);
            intent.putExtra(WeatherResultFragment.WEEK_TAG,resultWeek);
        }
        int selectedCity = categoryId;
        intent.putExtra(WeatherResultFragment.MESSAGE_TAG,resultWeather);
        intent.putExtra(WeatherResultFragment.PHOTO_WEATHER_TAG,categoryId);
        startActivityForResult(intent, REQUEST_CODE);
//                savedCity.edit().putString("City",resultWeather).apply(); //мой вариант
        savedCity.edit().putInt(CITY_SPINNER,selectedCity).apply();// видимо пишем позицию спиннера в префы
    }

    private void initializeViews(View view){
        descriptionText = (TextView) view.findViewById(R.id.textview_description);//в третьей версии андроид студии кастовать не надо, но препод показал как было
//        showWeatherButton = findViewById(R.id.button_show_weather);
        checkBoxPressure = view.findViewById(R.id.checkbox_pressure);
        checkBoxTomorrow = view.findViewById(R.id.checkbox_tomorrow);
        checkBoxWeek = view.findViewById(R.id.checkbox_week);
    }

    private void setListeners(){
//        showWeatherButton.setOnClickListener(this);
        checkBoxPressure.setOnClickListener(this);
        checkBoxTomorrow.setOnClickListener(this);
        checkBoxWeek.setOnClickListener(this);
    }

    private void initializePreferences(){
        savedCity = getActivity().getSharedPreferences(SAVED_CITY, Context.MODE_PRIVATE);
//        if(!savedCity.getString("City","Empty").equals("Empty")){
//            weatherText.setText(savedCity.getString("City","Empty")); //мой вариант
//            spinnerForCityes.setSelection(savedCity.getInt(CITY_SPINNER,0));
//        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {// Bundle  это посылка, в которую мы кладём данные; onSaveInstanceState вызывается перед onStop
//        outState.putInt(BUNDLE_EXTRAS_SELECTED_CITY,spinnerForCityes.getSelectedItemPosition());
        outState.putBoolean(CHECK_BOX_PRESSURE,checkBoxPressure.isChecked());
        outState.putBoolean(CHECK_BOX_TOMORROW,checkBoxTomorrow.isChecked());
        outState.putBoolean(CHECK_BOX_WEEK,checkBoxWeek.isChecked());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.button_show_description:
//                String result = ColorSpec.getEffect(MainActivity.this,spinnerForColors.getSelectedItemPosition());
//                descriptionText.setText(result);
//                break; // для кнопки с цветами, которую удалил, оставлю для примера
//            case R.id.button_show_weather:
//                Intent intent = new Intent(MainActivity.this,WeatherResult.class);
//                String resultWeather = WeatherSpec.getWeather(MainActivity.this,spinnerForCityes.getSelectedItemPosition());
//                if(checkBoxPressure.isChecked()){
//                    String resultPressure = WeatherSpec.getPressure(MainActivity.this,spinnerForCityes.getSelectedItemPosition());
//                    intent.putExtra(WeatherResult.PRESSURE_TAG,resultPressure);
//                }
//                if(checkBoxTomorrow.isChecked()){
//                    String resultTomorrow = WeatherSpec.getTomorrow(MainActivity.this,spinnerForCityes.getSelectedItemPosition());
//                    intent.putExtra(WeatherResult.TOMORROW_TAG,resultTomorrow);
//                }
//                if(checkBoxWeek.isChecked()){
//                    String resultWeek = WeatherSpec.getWeek(MainActivity.this,spinnerForCityes.getSelectedItemPosition());
//                    intent.putExtra(WeatherResult.WEEK_TAG,resultWeek);
//                }
//                int selectedCity = spinnerForCityes.getSelectedItemPosition();
//                intent.putExtra(WeatherResult.MESSAGE_TAG,resultWeather);
//                startActivityForResult(intent, REQUEST_CODE);
////                savedCity.edit().putString("City",resultWeather).apply(); //мой вариант
//                savedCity.edit().putInt(CITY_SPINNER,selectedCity).apply();// видимо пишем позицию спиннера в префы
//                break;
//            case R.id.checkbox_pressure:
//                savedCity.edit().putBoolean(CHECK_BOX_PRESSURE,checkBoxPressure.isChecked()).apply();
//            case R.id.checkbox_tomorrow:
//                savedCity.edit().putBoolean(CHECK_BOX_TOMORROW,checkBoxTomorrow.isChecked()).apply();
//            case R.id.checkbox_week:
//                savedCity.edit().putBoolean(CHECK_BOX_WEEK,checkBoxWeek.isChecked()).apply();
//
//        }
//
//
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== REQUEST_CODE &&data!=null){
            descriptionText.setText(data.getStringExtra(RESULT_OK_STRING));
        }
    }

//    //При нажатии на элемент списка - вызываем метод интерфейса (то есть метод нашей MainActivity)
//    private void showCitiesScreen(int categoryId) {
//        mainActivity.onListItemClick(categoryId);
//    }
}