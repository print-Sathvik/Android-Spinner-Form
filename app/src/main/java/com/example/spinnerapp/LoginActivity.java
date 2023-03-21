package com.example.spinnerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;


public class LoginActivity extends Activity {
    EditText t1,t2,t3,t4,t5;
    Button b;
    String name, address, address2, city, email, state, country;
    Spinner countrySpinner, stateSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        t1=findViewById(R.id.name);
        t2=findViewById(R.id.address);
        t3=findViewById(R.id.address2);
        t4=findViewById(R.id.city);
        t5=findViewById(R.id.email);
        b=findViewById(R.id.submitButton);
        HashMap<String, String[]> countryStates = new HashMap<String, String[]>();
        countryStates.put("India", new String[] {"Andaman and Nicobar Islands","Andhra Pradesh","Arunachal Pradesh ","Assam","Bihar","Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Lakshadweep","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","National Capital Territory of Delhi","Odisha","Puducherry","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"});
        countryStates.put("United States of America", new String[] {"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Guam", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Minor Outlying Islands", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "U.S. Virgin Islands", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"});
        countryStates.put("Australia", new String[]{"New South Wales","Queensland","South Australia","Tasmania","Victoria","Western Australia","The Australian Capital Territory","The Northern Territory"});
        String[] countries = {"India", "United States of America", "Australia"};

        countrySpinner = findViewById(R.id.countrySpinner);
        stateSpinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<String> stateArad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryStates.get(countries[0]));
        stateArad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateArad);
        ArrayAdapter<String> arad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        arad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String[] newStates = countryStates.get(countries[position]);
                ArrayAdapter<String> stateArad2 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, newStates);
                stateArad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stateSpinner.setAdapter(stateArad2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        countrySpinner.setAdapter(arad);
    }

    public void clickSubmit(View v)
    {
        name = t1.getText().toString().trim();
        address = t2.getText().toString().trim();
        address2 = t3.getText().toString().trim();
        city = t4.getText().toString().trim();
        email = t5.getText().toString().trim();
        if(name.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Incomplete",Toast.LENGTH_SHORT).show();
            t1.setError("This field cannot be empty");
        }
        if(address.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Incomplete",Toast.LENGTH_SHORT).show();
            t2.setError("This field cannot be empty");
        }
        if(email.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Incomplete",Toast.LENGTH_SHORT).show();
            t5.setError("This field cannot be empty");
        }
        if(TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_SHORT).show();
            t5.setError("Email address is invalid");
        }
        else if(name.length()!=0 && address.length()!=0)
        {
            state = stateSpinner.getSelectedItem().toString();
            country = countrySpinner.getSelectedItem().toString();
            Toast.makeText(getApplicationContext(),"Submitted",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(LoginActivity.this,HomeActivity.class);
            i.putExtra("email","Email: "+email);
            i.putExtra("name","Name:"+name);
            i.putExtra("address","Address: " + address + " " + address2 + " " + city);
            i.putExtra("stateCountry","State: " + state + ", " + country);
            startActivity(i);
        }

    }

}
