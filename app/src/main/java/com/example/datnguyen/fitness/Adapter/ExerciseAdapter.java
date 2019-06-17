package com.example.datnguyen.fitness.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datnguyen.fitness.Database.ExerciseDB;
import com.example.datnguyen.fitness.Model.TempExercise;
import com.example.datnguyen.fitness.R;

import java.util.ArrayList;

public class ExerciseAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<TempExercise> tempExercises ;
    private ExerciseDB exerciseDB;
    public ExerciseAdapter(Context context, int layout, ArrayList<TempExercise> tempExercises) {
        this.context = context;
        this.layout = layout;
        this.tempExercises = tempExercises;
    }

    @Override
    public int getCount() {
        return tempExercises.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        Switch rdiSelect;
        TextView txtExercise;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ExerciseAdapter.ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ExerciseAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.rdiSelect = convertView.findViewById(R.id.rdiSelect);
            viewHolder.txtExercise = convertView.findViewById(R.id.txtExercise);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder =(ExerciseAdapter.ViewHolder) convertView.getTag();
        }


        exerciseDB = new ExerciseDB(context);
        final TempExercise tempExercise = tempExercises.get(position);
        viewHolder.txtExercise.setText(tempExercise.getName());
        if (tempExercise.isCheck() == 1)
        {
            viewHolder.rdiSelect.setChecked(true);
        }
        else if (tempExercise.isCheck() == 0)
        {
            viewHolder.rdiSelect.setChecked(false);
        }


        // Change the state
        viewHolder.rdiSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check;
                if (viewHolder.rdiSelect.isChecked() == true)
                {
                    check = 1;
                    tempExercise.setCheck(check);
                    Toast.makeText(context, "Successfully added!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    check = 0;
                    tempExercise.setCheck(check);
                    Toast.makeText(context, "Successfully deleted!!!", Toast.LENGTH_SHORT).show();
                }
                exerciseDB.updateExercise(tempExercise);

            }
        });

        // Animation
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.slide_left);
        convertView.startAnimation(animation);

        return convertView;
    }
}
