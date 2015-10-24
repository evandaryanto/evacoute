package com.merdekabyte.evacoute.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.merdekabyte.evacoute.ui.fragment.BiodataGeneralFragment;
import com.merdekabyte.evacoute.ui.fragment.BiodataQRFragment;
import com.merdekabyte.evacoute.ui.fragment.BiodataSpecificFragment;

/**
 * Created by Michinggun on 10/24/2015.
 */
public class BiodataPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public BiodataPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        Fragment item = null;
        switch (position){
            case 0:
                item = new BiodataGeneralFragment();
                break;
            case 1:
                item = new BiodataSpecificFragment();
                break;
            case 2:
                item = new BiodataQRFragment();
                break;
        }
        return item;
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
