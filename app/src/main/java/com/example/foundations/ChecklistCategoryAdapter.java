package com.example.foundations;

        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.DialogFragment;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.LifecycleOwner;
        import androidx.lifecycle.ViewModelProvider;
        import androidx.lifecycle.ViewModelStoreOwner;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;
        import java.util.List;

public class ChecklistCategoryAdapter extends RecyclerView.Adapter<ChecklistCategoryAdapter.ChecklistCategoryViewHolder> {

    private static final String TAG = "ChecklistCategoryAdapter";
    private final LayoutInflater inflater;
    private List<Category> categories;
    private List<SubCategory> subcategories;
    private int categoryId;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    SubcategoryHandler subcategoryHandler;
    MainViewModel mainViewModel;

    public ChecklistCategoryAdapter(Context context, SubcategoryHandler subcategoryHandler, MainViewModel mainViewModel) {
        inflater = LayoutInflater.from(context);
        this.subcategoryHandler = subcategoryHandler;
        this.mainViewModel = mainViewModel;
    }
    @NonNull
    @Override
    public ChecklistCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View checklistCategoryLayoutView = inflater.inflate(R.layout.checklist_category_recyclerview_item, parent, false);
        return new ChecklistCategoryViewHolder(checklistCategoryLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChecklistCategoryViewHolder holder, int position) {
        if (categories != null) {
            Category current = categories.get(position);
            categoryId = current.getCategoryId();
            holder.checklistCategoryItemView.setText(current.getTitle());
            if (subcategories != null) {
                List<SubCategory> filteredList = new ArrayList<>();
                for (int i = 0; i < subcategories.size(); i++) {
                    if (subcategories.get(i).getCategoryId() == categoryId) {
                        filteredList.add(subcategories.get(i));
                    }
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(holder.subcategoryRecyclerView.getContext());
                ChecklistSubcategoryAdapter checklistSubcategoryAdapter = new ChecklistSubcategoryAdapter(filteredList);
                holder.subcategoryRecyclerView.setLayoutManager(layoutManager);
                holder.subcategoryRecyclerView.setAdapter(checklistSubcategoryAdapter);
                holder.subcategoryRecyclerView.setRecycledViewPool(viewPool);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (categories != null) {
            return categories.size();
        } else return 0;
    }

    void setCategories(List<Category> categories) {
        this.categories = categories;
        subcategoryHandler.setAllCategories(categories);
        notifyDataSetChanged();
    }

    void setSubcategories(List<SubCategory> subcategories) {
        this.subcategories = subcategories;
        notifyDataSetChanged();
    }


    static class ChecklistCategoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView checklistCategoryItemView;
        private final RecyclerView subcategoryRecyclerView;

        public ChecklistCategoryViewHolder(View itemView) {
            super(itemView);
            checklistCategoryItemView = itemView.findViewById(R.id.cl_category_rv_item_title);
            subcategoryRecyclerView = itemView.findViewById(R.id.cl_subcat_recyclerview);
        }
    }
}
