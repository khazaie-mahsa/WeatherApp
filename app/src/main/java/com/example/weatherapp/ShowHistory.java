package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ShowHistory extends AppCompatActivity {
    private DatabaseHelper db;
    private RecyclerView cityRV;
    private RecyclerView.Adapter cityRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);

        db = new DatabaseHelper(getBaseContext());
        List cities = db.getCities();
        cityRV = findViewById(R.id.citiesRV);
        cityRVAdapter = new CitiesRVAdapter(cities);
        cityRV.setAdapter(cityRVAdapter);
        openMain();
    }
    private void openMain(){
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }
}