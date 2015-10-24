package com.merdekabyte.evacoute.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
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
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Michinggun on 10/24/2015.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private Context mContext;
    private List<Refuge> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        // set the view's size, margins, paddings and layout parameters
        public TextView name, location, contact;
        public ImageView image;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.refuge_item_name);
            location = (TextView) v.findViewById(R.id.refuge_item_location);
            contact = (TextView) v.findViewById(R.id.refuge_item_contact);
            image = (ImageView) v.findViewById(R.id.refuge_item_image);
            layout = (RelativeLayout) v.findViewById(R.id.refuge_item_layout);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public LocationAdapter(Context context, List<Refuge> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_refuge_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Refuge refuge = mDataset.get(position);

        try {
            holder.name.setText(refuge.getName());
            holder.location.setText(refuge.getLocation());
            holder.contact.setText(refuge.getContact());
            Bitmap image = loadBitmap(refuge.getImageUrl());
            holder.image.setImageBitmap(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, RefugeLocationActivity.class);
                i.putExtra("Name", refuge.getName());
                i.putExtra("Location", refuge.getLocation());
                i.putExtra("Contact", refuge.getContact());
                i.putExtra("ImageURL", refuge.getImageUrl());
                mContext.startActivity(i);
            }
        });
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
