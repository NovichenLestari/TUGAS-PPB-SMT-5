package id.nesd.ovqat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.nesd.ovqat.tool.Helper;


public class TentangActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        new Helper().setTitleAppBar(this, getResources().getString(R.string.tentang));
    }
}