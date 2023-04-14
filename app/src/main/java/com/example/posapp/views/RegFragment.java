package com.example.posapp.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.posapp.databinding.FragmentRegBinding;
import com.example.posapp.db.AppDatabase;
import com.example.posapp.db.RegisterItem;
import com.example.posapp.utility.Utility;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;
public class RegFragment extends Fragment {

    TextInputEditText codeRegTextEdit;
    TextInputEditText itemItemTextEdit;
    TextInputEditText unitRegTextEdit;
    TextInputEditText unitPriceRegTextEdit;

    Button btnResetReg;
    Button btnSaveReg;
    FragmentRegBinding regBinding;

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        regBinding = FragmentRegBinding.inflate(getLayoutInflater());
        View view = regBinding.getRoot();

        codeRegTextEdit = regBinding.coderegid;
        itemItemTextEdit = regBinding.itemregid;
        unitRegTextEdit = regBinding.unitregid;
        unitPriceRegTextEdit = regBinding.unitpregid;

        btnResetReg = regBinding.resetregid;
        btnSaveReg = regBinding.saveregid;

        AppDatabase _db = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "registered").build();

        //RegItemDao regItemDao = _db.regItemDao();

        btnSaveReg.setOnClickListener(v -> {

            boolean validateForm = Utility.validate(codeRegTextEdit,itemItemTextEdit,unitRegTextEdit,unitPriceRegTextEdit);
            if (validateForm) {
                String code = codeRegTextEdit.getText().toString();
                String _item = itemItemTextEdit.getText().toString();
                String unit = unitRegTextEdit.getText().toString();
                float unitPrice = Float.parseFloat(unitPriceRegTextEdit.getText().toString());

                Completable completable = _db.regItemDao().insert(new RegisterItem(code,_item, unit,unitPrice));
                completable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
                            Toast.makeText(this.getContext(), "item saved", Toast.LENGTH_SHORT).show();
                            Utility.clear(codeRegTextEdit,itemItemTextEdit,unitRegTextEdit,unitPriceRegTextEdit);
                        }, throwable -> {
                            Toast.makeText(this.getContext(), "failed", Toast.LENGTH_SHORT).show();
                        });
                //RegisterItem registerItem = new RegisterItem();
                //registerItem.insertAll();
            } else {
                Toast.makeText(this.getContext(),"Invalid input",Toast.LENGTH_SHORT).show();
            }

        });

        btnResetReg.setOnClickListener(v -> {
            Toast.makeText(this.getContext(), "Clear Button Clicked", Toast.LENGTH_SHORT).show();
            Utility.clear(codeRegTextEdit,itemItemTextEdit,unitRegTextEdit,unitPriceRegTextEdit);
        });


        return view;
    }

}
