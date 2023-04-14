package com.example.posmachineapp.utility;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.posmachineapp.db.RegisterItem;
import com.example.posmachineapp.db.SalesSummary;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utility {
    public static boolean validate(@NonNull TextInputEditText... edtexts) {
        for (TextInputEditText editText : edtexts) {
            if (Objects.requireNonNull(editText.getText()).toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void clear(@NonNull TextInputEditText... textInputEditTexts) {
        for (TextInputEditText editText : textInputEditTexts) {
            editText.setText("");
        }
    }

    public static void clear(@NonNull TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setText("");
        }
    }

    public static void makeToast(Context context,String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
