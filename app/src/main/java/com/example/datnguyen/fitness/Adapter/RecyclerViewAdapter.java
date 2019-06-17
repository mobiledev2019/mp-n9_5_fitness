package com.example.datnguyen.fitness.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.datnguyen.fitness.Interface.ItemClickListener;
import com.example.datnguyen.fitness.Model.Exercise;
import com.example.datnguyen.fitness.R;
import com.example.datnguyen.fitness.Activity.Exercises.ViewExercise;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView image;
    public TextView text;
    private ItemClickListener itemClickListener;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.ex_img);
        text = itemView.findViewById(R.id.ex_name);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
    private List<Exercise> exerciseList;
    private Context context;
    private int lastPosition = -1;
    public RecyclerViewAdapter(List<Exercise> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise,viewGroup,false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, final int i) {
       // recyclerViewHolder.image.setImageResource(exerciseList.get(i).getImage_id());
        Glide.with(context).load(exerciseList.get(i).getImage_id()).into(recyclerViewHolder.image);
        recyclerViewHolder.text.setText(exerciseList.get(i).getName());
        recyclerViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                // call to new Activity
                Intent intent = new Intent(context,ViewExercise.class);
                intent.putExtra("image_id",exerciseList.get(position).getImage_id());
                intent.putExtra("name",exerciseList.get(position).getName());
                context.startActivity(intent);
            }
        });

        setAnimation(recyclerViewHolder.itemView, i);
    }

    private void setAnimation(View itemView, int i) {
        if (i > lastPosition )
        {
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.slide_left);
            itemView.startAnimation(animation);
            lastPosition = i;
        }
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}

