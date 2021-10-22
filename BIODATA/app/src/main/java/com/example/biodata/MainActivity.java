package com.example.biodata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PHONE_CALL = 1;

    public void kirimEmail (View view){
        Uri tabEmail = Uri.parse("mailto:novichenlestari@gmail.com");
        Intent Email = new Intent(Intent.ACTION_SENDTO, tabEmail);
        startActivity(Email);
    }

    public void kirimTelepon(View view) {
        Intent intentCall = new Intent(Intent.ACTION_CALL);
        intentCall.setData(Uri.parse("tel:082324856399"));
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        } else {

            startActivity(intentCall);
        }
    }

    public void tampilMaps(View view){
        Uri tabMaps = Uri.parse("geo:-6.6145429,111.0341183,15z/data=6°36'55.1\"S+111°01'49.7\"E");
        Intent Maps = new Intent(Intent.ACTION_VIEW, tabMaps);
        startActivity(Maps);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}