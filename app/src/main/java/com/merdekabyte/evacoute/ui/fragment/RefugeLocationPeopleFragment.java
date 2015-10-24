package com.merdekabyte.evacoute.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merdekabyte.evacoute.R;
import com.merdekabyte.evacoute.ui.adapter.RefugeLocationPeopleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michinggun on 10/25/2015.
 */
public class RefugeLocationPeopleFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_refuge_location_people, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.refuge_location_people_recycler);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<String> name = new ArrayList<String>();
        name.add("Michael Ingga Gunawan");
        name.add("Ignatius Evan Daryanto");
        name.add("Alif Raditya Rochman");
        name.add("Pandu Kartika Putra");
        name.add("Sonny Lazuardi Hermawan");
        name.add("Harits Elfahmi");
        mAdapter = new RefugeLocationPeopleAdapter(getActivity(), name);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }
}
