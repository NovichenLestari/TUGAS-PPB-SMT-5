package com.example.kalkulatorandroid;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HistoryHitungRepository {
    private HistoryHitungDao panggilDao;
    private LiveData<List<HistoryHitung>> semuaHistory;

    public HistoryHitungRepository(Application application) {
        DatabaseKalkulator db= DatabaseKalkulator.getDatabase(application);
        panggilDao = db.HistoryDao();
        semuaHistory=panggilDao.getAllHistory();

    }

    LiveData<List<HistoryHitung>> getSemuaRiwayat(){
        return semuaHistory;
    }

    void insert(HistoryHitung riwayatnya){
        DatabaseKalkulator.dbWriter.execute(() -> {
            panggilDao.insertRiwayat(riwayatnya);
        });
    }

    void delete(HistoryHitung riwayat){
        DatabaseKalkulator.dbWriter.execute(() -> {
            panggilDao.deleteRiwayat(riwayat);
        });
    }
}
