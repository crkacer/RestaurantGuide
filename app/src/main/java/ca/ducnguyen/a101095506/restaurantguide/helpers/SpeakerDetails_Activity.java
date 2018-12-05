package ca.ducnguyen.a101095506.restaurantguide.helpers;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.String;

public class SpeakerDetails_Activity extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_details_);

        getSupportActionBar().setDisplayShowHomeEnabled(true);//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//

        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        int speaker_id = preferences.getInt("chosen_speaker", -1);


        TextView txtName = findViewById(R.id.name_speaker);



        Cursor c =  myDb.getAllData();
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String aff = c.getString(2);
            String email = c.getString(3);
            String bio = c.getString(4);


            System.out.println(speaker_id);
            System.out.println(id);


            if ((id-1) == speaker_id ){
                txtName.setText( name + "\n"+ aff + "\n" + email + "\n" + bio);
                break;
            }


        }

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
