package com.example.kdzekov.helloworld;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtShowCountry;
    private Spinner spinner2;
    private Button button2;
    private HashMap<String, String> countryByCode = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Button button2 = (Button) findViewById(R.id.button2);
        final TextView txtShowCountry = (TextView) findViewById(R.id.txtShowCountry);

        addListenerOnButton();
        countryByCode = getCountryCodes();

        //addListenerOnSpinnerItemSelection();

        /*SqlDbHelper dbHelper = new SqlDbHelper(this);

        dbHelper.insertCountry("MKD", "Macedonia");
        dbHelper.insertCountry("BUL", "Bulgaria");
        dbHelper.insertCountry("CRO", "Croatia");
        dbHelper.insertCountry("ALB", "Albania");
        dbHelper.insertCountry("GRE", "Greece");

        dbHelper.insertMountains("M1", "MKD", "Shar Planina");
        dbHelper.insertMountains("M2", "MKD", "Korab");
        dbHelper.insertMountains("M3", "MKD", "Bistra");
        dbHelper.insertMountains("M4", "MKD", "Deshat");

        dbHelper.insertMountains("B1", "BUL", "Pirin");
        dbHelper.insertMountains("B2", "BUL", "Rila");
        dbHelper.insertMountains("B3", "BUL", "Stara Planina");
        dbHelper.insertMountains("B4", "BUL", "Rhodope");*/

        /*SqlDbHelper dbHelper = new SqlDbHelper(this);
        List<String> mountains = dbHelper.getMountainList("MKD");
        String selectedMountains = "";

        for (String mount:mountains){
            selectedMountains+=mount+"<br>";
        }*/

        //txtShowCountry.setText(selectedMountains);
    }

    /*public void addListenerOnSpinnerItemSelection() {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }*/

    public void addListenerOnButton() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        button2 = (Button) findViewById(R.id.button2);
        txtShowCountry = (TextView) findViewById(R.id.txtShowCountry);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txtShowCountry.setText(spinner2.getSelectedItem().toString());
                String ccode = countryByCode.get(spinner2.getSelectedItem().toString());
                txtShowCountry.setText(showMountainList(ccode));
            }
        });

    }

    private String showMountainList(String countryCode) {

        SqlDbHelper dbHelper = new SqlDbHelper(this);
        List<String> mountains = dbHelper.getMountainList(countryCode);
        String selectedMountains = "";

        //Button btn = null;

        for (String mount : mountains) {
            selectedMountains += mount + "\n";
        }

        return selectedMountains;
    }

    public void startNewActivity(View view){
        Intent intent = new Intent(this, DisplayMountainDetails.class);
        startActivity(intent);

    }

    private HashMap getCountryCodes() {

        HashMap<String, String> ccodes = new HashMap<>();

        ccodes.put("Macedonia", "MKD");
        ccodes.put("Bulgaria", "BUL");
        ccodes.put("Greece", "GRE");
        ccodes.put("Albania", "ALB");
        ccodes.put("Turkey", "TUR");
        ccodes.put("Austria", "AUS");
        ccodes.put("Italy", "ITA");
        ccodes.put("Russia", "RUS");
        ccodes.put("France", "FRA");

        return ccodes;
    }

}
