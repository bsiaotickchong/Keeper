package com.bstc.keeper;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Brian on 7/23/2015.
 * activity for adding a new Thing, event, person, etc. into the database
 */
public class AddThing extends ActionBarActivity{

    MyDBHandler dbHandler; //doesn't need to be instantiated in Home activity because the database stays?
    EditText nameField;
    EditText descField;
    EditText imageField;
    EditText tagsField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thing);

        nameField = (EditText)findViewById(R.id.nameField);
        descField = (EditText)findViewById(R.id.descriptionField);
        imageField = (EditText)findViewById(R.id.imageField);
        tagsField = (EditText)findViewById(R.id.tagsField);
        dbHandler = new MyDBHandler(this,null,null,1);


    }

    public void onAddButtonClicked(View v){
        Thing thing = new Thing(
                nameField.getText().toString(),
                //R.mipmap.ic_launcher,
                getResources().getIdentifier(imageField.getText().toString(), "mipmap", getPackageName()), //REQUIRES that the user inputs only the file name that's inside mipmap
                descField.getText().toString(),
                tagsField.getText().toString());

        dbHandler.addThing(thing);

        Toast.makeText(this, nameField.getText().toString() + " was added!", Toast.LENGTH_LONG).show();
        //printDatabase();
    }

    public void printDatabase(){
        Toast.makeText(this, dbHandler.databaseToString(), Toast.LENGTH_LONG).show();
    }


}
