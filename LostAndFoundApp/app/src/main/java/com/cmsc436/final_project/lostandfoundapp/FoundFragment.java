package com.cmsc436.final_project.lostandfoundapp;


import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoundFragment extends Fragment {
    ArrayList<ItemReport> myFoundReports;
    RecyclerView myReportsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragment_found, container, false);
        myReportsRecyclerView = (RecyclerView) fragView.findViewById(R.id.recyclerViewMyFoundReports);
        myReportsRecyclerView.setHasFixedSize(true);
        myReportsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //TODO: not sure if this will work inside of constraint layout

        myFoundReports = new ArrayList<ItemReport>();
        myFoundReports.add(new ItemReport(
                "Title0",
                "Description0",
                "Author0",
                new Date(),
                new Date(),
                new LatLng(0.0, 0.0),
                "Address0",
                true
        ));
        myFoundReports.add(new ItemReport(
                "Title1",
                "Description1",
                "Author1",
                new Date(),
                new Date(),
                new LatLng(0.01, -0.01),
                "Address1",
                true
        ));
        myFoundReports.add(new ItemReport(
                "Title2",
                "Description2",
                "Author2",
                new Date(),
                new Date(),
                new LatLng(0.02, -0.02),
                "Address2",
                true
        ));
        myFoundReports.add(new ItemReport(
                "Title3",
                "Description3",
                "Author3",
                new Date(),
                new Date(),
                new LatLng(0.03, -0.03),
                "Address3",
                true
        ));
        myFoundReports.add(new ItemReport(
                "Title4",
                "Description4",
                "Author4",
                new Date(),
                new Date(),
                new LatLng(0.04, -0.04),
                "Address4",
                true
        ));
        myFoundReports.add(new ItemReport(
                "Title5",
                "Description5",
                "Author5",
                new Date(),
                new Date(),
                new LatLng(0.05, -0.05),
                "Address5",
                true
        ));

        ReportAdapter mReportAdapter = new ReportAdapter(getContext(), myFoundReports);
        myReportsRecyclerView.setAdapter(mReportAdapter);

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

}
