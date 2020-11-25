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

public class MIListItemAdapter extends RecyclerView.Adapter<MIListItemAdapter.MIListItemAdapterViewHolder> {

    private static final String TAG = "MIListItemAdapter";
    private List<ListItem> miListItems;

    @NonNull
    @Override
    public MIListItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View MIListItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mi_listitem_item, parent, false);
        return new MIListItemAdapterViewHolder(MIListItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MIListItemAdapterViewHolder holder, int position) {
        if (miListItems != null) {
            ListItem currentListITem = miListItems.get(position);
            String testTitle = currentListITem.getListItemId() + " " +
                    currentListITem.getCategoryId() + " " + currentListITem.getSubCategoryId();
            holder.MIListItemItemView.setText(testTitle);
        }
    }

    @Override
    public int getItemCount() {
        if (miListItems != null) {
            return miListItems.size();
        } else return 0;
    }

    void setMiListItems(List<ListItem> listItems) {
        this.miListItems = listItems;
        notifyDataSetChanged();
    }

    static class MIListItemAdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView MIListItemItemView;

        public MIListItemAdapterViewHolder(View itemView) {
            super(itemView);
            MIListItemItemView = itemView.findViewById(R.id.mi_list_item_item_title);
        }
    }

    public MIListItemAdapter(List<ListItem> miListItems) {
        this.miListItems = miListItems;
    }

//    @Override
//    public void onBindViewHolder(@NonNull ChecklistCategoryViewHolder holder, int position) {
//        if (categories != null) {
//            Category current = categories.get(position);
//            categoryId = current.getCategoryId();
//            Log.d(TAG, "onBindViewHolder: " + categoryId);
//            holder.checklistCategoryItemView.setText(current.getTitle());
//            List<ListItem> filteredList = new ArrayList<>();
//            for (int i = 0; i < subcategories.size(); i++) {
//                Log.d(TAG, "setSubcategories categoryid: " + subcategories.get(i).getCategoryId() + " " + categoryId);
//                if (subcategories.get(i).getCategoryId() == categoryId) {
//                    filteredList.add(subcategories.get(i));
//                }
//            }
//            LinearLayoutManager layoutManager = new LinearLayoutManager(holder.list_itemRecyclerView.getContext());
//            ChecklistSubcategoryAdapter checklistSubcategoryAdapter = new ChecklistSubcategoryAdapter(filteredList);
//            holder.list_itemRecyclerView.setLayoutManager(layoutManager);
//            holder.list_itemRecyclerView.setAdapter(checklistSubcategoryAdapter);
//            holder.list_itemRecyclerView.setRecycledViewPool(viewPool);
//        }
//    }
}
