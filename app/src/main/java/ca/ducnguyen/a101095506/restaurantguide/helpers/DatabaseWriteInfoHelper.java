package com.example.a101094202.myapplication_conference_1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseWriteInfoHelper extends AppCompatActivity {


    DatabaseHelper myDb;
    EditText editName, editAffiliation, editEmail, editBio;
    Button btnAddData;
    Button btnViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_write_info_helper);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        editName=(EditText)findViewById(R.id.editText_name);
        editAffiliation=(EditText)findViewById(R.id.editText_affiliation);
        editEmail=(EditText)findViewById(R.id.editText_email);
        editBio=(EditText)findViewById(R.id.editText_bio);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnViewData= (Button) findViewById(R.id.btnViewData);

        AddData();
        viewAll();

    }



    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                      boolean isInserted =  myDb.insertData(editName.getText().toString(),
                                                            editAffiliation.getText().toString(),
                                                            editEmail.getText().toString(),
                                                            editBio.getText().toString());

                    if(isInserted)
                        Toast.makeText(DatabaseWriteInfoHelper.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(DatabaseWriteInfoHelper.this,"Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    public void viewAll(){
        btnViewData.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(DatabaseWriteInfoHelper.this, ViewSpeakerList_Activity.class);
                        startActivity(intent);



                    }
            }
        );
    }

    public  void showMessage(String Title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
