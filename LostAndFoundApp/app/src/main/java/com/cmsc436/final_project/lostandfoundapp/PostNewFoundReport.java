package com.cmsc436.final_project.lostandfoundapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PostNewFoundReport extends AppCompatActivity {

    private final static String TAG = "PostNewFound";

    protected EditText mTitleTextField; // The text field for the title of the post.
    protected EditText mDescriptionTextField;   // The text field for a description.
    protected EditText mAddressTextField;
    protected Query mUserNameQuery;
    protected DatabaseReference mDatabaseReference;
    protected String currentUserName;
    private static final int MAP_ACTIVITY_RESULT_CODE = 0;
    private Double curr_Lat, curr_Lng;
    private String curr_address;
    private String currentUser;
    private TextView coordinates;
    private TextView addressText;
    private static TextView dateFoundText;
    private static String datePicked;
    private DatePickerDialog datePicker;

    // Firebase tools
    protected FirebaseAuth mFirebaseAuth;
    // Buttons
    protected Button mGetLocationButton;
    protected Button mDateFoundPickerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_new_found);
        // TODO: Implement the logic to get the necessary information from the
        // EditText elements and push a new foundReport into the Firebase.
        // This requires us to have an xml that has the necessary id's. So Whoever
        // makes the xml and choose to do this, they can fill in the missing pieces.

        /* To push the new item report, we need to get the current users username.
         * this might involve more querying...Hope this skeleton helps.
         *
         */
        // Get reference to the 'users' tree in the Firebase Database
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        mGetLocationButton = findViewById(R.id.getFoundLocationButton);
        mDateFoundPickerButton = (Button) findViewById(R.id.pickDateFoundButton);
        dateFoundText = findViewById(R.id.postFoundDateFound);

        dateFoundText.setText(datePicked);

        mFirebaseAuth = FirebaseAuth.getInstance(); // Will use this to get the username of the person logged in.

        mGetLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: inside of the button");
                Intent intent = new Intent(PostNewFoundReport.this, MapActivity.class);
                startActivityForResult(intent,MAP_ACTIVITY_RESULT_CODE);
            }
        });

        // Logic for picking the date the item was found using a date picker from android
        mDateFoundPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Date picker button was clicked");
                Calendar c = Calendar.getInstance();
                int currMonth = c.get(Calendar.MONTH);
                int currDay = c.get(Calendar.DAY_OF_MONTH);
                int currYear = c.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(PostNewFoundReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateFoundText.setText((month + 1) + "/" + day + "/" + year);
                    }
                }, currYear, currMonth, currDay);
                datePicker.show();
            }
        });



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult: Enter");

        curr_Lat = data.getDoubleExtra("curr_lat",0.0);
        curr_Lng = data.getDoubleExtra("curr_lng", 0.0);
        curr_address = data.getStringExtra("curr_address");

        Log.i(TAG, "onActivityResult: curr_lng: "+ curr_Lng + " curr_lat: " + curr_Lat + " curr_address: "+ curr_address);

        coordinates = findViewById(R.id.postFoundCoordinates);
        coordinates.setText(new DecimalFormat("###.##").format(curr_Lat).toString() + ", " +
                new DecimalFormat("###.##").format(curr_Lng).toString());

        addressText = findViewById(R.id.textView11);
        addressText.setText(curr_address);

        if(MAP_ACTIVITY_RESULT_CODE == requestCode && RESULT_OK == requestCode){
            ItemReport foundReport = new ItemReport();


        }

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

}
