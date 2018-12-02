package com.cmsc436.final_project.lostandfoundapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ItemDetailPage extends AppCompatActivity {

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
    }
}
