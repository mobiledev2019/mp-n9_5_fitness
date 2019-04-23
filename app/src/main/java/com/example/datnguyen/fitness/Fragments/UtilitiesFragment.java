package com.example.datnguyen.fitness.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.datnguyen.fitness.Activity.ListYoutubeExercise;
import com.example.datnguyen.fitness.Activity.WeatherActivity;
import com.example.datnguyen.fitness.R;

public class UtilitiesFragment extends Fragment {
    private Button btnViewWeather,btnViewNote;
    public View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_utilities,container,false);
        init();
        btnViewWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocationEnabled(getContext()) == false)
                {
                    showDiaglog();
                }
                else {
                    Intent intent = new Intent(getContext(),WeatherActivity.class);
                    startActivity(intent);
                }

            }
        });


        btnViewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Toast ??", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    // initialize
    private void init() {

        btnViewWeather = view.findViewById(R.id.btnViewWeather);

        btnViewNote = view.findViewById(R.id.btnViewNote);

    }


    // check location
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    // show Dialog
    public void showDiaglog(){

        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
        alert.setTitle("GPS!!!");
        alert.setIcon(R.drawable.ic_gps_fixed_black_24dp);
        alert.setMessage("You have not turned on your GPS yet. Do you want to turn it on?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        alert.setNeutralButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.show();
    }
}
