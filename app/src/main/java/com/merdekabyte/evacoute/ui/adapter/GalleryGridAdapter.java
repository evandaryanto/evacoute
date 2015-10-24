package com.merdekabyte.evacoute.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.merdekabyte.evacoute.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michinggun on 10/25/2015.
 */
public class GalleryGridAdapter extends RecyclerView.Adapter<GalleryGridAdapter.ViewHolder> {
    private Context mContext;
    List<Integer> mItems;

    public GalleryGridAdapter(Context context) {
        super();
        mContext = context;
        mItems = new ArrayList<Integer>();
        int ic = R.drawable.refuge_evan;
        mItems.add(ic);
        mItems.add(ic);
        mItems.add(ic);
        mItems.add(ic);
        mItems.add(ic);
        mItems.add(ic);
        mItems.add(ic);
        mItems.add(ic);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_refuge_location_gallery_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Picasso.with(mContext).load(mItems.get(i)).into(viewHolder.imgThumbnail);
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.gallery_img);
        }
    }

}
