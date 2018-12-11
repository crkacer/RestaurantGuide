package ca.ducnguyen.a101095506.restaurantguide;

import android.content.Intent;
import android.location.LocationListener;
import android.media.Rating;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ca.ducnguyen.a101095506.restaurantguide.helpers.DatabaseHelper;
import ca.ducnguyen.a101095506.restaurantguide.models.RestaurantDAO;
import ca.ducnguyen.a101095506.restaurantguide.models.RestaurantDTO;
import ca.ducnguyen.a101095506.restaurantguide.utils.AddressManager;
import io.github.yavski.fabspeeddial.FabSpeedDial;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    RatingBar ratingBar;
    Button btnSubmit;
    RestaurantDAO restaurantDAO;
    RestaurantDTO restaurantDTO;
    MapView mapView;
    GoogleMap gmap;
    TextView name, address, phone, description, tags;
    FabSpeedDial fabSpeedDial;

    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyCadGpBujulZEarEefgb7NWbDikpES9Lc4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("Detail");
        String id;
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = null;
            }else{
                id = extras.getString(RestaurantDTO.COL_1);
            }
        }else{
            id = (String) savedInstanceState.getSerializable(RestaurantDTO.COL_1);
        }
        restaurantDAO = new RestaurantDAO(new DatabaseHelper(this));
        restaurantDTO = restaurantDAO.get(id);

        name = findViewById(R.id.txtName);
        address = findViewById(R.id.txtAddress);
        phone = findViewById(R.id.txtPhone);
        description = findViewById(R.id.txtDescription);
        tags = findViewById(R.id.txtTags);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        btnSubmit = (Button) findViewById(R.id.btnRating);

        name.setText("Name: " + "\n" + restaurantDTO.getName());
        address.setText("Address: " + "\n" + restaurantDTO.getAddress());
        phone.setText("Phone: " + "\n" + restaurantDTO.getPhone());
        description.setText("Description: " + "\n" + restaurantDTO.getDescription());
        tags.setText("Tags: " + "\n" + String.join(", ",restaurantDTO.getTags()));


        if(restaurantDTO.getRating() != null){
            float numStar = Float.parseFloat(restaurantDTO.getRating().split(" ")[0]);

            ratingBar.setRating(numStar);

        }
        Bundle mapViewBundle = null;
        if(savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        fabSpeedDial = (FabSpeedDial) findViewById(R.id.btnDetail);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                String title = menuItem.getTitle().toString();
                switch(title){
                    case "Edit":
                        Intent intent = new Intent(getApplicationContext(), EditRestaurantActivity.class);
                        intent.putExtra("restaurant", restaurantDTO);
                        finish();
                        startActivity(intent);
                        break;
                    case "Delete":
                        restaurantDAO.delete(restaurantDTO);
                        finish();
                        break;
                    case "Share via Email":
                        onShareClick();
                        break;
                    case "Share via Facebook":
                        break;
                    case "Share via Twitter":
                        break;
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
        addListenerOnRatingButton();

    }

    private void onShareClick() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = "Your body is here";
        String shareSub = "Your subject is here";
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(shareIntent, "Share using"));
    }

    public void addListenerOnRatingButton(){


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = ratingBar.getRating() + " star(s)";
                restaurantDTO.setRating(rating);
                restaurantDAO.update(restaurantDTO);
                finish();
            }
        });
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng pos = AddressManager.getLocationFromAddress(getApplicationContext(), restaurantDTO.getAddress());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(pos);
        markerOptions.title(restaurantDTO.getName());

        Marker locationMarker = gmap.addMarker(markerOptions);
        locationMarker.showInfoWindow();
        gmap.moveCamera(CameraUpdateFactory.newLatLng(pos));
    }

}
