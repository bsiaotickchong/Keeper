package com.bstc.keeper;

import android.app.Activity;
import android.app.Fragment;
import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Home extends FragmentActivity {

    MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);;

    protected Thing [] things = new Thing[]{
            new Thing("Brian", R.mipmap.default_image, "Cool guy.", "#such cool"),
            new Thing("Bertie", R.mipmap.ic_launcher, "Other cool guy.", "much wow")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        HomeList homeListFragment = (HomeList) getSupportFragmentManager().findFragmentById(R.id.homeListFragment);
        getSupportFragmentManager().beginTransaction()
                .add(homeListFragment, "homeList")
                        // Add this transaction to the back stack
                .addToBackStack("homeList")
                .commit();
                */

        Bundle loginData = getIntent().getExtras();
        if(loginData == null)
            return;

        String loginEmail = loginData.getString("loginEmail");
        String password = loginData.getString("password");

        Toast.makeText(Home.this, "Email: "+loginEmail, Toast.LENGTH_LONG).show();
        Toast.makeText(Home.this, "Password: "+password, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        HomeList listOfThings = (HomeList)getSupportFragmentManager().findFragmentById(R.id.homeListFragment);
        listOfThings.setList(dbHandler.databaseToThingList());
        super.onResume();
    }

    public void onAddButtonClick(View view){
        Intent i = new Intent(this, AddThing.class);
        startActivity(i);
    }
}
