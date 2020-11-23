package com.example.foundations;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CheckListsFragment extends Fragment {



    public CheckListsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_lists, container, false);
        RecyclerView checklistCategoryRecyclerView = view.findViewById(R.id.cl_category_recyclerview);
        final ChecklistCategoryAdapter checklistCategoryAdapter = new ChecklistCategoryAdapter(checklistCategoryRecyclerView.getContext());
        checklistCategoryRecyclerView.setAdapter(checklistCategoryAdapter);
        checklistCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        mainViewModel.getAllCategories().observe(getViewLifecycleOwner(), checklistCategoryAdapter::setCategories);
        mainViewModel.getAllSubCategories().observe(getViewLifecycleOwner(), checklistCategoryAdapter::setSubcategories);

        return view;
    }
}