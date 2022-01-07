package id.nesd.ovqat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import id.nesd.ovqat.model.UserModel;
import id.nesd.ovqat.tool.Helper;

public class RegisterActivity extends AppCompatActivity {

    private Helper helper;
    private EditText etNama;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private Button btRegis;
    private boolean onProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper = new Helper();
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btRegis = findViewById(R.id.btRegis);
        setNotProcess();

        btRegis.setOnClickListener(v -> {
            if (!onProcess) {
                String nama = etNama.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (nama.isEmpty()) {
                    etNama.setError("Kolom Wajib diisi!");
                } else if (email.isEmpty()) {
                    etEmail.setError("Kolom Wajib diisi!");
                } else if (!helper.isValidMail(email)) {
                    etEmail.setError("Email Tidak Valid!");
                } else if (username.isEmpty()) {
                    etUsername.setError("Kolom Wajib diisi!");
                } else if (password.isEmpty()) {
                    etPassword.setError("Kolom Wajib diisi!");
                } else {
                    UserModel user = new UserModel();
                    user.setUsername(username);
                    user.setDisplayName(nama);
                    user.setEmail(email);
                    user.setPassword(password);
                    createRequest(user);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void createRequest(UserModel user) {
        onProcess = true;
        btRegis.setText("Registering...");
        Context c = this;
        FirebaseFirestore.getInstance().collection("user")
                .whereEqualTo("username", user.getUsername()).get()
                .addOnSuccessListener(dataUsername -> {
                    boolean usernameRegistered = dataUsername.size() > 0;
                    if (usernameRegistered) {
                        setNotProcess();
                        Toast.makeText(c, "Username Sudah Ada!", Toast.LENGTH_SHORT).show();
                    } else {
                        FirebaseFirestore.getInstance().collection("user")
                                .whereEqualTo("email", user.getEmail()).get()
                                .addOnSuccessListener(dataEmail -> {
                                    boolean emailRegistered = dataEmail.size() > 0;
                                    if (emailRegistered) {
                                        setNotProcess();
                                        Toast.makeText(c, "Email Sudah Ada!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        register(user);
                                    }
                                }).addOnFailureListener(this::onFailure);
                    }
                }).addOnFailureListener(this::onFailure);
    }

    @SuppressLint("SetTextI18n")
    private void register(UserModel user) {
        Context c = this;
        FirebaseFirestore.getInstance().collection("user")
                .add(user).addOnSuccessListener(documentReference -> {
            setNotProcess();
            Toast.makeText(c, "Berhasil mendaftar!", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(this::finish, 2000);
        }).addOnFailureListener(this::onFailure);
    }


    private void onFailure(Exception e) {
        setNotProcess();
        Toast.makeText(this, "Gagal mendaftar!", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void setNotProcess() {
        onProcess = false;
        btRegis.setText("Register");
    }
}