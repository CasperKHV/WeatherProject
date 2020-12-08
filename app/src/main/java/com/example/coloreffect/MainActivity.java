package com.example.coloreffect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CitiesListFragment.CitiesListListener {

    private static final String TAG = "### MainActivity";
    private TextView descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate savedInstanceState" + savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void onListItemClick(int id, DataForBundle dataForBundle, TextView descriptionText) {
        this.descriptionText = descriptionText;
        View fragmentContainer = findViewById(R.id.fragment_container_land);
        if (fragmentContainer != null) {
            WeatherResultFragment weatherResultFragment = WeatherResultFragment.newInstance(dataForBundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fragment_container_land, weatherResultFragment);
            transaction.commit();
        } else {
            Intent intent = new Intent(this, WeatherResult.class);
            intent.putExtra(WeatherResultFragment.DATA_FOR_BUNDLE, dataForBundle);
            startActivityForResult(intent, CitiesListFragment.REQUEST_CODE);

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CitiesListFragment.REQUEST_CODE && data != null) {
            descriptionText.setText(data.getStringExtra(CitiesListFragment.RESULT_OK_STRING));
        }
    }
}
