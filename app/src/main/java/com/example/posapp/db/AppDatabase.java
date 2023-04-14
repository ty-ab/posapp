package com.example.posapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {RegisterItem.class,SalesSummary.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase {
    public abstract RegItemDao regItemDao();
    public abstract SalesSummaryDao salesSummaryDao();
}
