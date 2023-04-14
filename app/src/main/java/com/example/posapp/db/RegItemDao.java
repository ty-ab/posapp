package com.example.posapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface RegItemDao {

//    @Insert
//    Completable insertAll(RegisterItem... item);

    @Insert
    Completable insert(RegisterItem item);

    @Delete
    void delete(RegisterItem item);

    @Query("SELECT * FROM registered ")
    List<RegisterItem> getAll();

    @Query("SELECT * FROM registered WHERE code LIKE :code")
    public Single<RegisterItem> getByCode(String code);

    @Update
    public Completable updateItems(List<RegisterItem> items);

}
