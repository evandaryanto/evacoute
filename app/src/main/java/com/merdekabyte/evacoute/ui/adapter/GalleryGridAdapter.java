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
    List<String> mItems;

    public GalleryGridAdapter(Context context) {
        super();
        mContext = context;
        mItems = new ArrayList<String>();
        String uri = "https://evacoute.s3.amazonaws.com/bulk/1.jpg";
        String uri1 = "https://evacoute.s3.amazonaws.com/bulk/2.jpg";
        String uri2 = "https://evacoute.s3.amazonaws.com/bulk/3.jpg";
        String uri3 = "https://evacoute.s3.amazonaws.com/bulk/4.jpg";
        String uri4 = "https://evacoute.s3.amazonaws.com/bulk/5.jpg";
        String uri5 = "https://evacoute.s3.amazonaws.com/bulk/6.jpg";
        String uri6 = "https://evacoute.s3.amazonaws.com/bulk/7.jpg";
        String uri7 = "https://evacoute.s3.amazonaws.com/bulk/8.jpg";
        String uri8 = "https://evacoute.s3.amazonaws.com/bulk/9.jpg";


        //int ic = R.drawable.refuge_evan;
        mItems.add(uri);
        mItems.add(uri1);
        mItems.add(uri2);
        mItems.add(uri3);
        mItems.add(uri4);
        mItems.add(uri5);
        mItems.add(uri6);
        mItems.add(uri7);
        mItems.add(uri8);
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
