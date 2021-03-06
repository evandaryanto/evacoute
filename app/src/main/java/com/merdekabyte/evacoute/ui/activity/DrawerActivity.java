package com.merdekabyte.evacoute.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.merdekabyte.evacoute.R;
import com.merdekabyte.evacoute.ui.fragment.BiodataFragment;
import com.merdekabyte.evacoute.ui.fragment.FindPeopleFragment;
import com.merdekabyte.evacoute.ui.fragment.RefugeFragment;
import com.merdekabyte.evacoute.ui.fragment.VolenteersRefugeFragment;
import com.parse.Parse;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SearchBox searchBox;

    protected void initializeParse() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "AagLzvHfbym5hSVsfgtPIe2S4aVxIxuJrS1tLQcs", "VJD8wEvkodDkjGmXipwuPVcGrrwwcG5BFKK4EDPq");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchBox = (SearchBox) findViewById(R.id.search_box);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MapActivity.class);
                String judul = getActionBar().getTitle().toString();
                if (judul == "Pengungsian Publik") {
                    i.putExtra("Category","public");
                }
                else if (judul == "Pengungsian Rumah Warga") {
                    i.putExtra("Category","nonPublic");
                }
                else {
                    i.putExtra("Category", "All");
                }
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        initializeParse();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_location_all);
        updateFragment(new RefugeFragment(), "Evacoute");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }
        else if (id == R.id.action_search) {
            searchBox = (SearchBox) findViewById(R.id.search_box);
            searchBox.revealFromMenuItem(R.id.search_box, this);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_location_all) {
            RefugeFragment fragment = new RefugeFragment();
            Bundle args = new Bundle();
            args.putString("state", "");
            fragment.setArguments(args);
            updateFragment(fragment, "Evacoute");
        } else if (id == R.id.nav_location_central) {
            RefugeFragment fragment = new RefugeFragment();
            Bundle args = new Bundle();
            args.putString("state", "nonPublic");
            fragment.setArguments(args);
            updateFragment(fragment, "Evacoute");
        } else if (id == R.id.nav_location_resident) {
            RefugeFragment fragment = new RefugeFragment();
            Bundle args = new Bundle();
            args.putString("state", "public");
            fragment.setArguments(args);
            updateFragment(fragment, "Evacoute");
        } else if (id == R.id.nav_information) {
            updateFragment(new BiodataFragment(), "Data Diri");
        } else if (id == R.id.nav_search) {
            updateFragment(new FindPeopleFragment(), "Cari Pengungsi");
        } else if (id == R.id.nav_help_me) {

        } else if (id == R.id.nav_manage_refege) {
            updateFragment(new VolenteersRefugeFragment(), "Manajemen Tempat Pengungsi");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.drawer_container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        View parentLayout = findViewById(R.id.drawer_container_body);
        if (scanResult != null) {
            Snackbar.make(parentLayout, "Alif Raditya berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
        }
    }
}
