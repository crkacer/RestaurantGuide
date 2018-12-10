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
    public static final String COL_7 = "RATE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID TEXT PRIMARY KEY, NAME TEXT, ADDRESS TEXT, DESCRIPTION TEXT, PHONE VARCHAR, TAGS VARCHAR, RATE VARCHAR)"
        );

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}

