package com.merdekabyte.evacoute.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merdekabyte.evacoute.R;
import com.merdekabyte.evacoute.data.model.Refuge;
import com.merdekabyte.evacoute.data.model.User;
import com.merdekabyte.evacoute.data.repository.RefugeRepository;
import com.merdekabyte.evacoute.data.repository.UserRepository;
import com.merdekabyte.evacoute.ui.adapter.LocationAdapter;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michinggun on 10/24/2015.
 */
public class RefugeFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private PullToRefreshView mPullToRefreshView;
    public static final int REFRESH_DELAY = 2000;

    private RefugeRepository refugeRepository;
    private UserRepository userRepository;

    SearchBox searchBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_refuge, container, false);
        setmPullToRefreshView(v);
        mPullToRefreshView.setRefreshing(true);
        setmRecyclerView(v);

        Bundle arguments = getArguments();
        String state = "";
        if (arguments != null) {
            state = arguments.getString("state");
            if (state == null) {
                state = "";
            }
        }

        searchBox = (SearchBox) getActivity().findViewById(R.id.search_box);
        searchBox.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {

            }

            @Override
            public void onSearchCleared() {

            }

            @Override
            public void onSearchClosed() {
                closeSearch();
            }

            @Override
            public void onSearchTermChanged(String s) {
                LocationAdapter locationAdapter = (LocationAdapter) mAdapter;
                locationAdapter.filterDataSet(s);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSearch(String s) {
            }

            @Override
            public void onResultClick(SearchResult searchResult) {

            }
        });
        return v;
    }

    private void setmRecyclerView(View v){
        mRecyclerView = (RecyclerView) v.findViewById(R.id.refuge_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        getLocations();
    }

    private List<Refuge> resolveAll() {
        try {
            List<Refuge> locations = new ArrayList<Refuge>();
            locations = this.refugeRepository.resolveAll();
            return locations;
        } catch (com.parse.ParseException e) {
            Log.e("ParseException", e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Refuge> resolvePublic() {
        try {
            List<User> users = this.userRepository.resolvePublicUser();
            List<String> userIds = new ArrayList<>();
            for (User user: users) userIds.add(user.getObjectId());
            List<Refuge> locations = this.refugeRepository.resolveByUsers(userIds);
            return locations;
        } catch (com.parse.ParseException e) {
            Log.e("ParseException", e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Refuge> resolveNonPublic() {
        try {
            List<User> users = this.userRepository.resolveNonPublicUser();
            List<String> userIds = new ArrayList<>();
            for (User user: users) userIds.add(user.getObjectId());
            List<Refuge> locations = this.refugeRepository.resolveByUsers(userIds);
            return locations;
        } catch (com.parse.ParseException e) {
            Log.e("ParseException", e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void getLocations(){
        this.refugeRepository = new RefugeRepository();
        this.userRepository = new UserRepository();

        Bundle arguments = getArguments();
        String state = "";
        if (arguments != null) {
            state = arguments.getString("state");
            if (state == null) {
                state = "";
            }
        }

        if(state.equals("public")) {
            mAdapter = new LocationAdapter(getActivity(), this.resolvePublic());
        } else if (state.equals("nonPublic")) {
            mAdapter = new LocationAdapter(getActivity(), this.resolveNonPublic());
        } else {
            mAdapter = new LocationAdapter(getActivity(), this.resolveAll());
        }

        mRecyclerView.setAdapter(mAdapter);
        mPullToRefreshView.setRefreshing(false);
    }

    private void setmPullToRefreshView(View v){
        mPullToRefreshView = (PullToRefreshView) v.findViewById(R.id.refuge_pull_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.post(new Runnable() {
                    @Override
                    public void run() {
                        getLocations();
                    }
                });
            }
        });
    }

    protected void closeSearch() {
        searchBox.hideCircularly(getActivity());
    }
}
