package com.example.kalkulatorandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup Tambah;
    RadioButton radKurang, radKali, radBagi, radTambah;
    EditText txtBil1,txtBil2;
    Button btnHitung, btnHistory;
    TextView txtViewhasil1;
    String pilihan="Tambah";
    private ViewModelHistory ViewModelHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBil1=findViewById(R.id.txtBil1);
        txtBil2=findViewById(R.id.txtBil2);
        Tambah =findViewById(R.id.Tambah);
        radTambah=findViewById(R.id.radTambah);
        radKurang=findViewById(R.id.radKurang);
        radKali=findViewById(R.id.radKali);
        radBagi=findViewById(R.id.radBagi);

        txtViewhasil1=findViewById(R.id.txtViewhasil1);
        btnHitung=findViewById(R.id.btnHitung);
        btnHistory=findViewById(R.id.btnHistory);


       btnHitung.setOnClickListener(view -> {
            cekOperasi();
        });
        btnHistory.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,list_riwayat.class);
            startActivity(intent);
        });

    }
    
    public void cekOperasi(){

        final int idTombolTambah=R.id.radTambah;
        final int idTombolKurang=R.id.radKurang;
        final int idTombolKali=R.id.radKali;
        final int idTombolBagi=R.id.radBagi;


        switch (Tambah.getCheckedRadioButtonId()) {

            case -1:
                Toast.makeText(getApplicationContext(),
                        "Pilih salah satu operasi!",
                        Toast.LENGTH_SHORT)
                        .show();
                break;

            case idTombolTambah:

                penjumlahan();
                break;

            case idTombolKurang:
                pengurangan();
                break;

            case idTombolKali:
                perkalian();
                break;

            case idTombolBagi:
                pembagian();
                break;
        }


    }

    public void penjumlahan(){
        //rumus : angka pertama + angka kedua
        int getAngka1=Integer.parseInt(String.valueOf(txtBil1.getText()));
        int getAngka2=Integer.parseInt(String.valueOf(txtBil2.getText()));

        int hasil = getAngka1 + getAngka2;
        txtViewhasil1.setText(String.valueOf(hasil));

        final HistoryHitung riwayatJumlah= new HistoryHitung(getAngka1+" + "+getAngka2+" = "+hasil);
        ViewModelHistory=new ViewModelProvider(this).get(ViewModelHistory.class);
        ViewModelHistory.insert(riwayatJumlah);

        txtBil1.setText("");
        txtBil2.setText("");

        Tambah.clearCheck();
    }

    public void pengurangan(){
        //rumus : angka pertama - angka kedua
        int getAngka1=Integer.parseInt(String.valueOf(txtBil1.getText()));
        int getAngka2=Integer.parseInt(String.valueOf(txtBil2.getText()));

        int hasil=getAngka1-getAngka2;
        txtViewhasil1.setText(String.valueOf(hasil));

        final HistoryHitung riwayatJumlah= new HistoryHitung(getAngka1+" - "+getAngka2+" = "+hasil);
        ViewModelHistory=new ViewModelProvider(this).get(ViewModelHistory.class);
        ViewModelHistory.insert(riwayatJumlah);

        txtBil1.setText("");
        txtBil2.setText("");

       Tambah.clearCheck();

    }

    public void perkalian(){
        //rumus : angka pertama x angka kedua
        int getAngka1=Integer.parseInt(String.valueOf(txtBil1.getText()));
        int getAngka2=Integer.parseInt(String.valueOf(txtBil2.getText()));

        int hasil=getAngka1 * getAngka2;
        txtViewhasil1.setText(String.valueOf(hasil));

        final HistoryHitung riwayatJumlah= new HistoryHitung(getAngka1+" x "+getAngka2+" = "+hasil);
        ViewModelHistory=new ViewModelProvider(this).get(ViewModelHistory.class);
        ViewModelHistory.insert(riwayatJumlah);

        //clear input / bersihkan inputnya
        txtBil1.setText("");
        txtBil2.setText("");

        Tambah.clearCheck();
    }

    public void pembagian(){
        //rumus : angka pertama / angka kedua
        int getAngka1=Integer.parseInt(String.valueOf(txtBil1.getText()));
        int getAngka2=Integer.parseInt(String.valueOf(txtBil2.getText()));

        //
        int hasil=getAngka1/getAngka2;
        txtViewhasil1.setText(String.valueOf(hasil));

        //
        final HistoryHitung riwayatJumlah= new HistoryHitung(getAngka1+" / "+getAngka2+" = "+hasil);
        ViewModelHistory=new ViewModelProvider(this).get(ViewModelHistory.class);
        ViewModelHistory.insert(riwayatJumlah);

        //clear input / bersihkan inputnya
        txtBil1.setText("");
        txtBil2.setText("");

        Tambah.clearCheck();
    }
}