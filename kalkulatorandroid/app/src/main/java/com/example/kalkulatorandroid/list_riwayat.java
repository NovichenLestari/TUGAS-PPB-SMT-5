package com.example.kalkulatorandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class list_riwayat extends AppCompatActivity {
    private ViewModelHistory HistoryModel;
    TextView txtketeranganhistory;
    ArrayList<HistoryHitung> listHistory;
    RecyclerView rcyHistory;
    HistoryHitungAdapter adapter;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_riwayat);

        rcyHistory = findViewById(R.id.rcyRiwayat);
        txtketeranganhistory = findViewById(R.id.txtketerangan);

        txtketeranganhistory.setText("History Kalkulator");
        listHistory= new ArrayList<>();
        adapter = new HistoryHitungAdapter(listHistory);
        rcyHistory.setAdapter(adapter);
        rcyHistory.setLayoutManager(new LinearLayoutManager(this));

        HistoryModel= new ViewModelProvider(this).get(ViewModelHistory.class);
        HistoryModel.getHistory().observe(this, new Observer<List<HistoryHitung>>() {
            @Override
            public void onChanged(List<HistoryHitung> historyHitungs) {
                listHistory.clear();
                listHistory.addAll(historyHitungs);
                adapter.notifyDataSetChanged();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                HistoryModel.delete(adapter.HistoryDiLokasi(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(),"Riwayat dihapus!",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(rcyHistory);

    }

    private void caraHapus() {
        dialog=new AlertDialog.Builder(list_riwayat.this)
                .setTitle("Tips")
                .setMessage("Geser ke Kiri Untuk Membersihkan")
                .setPositiveButton("Siap!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.create();

    }

    }