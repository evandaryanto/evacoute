package com.merdekabyte.evacoute.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.merdekabyte.evacoute.ui.fragment.RefugeLocationGalleryFragment;
import com.merdekabyte.evacoute.ui.fragment.RefugeLocationInformationFragment;

/**
 * Created by Michinggun on 10/24/2015.
 */
public class RefugeLocationAdapter extends FragmentStatePagerAdapter{
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public RefugeLocationAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
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
                item = new RefugeLocationInformationFragment();
                break;
            case 1:
                item = new RefugeLocationGalleryFragment();
                break;
            case 2:
                item = new RefugeLocationInformationFragment();
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
