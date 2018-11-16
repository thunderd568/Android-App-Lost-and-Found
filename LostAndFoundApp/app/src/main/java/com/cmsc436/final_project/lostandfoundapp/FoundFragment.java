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
                "Description",
                new Date(),
                new Date(),
                new LatLng(0.0, 0.0),
                "Address0",
                true
        ));
        myFoundReports.add(new ItemReport(
                "Title1",
                "Description1",
                new Date(),
                new Date(),
                new LatLng(0.01, -0.01),
                "Address1",
                true
        ));
        myFoundReports.add(new ItemReport(
                "Title2",
                "Description2",
                new Date(),
                new Date(),
                new LatLng(0.02, -0.02),
                "Address2",
                true
        ));
        myFoundReports.add(new ItemReport(
                "Title3",
                "Description3",
                new Date(),
                new Date(),
                new LatLng(0.03, -0.03),
                "Address3",
                true
        ));

        ReportAdapter mReportAdapter = new ReportAdapter(getContext(), myFoundReports);
        myReportsRecyclerView.setAdapter(mReportAdapter);

        // Inflate the layout for this fragment
        return fragView;
    }

}
