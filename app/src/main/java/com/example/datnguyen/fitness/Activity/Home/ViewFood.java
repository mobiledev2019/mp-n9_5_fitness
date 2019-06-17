package com.example.datnguyen.fitness.Activity.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.datnguyen.fitness.Adapter.ViewFoodAdapter;
import com.example.datnguyen.fitness.Database.FoodDBManager;
import com.example.datnguyen.fitness.Model.Food;
import com.example.datnguyen.fitness.R;

import java.util.List;

public class ViewFood extends AppCompatActivity {
    private List<Food> foodList;
    private ViewFoodAdapter adapter;
    private ListView listView;
    private FoodDBManager foodDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food);
        foodDBManager = new FoodDBManager(this);
        init();
    }

    private void init() {
        foodList = foodDBManager.getAllFood();
        adapter = new ViewFoodAdapter(this,R.layout.row_view_food,foodList);
        listView = findViewById(R.id.lvViewFood);
        listView.setAdapter(adapter);


    }
}
