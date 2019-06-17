package com.example.datnguyen.fitness.Activity.Statistics;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.datnguyen.fitness.Database.CaloriesDB;
import com.example.datnguyen.fitness.Database.DBManager;
import com.example.datnguyen.fitness.Database.FoodDBManager;
import com.example.datnguyen.fitness.Model.Calories;
import com.example.datnguyen.fitness.Model.Food;
import com.example.datnguyen.fitness.Model.Water;
import com.example.datnguyen.fitness.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class ViewStatistics extends AppCompatActivity {

    private DBManager dbm;
    private FoodDBManager foodDBManager;
    private CaloriesDB caloriesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_statistics);
        dbm = new DBManager(this);
        foodDBManager = new FoodDBManager(this);
        caloriesDB = new CaloriesDB(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        if (id.equals("water"))
        {
            Toast.makeText(this, "water", Toast.LENGTH_SHORT).show();
            List<Water> lstWater = new ArrayList<Water>();
            lstWater = dbm.getAllWater();
            int max=0;

            for ( int i=0 ; i<lstWater.size() ; i++ ){
                if (lstWater.get(i).getWaterMl() > max){
                    max = lstWater.get(i).getWaterMl();
                }
            }
            LineChartView lineChartView = findViewById(R.id.chart);
            List yAxisValues = new ArrayList();
            List axisValues = new ArrayList();
            Line line = new Line(yAxisValues).setColor(Color.parseColor("#03A9F4"));

            for(int i = 0; i < lstWater.size(); i++){
                axisValues.add(i, new AxisValue(i).setLabel(lstWater.get(i).getInputDay()));
            }

            for (int i = 0; i < lstWater.size(); i++){
                yAxisValues.add(new PointValue(i, lstWater.get(i).getWaterMl()));
            }

            List lines = new ArrayList();
            lines.add(line);

            LineChartData data = new LineChartData();
            data.setLines(lines);

            lineChartView.setLineChartData(data);

            Axis axis = new Axis();
            axis.setValues(axisValues);
            data.setAxisXBottom(axis);

            Axis yAxis = new Axis();
            data.setAxisYLeft(yAxis);

            axis.setTextSize(10);
            axis.setTextColor(Color.parseColor("#03A9F4"));

            yAxis.setTextColor(Color.parseColor("#03A9F4"));
            yAxis.setTextSize(10);
            yAxis.setName("Water ml"+"\n ");

            Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
            viewport.top =max+50;
            lineChartView.setMaximumViewport(viewport);
            lineChartView.setCurrentViewport(viewport);
        }
        else if (id.equals("food"))
        {
            Toast.makeText(this, "food", Toast.LENGTH_SHORT).show();
            List<Food> lstFood = foodDBManager.countCalories();
            int max = 0;

            for ( int i=0 ; i<lstFood.size() ; i++ ){
                if (lstFood.get(i).getCalories() > max){
                    max = lstFood.get(i).getCalories();
                }
            }

            LineChartView lineChartView = findViewById(R.id.chart);
            List yAxisValues = new ArrayList();
            List axisValues = new ArrayList();
            Line line = new Line(yAxisValues).setColor(Color.parseColor("#03A9F4"));

            for(int i = 0; i < lstFood.size(); i++){
                axisValues.add(i, new AxisValue(i).setLabel(lstFood.get(i).getInputDay()));
            }

            for (int i = 0; i < lstFood.size(); i++){
                yAxisValues.add(new PointValue(i, lstFood.get(i).getCalories()));
            }

            List lines = new ArrayList();
            lines.add(line);

            LineChartData data = new LineChartData();
            data.setLines(lines);

            lineChartView.setLineChartData(data);

            Axis axis = new Axis();
            axis.setValues(axisValues);
            data.setAxisXBottom(axis);

            Axis yAxis = new Axis();
            data.setAxisYLeft(yAxis);

            axis.setTextSize(10);
            axis.setTextColor(Color.parseColor("#03A9F4"));

            yAxis.setTextColor(Color.parseColor("#03A9F4"));
            yAxis.setTextSize(10);
            yAxis.setName("Food calories"+"\n ");

            Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
            viewport.top =max+50;
            lineChartView.setMaximumViewport(viewport);
            lineChartView.setCurrentViewport(viewport);
        }

        else if (id.equals("exercise"))
        {
            Toast.makeText(this, "exercise", Toast.LENGTH_SHORT).show();
            List<Calories> lstCalo = caloriesDB.countCalories();
            int max = 0;

            for ( int i=0 ; i<lstCalo.size() ; i++ ){
                if (lstCalo.get(i).getCalorie() > max){
                    max = lstCalo.get(i).getCalorie();
                }
            }

            LineChartView lineChartView = findViewById(R.id.chart);
            List yAxisValues = new ArrayList();
            List axisValues = new ArrayList();
            Line line = new Line(yAxisValues).setColor(Color.parseColor("#03A9F4"));

            for(int i = 0; i < lstCalo.size(); i++){
                axisValues.add(i, new AxisValue(i).setLabel(lstCalo.get(i).getDay()));
            }

            for (int i = 0; i < lstCalo.size(); i++){
                yAxisValues.add(new PointValue(i, lstCalo.get(i).getCalorie()));
            }

            List lines = new ArrayList();
            lines.add(line);

            LineChartData data = new LineChartData();
            data.setLines(lines);

            lineChartView.setLineChartData(data);

            Axis axis = new Axis();
            axis.setValues(axisValues);
            data.setAxisXBottom(axis);

            Axis yAxis = new Axis();
            data.setAxisYLeft(yAxis);

            axis.setTextSize(10);
            axis.setTextColor(Color.parseColor("#03A9F4"));

            yAxis.setTextColor(Color.parseColor("#03A9F4"));
            yAxis.setTextSize(10);
            yAxis.setName("Food calories"+"\n ");

            Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
            viewport.top =max+50;
            lineChartView.setMaximumViewport(viewport);
            lineChartView.setCurrentViewport(viewport);
        }
    }
}
