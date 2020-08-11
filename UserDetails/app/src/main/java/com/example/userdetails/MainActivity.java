package com.example.userdetails;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;

// Base Stitch Packages
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;

// Packages needed to interact with MongoDB and Stitch
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

// Necessary component for working with MongoDB Mobile
import com.mongodb.stitch.android.services.mongodb.local.LocalMongoDbService;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class MainActivity extends AppCompatActivity {

    DatePickerDialog picker;
    EditText eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eText=(EditText) findViewById(R.id.dob);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });
    }
    public void onRegister(View view){
        EditText name, email, phoneno, dob;
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phoneno = (EditText) findViewById(R.id.phoneno);
        dob = (EditText) findViewById(R.id.dob);
        RadioGroup radiogp = (RadioGroup) findViewById(R.id.gender);
        String result = "";

        try {
            String nameText = name.getText().toString().trim();
            if (!nameText.matches("[a-zA-Z ]+"))
            {
                Toast t = Toast.makeText(getApplicationContext(),"Not a valid Name", Toast.LENGTH_SHORT);
                t.show();
                return ;
            }
            String ValidateEmail = email.getText().toString().trim();
            if (!ValidateEmail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                Toast t = Toast.makeText(getApplicationContext(),"Not a valid Email Address", Toast.LENGTH_SHORT);
                t.show();
                return ;
            }
            if (phoneno.getText().toString().length() != 10 ){
                Toast t = Toast.makeText(getApplicationContext(),"Not a valid Mobile Number", Toast.LENGTH_SHORT);
                t.show();
                return ;
            }
            if(dob.getText().toString().trim().equals("")){
                Toast t = Toast.makeText(getApplicationContext(),"Please Choose Date of Birth", Toast.LENGTH_SHORT);
                t.show();
                return ;
            }
        }
        catch(Exception e){
            Toast t = Toast.makeText(getApplicationContext(),"Some Error Occured", Toast.LENGTH_SHORT);
            t.show();
            return ;
        }

        String curyear = (String.valueOf(java.time.LocalDate.now()));
        curyear = curyear.substring(0, 4);
        String dobyear = String.valueOf(dob.getText());
        dobyear = dobyear.substring(5, dobyear.length());
        String age = String.valueOf(Integer.parseInt(curyear) - Integer.parseInt(dobyear));
        System.out.println(age);
        result += "Hello " + String.valueOf(name.getText());
        result += "\n We're Glad to know that your are " + age + " year  old";
        result += "\n Email: " + email.getText().toString();
        result += "\n Phone Number : " + phoneno.getText().toString();

        int selectedId = radiogp.getCheckedRadioButtonId();

        if (selectedId == -1){
            Toast t = Toast.makeText(getApplicationContext(),"No Gender Selected", Toast.LENGTH_SHORT);
            t.show();
            return;
        } else {
            RadioButton rd = (RadioButton) findViewById(selectedId);
            result += "\n Gender : " + String.valueOf(rd.getText());
        }


        Toast t = Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG);
        t.show();

    }

    public void onCancel(View view){
        this.finishAffinity();
    }

    // Create the default Stitch Client
    final StitchAppClient client = Stitch.initializeDefaultAppClient("<APP ID>");

    // Create a Client for MongoDB Mobile (initializing MongoDB Mobile)
    final MongoClient mobileClient = client.getServiceClient(LocalMongoDbService.clientFactory);

}
