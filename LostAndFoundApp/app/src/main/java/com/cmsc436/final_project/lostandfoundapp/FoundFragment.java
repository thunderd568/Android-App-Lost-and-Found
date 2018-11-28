package com.cmsc436.final_project.lostandfoundapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoundFragment extends Fragment {
    ArrayList<ItemReport> myFoundReports;
    RecyclerView myReportsRecyclerView;
    DatabaseReference databaseFoundItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragment_found, container, false);
        myReportsRecyclerView = (RecyclerView) fragView.findViewById(R.id.recyclerViewMyFoundReports);
        myReportsRecyclerView.setHasFixedSize(true);
        myReportsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //TODO: not sure if this will work inside of constraint layout

        myFoundReports = new ArrayList<ItemReport>();

        databaseFoundItems = FirebaseDatabase.getInstance().getReference("found_reports");


/*  Test data, do not run again unless you wanna rape database everytime we create found fragment
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("found_reports");
        database.child(database.push().getKey()).setValue(myFoundReports.get(0));
        database.child(database.push().getKey()).setValue(myFoundReports.get(1));
        database.child(database.push().getKey()).setValue(myFoundReports.get(2));
        database.child(database.push().getKey()).setValue(myFoundReports.get(3));
        database.child(database.push().getKey()).setValue(myFoundReports.get(4));
        database.child(database.push().getKey()).setValue(myFoundReports.get(5));
*/

        // Inflate the layout for this fragment
        return fragView;
    }


    @Override
    public void onStart() {
        super.onStart();

        databaseFoundItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myFoundReports.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    String address = snapshot.child("address").getValue().toString();
                    String author = snapshot.child("author").getValue().toString();
                    Date dateAuthored = snapshot.child("dateAuthored").getValue(Date.class);
                    Date dateOccurred = snapshot.child("dateOccurred").getValue(Date.class);
                    String description = snapshot.child("description").getValue().toString();
                    Double lat = snapshot.child("latLng").child("latitude").getValue(Double.class);
                    Double longitude = snapshot.child("latLng").child("longitude").getValue(Double.class);
                    String method = snapshot.child("method").getValue().toString();
                    String status = snapshot.child("status").getValue().toString();
                    String title = snapshot.child("title").getValue().toString();
                    String type = snapshot.child("type").getValue().toString();

                    // Make a new latLng object. We can't deserialize the LatLng object inside our object
                    // So the work around is to get the double values, make a new LatLng object
                    // and pass it into the constructor. Note that since this is the found fragment
                    // "true" will be passed in as the argument...duh!
                    LatLng latLng = new LatLng(lat, longitude);


                    ItemReport report = new ItemReport(title, description, author, dateOccurred,
                            dateAuthored, latLng, address, true);

                    myFoundReports.add(report);
                }

                ReportAdapter mReportAdapter = new ReportAdapter(getContext(), myFoundReports);
                myReportsRecyclerView.setAdapter(mReportAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

    }

}
