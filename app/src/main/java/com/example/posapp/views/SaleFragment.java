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

import com.example.posapp.adapters.CardAdapter;
import com.example.posapp.databinding.FragmentSaleBinding;
import com.example.posapp.db.AppDatabase;
import com.example.posapp.db.RegisterItem;
import com.example.posapp.db.SalesSummary;
import com.example.posapp.model.RegItemWithSale;
import com.example.posapp.utility.Utility;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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

        AppDatabase db = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "registered").build();

        AppDatabase db1 = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "sales_summary").build();

        List<RegItemWithSale> regCardList = new ArrayList<>();


        AtomicReference<RegisterItem> registerItem = new AtomicReference<>(new RegisterItem());

        btnSearchSale.setOnClickListener(v -> {
            boolean validateForm = Utility.validate(codeSaleTextEdit);

            if (validateForm) {

                String code = codeSaleTextEdit.getText().toString();
                // Create an Observable that emits the list of users from the database
                @NonNull Observable<RegisterItem> observable = db.regItemDao().getByCode(code).toObservable();

// Subscribe to the Observable to receive the list of users
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(item -> {
                            // Handle the list of users
                            registerItem.set(item);
                            regItemTextView.setText("item: "+item._item);
                            itemUnitTextView.setText("unit: "+item.unit);
                            itemUnitPrice.setText("unit price: "+item.unit_price);
                            Utility.clear(codeSaleTextEdit);
                        }, throwable -> {
                            // Handle the error
                            Toast.makeText(this.getContext(), "no result", Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(this.getContext(), "invalid input", Toast.LENGTH_SHORT).show();
            }
        });

        CardAdapter adapter = new CardAdapter(regCardList);
        mRecyclerView.setAdapter(adapter);

        SalesSummary salesSummary = new SalesSummary(0,0,0,0);

        btnAddSale.setOnClickListener(v -> {

            boolean validateForm = Utility.validate(quantitySaleTextEdit);

            if (validateForm && (registerItem.get().code != null)){
                int quantity = Integer.parseInt(quantitySaleTextEdit.getText().toString());
                double cost = quantity * registerItem.get().unit_price;
                RegItemWithSale regItemWithSale = new RegItemWithSale(registerItem.get(),quantity,cost);
                regCardList.add(regItemWithSale);

                adapter.notifyItemInserted(regCardList.size() - 1); // notify adapter about the new item

                salesSummary.upTotal(quantity);
                salesSummary.upTCost(cost);
                salesSummary.upTax(cost * 0.15d);
                salesSummary.setPayable(salesSummary.gettCost() + salesSummary.getTax());

                totalSaleTextEdit.setText(String.format("%s", Utility.roundNumbers(salesSummary.gettCost())));
                taxSale.setText(String.format("%s", Utility.roundNumbers(salesSummary.getTax())));
                payableSaleTextEdit.setText(String.format("%s",Utility.roundNumbers(salesSummary.getPayable())));
//                    Completable completable = db.regItemDao().updateItems(cardList);
//                    completable.subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(()->{
//                                Toast.makeText(this.getContext(), "saved", Toast.LENGTH_SHORT).show();
//                            }, throwable -> {
//                                Toast.makeText(this.getContext(), "failed to save", Toast.LENGTH_SHORT).show();
//                            });
                //Utility.calc(regCardList);
//                total[0].addAndGet(quantity);
//                tax[0] += tax[0];

            } else {
                Utility.makeToast(this.getContext(),"invalid input");
            }

        });

        btnPaidSale.setOnClickListener(v -> {
            if (salesSummary.getTotal() != 0){
                salesSummary.setTransactionDate(new Date());
                Completable completable = db1.salesSummaryDao().insert(salesSummary);
                completable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {

                            Utility.clear(codeSaleTextEdit,totalSaleTextEdit,payableSaleTextEdit,taxSale,quantitySaleTextEdit);
                            Utility.clear(regItemTextView,itemUnitTextView,itemUnitPrice );

                            salesSummary.setTotal(0);
                            salesSummary.setTax(0);
                            salesSummary.upPayable(0);
                            salesSummary.settCost(0);

                            registerItem.set(new RegisterItem(null,null,null,0));

                            regCardList.clear();
                            adapter.notifyDataSetChanged();
                            Utility.makeToast(this.getContext(),"paid");
                        }, throwable -> {
                            Utility.makeToast(this.getContext(),"failed");
                        });
            }else {
                Utility.makeToast(this.getContext(),"please add items");
            }

        });

        btnResetSale.setOnClickListener(v -> {
            Utility.clear(codeSaleTextEdit,totalSaleTextEdit,payableSaleTextEdit,taxSale,quantitySaleTextEdit);
            Utility.clear(regItemTextView,itemUnitTextView,itemUnitPrice );

            salesSummary.setTotal(0);
            salesSummary.setTax(0);
            salesSummary.upPayable(0);
            salesSummary.settCost(0);

            registerItem.set(null);
            registerItem.set(new RegisterItem(null,null,null,0));
            regCardList.clear();
            adapter.notifyDataSetChanged();
            Utility.makeToast(this.getContext(),"reset");
        });

        return view;
    }
}
