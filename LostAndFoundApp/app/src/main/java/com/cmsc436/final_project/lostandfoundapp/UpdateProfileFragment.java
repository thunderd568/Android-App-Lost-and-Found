package com.cmsc436.final_project.lostandfoundapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    Users mUser;
    DatabaseReference databaseReference;
    String mEmail;
    TextView username, password,cPassword;
    FirebaseUser user;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment_update_profile, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        firebaseAuth = FirebaseAuth.getInstance().getInstance();
        user = firebaseAuth.getCurrentUser();
        mEmail = user.getEmail();
        username = fragview.findViewById(R.id.username);
        password = fragview.findViewById(R.id.changePassword);
        cPassword = fragview.findViewById(R.id.confirmPasswordChanged);

        return fragview;
    }

    public void onStart(){
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
