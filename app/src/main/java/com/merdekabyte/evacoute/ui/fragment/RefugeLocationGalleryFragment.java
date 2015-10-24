package com.merdekabyte.evacoute.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merdekabyte.evacoute.R;
import com.merdekabyte.evacoute.ui.adapter.GalleryGridAdapter;

/**
 * Created by Michinggun on 10/25/2015.
 */
public class RefugeLocationGalleryFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_refuge_location_gallery, container, false);
        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.refuge_location_gallery_recycler);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GalleryGridAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }
}
