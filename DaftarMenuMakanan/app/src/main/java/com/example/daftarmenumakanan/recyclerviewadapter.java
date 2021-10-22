package com.example.daftarmenumakanan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class recyclerviewadapter extends RecyclerView.Adapter<recyclerviewadapter.ViewHolder>{

    private ArrayList<String> fotoMakanan = new ArrayList<>();
    private ArrayList<String> namaMakanan = new ArrayList<>();
    private ArrayList<String> infoMakanan = new ArrayList<>();
    private ArrayList<String> hargaMakanan = new ArrayList<>();
    private Context context;

    public recyclerviewadapter(ArrayList<String> fotoMakanan, ArrayList<String> namaMakanan,ArrayList<String>infoMakanan,ArrayList<String>hargaMakanan, Context context) {
        this.fotoMakanan = fotoMakanan;
        this.namaMakanan = namaMakanan;
        this.infoMakanan = infoMakanan;
        this.hargaMakanan = hargaMakanan;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desain_layout_adpter, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  final int position) {
        Glide.with(context).asBitmap().load(fotoMakanan.get(holder.getAdapterPosition())).into(holder.imageViewgambarmakanan);

        holder.textViewnamamakanan.setText((CharSequence) namaMakanan.get(holder.getAdapterPosition()));

        holder.ConstrainLayout.setOnClickListener(view -> {

                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("foto_makanan",fotoMakanan.get(holder.getAdapterPosition()));
                intent.putExtra("nama_makanan",namaMakanan.get(holder.getAdapterPosition()));
                intent.putExtra("info_makanan",infoMakanan.get(holder.getAdapterPosition()));
                intent.putExtra("harga_makanan",hargaMakanan.get(holder.getAdapterPosition()));

                context.startActivity(intent);
            });
    }

    @Override
    public int getItemCount() {
        return namaMakanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageViewgambarmakanan;
        TextView textViewnamamakanan;
        ConstraintLayout ConstrainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewgambarmakanan = itemView.findViewById(R.id.imageViewgambarmakanan);
            textViewnamamakanan = itemView.findViewById(R.id.textViewnamamakanan);
            ConstrainLayout = itemView.findViewById(R.id.constrainLayout);
        }
    }
}
