package com.example.kalkulatorandroid;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryHitungDao {
    @Query("SELECT * FROM history_kalkulator")
    LiveData<List<HistoryHitung>> getAllHistory();

    @Insert
    void insertRiwayat(HistoryHitung masukkanRiwayat);

    @Delete
    void deleteRiwayat(HistoryHitung hapusRiwayat);
}

