package com.example.posapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.posapp.databinding.FragmentSalesSummaryBinding;


public class SaleSummaryFragment extends Fragment {

    RecyclerView recyclerView;
    Button refreshBtn;
    FragmentSalesSummaryBinding fragmentSalesSummaryBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSalesSummaryBinding = FragmentSalesSummaryBinding.inflate(getLayoutInflater());
        View view = fragmentSalesSummaryBinding.getRoot();

        refreshBtn = fragmentSalesSummaryBinding.refreshBtn;

        recyclerView = fragmentSalesSummaryBinding.recyclerViewList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));





        refreshBtn.setOnClickListener(v -> {

        });


        return view;
    }
}
