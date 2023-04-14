package com.example.posapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.posapp.utility.Converters;

@Database(entities = {RegisterItem.class,SalesSummary.class}, version = 1)
@TypeConverters({Converters.class})
public abstract  class AppDatabase extends RoomDatabase {
    public abstract RegItemDao regItemDao();
    public abstract SalesSummaryDao salesSummaryDao();
}
