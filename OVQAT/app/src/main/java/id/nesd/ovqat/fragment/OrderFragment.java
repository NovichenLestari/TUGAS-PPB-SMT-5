package id.nesd.ovqat.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.nesd.ovqat.R;
import id.nesd.ovqat.adapter.FoodAdapter;
import id.nesd.ovqat.model.FoodModel;
import id.nesd.ovqat.model.HistoryModel;
import id.nesd.ovqat.model.app.ConverterModel;
import id.nesd.ovqat.model.app.SingletonModel;

public class OrderFragment extends Fragment {
    private FoodAdapter adFood;
    private List<FoodModel> foods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        RecyclerView rvFood = v.findViewById(R.id.rvFood);
        foods = new ArrayList<>();
        rvFood.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        adFood = new FoodAdapter(v.getContext(), foods);
        rvFood.setAdapter(adFood);
        rvFood.setLayoutManager(layoutManager);
        getHistory();
        return v;
    }

    private void getHistory() {
        FirebaseFirestore.getInstance().collection("history")
                .whereEqualTo("user", SingletonModel.getInstance().getUser().getId()).get()
                .addOnSuccessListener(this::onHistorySuccess)
                .addOnFailureListener(this::onFailure);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void onHistorySuccess(QuerySnapshot data) {
        foods.clear();
        List<HistoryModel> histories = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            try {
                DocumentSnapshot doc = data.getDocuments().get(i);
                String json = ConverterModel.jsonEncode(doc.getData());
                HistoryModel h = ConverterModel.historyFromJsonString(json);
                histories.add(h);
            } catch (IOException e) {
                e.printStackTrace();
                onFailure(e);
            }
        }
        if (!histories.isEmpty()) {
            getFoods(histories);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getFoods(List<HistoryModel> histories) {
        for (int i = 0; i < histories.size(); i++) {
            FirebaseFirestore.getInstance().collection("food")
                    .document(histories.get(i).getFood()).get()
                    .addOnSuccessListener(doc -> {
                        try {
                            String json = ConverterModel.jsonEncode(doc.getData());
                            FoodModel f = ConverterModel.foodFromJsonString(json);
                            f.setId(doc.getId());
                            Log.e("doc", f.getName());
                            foods.add(f);
                            adFood.notifyDataSetChanged();
                        } catch (IOException e) {
                            e.printStackTrace();
                            onFailure(e);
                        }
                    })
                    .addOnFailureListener(this::onFailure);
        }
    }

    private void onFailure(Exception e) {
        Toast.makeText(getContext(), "Gagal memuat data!", Toast.LENGTH_SHORT).show();
    }
}
