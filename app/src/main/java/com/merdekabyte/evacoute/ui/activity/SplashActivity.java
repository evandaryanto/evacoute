package com.merdekabyte.evacoute.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.merdekabyte.evacoute.R;

/**
 * Created by Michinggun on 10/24/2015.
 */
public class SplashActivity extends Activity {
    // Splash screen timer
    private static final int SPLASH_INTERVAL = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            /*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, DrawerActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_INTERVAL);
    }

}
