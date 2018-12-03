package com.cmsc436.final_project.lostandfoundapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.Date;

public class SearchFoundReportsActivity extends AppCompatActivity {

    private static String found_reports = "found_reports";
    private static final int MAP_ACTIVITY_RESULT_CODE = 0;
    private static final String TAG = "SearchFoundReport";

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseReference;

    // Buttons and textfields.
    private Button mLocationSearchingFoundButton;
    private TextView mSearchFoundCoordinates, mSearchFoundAddress;
    private Spinner mRadiusSearchFoundSpinner;
    private Button mLaunchSearchFoundButton;

    boolean locationSelected = false;
    double curr_Lat = 0.0;
    double curr_Lng = 0.0;

    double latMax, latMin, lngMax, lngMin = 0.0;
    double radiusMiles = 0.25;

    boolean earlyBoundSelected = false;
    Date earlyBound = new Date();
    boolean lateBoundSelected = false;
    Date lateBound = new Date();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_found_reports);

        // Init instance variables
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(found_reports);

        mLocationSearchingFoundButton = findViewById(R.id.getFoundSearchLocationButton);
        mSearchFoundCoordinates = findViewById(R.id.searchFoundCoordinates);
        mSearchFoundAddress = findViewById(R.id.searchFoundAddress);
        mLaunchSearchFoundButton = findViewById(R.id.launchSearchFoundButton);


        mRadiusSearchFoundSpinner = findViewById(R.id.radiusSearchFoundSpinner);
        ArrayAdapter<CharSequence> mArrayAdapter = ArrayAdapter.createFromResource(
                this, R.array.radius_spinner_options, android.R.layout.simple_spinner_item);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRadiusSearchFoundSpinner.setAdapter(mArrayAdapter);
        mRadiusSearchFoundSpinner.setSelection(4);
        mRadiusSearchFoundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: // 5 miles
                        radiusMiles = 5.0;
                        break;
                    case 1: // 2 miles
                        radiusMiles = 2.0;
                        break;
                    case 2: // 1 mile
                        radiusMiles = 1.0;
                        break;
                    case 3: // half mile
                        radiusMiles = 0.5;
                        break;
                    case 4: // quarter mile
                        radiusMiles = 0.25;
                        break;
                    case 5: // 500 feet
                        radiusMiles = 500.0/5280.0;
                        break;
                    case 6: // 250 feet
                        radiusMiles = 250.0/5280.0;
                        break;
                    default:
                        Log.i("wtf: ", "How did we get here");
                        break;
                }
                if(locationSelected){
                    calcLatLngMaxMin();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mLocationSearchingFoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Entering onClick method for Get Location button");
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivityForResult(intent, MAP_ACTIVITY_RESULT_CODE);
            }
        });

        mLaunchSearchFoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchResults.class);
                intent.putExtra("locationSelected", (locationSelected ? 1 : 0));
                intent.putExtra("latMax", latMax);
                intent.putExtra("latMin", latMin);
                intent.putExtra("lngMax", lngMax);
                intent.putExtra("lngMin", lngMin);

                intent.putExtra("earlyBoundSelected", (earlyBoundSelected ? 1 : 0));
                intent.putExtra("earlyBound", earlyBound);
                intent.putExtra("lateBoundSelected", (lateBoundSelected ? 1 : 0));
                intent.putExtra("lateBound", lateBound);

                intent.putExtra("isFound", 1);

                startActivity(intent);
            }
        });



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "coming back from MapActivity with results");
        locationSelected = true;

        // Store the results in variables.
        curr_Lat = data.getDoubleExtra("curr_lat",0.0);
        curr_Lng = data.getDoubleExtra("curr_lng", 0.0);
        String curr_address = data.getStringExtra("curr_address");

        mSearchFoundCoordinates = findViewById(R.id.searchFoundCoordinates);

        Log.i(TAG, "onActivityResult: curr_lng: "+ curr_Lng + " curr_lat: " + curr_Lat + " curr_address: "+ curr_address);

        mSearchFoundCoordinates.setText(new DecimalFormat("###.##").format(curr_Lat).toString() + ", " +
                new DecimalFormat("###.##").format(curr_Lng).toString());
        mSearchFoundAddress = findViewById(R.id.searchFoundAddress);
        mSearchFoundAddress.setText(curr_address);

        calcLatLngMaxMin();

    }

    void calcLatLngMaxMin(){
        latMax = curr_Lat + (radiusMiles/69.1);
        latMin = curr_Lat - (radiusMiles/69.1);
        lngMax = curr_Lng + (radiusMiles/(69.1*(Math.cos(curr_Lat/57.3))));
        lngMin = curr_Lng - (radiusMiles/(69.1*(Math.cos(curr_Lat/57.3))));
        Toast.makeText(getApplicationContext(),
                "latMax:"+latMax+",latMin:"+latMin+",lngMax:"+lngMax+",lngMin:"+lngMin,
                Toast.LENGTH_LONG).show();
    }
}
