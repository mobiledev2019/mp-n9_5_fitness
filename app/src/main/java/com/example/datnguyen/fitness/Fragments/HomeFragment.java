package com.example.datnguyen.fitness.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datnguyen.fitness.Activity.Home.AddFood;
import com.example.datnguyen.fitness.Activity.Home.ViewFood;
import com.example.datnguyen.fitness.Database.DBManager;
import com.example.datnguyen.fitness.Model.Water;
import com.example.datnguyen.fitness.R;
import com.example.datnguyen.fitness.Activity.Home.TargetWater;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    Button btnFoodView, btnFoodAdd, btnAddWater, btnSubWater;
    ImageView imgWater;
    private TextView mShowWater;
    private DBManager dbm;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnFoodView = view.findViewById(R.id.btnViewFood);
        btnFoodAdd = view.findViewById(R.id.btnAddFood);
        btnAddWater = view.findViewById(R.id.btnWaterPlus);
        btnSubWater = view.findViewById(R.id.btnWaterMinus);

        imgWater = view.findViewById(R.id.imgWater);
        mShowWater = (TextView) view.findViewById(R.id.showWater);

        dbm = new DBManager(getContext());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
        String strDate = mdformat.format(calendar.getTime());

        Water water = dbm.getNumberWater();
        int glasses = water.getWaterNumber();
        if ( !water.getInputDay().equals(strDate) ) mShowWater.setText("0");
        else mShowWater.setText(""+glasses);

        // food
        btnFoodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddFood.class));
            }
        });
        btnFoodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewFood.class));
            }
        });


        // water
        imgWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),TargetWater.class));
            }
        });
        btnAddWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
                String strDate = mdformat.format(calendar.getTime());

                Water water = dbm.getWaterByDate(strDate);

                System.out.println(dbm.countWater());
                if (dbm.countWater()==0 || water==null){
                    water = new Water(0,strDate,1,250);
                    dbm.addWater(water);
                }
                else if (dbm.countWater()>0 && water!=null){
                    water = dbm.getWaterByDate(strDate);
                    dbm.updateAddWater(water);
                }

                String sCount;
                sCount = mShowWater.getText().toString();
                int nCount;
                nCount = Integer.parseInt(sCount);
                ++nCount;

                if ( mShowWater!=null ){
                    mShowWater.setText(Integer.toString(nCount));
                }
            }
        });

        btnSubWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
                String strDate = mdformat.format(calendar.getTime());

                Water water = dbm.getWaterByDate(strDate);

                if (dbm.countWater()>0 && water!=null){
                    dbm.updateSubWater(water);

                    String sCount;
                    sCount = mShowWater.getText().toString();
                    int nCount;
                    nCount = Integer.parseInt(sCount);
                    --nCount;

                    if ( mShowWater!=null ){
                        mShowWater.setText(Integer.toString(nCount));
                    }
                }
            }
        });

        return view;
    }


    public void deleteWater(View view) {
        dbm.deleteTableWater();
    }

    public void show(View view) {
        List<Water> lstWater = new ArrayList<Water>();
        lstWater = dbm.getAllWater();
        for ( Water water:lstWater ){
            System.out.println(water.toString());
        }
    }

    public void search(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
        String strDate = mdformat.format(calendar.getTime());

        Water water = dbm.getWaterByDate(strDate);

        if ( water!=null ){
            System.out.println(water.toString());
            dbm.updateAddWater(water);
        }
        else System.out.println("null");
    }

}
