package com.merdekabyte.evacoute.ui.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by evandaryanto on 10/25/15.
 */
public class CachedBitmapLoader {

    public static HashMap<String, Bitmap> cache;

    public static  Bitmap load(String url) {
        if(CachedBitmapLoader.cache == null)
            CachedBitmapLoader.cache = new HashMap<String, Bitmap>();
        if(CachedBitmapLoader.cache.containsKey(url)) {
            return cache.get(url);
        } else {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                cache.put(url, bitmap);
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
