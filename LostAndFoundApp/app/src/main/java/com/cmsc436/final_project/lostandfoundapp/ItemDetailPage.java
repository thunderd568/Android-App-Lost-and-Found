package com.cmsc436.final_project.lostandfoundapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class ItemDetailPage extends AppCompatActivity {

    private TextView titleReportText, addressDetailText, datePostedInfo, reportDescriptionText;
    private Button deletePostButton;
    private Button contactAuthorButton;

    // These will be the items we unpackage from our intent
    private String mTitle, mDescription, mAddress, reportAuthor;
    private double mLatitude, mLongitude;
    private Date mDate;

    //Firebase stuff
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_page);


        // TODO: This class will display all the information of an item.
        // The user will click the item via either their own post fragments or
        // while searching for an item. The Items info will be unpackaged from an intent.
        // There will be a button defined that gives the user the option to delete the items report
        // if and only if the user is view their OWN authored post. Otherwise a user will be able
        // to get the contact information of the author.

        // Init Firebase stuff here
        mFirebaseAuth = FirebaseAuth.getInstance();

        // Init textViews and buttons here
        titleReportText = findViewById(R.id.titleReportText);
        addressDetailText = findViewById(R.id.addressDetailText);
        datePostedInfo = findViewById(R.id.datePostedInfo);
        reportDescriptionText = findViewById(R.id.reportDescriptionText);
        deletePostButton = findViewById(R.id.deletePostButton);
        contactAuthorButton = findViewById(R.id.contactAuthorButton);


        // Begin unpackaging intent extras here
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mAddress = intent.getStringExtra("address");
        mDescription = intent.getStringExtra("description");
        mDate = (Date) intent.getSerializableExtra("dateAuthored");

        // If the person viewing this post is the author of the post then only show them the button
        // to delete the post, not contact the poster. Show the other button otherwise
        reportAuthor = intent.getStringExtra("author");

        if (reportAuthor.equals(mFirebaseAuth.getCurrentUser().getDisplayName())) {
            // Make the visibility of the delete button true and the contact button false
            deletePostButton.setVisibility(View.VISIBLE);
            contactAuthorButton.setVisibility(View.INVISIBLE);
        } else {
            // The person viewing this report is not the OP, Show them the contact author button.
            deletePostButton.setVisibility(View.INVISIBLE);
            contactAuthorButton.setVisibility(View.VISIBLE);
        }

        setTextViews(mTitle, mAddress, mDescription, mDate);

        // TODO: Implement the button functionality for contacting the OP and deleting a post
        deletePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        contactAuthorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    private void setTextViews(String mTitle, String mAddress, String mDescription, Date mDate) {
        titleReportText.setText(mTitle);
        addressDetailText.setText(mAddress);
        reportDescriptionText.setText(mDescription);
        datePostedInfo.setText(mDate.toString());

    }
}
