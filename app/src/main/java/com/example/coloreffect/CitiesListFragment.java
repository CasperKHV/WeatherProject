package com.example.coloreffect;

import android.content.Context;
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


public class CitiesListFragment extends Fragment {


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

    private CitiesListListener citiesListListener;

    interface CitiesListListener {
        void onListItemClick(int id, DataForBundle dataForBundle, TextView descriptionText);
    }


    @Override
    public void onAttach(Context context) {
        try {
            citiesListListener = (CitiesListListener) context;
        } catch (ClassCastException | NullPointerException e) {
            throw new IllegalArgumentException(context.toString() + " must implement CitiesListListener");
        }
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cities_list, container, false);
        RecyclerView cityesCategoriesRecyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cityesCategoriesRecyclerView.setLayoutManager(layoutManager);
        cityesCategoriesRecyclerView.setAdapter(new MyAdapter());


        initializeViews(rootView);

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializePreferences();
        checkBoxPressure.setChecked(savedCity.getBoolean(CHECK_BOX_PRESSURE, false));
        checkBoxTomorrow.setChecked(savedCity.getBoolean(CHECK_BOX_TOMORROW, false));
        checkBoxWeek.setChecked(savedCity.getBoolean(CHECK_BOX_WEEK, false));
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int[] ID = new int[]{R.drawable.khv_image, R.drawable.spb_image, R.drawable.moscow_image};
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
        String resultPressure = null;
        String resultTomorrow = null;
        String resultWeek = null;

        String resultWeather = WeatherSpec.getWeather(getActivity(), categoryId);
        if (checkBoxPressure.isChecked()) {
            resultPressure = WeatherSpec.getPressure(getActivity(), categoryId);
        }

        if (checkBoxTomorrow.isChecked()) {
            resultTomorrow = WeatherSpec.getTomorrow(getActivity(), categoryId);
        }

        if (checkBoxWeek.isChecked()) {
            resultWeek = WeatherSpec.getWeek(getActivity(), categoryId);
        }


        DataForBundle dataForBundle = new DataForBundle(resultPressure, resultTomorrow, resultWeek, resultWeather, categoryId);
        citiesListListener.onListItemClick(categoryId, dataForBundle, descriptionText);
    }

    private void initializeViews(View view) {
        descriptionText = view.findViewById(R.id.textview_description);
        checkBoxPressure = view.findViewById(R.id.checkbox_pressure);
        checkBoxTomorrow = view.findViewById(R.id.checkbox_tomorrow);
        checkBoxWeek = view.findViewById(R.id.checkbox_week);


    }


    private void initializePreferences() {
        savedCity = getActivity().getSharedPreferences(SAVED_CITY, Context.MODE_PRIVATE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        savedCity.edit().putBoolean(CHECK_BOX_PRESSURE, checkBoxPressure.isChecked()).apply();
        savedCity.edit().putBoolean(CHECK_BOX_TOMORROW, checkBoxTomorrow.isChecked()).apply();
        savedCity.edit().putBoolean(CHECK_BOX_WEEK, checkBoxWeek.isChecked()).apply();
        super.onSaveInstanceState(outState);
    }


}