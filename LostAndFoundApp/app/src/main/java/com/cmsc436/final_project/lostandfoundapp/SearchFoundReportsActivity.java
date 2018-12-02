package com.cmsc436.final_project.lostandfoundapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;

public class SearchFoundReportsActivity extends AppCompatActivity {

    private static String found_reports = "found_reports";
    private static final int MAP_ACTIVITY_RESULT_CODE = 0;
    private static final String TAG = "SearchFoundReport";

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseReference;

    // Buttons and textfields.
    private Button mLocationSearchingFoundButton;
    private TextView mSearchFoundCoordinates, mSearchFoundAddress;


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

        mLocationSearchingFoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Entering onClick method for Get Location button");
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivityForResult(intent, MAP_ACTIVITY_RESULT_CODE);
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "coming back from MapActivity with results");

        // Store the results in variables.
        double curr_Lat = data.getDoubleExtra("curr_lat",0.0);
        double curr_Lng = data.getDoubleExtra("curr_lng", 0.0);
        String curr_address = data.getStringExtra("curr_address");

        mSearchFoundCoordinates = findViewById(R.id.searchFoundCoordinates);

        Log.i(TAG, "onActivityResult: curr_lng: "+ curr_Lng + " curr_lat: " + curr_Lat + " curr_address: "+ curr_address);

        mSearchFoundCoordinates.setText(new DecimalFormat("###.##").format(curr_Lat).toString() + ", " +
                new DecimalFormat("###.##").format(curr_Lng).toString());
        mSearchFoundAddress = findViewById(R.id.searchFoundAddress);
        mSearchFoundAddress.setText(curr_address);

    }

}
