package com.cmsc436.final_project.lostandfoundapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private String TAG = "Update Profile Fragment";
    DatabaseReference databaseUsers;
    String mEmail,mPassword,mCpassword;
    TextView email,username, password,cPassword;
    FirebaseUser user;
    Button cancel, update;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment_update_profile, container, false);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        firebaseAuth = FirebaseAuth.getInstance().getInstance();
        user = firebaseAuth.getCurrentUser();
        mEmail = user.getEmail();
        email = fragview.findViewById(R.id.updateuseremail);
        password = fragview.findViewById(R.id.changePassword);
        cPassword = fragview.findViewById(R.id.confirmPasswordChanged);

        cancel = fragview.findViewById(R.id.cancel);
        update = fragview.findViewById(R.id.btProfileUpdateSubmit);
        email.setText(mEmail);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.tab_fragment_container,new UserProfileFragment());
                ft.commit();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPassword = password.getText().toString().trim();
                mCpassword = cPassword.getText().toString().trim();

                if(!mPassword.equals(mCpassword)){
                    Toast.makeText(getContext(),"Please check if both passwords are the same", Toast.LENGTH_LONG).show();
                }else if(mPassword.length() < 6 ){
                    Toast.makeText(getContext(), "Password must be at least 6 characters", Toast.LENGTH_LONG).show();
                }else{
                    user.updatePassword(mPassword);
                    firebaseAuth.updateCurrentUser(user);
                    Toast.makeText(getContext(), "Password has been updated", Toast.LENGTH_LONG).show();
                }
            }
        });


        return fragview;
    }
}
