package id.nesd.ovqat.adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.nesd.ovqat.DetailActivity;
import id.nesd.ovqat.R;
import id.nesd.ovqat.model.CategoryModel;
import id.nesd.ovqat.model.FoodModel;
import id.nesd.ovqat.model.app.ConverterModel;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final Context context;

    private final List<FoodModel> foods;

    public FoodAdapter(Context context, List<FoodModel> foods) {
        this.foods = foods;
        this.context = context;
    }


    static class FoodViewHolder extends RecyclerView.ViewHolder {
        final CardView cvFood;
        final CircleImageView civFood;
        final TextView tvName;
        final TextView tvCategory;
        final TextView tvPrice;

        FoodViewHolder(View itemView) {
            super(itemView);
            cvFood = itemView.findViewById(R.id.cvFood);
            civFood = itemView.findViewById(R.id.civFood);
            tvName = itemView.findViewById(R.id.tvName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);

        }
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup p, int viewType) {
        View v = LayoutInflater.from(p.getContext()).inflate(R.layout.item_food, p, false);
        return new FoodViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder h, int i) {
        FoodModel f = foods.get(i);

        Glide.with(context).load(f.getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(h.civFood);
        h.tvName.setText(f.getName());
        FirebaseFirestore.getInstance().collection("category")
                .document(f.getCategory())
                .get().addOnSuccessListener(data -> {
            try {
                String json = ConverterModel.jsonEncode(data.getData());
                CategoryModel c = ConverterModel.categoryFromJsonString(json);
                h.tvCategory.setText(c.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).addOnFailureListener(
                e -> Toast.makeText(context, "Gagal memuat category " + f.getName(), Toast.LENGTH_SHORT)
                        .show());
        h.tvPrice.setText("Rp. " + f.getPrice());
        h.cvFood.setOnClickListener(v -> {
            Intent i1 = new Intent(context, DetailActivity.class);
            i1.putExtra("id", f.getId());
            context.startActivity(i1);
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

}
