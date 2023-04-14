package com.example.posapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;

import android.view.View;

import android.widget.ListView;

import com.example.posapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;


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
        
    }

}
