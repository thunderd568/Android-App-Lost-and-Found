package com.cmsc436.final_project.lostandfoundapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {

    ArrayList<ItemReport> myReports;
    RecyclerView myReportsRecyclerView;
    DatabaseReference databaseResultItems;
    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseUsers;
    Query myAuthorQuery;
    Query myReportsQuery;

    boolean locationSelected = false;
    double curr_Lat = 0.0;
    double curr_Lng = 0.0;

    double latMax, latMin, lngMax, lngMin = 0.0;
    double radiusMiles = 0.25;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Intent intent = getIntent();





    }
}
