package com.example.datnguyen.fitness.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.datnguyen.fitness.Model.Water;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="db";
    private static final String TABLE_NAME ="tblWater";
    private static final String ID ="id";
    private static final String INPUTDAY ="day";
    private static final String WATERNUMBER ="number";
    private static final String WATERML ="email";

    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                INPUTDAY + " TEXT, "+
                WATERNUMBER +" INTEGER, "+
                WATERML+" INTEGER)";
        db.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }

    public void addWater(Water water){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(INPUTDAY, water.getInputDay());
        values.put(WATERNUMBER, water.getWaterNumber());
        values.put(WATERML, water.getWaterMl());
        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public Water getWaterByDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { ID,
                        INPUTDAY, WATERNUMBER,WATERML }, INPUTDAY + "=?",
                new String[] { date }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Water water = null;
        if (cursor.getCount()>0){
            water = new Water(cursor.getInt(0),cursor.getString(1),
                    cursor.getInt(2),cursor.getInt(3));
        }
        cursor.close();
        db.close();
        return water;
    }

    /*
    Update add water
     */
    public int updateAddWater(Water water) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WATERNUMBER, water.getWaterNumber() + 1);
        values.put(WATERML, water.getWaterMl() + 250);

        return db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(water.getId())});
    }

    /*
    Update sub water
     */
    public int updateSubWater(Water water) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WATERNUMBER, water.getWaterNumber() - 1);
        values.put(WATERML, water.getWaterMl() - 250);

        return db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(water.getId())});
    }

    /*
     Getting All Water
      */

    public List<Water> getAllWater() {
        List<Water> lstWater = new ArrayList<Water>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Water water = new Water();
                water.setId(cursor.getInt(0));
                water.setInputDay(cursor.getString(1));
                water.setWaterNumber(cursor.getInt(2));
                water.setWaterMl(cursor.getInt(3));
                lstWater.add(water);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lstWater;
    }

    public int countWater() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public Water getNumberWater(){
        List<Water> lstWater = new ArrayList<Water>();
        boolean flag = false;

        String query = "SELECT * FROM tblWater";
        System.out.println(query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            flag = true;
            do {
                Water water = new Water();
                water.setId(cursor.getInt(0));
                water.setInputDay(cursor.getString(1));
                water.setWaterNumber(cursor.getInt(2));
                water.setWaterMl(cursor.getInt(3));
                lstWater.add(water);
            } while (cursor.moveToNext());
        }

        Water water = new Water(0,"0",0,0);
        if (flag == true)
        {
            water = lstWater.get(lstWater.size()-1);
        }
        cursor.close();
        return water;
    }

    public void deleteTableWater() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }
}
