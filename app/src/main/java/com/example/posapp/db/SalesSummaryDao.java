package com.example.posapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface SalesSummaryDao {

    @Insert
    Completable insert(SalesSummary sale);

    @Query("SELECT * FROM sales_summary")
    Flowable<List<SalesSummary>> getAll();

}
