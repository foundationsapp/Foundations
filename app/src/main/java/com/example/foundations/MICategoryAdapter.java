package com.example.foundations;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MICategoryAdapter extends RecyclerView.Adapter<MICategoryAdapter.MICategoryAdapterViewHolder> {

    private static final String TAG = "miCategoryAdapter";
    private final LayoutInflater inflater;
    private List<Category> miCategories;
    private List<SubCategory> miSubcategories;
    private int categoryId;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    @NonNull
    @Override
    public MICategoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miCategoryLayoutView = inflater.inflate(R.layout.mi_category_item, parent, false);
        return new MICategoryAdapterViewHolder(miCategoryLayoutView);
    }

//    @Override
//    public void onBindViewHolder(@NonNull MICategoryAdapterViewHolder holder, int position) {
//        if (miCategories != null) {
//            Category currentCategory = miCategories.get(position);
//            Log.d(TAG, "onBindViewHolder: " + currentCategory.getTitle());
//
//            holder.miCategoryItemView.setText(currentCategory.getTitle());
//        }
//    }

    @Override
    public void onBindViewHolder(@NonNull MICategoryAdapterViewHolder holder, int position) {
        if (miCategories != null) {
            Category currentCategory = miCategories.get(position);
            categoryId = currentCategory.getCategoryId();
            holder.miCategoryItemView.setText(currentCategory.getTitle());
            List<SubCategory> filteredList = new ArrayList<>();
            for (int i = 0; i < miSubcategories.size(); i++) {
                Log.d(TAG, "setSubcategories categoryid: " + miSubcategories.get(i).getCategoryId() + " " + categoryId);
                if (miSubcategories.get(i).getCategoryId() == categoryId) {
                    filteredList.add(miSubcategories.get(i));
                }
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(holder.miSubcategoryRecyclerView.getContext());
            MISubcategoryAdapter miSubcategoryAdapter = new MISubcategoryAdapter(filteredList);
            holder.miSubcategoryRecyclerView.setLayoutManager(layoutManager);
            holder.miSubcategoryRecyclerView.setAdapter(miSubcategoryAdapter);
            holder.miSubcategoryRecyclerView.setRecycledViewPool(viewPool);
        }
    }

    @Override
    public int getItemCount() {
        if (miCategories != null) {
            return miCategories.size();
        } else return 0;
    }

    void setMiCategories(List<Category> categories) {
        this.miCategories = categories;
        notifyDataSetChanged();
    }

    void setMiSubcategories(List<SubCategory> subcategories) {
        this.miSubcategories = subcategories;
        notifyDataSetChanged();
    }

    static class MICategoryAdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView miCategoryItemView;
        private final RecyclerView miSubcategoryRecyclerView;

        public MICategoryAdapterViewHolder(View itemView) {
            super(itemView);
            miCategoryItemView = itemView.findViewById(R.id.mi_category_item_title);
            miSubcategoryRecyclerView = itemView.findViewById(R.id.mi_subcategory_recyclerview);
        }
    }

    public MICategoryAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }
}
