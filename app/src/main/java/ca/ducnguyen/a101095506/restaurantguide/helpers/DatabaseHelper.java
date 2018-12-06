package ca.ducnguyen.a101095506.restaurantguide.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RestaurantApp.db";
    public static final String TABLE_NAME = "restaurants_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ADDRESS";
    public static final String COL_4 = "DESCRIPTION";
    public static final String COL_5 = "PHONE";
    public static final String COL_6 = "TAGS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID TEXT PRIMARY KEY, NAME TEXT, ADDRESS TEXT, DESCRIPTION TEXT, PHONE VARCHAR, TAGS VARCHAR)"
        );

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//    public boolean insertData(String name, String affiliation, String email, String bio){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_2, name);
//        contentValues.put(COL_3, affiliation);
//        contentValues.put(COL_4, email);
//        contentValues.put(COL_5, bio);
//        long result = db.insert(TABLE_NAME, null, contentValues);
//        if (result == -1)
//            return false;
//        else
//            return true;
//    }
//
//    public Cursor getAllData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor results = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
//        return results;
//    }
}

