package com.cmsc436.final_project.lostandfoundapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PostNewLostReportActivity extends AppCompatActivity {

    private final static String TAG = "Post New Lost Report Activity";

    protected EditText mTitleTextField; // The text field for the title of the post.
    protected EditText mDescriptionTextField;   // The text field for a description.
    protected EditText mAddressTextField;
    protected FirebaseAuth mFirebaseAuth;
    protected Query mUserNameQuery;
    protected DatabaseReference mDatabaseReference;
    protected String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_new_lost_report);

        // TODO: Implement the logic to get the necessary information from the
        // EditText elements and push a new foundReport into the Firebase.
        // This requires us to have an xml that has the necessary id's. So Whoever
        // makes the xml and choose to do this, they can fill in the missing pieces.

        /* To push the new item report, we need to get the current users username.
         * this might involve more querying...Hope this skeleton helps.
         *
         */


        mFirebaseAuth = FirebaseAuth.getInstance();
        String email = mFirebaseAuth.getCurrentUser().getEmail();

        // Get reference to the 'users' tree in the Firebase Database
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");

    }

}
