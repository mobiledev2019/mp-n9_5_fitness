package com.example.datnguyen.fitness.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datnguyen.fitness.Database.FoodDBManager;
import com.example.datnguyen.fitness.Model.Food;
import com.example.datnguyen.fitness.Model.TempExercise;
import com.example.datnguyen.fitness.R;

import java.util.ArrayList;
import java.util.List;

public class ViewFoodAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Food> foodList;
    private FoodDBManager foodDBManager;

    public ViewFoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
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
        TextView txtName, txtInput, txtCalories;
        ImageView imgBin;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        foodDBManager = new FoodDBManager(context);
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.txtName = convertView.findViewById(R.id.txtFoodName);
            viewHolder.txtInput = convertView.findViewById(R.id.txtInputDay);
            viewHolder.txtCalories = convertView.findViewById(R.id.txtCalories);
            viewHolder.imgBin = convertView.findViewById(R.id.imgBin);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        final Food food = foodList.get(position);
        viewHolder.txtName.setText(food.getFoodName());
        viewHolder.txtInput.setText("Day: "+food.getInputDay());
        viewHolder.txtCalories.setText("Calories: "+food.getCalories());
        viewHolder.imgBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodDBManager.deleteFood(food.getId());
                foodList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Deleted!!!", Toast.LENGTH_SHORT).show();
            }
        });

        // Animation
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.slide_left);
        convertView.startAnimation(animation);
        return convertView;
    }
}
