package ca.ducnguyen.a101095506.restaurantguide.models;
import android.database.Cursor;
import android.support.annotation.Nullable;

import java.util.*;
public interface DAO<T> {
    @Nullable T get(String id);
    Cursor getAll();
    boolean save(T t);
    void update(T t);
    void delete(T t);

}
