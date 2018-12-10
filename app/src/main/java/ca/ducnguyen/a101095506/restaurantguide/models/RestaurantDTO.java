package ca.ducnguyen.a101095506.restaurantguide.models;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestaurantDTO implements Serializable {


    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ADDRESS";
    public static final String COL_4 = "DESCRIPTION";
    public static final String COL_5 = "PHONE";
    public static final String COL_6 = "TAGS";
    public static final String COL_7 = "RATE";

    public String getID(){
        return this.id;
    }
    public void setID(String id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    private String id = UUID.randomUUID().toString();
    private String name;
    private String address;
    private String phone;
    private String description;
    private List<String> tags;
    private String rating = "0 star(s)";

    public ContentValues getContentValueCreate(){
        ContentValues content = new ContentValues();
        content.put(COL_1, this.id);
        content.put(COL_2, this.name);
        content.put(COL_3, this.address);
        content.put(COL_4, this.description);
        content.put(COL_5, this.phone);
        String strTag = android.text.TextUtils.join(",", this.tags);
        content.put(COL_6, strTag);
        return content;
    }

    public ContentValues getContentValueUpdate(){
        ContentValues content = new ContentValues();
        content.put(COL_2, this.name);
        content.put(COL_3, this.address);
        content.put(COL_4, this.description);
        content.put(COL_5, this.phone);
        String strTag = android.text.TextUtils.join(",", this.tags);
        content.put(COL_6, strTag);
        content.put(COL_7, this.rating);
        return content;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
