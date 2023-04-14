package com.example.posapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.posapp.databinding.FragmentSaleBinding;
import com.google.android.material.textfield.TextInputEditText;




public class SaleFragment extends Fragment {

    TextInputEditText codeSaleTextEdit;
    TextInputEditText itemSaleTextEdit;
    TextInputEditText quantitySaleTextEdit;
    TextInputEditText lsiat;
    TextInputEditText totalSaleTextEdit;
    TextInputEditText taxSale;
    TextInputEditText payableSaleTextEdit;

    Button btnSearchSale;
    Button btnAddSale;
    Button btnResetSale;
    Button btnPaidSale;

    TextView regItemTextView;
    TextView itemUnitTextView;
    TextView itemUnitPrice;

    RecyclerView mRecyclerView;
    FragmentSaleBinding salesUiBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        salesUiBinding = FragmentSaleBinding.inflate(getLayoutInflater());
        View view = salesUiBinding.getRoot();

        codeSaleTextEdit = salesUiBinding.codesalegid;
        quantitySaleTextEdit = salesUiBinding.quantitysaleid;

        //reault
        totalSaleTextEdit = salesUiBinding.totalsaleid;
        totalSaleTextEdit.setFocusable(false);

        taxSale = salesUiBinding.taxsaleid;
        taxSale.setFocusable(false);

        payableSaleTextEdit = salesUiBinding.paysaleid;
        payableSaleTextEdit.setFocusable(false);

        btnSearchSale = salesUiBinding.searchsaleid;
        btnAddSale = salesUiBinding.addsaleid;
        btnResetSale = salesUiBinding.resetsaleid;
        btnPaidSale = salesUiBinding.paidsaleid;

        regItemTextView = salesUiBinding.regItem;
        itemUnitTextView = salesUiBinding.itemUnit;
        itemUnitPrice = salesUiBinding.itemUnitPrice;

        mRecyclerView = salesUiBinding.recyclerViewCards;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));






        btnSearchSale.setOnClickListener(v -> {

        });



        btnAddSale.setOnClickListener(v -> {

        });

        btnPaidSale.setOnClickListener(v -> {


        });

        btnResetSale.setOnClickListener(v -> {

        });

        return view;
    }
}
