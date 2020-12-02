package com.example.foundations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainInspectionFragment extends Fragment implements SubcategoryHandler {

    private FragmentSwitcher fragmentSwitcher;
    private MainViewModel mainViewModel;
    private int currentReportId;
    private List<Category> allCategories;

    public MainInspectionFragment(FragmentSwitcher fragmentSwitcher, int currentReportId) {
        this.fragmentSwitcher = fragmentSwitcher;
        this.currentReportId = currentReportId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_inspection, container, false);
        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        RecyclerView miCategoryRecyclerView = view.findViewById(R.id.mi_category_recyclerview);
        final MICategoryAdapter mCategoryAdapter = new MICategoryAdapter(miCategoryRecyclerView.getContext(), this, mainViewModel, currentReportId);
        miCategoryRecyclerView.setAdapter(mCategoryAdapter);
        miCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainViewModel.getAllCategories().observe(getViewLifecycleOwner(), mCategoryAdapter::setMiCategories);
        mainViewModel.getAllSubcategories().observe(getViewLifecycleOwner(), mCategoryAdapter::setMiSubcategories);
        mainViewModel.getAllListItems().observe(getViewLifecycleOwner(), mCategoryAdapter::setMiListItems);
        Button addCategory = view.findViewById(R.id.mi_add_category);
        addCategory.setOnClickListener(v -> {
            showAddCategoryDialog(mainViewModel);
        });
        Button addSubcategory = view.findViewById(R.id.mi_add_subcategory);
        addSubcategory.setOnClickListener(v -> {
            showAddSubcategoryDialog(mainViewModel);
        });
        return view;
    }

    private void showAddCategoryDialog(MainViewModel mainViewModel) {
        DialogFragment dialog = new AddCategoryDialogFragment(mainViewModel);
        dialog.show(getParentFragmentManager(), "addCategory");
    }

    public void showAddSubcategoryDialog(MainViewModel mainViewModel) {
        DialogFragment dialog = new AddSubcategoryDialogFragment(mainViewModel, allCategories);
        dialog.show(getParentFragmentManager(), "addSubcategory");
    }

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories = allCategories;
    }

    public void showListItemDialog(MainViewModel mainViewModel, List<SubCategory> allSubcategories) {
        DialogFragment dialog = new ListItemDialogFragment(mainViewModel, allSubcategories, currentReportId);
        dialog.show(getParentFragmentManager(), "listItem");
    }

}
