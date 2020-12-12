package com.example.coloreffect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        if (descriptionText != null) {
            if (requestCode == CitiesListFragment.REQUEST_CODE && data != null) {
                descriptionText.setText(data.getStringExtra(CitiesListFragment.RESULT_OK_STRING));

            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast toast = Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
        return true;
    }


}
