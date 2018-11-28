package com.cmsc436.final_project.lostandfoundapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LostFragment extends Fragment {

    protected RecyclerView myReportsRecyclerView;
    protected DatabaseReference databaseLostItems;
    protected List<ItemReport> reports;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_lost, container, false);

        databaseLostItems = FirebaseDatabase.getInstance().getReference("lost_reports");

        myReportsRecyclerView = (RecyclerView) fragView.findViewById(R.id.recyclerViewMyLostReports);
        myReportsRecyclerView.setHasFixedSize(true);
        myReportsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        reports = new ArrayList<ItemReport>();

        return fragView;
    }


    @Override
    public void onStart() {
        super.onStart();

        databaseLostItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reports.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ItemReport report = postSnapshot.getValue(ItemReport.class);
                    reports.add(report);
                }

                ReportAdapter mAdapter = new ReportAdapter(getContext(), reports);
                myReportsRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
