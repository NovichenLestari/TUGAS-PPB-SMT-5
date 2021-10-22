package com.example.daftarmenumakanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> fotoMakanan = new ArrayList<>();
    private ArrayList<String> namaMakanan = new ArrayList<>();
    private ArrayList<String> infoMakanan = new ArrayList<>();
    private ArrayList<String> hargaMakanan = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromInternet();
    }


    private void prosesrecyclerviewadapter (){
        RecyclerView recyclerView = findViewById(R.id.rec_menu);
        recyclerviewadapter recyclerviewadapter = new recyclerviewadapter(fotoMakanan, namaMakanan, infoMakanan,hargaMakanan,this);

        recyclerView.setAdapter(recyclerviewadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDataFromInternet(){
        namaMakanan.add("Nasi Gandul");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Nasi-Gandul.jpg");
        infoMakanan.add("Nasi Gandul sendiri merupakan masakan yang dihidangkan dengan nasi putih, ditambah dengan kuah bersantan kental yang memiliki rasa gurih dan manis. Kuah santan yang dituangkan di atas nasi tersebut memiliki warna merah kecoklatan, dimana pembeli bisa memilih menggunakan jenis daging sebagai pelengkapnya.");
        hargaMakanan.add(" Harga : Rp15.000");

        namaMakanan.add("Mangut Kepala Manyung");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Mangut-Kepala-Manyung.jpg");
        infoMakanan.add("Kepala Manyung, yang memiliki kuah santan berwarna kuning, akibat dari kunyit yang merupakan salah satu bumbu untuk masakan tersebut. Kepala ikan manyung dimasak biasanya merupakan kepala ikan yang telah diasap, jadi akan sangat terasa khas baik dari aroma serta cita rasanya.");
        hargaMakanan.add(" Harga : Rp35.000");

        namaMakanan.add("Jangan Tewel");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Jangan-Tewel-atau-Sayur-Nangka-Muda.jpg");
        infoMakanan.add("Tewel adalah bahasa Jawa untuk nangka muda, jadi sego tewel merupakan nasi yang berdampingan dengan sayur nangka muda. Menunya cukup sederhana, nasi tersebut akan dialasi dengan daun jati, kemudian ditambah sayur tewel yang dimasak kuah santan encer dengan cita rasa pedas gurih.");
        hargaMakanan.add(" Harga : Rp12.000");

        namaMakanan.add("Soto Kemiri");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Soto-Kemiri.jpg");
        infoMakanan.add("Soto Kemiri merupakan soto ayam yang memiliki sajian cukup berbeda dari soto pada umumnya. Dari segi cita rasanya pun cukup jauh berbeda dari soto ayam kebanyakan yang ada di daerah lain. Kuah kaldu ayam serta bumbu soto yang ada di soto Kemiri akan lebih terasa di lidah saat Anda mencicipinya.");
        hargaMakanan.add(" Harga : Rp10.000");

        namaMakanan.add("Jangan Tempe Pedes");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Jangan-Tempe-Pedes.jpg");
        infoMakanan.add("Jangan Tempe ini merupakan masakan kuah santan yang terbuat dari olahan tempe busuk atau semangit, yang telah dibumbui dengan sangat pedas.");
        hargaMakanan.add(" Harga : Rp10.000");

        namaMakanan.add("Jangan Mrico atau Kelo Mrico");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Jangan-Mrico-atau-Kelo-Mrico.jpg");
        infoMakanan.add("Rasa pedas alami merica yang dipadukan dengan asam tomat hijau ditambah gurihnya ikan patin yang segar, akan menyapa lidah Anda ketika pertama kali menyuap hidangan satu ini.");
        hargaMakanan.add(" Harga : Rp25.000");

        namaMakanan.add("Petis Kambing Runting");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Petis-Kambing-Runting.jpg");
        infoMakanan.add("Petis Kambing Runting adalah masakan yang memiliki bahan dasar sumsum tulang kambing, atau di daerah Pati biasa disebut dengan nama balungan. Yang digunakan dalam masakan ini biasanya daging kambing bagian iga dan dicampur dengan jeroan.");
        hargaMakanan.add(" Harga : Rp20.000");

        namaMakanan.add("Opor Saudah");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Opor-Saudah.jpg");
        infoMakanan.add("Opor ayam Ibu Sudah ini sepintas memang terlihat layaknya opor ayam pada umumnya yang sering Anda temui. Namun yang membuat opor ayam ini istimewa adalah porsi potongan dari ayamnya yang begitu besar, tidak seperti hidangan opor ayam lainnya.");
        hargaMakanan.add(" Harga : Rp20.000");

        namaMakanan.add("Swike Kerang");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Swike-Kerang.jpg");
        infoMakanan.add("Kerang kerang tersebut dimasak lengkap dengan cangkangnya, cara memasaknya yaitu dengan merebus kerang di dalam kuah yang telah diberi bumbu khusus. Tidak heran jika aroma makanan ini bisa sangat begitu menggoda indra penciuman.");
        hargaMakanan.add(" Harga : Rp20.000");

        namaMakanan.add("Sarang Madu");
        fotoMakanan.add("https://www.javatravel.net/wp-content/uploads/2020/09/Sarang-Madu.jpg");
        infoMakanan.add("Makanan khas Pati ini dibuat dari tepung beras ketan yang dipadu dengan tepung beras biasa.");
        hargaMakanan.add(" Harga : Rp10.000");

        prosesrecyclerviewadapter();
    }
}