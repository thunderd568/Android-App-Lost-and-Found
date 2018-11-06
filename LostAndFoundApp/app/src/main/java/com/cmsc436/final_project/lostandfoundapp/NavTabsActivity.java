package com.cmsc436.final_project.lostandfoundapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class NavTabsActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_tabs);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new UserProfileFragment());
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.tab_fragment_container, fragment)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_profile_tab:
                fragment = new UserProfileFragment();
                break;

            case R.id.navigation_lost_tab:
                fragment = new LostFragment();
                break;

            case R.id.navigation_found_tab:
                fragment = new FoundFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
