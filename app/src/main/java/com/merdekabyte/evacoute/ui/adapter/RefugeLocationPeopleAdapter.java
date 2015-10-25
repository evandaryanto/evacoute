package com.merdekabyte.evacoute.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.merdekabyte.evacoute.R;
import com.merdekabyte.evacoute.data.model.Refuge;
import com.merdekabyte.evacoute.ui.activity.RefugeLocationActivity;
import com.merdekabyte.evacoute.ui.helper.CachedBitmapLoader;

import java.io.IOException;
import java.util.List;

/**
 * Created by Michinggun on 10/25/2015.
 */
public class RefugeLocationPeopleAdapter extends RecyclerView.Adapter<RefugeLocationPeopleAdapter.ViewHolder>{
    private Context mContext;
    private List<String> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        // set the view's size, margins, paddings and layout parameters
        public TextView name;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.refuge_location_people_name);
            image = (ImageView) v.findViewById(R.id.refuge_location_people_pic);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RefugeLocationPeopleAdapter(Context context, List<String> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RefugeLocationPeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_refuge_location_people_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(mDataset.get(position));

//        try {
////            Bitmap image = loadBitmap(refuge.getImageUrl());
////            holder.image.setImageBitmap(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public Bitmap loadBitmap(String url) throws IOException {
        Log.d("loadBitmap", url);
        return CachedBitmapLoader.load(url);
    }
}
