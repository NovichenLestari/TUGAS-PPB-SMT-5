package com.example.kalkulatorandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryHitungAdapter extends RecyclerView.Adapter<HistoryHitungAdapter.RiwayatHitungViewHolder> {
    private final List<HistoryHitung> listHistory;


    public HistoryHitungAdapter(List<HistoryHitung> listHistory) {
        this.listHistory = listHistory;

    }

    @NonNull
    @Override
    public HistoryHitungAdapter.RiwayatHitungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        RiwayatHitungViewHolder holder=new RiwayatHitungViewHolder(
                inflater.inflate(R.layout.history_hitung_view,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHitungAdapter.RiwayatHitungViewHolder holder, int position) {
        HistoryHitung riwayats=listHistory.get(position);
        holder.tampilkanRiwayat.setText(riwayats.history);

    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }


    public HistoryHitung HistoryDiLokasi(int position){
        return listHistory.get(position);
    }
    public class RiwayatHitungViewHolder extends RecyclerView.ViewHolder  {
        TextView tampilkanRiwayat;
        ConstraintLayout layoutRiwayat;

        public RiwayatHitungViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutRiwayat=itemView.findViewById(R.id.layoutHistory);
            tampilkanRiwayat=itemView.findViewById(R.id.txtHistory);

        }


    }
}
