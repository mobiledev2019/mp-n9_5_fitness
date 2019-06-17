package com.example.datnguyen.fitness.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.datnguyen.fitness.Model.TempExercise;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class ExerciseDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "exercise.sql";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "exercise";

    // columns
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CODE = "code";

    private String create_table = "CREATE TABLE "+TABLE_NAME+" ("
            +ID +" INTEGER PRIMARY KEY, "+
            NAME +" TEXT, "+
            CODE+" INTEGER)";

    public ExerciseDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // get all exercises
    public ArrayList<TempExercise> getAllTempExercises(){
        ArrayList<TempExercise> excercises = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            TempExercise tempExercise = new TempExercise(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
            excercises.add(tempExercise);
            cursor.moveToNext();
        }
        return excercises;
    }

    // update
    public void updateExercise(TempExercise tempExercise){
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,tempExercise.getName());
        values.put(CODE,tempExercise.isCheck());
        sqLiteDatabase.update(TABLE_NAME, values, ID + " = ?", new String[] { String.valueOf(tempExercise.getId()) });
        sqLiteDatabase.close();

    }
}
