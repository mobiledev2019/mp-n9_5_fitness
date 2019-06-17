package com.example.datnguyen.fitness.Activity.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datnguyen.fitness.Database.FoodDBManager;
import com.example.datnguyen.fitness.Model.Food;
import com.example.datnguyen.fitness.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddFood extends AppCompatActivity {

    private EditText mFood;
    private EditText mCalories;
    private FoodDBManager dbm;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        //Animation
        overridePendingTransition(R.anim.fade_in,R.anim.zoom_out);
        dbm = new FoodDBManager(this);
        btnSave = findViewById(R.id.btnSaveFood);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
                String strDate = mdformat.format(calendar.getTime());

                mFood = findViewById(R.id.editName);
                mCalories = findViewById(R.id.editCalories);

                String name = mFood.getText().toString();


                if (name.equals("") || mCalories.getText().toString().equals("")) {
                    Toast.makeText(AddFood.this, "Required fields!!!", Toast.LENGTH_SHORT).show();
                }
               else {
                    name = mFood.getText().toString();
                    int calories = Integer.parseInt(mCalories.getText().toString());
                    Food food = new Food(0,name,calories,strDate);

                    dbm.addFood(food);
                    Toast.makeText(AddFood.this, "Added !!!", Toast.LENGTH_SHORT).show();
                    mFood.setText("");
                    mCalories.setText("");
                }
            }
        });
    }

//    public void addFood(View view) {
//        java.util.Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
//        String strDate = mdformat.format(calendar.getTime());
//
//        mFood = findViewById(R.id.editName);
//        mCalories = findViewById(R.id.editCalories);
//
//        String name;
//        int calories;
//
//
//        if (mFood.getText().equals("") || mCalories.getText().equals("")) {
//            Toast.makeText(this, "Required fields!!!", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            name = mFood.getText().toString();
//            calories = Integer.parseInt(mCalories.getText().toString());
//            Food food = new Food(0,name,calories,strDate);
//
//            dbm.addFood(food);
//            Toast.makeText(this, "Added !!!", Toast.LENGTH_SHORT).show();
//            mFood.setText("");
//            mCalories.setText("");
//        }
//
//
//
//
//    }

    public void deleteFood(View view) {
        dbm.deleteTableFood();
    }

//    public void sumFood(View view) {
//        int n = dbm.countCalories();
//        HashMap< String, Integer > hmap = new HashMap < String, Integer > ();
//        hmap = dbm.countCalories();
//        System.out.println(n);
//        for (Map.Entry<String, Integer> entry : hmap.entrySet()) {
//            System.out.println(entry.getKey()+"***"+entry.getValue());
//        }
//    }


}
