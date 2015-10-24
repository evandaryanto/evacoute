package com.merdekabyte.evacoute.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merdekabyte.evacoute.R;

/**
 * Created by Michinggun on 10/24/2015.
 */
public class RefugeLocationInformationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_refuge_location_information, container, false);
        return v;
    }
}
