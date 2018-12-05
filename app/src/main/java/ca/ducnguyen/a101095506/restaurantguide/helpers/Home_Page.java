package ca.ducnguyen.a101095506.restaurantguide.helpers;
import android.app.ActivityOptions;
import android.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.TextView;


public class Home_Page extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);


        Log.d("TEST", "onCreate-SECOND");

        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String display = preferences.getString("display", "");


        TextView username = findViewById(R.id.username);
        username.setText(display);


        findViewById(R.id.btn_gensched).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), General_Schedule.class);
                startActivity(i);

            }
        });

        findViewById(R.id.btn_twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Twitter.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_maps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Maps.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_mysched).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Personal_Schedule.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_survey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SurveyActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id. btn_speakerLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DatabaseWriteInfoHelper.class);
                startActivity(i);
            }
        });




    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TEST", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TEST", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TEST", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TEST", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TEST", "onDestroy");
    }


    @Override
    public void finish() {
        super.finish();
        Log.d("TEST", "onFinish");
    }
}

