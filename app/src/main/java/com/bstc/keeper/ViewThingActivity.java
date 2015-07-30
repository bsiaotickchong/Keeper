package com.bstc.keeper;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Brian on 7/28/2015.
 * activity that displays when you press an item on the homelist
 */
public class ViewThingActivity extends ActionBarActivity{

    TextView name;
    TextView description;
    TextView tags;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_thing);

        name = (TextView)findViewById(R.id.viewThingNameText);
        description = (TextView)findViewById(R.id.viewThingDescriptionText);
        tags = (TextView)findViewById(R.id.viewThingTagsText);
        icon = (ImageView)findViewById(R.id.viewThingImage);

        Bundle thingData = getIntent().getExtras(); //get data from home activity or whatever's calling this

        name.setText(thingData.getString("name"));
        description.setText(thingData.getString("description"));
        tags.setText("Tags: " + thingData.getString("tags"));
        icon.setImageResource(thingData.getInt("iconResourceId"));
    }
}
