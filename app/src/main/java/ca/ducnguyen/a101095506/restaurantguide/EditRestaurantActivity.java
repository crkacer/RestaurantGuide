package ca.ducnguyen.a101095506.restaurantguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import ca.ducnguyen.a101095506.restaurantguide.helpers.DatabaseHelper;
import ca.ducnguyen.a101095506.restaurantguide.models.RestaurantDAO;
import ca.ducnguyen.a101095506.restaurantguide.models.RestaurantDTO;

public class EditRestaurantActivity extends AppCompatActivity{

    Button btnEdit;
    EditText name, street, apartment, city, state, zip, country, desc, tags, phone;
    RestaurantDAO restaurantDAO;
    RestaurantDTO restaurantDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant);

        String id;
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                restaurantDTO = null;
            }else{
                restaurantDTO = (RestaurantDTO)extras.getSerializable("restaurant");
            }
        }
        restaurantDAO = new RestaurantDAO(new DatabaseHelper(this));
        btnEdit = findViewById(R.id.btn_Edit);
        name = findViewById(R.id.fieldRest_Name);
        street = findViewById(R.id.fieldRest_Street);
        apartment = findViewById(R.id.fieldRest_Apartment);
        city = findViewById(R.id.fieldRest_City);
        state = findViewById(R.id.fieldRest_State);
        zip = findViewById(R.id.fieldRest_Zip);
        country = findViewById(R.id.fieldRest_Country);
        desc = findViewById(R.id.fieldEdit_Description);
        tags = findViewById(R.id.fieldRes_Tags);
        phone = findViewById(R.id.fieldEdit_phone);

        populateInputs();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantDTO.setName(name.getText().toString());
                String address = street.getText().toString() + ", " + apartment.getText().toString() + ", " + city.getText().toString() + ", " + state.getText().toString() + " " + country.getText().toString() + ", " + zip.getText().toString();
                restaurantDTO.setAddress(address);
                restaurantDTO.setPhone(phone.getText().toString());
                restaurantDTO.setDescription(desc.getText().toString());
                String[] tagsArr = tags.getText().toString().split(",");
                restaurantDTO.setTags(new ArrayList<String>(Arrays.asList(tagsArr)));
                restaurantDAO.update(restaurantDTO);
                finish();
            }
        });
    }
    public void populateInputs(){
        name.setText(restaurantDTO.getName());
        String[] parts = restaurantDTO.getAddress().split(",");
        String streetTxt = parts[0];
        street.setText(streetTxt);
        String apartmentTxt = parts[1];
        apartment.setText(apartmentTxt);
        String cityTxt = parts[2];
        city.setText(cityTxt);
        String stateAndCountry = parts[3].trim();
        String[] partsState = stateAndCountry.split(" ");
        String stateTxt = partsState[0];
        state.setText(stateTxt);
        String countryTxt = partsState[1];
        country.setText(countryTxt);
        String zipTxt = parts[4];
        zip.setText(zipTxt);
        desc.setText(restaurantDTO.getDescription());
        tags.setText(String.join(", ", restaurantDTO.getTags()));
        phone.setText(restaurantDTO.getPhone());
    }




 //submit button


}
