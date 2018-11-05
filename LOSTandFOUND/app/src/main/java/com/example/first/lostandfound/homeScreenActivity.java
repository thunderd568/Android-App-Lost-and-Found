package com.example.first.lostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;


public class homeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        final Button lostItemsButton = (Button) findViewById(R.id.LostItemsButton);
        lostItemsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                lostItemsButtonAction();
            }
        });

        final Button foundItemsButton = (Button) findViewById(R.id.FoundItemsButton);
        foundItemsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                foundItemsButtonAction();
            }
        });

        final Button myAccountButton = (Button) findViewById(R.id.myAccountButton);
        myAccountButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myAccountButtonAction();
            }
        });

        final Button settingButton = (Button) findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                settingButtonAction();
            }
        });
    }


    private void lostItemsButtonAction(){
        Intent intent = new Intent (this,LostItemsActivity.class);
        startActivity(intent);
    }

    private void foundItemsButtonAction(){
        Intent intent = new Intent (this,FoundItemsActivity.class);
        startActivity(intent);
    }

    private void myAccountButtonAction(){
        Intent intent = new Intent (this,myAccountActivity.class);
        startActivity(intent);
    }

    private void settingButtonAction(){
        Intent intent = new Intent (this,settingActivity.class);
        startActivity(intent);
    }
}
