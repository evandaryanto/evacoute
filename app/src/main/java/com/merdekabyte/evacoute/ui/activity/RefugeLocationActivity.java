package com.merdekabyte.evacoute.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.merdekabyte.evacoute.R;
import com.merdekabyte.evacoute.ui.SlidingTabLayout;
import com.merdekabyte.evacoute.ui.adapter.BiodataPagerAdapter;
import com.merdekabyte.evacoute.ui.adapter.RefugeLocationAdapter;

import org.w3c.dom.Text;

/**
 * Created by Michinggun on 10/24/2015.
 */
public class RefugeLocationActivity extends AppCompatActivity {
    private String name, location, contact;
    private TextView tvName, tvLocation, tvContact;
    ViewPager pager;
    RefugeLocationAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Informasi","Galeri","Daftar Pengungsi"};
    int Numboftabs =3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuge_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
        } else {
            name = extras.getString("Name");
            location = extras.getString("Location");
            contact = extras.getString("Contact");
        }
        tvName = (TextView) findViewById(R.id.refuge_location_name);
        tvLocation = (TextView) findViewById(R.id.refuge_location_place);
        tvContact = (TextView) findViewById(R.id.refuge_location_phone);
        tvName.setText(name);
        tvLocation.setText(location);
        tvContact.setText(contact);

        adapter =  new RefugeLocationAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.refuge_location_pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.refuge_location_tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
