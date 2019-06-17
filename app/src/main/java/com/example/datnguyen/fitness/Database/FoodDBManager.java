package com.example.datnguyen.fitness.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.datnguyen.fitness.Model.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoodDBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="dbFood";
    private static final String TABLE_NAME ="tblFood";
    private static final String ID ="id";
    private static final String INPUTDAY ="day";
    private static final String FOODNAME ="name";
    private static final String CALORIES ="calories";

    private Context context;

    public FoodDBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                INPUTDAY + " TEXT, "+
                FOODNAME +" TEXT, "+
                CALORIES+" INTEGER)";
        db.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }

    public void addFood(Food food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(INPUTDAY, food.getInputDay());
        values.put(FOODNAME, food.getFoodName());
        values.put(CALORIES, food.getCalories());
        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public List<Food> getAllFood() {
        List<Food> lstFood = new ArrayList<Food>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setId(cursor.getInt(0));
                food.setInputDay(cursor.getString(1));
                food.setFoodName(cursor.getString(2));
                food.setCalories(cursor.getInt(3));
                lstFood.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lstFood;
    }

    public List<Food> countCalories() {
        List<Food> lstFood = getAllFood();
        List<Food> lstFoodReturn = new ArrayList<>();
       LinkedHashMap< String, Integer > hmap = new LinkedHashMap< String, Integer >();

        int sum = 0;
        String date;
        for ( int i=0 ; i<lstFood.size() ; i++ ){
            sum += lstFood.get(i).getCalories();
            date = lstFood.get(i).getInputDay();

            if ( hmap.get(date) == null ) hmap.put(date,lstFood.get(i).getCalories());
            else hmap.put(date, hmap.get(date) + lstFood.get(i).getCalories());
        }

        for (Map.Entry<String, Integer> entry : hmap.entrySet()) {
            Food food = new Food();
            food.setId(1);
            food.setFoodName("Food");
            food.setInputDay(entry.getKey());
            food.setCalories(entry.getValue());
            lstFoodReturn.add(food);
        }
        return lstFoodReturn;
    }

    public void deleteTableFood() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }
    public void deleteFood(int foodId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?", new String[] { String.valueOf(foodId) });
        db.close();
    }
}
