package com.merdekabyte.evacoute.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.merdekabyte.evacoute.R;
import com.merdekabyte.evacoute.data.model.Refuge;
import com.merdekabyte.evacoute.ui.activity.RefugeLocationActivity;
import com.merdekabyte.evacoute.ui.helper.CachedBitmapLoader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Michinggun on 10/24/2015.
 */
public class VolenteersLocationAdapter extends RecyclerView.Adapter<VolenteersLocationAdapter.ViewHolder> {
    private Context mContext;
    private List<Refuge> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        // set the view's size, margins, paddings and layout parameters
        public TextView name, location;
        public ImageView image;
        public View layout;
        public Button qrCode;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.refuge_item_name);
            location = (TextView) v.findViewById(R.id.refuge_item_location);
            image = (ImageView) v.findViewById(R.id.refuge_item_image);
            layout = (RelativeLayout) v.findViewById(R.id.refuge_item_layout);
            qrCode = (Button) v.findViewById(R.id.refuge_item_volenteer_qrcode);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public VolenteersLocationAdapter(Context context, List<Refuge> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VolenteersLocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_refuge_item_volenteers, parent, false);
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

        holder.qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCapture();
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

    private void startCapture(){
        IntentIntegrator integrator = new IntentIntegrator((Activity)mContext);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan Evacoute-QR");
        integrator.setBeepEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
    }
}
