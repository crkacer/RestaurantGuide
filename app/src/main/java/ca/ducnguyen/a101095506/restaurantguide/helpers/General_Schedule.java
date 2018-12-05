package ca.ducnguyen.a101095506.restaurantguide.helpers;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class General_Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general__schedule);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("TEST", "onCreate-THIRD");

        findViewById(R.id.btn_Speaker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Speaker.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_Sponsors).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Sponsors.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_NoAttend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Attendees.class);
                startActivity(i);
            }
        });

        //button2

        findViewById(R.id.btn_Speaker1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Speaker.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_Sponsors1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Sponsors.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_NoAttend1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Attendees.class);
                startActivity(i);
            }
        });


        //button3

        findViewById(R.id.btn_Speaker2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Speaker.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_Sponsors2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Sponsors.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_NoAttend2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Attendees.class);
                startActivity(i);
            }
        });

        //button4
        findViewById(R.id.btn_Speaker2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Speaker.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_Sponsors2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Sponsors.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_NoAttend2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Attendees.class);
                startActivity(i);
            }
        });

        //button4
        findViewById(R.id.btn_Speaker3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Speaker.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_Sponsors3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Sponsors.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_NoAttend3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Attendees.class);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("TEST", "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("TEST", "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("TEST", "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("TEST", "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("TEST", "onDestroy");
    }

    @Override
    public void finish(){
        super.finish();
        Log.d("TEST", "onFinish");
    }
}

