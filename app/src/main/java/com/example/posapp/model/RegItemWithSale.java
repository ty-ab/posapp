package com.example.posapp.model;


import com.example.posapp.db.RegisterItem;

public class RegItemWithSale {


    public String code;
    public String _item;

    public String unit;
    public double unit_price;
    public int quantity;

    public double cost;

    public RegItemWithSale(String code, String _item, String unit, double unit_price, int quantity) {
        this.code = code;
        this._item = _item;
        this.unit = unit;
        this.unit_price = unit_price;
        this.quantity = quantity;
    }

    public RegItemWithSale(RegisterItem registerItem, int quantity, double cost) {
        this.code = registerItem.getCode();
        this._item = registerItem.get_item();
        this.unit = registerItem.getUnit();
        this.unit_price = registerItem.getUnit_price();
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }

    public String get_item() {
        return _item;
    }
    public double getCost() {
        return cost;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public int getQuantity() {
        return quantity;
    }
}
