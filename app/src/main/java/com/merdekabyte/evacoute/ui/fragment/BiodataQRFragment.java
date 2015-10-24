package com.merdekabyte.evacoute.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.merdekabyte.evacoute.R;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Michinggun on 10/24/2015.
 */
public class BiodataQRFragment extends Fragment{
    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_biodata_qr, container, false);
        ImageView barCode = (ImageView) v.findViewById(R.id.biodata_qr_barcode);

//        Bundle extras = getIntent().getExtras();
//        if(extras == null) {
//            transactionID = null;
//            itemName = null;
//            itemPrice = null;
//        } else {
//            transactionID = extras.getString("TRANSACTION_ID");
//            itemName = extras.getString("ITEM_NAME");
//            itemPrice = extras.getString("ITEM_PRICE");
//        }
        //System.out.println("TRANSACTION " + transactionID + " " + itemName + " " + itemPrice);
        try{
            Bitmap bitmap = encodeAsBitmap("evacoute", 500);
            barCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return v;
    }

    Bitmap encodeAsBitmap(String contents, int dimension) throws WriterException {
        String contentsToEncode = contents;

        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType,Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(contentsToEncode, BarcodeFormat.QR_CODE, dimension, dimension, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

}
