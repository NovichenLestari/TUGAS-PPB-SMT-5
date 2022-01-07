package id.nesd.ovqat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;

import id.nesd.ovqat.data.SPData;
import id.nesd.ovqat.model.UserModel;
import id.nesd.ovqat.model.app.ConverterModel;
import id.nesd.ovqat.model.app.SingletonModel;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            AppCompatActivity a = SplashActivity.this;
            String userData = SPData.getInstance(a).getUser();
            if (userData == null) {
                startActivity(new Intent(a, LoginActivity.class));
                finish();
            } else {
                try {
                    UserModel user =  ConverterModel.userFromJsonString(userData);
                    SingletonModel.getInstance().setUser(user);
                    startActivity(new Intent(a, MainActivity.class));
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(a, "Gagal Memuat Data!", Toast.LENGTH_SHORT).show();
                }
            }
        }, 2000);
    }
}