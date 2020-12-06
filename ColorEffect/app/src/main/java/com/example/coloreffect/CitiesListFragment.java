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



public class CitiesListFragment extends Fragment  {

    private static final String SAVED_CITY = "savedCity";
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
        initializePreferences();
        checkBoxPressure.setChecked(savedCity.getBoolean(CHECK_BOX_PRESSURE,false));
        checkBoxTomorrow.setChecked(savedCity.getBoolean(CHECK_BOX_TOMORROW,false));
        checkBoxWeek.setChecked(savedCity.getBoolean(CHECK_BOX_WEEK,false));

        return rootView;

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
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
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
        Bundle bundle = new Bundle();
        if(checkBoxPressure.isChecked()){
            String resultPressure = WeatherSpec.getPressure(getActivity(),categoryId);
//            intent.putExtra(WeatherResultFragment.PRESSURE_TAG,resultPressure);
            bundle.putString(WeatherResultFragment.PRESSURE_TAG,resultPressure);
        }
        if(checkBoxTomorrow.isChecked()){
            String resultTomorrow = WeatherSpec.getTomorrow(getActivity(),categoryId);
//            intent.putExtra(WeatherResultFragment.TOMORROW_TAG,resultTomorrow);
            bundle.putString(WeatherResultFragment.TOMORROW_TAG,resultTomorrow);
        }
        if(checkBoxWeek.isChecked()){
            String resultWeek = WeatherSpec.getWeek(getActivity(),categoryId);
//            intent.putExtra(WeatherResultFragment.WEEK_TAG,resultWeek);
            bundle.putString(WeatherResultFragment.WEEK_TAG,resultWeek);
        }
//        intent.putExtra(WeatherResultFragment.MESSAGE_TAG,resultWeather);
//        intent.putExtra(WeatherResultFragment.PHOTO_WEATHER_TAG,categoryId);
        bundle.putString(WeatherResultFragment.MESSAGE_TAG,resultWeather);
        bundle.putInt(WeatherResultFragment.PHOTO_WEATHER_TAG,categoryId);
        intent.putExtra(WeatherResultFragment.BUNDLE,bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void initializeViews(View view){
        descriptionText = (TextView) view.findViewById(R.id.textview_description);//в третьей версии андроид студии кастовать не надо, но препод показал как было
        checkBoxPressure = view.findViewById(R.id.checkbox_pressure);
        checkBoxTomorrow = view.findViewById(R.id.checkbox_tomorrow);
        checkBoxWeek = view.findViewById(R.id.checkbox_week);
    }



    private void initializePreferences(){
        savedCity = getActivity().getSharedPreferences(SAVED_CITY, Context.MODE_PRIVATE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {// Bundle  это посылка, в которую мы кладём данные; onSaveInstanceState вызывается перед onStop
        savedCity.edit().putBoolean(CHECK_BOX_PRESSURE,checkBoxPressure.isChecked()).apply();
        savedCity.edit().putBoolean(CHECK_BOX_TOMORROW,checkBoxTomorrow.isChecked()).apply();
        savedCity.edit().putBoolean(CHECK_BOX_WEEK,checkBoxWeek.isChecked()).apply();
        super.onSaveInstanceState(outState);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== REQUEST_CODE &&data!=null){
            descriptionText.setText(data.getStringExtra(RESULT_OK_STRING));
        }
    }

}