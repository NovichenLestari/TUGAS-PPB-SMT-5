package id.nesd.ovqat.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.nesd.ovqat.R;
import id.nesd.ovqat.adapter.CategoryAdapter;
import id.nesd.ovqat.adapter.FoodAdapter;
import id.nesd.ovqat.model.CategoryModel;
import id.nesd.ovqat.model.FoodModel;
import id.nesd.ovqat.model.app.ConverterModel;

public class HomeFragment extends Fragment {
    private List<CategoryModel> categories;
    private CategoryAdapter adCategory;
    private FoodAdapter adFood;
    private List<FoodModel> foods;
    private CategoryModel selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        GridView gvCategory = v.findViewById(R.id.gvCategory);
        categories = new ArrayList<>();
        adCategory = new CategoryAdapter(v.getContext(), categories, category -> {
            if (category.getName().equals("Semua")) {
                selected = null;
            } else {
                selected = category;
            }
            getFood();
        });
        gvCategory.setAdapter(adCategory);

        RecyclerView rvFood = v.findViewById(R.id.rvFood);
        foods = new ArrayList<>();
        rvFood.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        adFood = new FoodAdapter(v.getContext(), foods);
        rvFood.setAdapter(adFood);
        rvFood.setLayoutManager(layoutManager);

        getCategory();
        return v;
    }

    private void getCategory() {
        FirebaseFirestore.getInstance().collection("category").get()
                .addOnSuccessListener(data -> {
                    for (int i = 0; i < data.size(); i++) {
                        try {
                            String json = ConverterModel.jsonEncode(data.getDocuments().get(i).getData());
                            CategoryModel c = ConverterModel.categoryFromJsonString(json);
                            categories.add(c);
                            adCategory.notifyDataSetChanged();
                            getFood();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Gagal memuat category!", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                }).addOnFailureListener(
                e -> Toast.makeText(getContext(), "Gagal memuat category!", Toast.LENGTH_SHORT)
                        .show());
    }

    private void getFood() {
        CollectionReference col = FirebaseFirestore.getInstance().collection("food");
        if (selected == null) {
            col.get().addOnSuccessListener(this::onFoodSuccess)
                    .addOnFailureListener(this::onFoodFailure);
        } else {
            col.whereEqualTo("category", selected).get()
                    .addOnSuccessListener(this::onFoodSuccess)
                    .addOnFailureListener(this::onFoodFailure);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void onFoodSuccess(QuerySnapshot data) {
        foods.clear();
        for (int i = 0; i < data.size(); i++) {
            try {
                DocumentSnapshot doc = data.getDocuments().get(i);
                String json = ConverterModel.jsonEncode(doc.getData());
                FoodModel f = ConverterModel.foodFromJsonString(json);
                f.setId(doc.getId());
                foods.add(f);
                adFood.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Gagal memuat data!", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void onFoodFailure(Exception e) {
        Toast.makeText(getContext(), "Gagal memuat data!", Toast.LENGTH_SHORT).show();
    }
}
