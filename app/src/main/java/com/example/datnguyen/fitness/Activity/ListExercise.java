package com.example.datnguyen.fitness.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.datnguyen.fitness.Adapter.RecyclerViewAdapter;
import com.example.datnguyen.fitness.Model.Exercise;
import com.example.datnguyen.fitness.R;

import java.util.ArrayList;
import java.util.List;

public class ListExercise extends AppCompatActivity {
    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);
        initData();
        recyclerView = findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private void initData() {
        exerciseList.add(new Exercise(R.drawable.fdfdf,"Chống đẩy óc chó"));
        exerciseList.add(new Exercise(R.drawable.img_37466,"Chống đẩy óc bò"));
        exerciseList.add(new Exercise(R.drawable.img_314987,"Chống đẩy óc gà"));
        exerciseList.add(new Exercise(R.drawable.sdsf,"Ngồi kiểu óc lợn"));
        exerciseList.add(new Exercise(R.drawable.fdfdf,"Chống đẩy óc chó"));
        exerciseList.add(new Exercise(R.drawable.img_37466,"Chống đẩy óc bò"));
        exerciseList.add(new Exercise(R.drawable.img_314987,"Chống đẩy óc gà"));
        exerciseList.add(new Exercise(R.drawable.sdsf,"Ngồi kiểu óc lợn"));
    }
}
