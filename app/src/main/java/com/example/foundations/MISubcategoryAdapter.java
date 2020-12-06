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
import java.util.zip.Inflater;

public class MISubcategoryAdapter extends RecyclerView.Adapter<MISubcategoryAdapter.MISubcategoryAdapterViewHolder> {

    private static final String TAG = "MISubcategoryAdapter";
    private List<SubCategory> miSubcategories;
    private List<ListItem> miListItems;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    ListItemHandler listItemHandler;

    @NonNull
    @Override
    public MISubcategoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View MISubcategoryLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mi_subcategory_item, parent, false);
        return new MISubcategoryAdapterViewHolder(MISubcategoryLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MISubcategoryAdapterViewHolder holder, int position) {
        if (miSubcategories != null) {
            SubCategory currentSubcategory = miSubcategories.get(position);
            int subcategoryId = currentSubcategory.getSubCategoryId();
            holder.MISubcategoryItemView.setText(currentSubcategory.getTitle());
            List<ListItem> filteredListItemList = new ArrayList<>();
            for (int i = 0; i < miListItems.size(); i++) {
                if (miListItems.get(i).getSubCategoryId() == subcategoryId) {
                    filteredListItemList.add(miListItems.get(i));
                }
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(holder.miListItemRecyclerView.getContext());
            MIListItemAdapter miListItemAdapter = new MIListItemAdapter(filteredListItemList, listItemHandler);
            holder.miListItemRecyclerView.setLayoutManager(layoutManager);
            holder.miListItemRecyclerView.setAdapter(miListItemAdapter);
            holder.miListItemRecyclerView.setRecycledViewPool(viewPool);
        }
    }

    @Override
    public int getItemCount() {
        if (miSubcategories != null) {
            return miSubcategories.size();
        } else return 0;
    }

    void setMISubcategories(List<SubCategory> subcategories) {
        this.miSubcategories = subcategories;
        notifyDataSetChanged();
    }

    static class MISubcategoryAdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView MISubcategoryItemView;
        private final RecyclerView miListItemRecyclerView;

        public MISubcategoryAdapterViewHolder(View itemView) {
            super(itemView);
            MISubcategoryItemView = itemView.findViewById(R.id.mi_subcategory_item_title);
            miListItemRecyclerView = itemView.findViewById(R.id.mi_listitem_recyclerview);
        }
    }

    public MISubcategoryAdapter(List<SubCategory> miSubcategories, List<ListItem> miListItems, ListItemHandler listItemHandler
    ) {
        this.miSubcategories = miSubcategories;
        this.miListItems = miListItems;
        this.listItemHandler = listItemHandler;
    }

//    @Override
//    public void onBindViewHolder(@NonNull ChecklistCategoryViewHolder holder, int position) {
//        if (categories != null) {
//            Category current = categories.get(position);
//            categoryId = current.getCategoryId();
//            Log.d(TAG, "onBindViewHolder: " + categoryId);
//            holder.checklistCategoryItemView.setText(current.getTitle());
//            List<SubCategory> filteredList = new ArrayList<>();
//            for (int i = 0; i < subcategories.size(); i++) {
//                Log.d(TAG, "setSubcategories categoryid: " + subcategories.get(i).getCategoryId() + " " + categoryId);
//                if (subcategories.get(i).getCategoryId() == categoryId) {
//                    filteredList.add(subcategories.get(i));
//                }
//            }
//            LinearLayoutManager layoutManager = new LinearLayoutManager(holder.subcategoryRecyclerView.getContext());
//            ChecklistSubcategoryAdapter checklistSubcategoryAdapter = new ChecklistSubcategoryAdapter(filteredList);
//            holder.subcategoryRecyclerView.setLayoutManager(layoutManager);
//            holder.subcategoryRecyclerView.setAdapter(checklistSubcategoryAdapter);
//            holder.subcategoryRecyclerView.setRecycledViewPool(viewPool);
//        }
//    }
}
