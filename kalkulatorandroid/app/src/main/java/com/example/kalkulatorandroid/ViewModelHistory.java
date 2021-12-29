package com.example.kalkulatorandroid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModelHistory extends AndroidViewModel {
    private HistoryHitungRepository repo;
    private final LiveData<List<HistoryHitung>> allRiwayat;

    public ViewModelHistory(@NonNull Application application) {
        super(application);
        repo=new HistoryHitungRepository(application);
        allRiwayat=repo.getSemuaRiwayat();
    }

    LiveData<List<HistoryHitung>> getHistory(){
        return allRiwayat;
    }

    public void insert(HistoryHitung riwayat){ repo.insert(riwayat);}


    public void delete(HistoryHitung riwayat){ repo.delete(riwayat);}
}
