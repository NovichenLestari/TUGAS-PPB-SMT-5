package id.nesd.ovqat.adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import id.nesd.ovqat.R;
import id.nesd.ovqat.mInterface.OnCategoryClicked;
import id.nesd.ovqat.model.CategoryModel;

public class CategoryAdapter extends ArrayAdapter<CategoryModel> {
    Context context;
    List<CategoryModel> categories;
    OnCategoryClicked onClick;

    public CategoryAdapter(@NonNull Context context, List<CategoryModel> categories, OnCategoryClicked onClick) {
        super(context, 0, categories);
        this.context = context;
        this.categories = categories;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_category, parent, false);
        }
        CategoryModel category = getItem(position);
        CardView cvCategory = v.findViewById(R.id.cvCategory);
        TextView tvCategory = v.findViewById(R.id.tvCategory);
        ImageView ivCategory = v.findViewById(R.id.ivCategory);
        tvCategory.setText(category.getName());
        Glide.with(getContext()).load(category.getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(ivCategory);
        cvCategory.setOnClickListener(view -> onClick.onClick(category));
        return v;
    }
}
