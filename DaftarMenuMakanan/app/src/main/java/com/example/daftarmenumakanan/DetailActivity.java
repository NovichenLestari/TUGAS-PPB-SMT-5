package com.example.daftarmenumakanan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView imageViewFotoMakanan;
    TextView textViewNamaMakanan, textViewInfoMakanan, textViewHargaMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageViewFotoMakanan = findViewById(R.id.imageViewFotoMakanan);
        textViewNamaMakanan = findViewById(R.id.textViewNamaMakanan);
        textViewInfoMakanan = findViewById(R.id.textViewInfoMakanan);
        textViewHargaMakanan = findViewById(R.id.textViewHargaMakanan);

        getIncomingExtra();
    }

    private void getIncomingExtra(){

        if (getIntent().hasExtra("foto_makanan")&& getIntent().hasExtra("nama_makanan")&& getIntent().hasExtra("info_makanan")&& getIntent().hasExtra("harga_makanan")){

            String fotoMakanan = getIntent().getStringExtra("foto_makanan");
            String namaMakanan = getIntent().getStringExtra("nama_makanan");
            String infoMakanan = getIntent().getStringExtra("info_makanan");
            String hargaMakanan = getIntent().getStringExtra("harga_makanan");

            setDataActivity(fotoMakanan, namaMakanan, infoMakanan, hargaMakanan);
        }
    }

    private void setDataActivity(String fotoMakanan, String namaMakanan, String infoMakanan, String hargaMakanan){

        Glide.with(this).asBitmap().load(fotoMakanan).into(imageViewFotoMakanan);

        textViewNamaMakanan.setText(namaMakanan);
        textViewInfoMakanan.setText(infoMakanan);
        textViewHargaMakanan.setText(hargaMakanan);

    }
}