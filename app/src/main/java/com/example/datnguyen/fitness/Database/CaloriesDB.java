package com.example.datnguyen.fitness.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.datnguyen.fitness.Model.Calories;
import com.example.datnguyen.fitness.Model.Food;
import com.example.datnguyen.fitness.Model.TempExercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CaloriesDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calories.sql";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "calorie";

    // columns
    private static final String ID = "id";
    private static final String DAY = "day";
    private static final String CALORIES = "calories";

    private String create_table = "CREATE TABLE "+TABLE_NAME+" ("
            +ID +" INTEGER PRIMARY KEY, "+
            DAY +" TEXT, "+
            CALORIES+" INTEGER)";


    public CaloriesDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //insert
    public long saveCalories(Calories calories){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DAY,calories.getDay());
        values.put(CALORIES,calories.getCalorie());
        long value = sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();
        return value;
    }

    public ArrayList<Calories> getAllCalories(){
        ArrayList<Calories> calories = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            Calories calo = new Calories(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
            calories.add(calo);
            cursor.moveToNext();
        }
        return calories;
    }

    public List<Calories> countCalories() {
        List<Calories> lstCalo = getAllCalories();
        List<Calories> lstCaloReturn = new ArrayList<>();
        LinkedHashMap< String, Integer > hmap = new LinkedHashMap < String, Integer > ();

        int sum = 0;
        String date;
        for ( int i=0 ; i<lstCalo.size() ; i++ ){
            date = lstCalo.get(i).getDay();

            if ( hmap.get(date) == null ) hmap.put(date,lstCalo.get(i).getCalorie());
            else hmap.put(date, hmap.get(date) + lstCalo.get(i).getCalorie());
        }

        for (Map.Entry<String, Integer> entry : hmap.entrySet()) {
            Calories calo = new Calories(1,entry.getKey(),entry.getValue());
            lstCaloReturn.add(calo);
        }
        return lstCaloReturn;
    }
}
