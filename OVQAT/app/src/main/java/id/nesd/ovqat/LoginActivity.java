package id.nesd.ovqat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import id.nesd.ovqat.data.SPData;
import id.nesd.ovqat.model.UserModel;
import id.nesd.ovqat.model.app.ConverterModel;
import id.nesd.ovqat.model.app.SingletonModel;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btMasuk;
    private boolean onProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btMasuk = findViewById(R.id.btMasuk);
        TextView tvRegis = findViewById(R.id.tvRegis);
        setNotProcess();

        btMasuk.setOnClickListener(v -> {
            if (!onProcess) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty()) {
                    etUsername.setError("Kolom Wajib diisi!");
                } else if (password.isEmpty()) {
                    etPassword.setError("Kolom Wajib diisi!");
                } else {
                    login(username, password);
                }
            }
        });

        tvRegis.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class))
        );
    }

    @SuppressLint("SetTextI18n")
    private void login(String username, String password) {
        onProcess = true;
        btMasuk.setText("Logged In...");
        Context c = this;
        FirebaseFirestore.getInstance().collection("user")
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get().addOnSuccessListener(data -> {
            if (data.size() != 1) {
                setNotProcess();
                Toast.makeText(c, "Username/Password Salah!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    DocumentSnapshot doc = data.getDocuments().get(0);
                    Map<String, Object> d = doc.getData();
                    d.put("id", doc.getId());
                    String json = ConverterModel.jsonEncode(d);
                    UserModel user = ConverterModel.userFromJsonString(json);
                    SingletonModel.getInstance().setUser(user);
                    SPData.getInstance(this).setUser(json);
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Gagal Memuat Data!", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(e -> {
            setNotProcess();
            Toast.makeText(c, "Gagal masuk!", Toast.LENGTH_SHORT).show();
        });
    }

    @SuppressLint("SetTextI18n")
    private void setNotProcess() {
        onProcess = false;
        btMasuk.setText("Login");
    }
}