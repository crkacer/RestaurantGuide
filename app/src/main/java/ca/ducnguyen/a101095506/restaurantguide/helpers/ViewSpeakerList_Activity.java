package com.example.a101094202.myapplication_conference_1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewSpeakerList_Activity extends AppCompatActivity {


    DatabaseHelper myDb;
    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_speaker_list_);
        getSupportActionBar().setDisplayShowHomeEnabled(true);//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//

        ListView listView = (ListView) findViewById(R.id.listView);
        myDb = new DatabaseHelper(this);



        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDb.getAllData();
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                myintent = new Intent(view.getContext(), SpeakerDetails_Activity.class);
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

                //testing on console
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("chosen_speaker", position);
                System.out.println("*********************" + position);
                editor.apply();
                startActivity(myintent);
        }

        });

    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}