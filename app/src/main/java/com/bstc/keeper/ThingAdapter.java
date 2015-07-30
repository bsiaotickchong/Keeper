package com.bstc.keeper;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Brian on 7/21/2015.
 */
class ThingAdapter extends ArrayAdapter<Thing>{

    Context myContext;

    //constructor takes array of things which is passed to parent class which stores it
    ThingAdapter(Context context, Thing[]things) {
        super(context, R.layout.custom_thing_row, things);

        myContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_thing_row, parent, false);

        final Thing thing = getItem(position); //get item will get the object out of the things array at the position
        //Image image = getItem(position);
        final TextView nameText = (TextView)customView.findViewById(R.id.thingName);
        final ImageView icon = (ImageView)customView.findViewById(R.id.thingIcon);
        final TextView descriptionText = (TextView)customView.findViewById(R.id.thingDescription);
        final TextView tags = (TextView)customView.findViewById(R.id.thingTags);
        final LinearLayout row = (LinearLayout) customView.findViewById(R.id.customThingRowLayout);

        nameText.setText(thing.get_name());
        icon.setImageResource(thing.get_iconResourceId());
        descriptionText.setText(thing.get_description());
        tags.setText(thing.get_tags());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myContext, ViewThingActivity.class);
                i.putExtra("name", nameText.getText().toString());
                i.putExtra("iconResourceId", thing.get_iconResourceId()); //don't try to get image resource id from imageview or else it'll get the id of the imageview instead
                i.putExtra("description", descriptionText.getText().toString());
                i.putExtra("tags", tags.getText().toString());

                myContext.startActivity(i);
            }
        });

        return customView;
    }
}
