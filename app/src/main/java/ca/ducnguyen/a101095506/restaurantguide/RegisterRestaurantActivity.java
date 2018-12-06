package ca.ducnguyen.a101095506.restaurantguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

import ca.ducnguyen.a101095506.restaurantguide.helpers.DatabaseHelper;
import ca.ducnguyen.a101095506.restaurantguide.models.RestaurantDAO;
import ca.ducnguyen.a101095506.restaurantguide.models.RestaurantDTO;

public class RegisterRestaurantActivity extends AppCompatActivity {

    Button btnRegister;
    EditText name, street, apartment, city, state, zip, country, desc, tags, phone;
    DatabaseHelper myDb;
    RestaurantDAO restaurantDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);
        btnRegister = findViewById(R.id.btn_Register);

        name = findViewById(R.id.fieldRest_Name);
        street = findViewById(R.id.fieldRest_Street);
        apartment = findViewById(R.id.fieldRest_Apartment);
        phone = findViewById(R.id.fieldRest_phone);
        city = findViewById(R.id.fieldRest_City);
        state = findViewById(R.id.fieldRest_State);
        zip = findViewById(R.id.fieldRest_Zip);
        country = findViewById(R.id.fieldRest_Country);
        desc = findViewById(R.id.fieldRest_Description);
        tags = findViewById(R.id.fieldRes_Tags);
        myDb = new DatabaseHelper(this);
        restaurantDAO = new RestaurantDAO(myDb);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantDTO restaurantDTO = new RestaurantDTO();
                restaurantDTO.setName(name.getText().toString());
                String address = street.getText().toString() + ", " + city.getText().toString() + ", " + state.getText().toString() + " " + country.getText().toString() + ", " + zip.getText().toString();
                restaurantDTO.setAddress(address);
                restaurantDTO.setPhone(phone.getText().toString());
                restaurantDTO.setDescription(desc.getText().toString());
                String[] tagsArr = tags.getText().toString().split(",");
                restaurantDTO.setTags(new ArrayList<String>(Arrays.asList(tagsArr)));
                restaurantDAO.save(restaurantDTO);
                myDb.close();
                finish();
            }
        });
    }
}
