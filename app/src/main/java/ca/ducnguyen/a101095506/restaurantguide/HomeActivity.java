package ca.ducnguyen.a101095506.restaurantguide;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ca.ducnguyen.a101095506.restaurantguide.helpers.DatabaseHelper;
import ca.ducnguyen.a101095506.restaurantguide.helpers.RightDrawableOnTouchListener;
import ca.ducnguyen.a101095506.restaurantguide.models.RestaurantDAO;
import ca.ducnguyen.a101095506.restaurantguide.models.RestaurantDTO;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RestaurantDAO restaurantDAO;
    ListView listView;
    List<String> idList;
    SimpleAdapter adapter;
    Map<String, String> restaurantData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Database here
        restaurantDAO = new RestaurantDAO(new DatabaseHelper(this));

        listView = (ListView) findViewById(R.id.restaurant_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterRestaurantActivity.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final EditText editText = (EditText) findViewById(R.id.search);
        editText.setOnTouchListener(new RightDrawableOnTouchListener(editText) {
            @Override
            public boolean onDrawableTouch(final MotionEvent event) {
                return onClickSearch(editText,event);
            }
        });


        populateDataSet();


    }
    @Override
    protected void onResume() {
        super.onResume();
        populateDataSet();
    }

    private void populateDataSet(){
        Cursor cursor = restaurantDAO.getAll();
        restaurantData = new LinkedHashMap<>();
        idList = new ArrayList<>();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No content", Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                idList.add(cursor.getString(cursor.getColumnIndex(RestaurantDTO.COL_1)));
                String name = cursor.getString(cursor.getColumnIndex(RestaurantDTO.COL_2));
                String address = cursor.getString(cursor.getColumnIndex(RestaurantDTO.COL_3));
                restaurantData.put(name, address);
            }
            List<HashMap<String, String>> listItem = new ArrayList<>();
            adapter = new SimpleAdapter(this, listItem, R.layout.list_item, new String[]{"First Line", "Second Line"}, new int[]{R.id.nameTitle, R.id.nameSubtitle});
            Iterator it = restaurantData.entrySet().iterator();
            while(it.hasNext()){
                HashMap<String, String> resultsMap = new HashMap<>();
                Map.Entry pair = (Map.Entry)it.next();
                resultsMap.put("First Line", pair.getKey().toString());
                resultsMap.put("Second Line", pair.getValue().toString());
                listItem.add(resultsMap);
            }

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String[] arr = idList.toArray(new String[idList.size()]);
                    Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                    intent.putExtra(RestaurantDTO.COL_1, arr[position]);
                    startActivity(intent);
                }
            });
            cursor.close();
        }
    }
    private boolean onClickSearch(final View view, MotionEvent event) {
        // do something
        event.setAction(MotionEvent.ACTION_CANCEL);
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
