package com.example.kalkulatorandroid;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_kalkulator")
public class HistoryHitung {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "history_tersimpan")
    public String history;

    public HistoryHitung( String history) {
        this.history = history;
    }



    @NonNull
    @Override
    public String toString() {
        return this.history;
    }
}
