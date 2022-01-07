package id.nesd.ovqat;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;

import id.nesd.ovqat.model.CategoryModel;
import id.nesd.ovqat.model.FoodModel;
import id.nesd.ovqat.model.HistoryModel;
import id.nesd.ovqat.model.app.ConverterModel;
import id.nesd.ovqat.model.app.SingletonModel;

public class DetailActivity extends AppCompatActivity {
    private AppBarLayout barLayout;
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView ivImage;
    private TextView tvName;
    private TextView tvCategory;
    private TextView tvPrice;
    private TextView tvDescription;
    private Button btOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        barLayout = findViewById(R.id.app_bar_layout);
        toolbarLayout = findViewById(R.id.coll);
        ivImage = findViewById(R.id.ivImage);
        tvName = findViewById(R.id.tvName);
        tvCategory = findViewById(R.id.tvCategory);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        btOrder = findViewById(R.id.btOrder);

        getData();
    }

    @SuppressLint("SetTextI18n")
    private void getData() {
        String id = getIntent().getStringExtra("id");
        FirebaseFirestore.getInstance().collection("food")
                .document(id).get()
                .addOnSuccessListener(data -> {
                try {
                    String json = ConverterModel.jsonEncode(data.getData());
                    FoodModel f = ConverterModel.foodFromJsonString(json);
                    f.setId(id);
                    Glide.with(this).load(f.getImage())
                            .thumbnail(0.5f)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .into(ivImage);
                    tvName.setText(f.getName());
                    FirebaseFirestore.getInstance().collection("category")
                            .document(f.getCategory())
                            .get().addOnSuccessListener(dc -> {
                        try {
                            String jc = ConverterModel.jsonEncode(dc.getData());
                            CategoryModel c = ConverterModel.categoryFromJsonString(jc);
                            tvCategory.setText(c.getName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).addOnFailureListener(
                            e -> Toast.makeText(this, "Gagal memuat category " + f.getName(), Toast.LENGTH_SHORT)
                                    .show());
                    tvPrice.setText("Rp. " + f.getPrice());
                    tvDescription.setText(f.getDescription());
                    btOrder.setOnClickListener(view -> order(f));
                    handleBar(f.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                    onFailure(e);
                }

        }).addOnFailureListener(this::onFailure);

    }

    private void handleBar(String title) {
        barLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            Resources r = getResources();
            Resources.Theme t = getApplicationContext().getTheme();
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                //  on Collapse
                toolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_VERTICAL);
                toolbarLayout.setTitle(title);
                toolbarLayout.setCollapsedTitleTextColor(r.getColor(R.color.white, t));
                toolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.transparent, t));
                toolbarLayout.setContentScrim(new ColorDrawable(getResources().getColor(R.color.transparentBlack, t)));
            } else {
                toolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_VERTICAL);
                toolbarLayout.setTitle("\t");
                toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white, t));
            }
        });
    }

    private void order(FoodModel food) {
        HistoryModel history = new HistoryModel();
        history.setUser(SingletonModel.getInstance().getUser().getId());
        history.setFood(food.getId());
        FirebaseFirestore.getInstance().collection("history")
                .add(history).addOnSuccessListener(documentReference -> {
            Toast.makeText(this, "Berhasil memesan!", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(this::finish, 2000);
        }).addOnFailureListener(this::onFailure);
    }

    private void onFailure(Exception e) {
        Toast.makeText(this, "Gagal Memuat Data!", Toast.LENGTH_SHORT)
                .show();
        new Handler(Looper.getMainLooper()).postDelayed(this::finish, 2000);

    }
}