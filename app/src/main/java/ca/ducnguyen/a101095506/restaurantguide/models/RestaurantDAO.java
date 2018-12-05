package ca.ducnguyen.a101095506.restaurantguide.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ca.ducnguyen.a101095506.restaurantguide.helpers.DatabaseHelper;

public class RestaurantDAO implements DAO<RestaurantDTO> {

    DatabaseHelper db;
    public static final String TABLE_NAME = "restaurants_table";
    public RestaurantDAO(DatabaseHelper db) {
        this.db = db;
    }

    @Override
    public @Nullable RestaurantDTO get(String id) {
        SQLiteDatabase sql_lite = db.getReadableDatabase();

        Cursor cursor = sql_lite.query(TABLE_NAME, null, "ID=?", new String[]{id}, null, null,null);
        RestaurantDTO restaurantDTO = null;
        if(cursor.moveToFirst()){
            restaurantDTO = new RestaurantDTO();
            restaurantDTO.setID(cursor.getString(cursor.getColumnIndex(RestaurantDTO.COL_1)));
            restaurantDTO.setName(cursor.getString(cursor.getColumnIndex(RestaurantDTO.COL_2)));
            restaurantDTO.setAddress(cursor.getString(cursor.getColumnIndex(RestaurantDTO.COL_3)));
            restaurantDTO.setDescription(cursor.getString(cursor.getColumnIndex(RestaurantDTO.COL_4)));
            restaurantDTO.setPhone(cursor.getString(cursor.getColumnIndex(RestaurantDTO.COL_5)));
            String[] tags = cursor.getString(cursor.getColumnIndex(RestaurantDTO.COL_6)).split(",");
            restaurantDTO.setTags(new ArrayList<String>(Arrays.asList(tags)));

        }
        cursor.close();
        return restaurantDTO;
    }

    @Override
    public Cursor getAll() {
        return db.getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME,null);
    }

    @Override
    public boolean save(RestaurantDTO restaurantDTO) {
        ContentValues content = restaurantDTO.getContentValueCreate();
        long result = db.getWritableDatabase().insert(TABLE_NAME, null, content);
        return result == -1;
    }

    @Override
    public void update(RestaurantDTO restaurantDTO) {
        ContentValues content = restaurantDTO.getContentValueUpdate();
        db.getWritableDatabase().update(TABLE_NAME, content, "ID=?",new String[]{restaurantDTO.getID()});

    }

    @Override
    public void delete(RestaurantDTO restaurantDTO) {
        db.getWritableDatabase().delete(TABLE_NAME, "ID=?", new String[]{restaurantDTO.getID()});
    }
}
