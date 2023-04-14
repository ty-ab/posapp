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
import com.google.android.material.textfield.TextInputEditText;

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


        //RegItemDao regItemDao = _db.regItemDao();

        btnSaveReg.setOnClickListener(v -> {


        });

        btnResetReg.setOnClickListener(v -> {
            Toast.makeText(this.getContext(), "Clear Button Clicked", Toast.LENGTH_SHORT).show();

        });


        return view;
    }

}
