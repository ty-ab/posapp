package com.example.posapp.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "sales_summary")
public class SalesSummary{

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "transaction_date")
    Date transactionDate;

    @ColumnInfo(name = "total")
    int total;

    @ColumnInfo(name = "tax")
    double tax;

    @ColumnInfo(name = "payable")
    double payable;



    @ColumnInfo(name = "total_cost")
    double tCost;
    @Ignore
    public SalesSummary(int total, double tax, double payable , double tCost) {
        this.total = total;
        this.tax = tax;
        this.payable = payable;
        this.tCost = tCost;
    }

    public SalesSummary(Date transactionDate,int total, double tax, double payable , double tCost) {
        this.transactionDate = transactionDate;
        this.total = total;
        this.tax = tax;
        this.payable = payable;
        this.tCost = tCost;
    }

    public double getTotal() {
        return total;
    }


    public void upTotal(int total) {
        this.total += total;
    }

    public void upTCost(double tCost ) {
        this.tCost += tCost;
    }
    public double gettCost() {
        return tCost;
    }
    public double getTax() {
        return tax;
    }

    public void upTax(double tax) {
        this.tax += tax;
    }

    public double getPayable() {
        return payable;
    }

    public void upPayable(double payable) {
        this.payable += payable;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setPayable(double payable) {
        this.payable = payable;
    }
    public void settCost(double tCost) {
        this.tCost = tCost;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

}
