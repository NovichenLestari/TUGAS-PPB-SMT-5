package com.example.kalkulatorandroid;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {HistoryHitung.class},version = 2,exportSchema = false)
public abstract class DatabaseKalkulator extends RoomDatabase {
    public abstract HistoryHitungDao HistoryDao();

    private static volatile DatabaseKalkulator INSTANCE;
    static final ExecutorService dbWriter= Executors.newFixedThreadPool(4);

    static DatabaseKalkulator getDatabase(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseKalkulator.class,"db_kalkulator")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(dbCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback dbCallback= new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dbWriter.execute(new Runnable() {
                @Override
                public void run() {
                    HistoryHitungDao panggilDao = INSTANCE.HistoryDao();
                    panggilDao.insertRiwayat(
                            new HistoryHitung("5 + 5 = 10")
                    );
                    panggilDao.insertRiwayat(
                            new HistoryHitung("3 x 2 = 6")
                    );

                }
            });
        }
    };
}