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
import com.merdekabyte.evacoute.data.repository.RefugeRepository;
import com.merdekabyte.evacoute.ui.adapter.LocationAdapter;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_refuge, container, false);
        mPullToRefreshView = (PullToRefreshView) v.findViewById(R.id.refuge_pull_refresh);
        mPullToRefreshView.setRefreshing(true);
        setmRecyclerView(v);
        this.refugeRepository = new RefugeRepository();
        List<Refuge> locations = new ArrayList<Refuge>();
        try {
            locations = this.refugeRepository.resolveAll();
        } catch (com.parse.ParseException e) {
            Log.e("ParseException", e.getMessage());
            e.printStackTrace();
        }

        mAdapter = new LocationAdapter(getActivity(), locations);
        mRecyclerView.setAdapter(mAdapter);
        mPullToRefreshView.setRefreshing(false);
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
        //getList();
    }

    private void getList(){
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(VTEndpoint.URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
//        VTTransactionInterface historyService = retrofit.create(VTTransactionInterface.class);
//        historyService.getAllTransaction(InputActivity.M_ID).enqueue(this);
    }



}
