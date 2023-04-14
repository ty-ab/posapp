package com.example.posapp.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "registered",indices = {@Index(value = "code",unique = true)})
public class RegisterItem {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "code")
    public String code;

    @ColumnInfo(name = "item")
    public String _item;

    @ColumnInfo(name = "unit")
    public String unit;

    @ColumnInfo(name = "unit_price")
    public double unit_price;

    public RegisterItem() {
    }
    public RegisterItem(String code, String _item, double unit_price) {
        this.code = code;
        this._item = _item;
        this.unit_price = unit_price;
    }
    public RegisterItem(String code, String _item, String unit, double unit_price) {
        this.code = code;
        this._item = _item;
        this.unit = unit;
        this.unit_price = unit_price;
    }

    public String get_item() {
        return _item;
    }
    public String getCode() {
        return code;
    }

    public String getUnit() {
        return unit;
    }

    public double getUnit_price() {
        return unit_price;
    }
}
