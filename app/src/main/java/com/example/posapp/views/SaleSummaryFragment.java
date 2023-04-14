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

import com.example.posapp.adapters.ListAdapter;
import com.example.posapp.databinding.FragmentSalesSummaryBinding;
import com.example.posapp.db.AppDatabase;
import com.example.posapp.db.SalesSummary;
import com.example.posapp.db.SalesSummaryDao;
import com.example.posapp.utility.Utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import io.reactivex.rxjava3.schedulers.Schedulers;

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

        List<SalesSummary> salesSummariesList = new ArrayList<>();
        ListAdapter listAdapter = new ListAdapter(salesSummariesList);
        recyclerView.setAdapter(listAdapter);

        AppDatabase db1 = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "sales_summary").build();
        AtomicReference<List<SalesSummary>> summaryAtomicReference = new AtomicReference<>(new ArrayList<>());



        refreshBtn.setOnClickListener(v -> {
            SalesSummaryDao summaryDao = db1.salesSummaryDao();
            summaryDao.getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(salesSummaries -> {
                        summaryAtomicReference.set(salesSummaries);
                    }, throwable -> {
                        Utility.makeToast(this.getContext(),"empty");
                    });
            salesSummariesList.clear();
            salesSummariesList.addAll(summaryAtomicReference.get());
            listAdapter.notifyItemChanged(salesSummariesList.size()-1);
            Utility.makeToast(this.getContext(),"refreshing.");
        });


        return view;
    }
}
