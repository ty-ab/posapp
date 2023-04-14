package com.example.posapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.posapp.databinding.ActivityMainBinding;
import com.example.posapp.utility.MyPagerAdapter;
import com.example.posapp.views.RegFragment;
import com.example.posapp.views.SaleFragment;
import com.example.posapp.views.SaleSummaryFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    MyPagerAdapter adapter;

    DrawerLayout drawerLayout;
    ListView drawerList;

    ActionBarDrawerToggle drawerToggle;

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View aView = binding.getRoot();
        setContentView(aView);

        viewPager = binding.viewPager;
        tabLayout = binding.tabLayout;
        drawerLayout = binding.drawerLayout;
        drawerList = binding.leftDrawer;

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RegFragment(), "Register");
        adapter.addFragment(new SaleFragment(), "Sale");
        adapter.addFragment(new SaleSummaryFragment(), "Sale Summary");

        //drawerList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new String[]{"REGISTER ITEM", "SALE"}));
// Create an array of strings to use as the drawer items
        String[] drawerItems = {"REGISTER ITEM", "SALE", "SALE SUMMARY"};

// Set the adapter for the drawer list
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.listlayout, R.id.text_view_id, drawerItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                return view;
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        drawerList.setOnItemClickListener((parent, view, position, id) -> {
//            if (position == 2) {
//                selectItem(position);
//                //drawerLayout.closeDrawer(drawerList);
//            } else {
//
//            }
            viewPager.setCurrentItem(position);
            //drawerLayout.closeDrawer(drawerList);

            Log.d("TAYIE","POSITION: "+position);
        });
    }

    private void selectItem(int position) {
        // Create a new fragment and specify the fragment to show based on position
        Fragment fragment;
        if (position == 2) {
            fragment = new SaleSummaryFragment();
        } else {
            fragment = null;
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.viewPager, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle("Fragment " + (position + 1));
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        drawerToggle.onConfigurationChanged(newConfig);
    }


}