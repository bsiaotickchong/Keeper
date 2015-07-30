package com.bstc.keeper;

import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeList extends Fragment {

    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);
        listView = (ListView)view.findViewById(R.id.listOfThings);
        return view;
        //return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void setList(Thing[] things){
        //use custom list adapter
        ListAdapter thingAdapter = new ThingAdapter(getActivity(), things); //context used is the activity this fragment belongs to
        listView.setAdapter(thingAdapter);
    }
}
