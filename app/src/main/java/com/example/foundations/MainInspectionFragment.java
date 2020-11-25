package com.example.foundations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainInspectionFragment extends Fragment {

    private FragmentSwitcher fragmentSwitcher;
    private MainViewModel mainViewModel;

    public MainInspectionFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher = fragmentSwitcher;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_inspection, container, false);

        RecyclerView miCategoryRecyclerView = view.findViewById(R.id.mi_category_recyclerview);
        final MICategoryAdapter mCategoryAdapter = new MICategoryAdapter(miCategoryRecyclerView.getContext());
        miCategoryRecyclerView.setAdapter(mCategoryAdapter);
        miCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        mainViewModel.getAllCategories().observe(getViewLifecycleOwner(), mCategoryAdapter::setMiCategories);
        mainViewModel.getAllSubcategories().observe(getViewLifecycleOwner(), mCategoryAdapter::setMiSubcategories);
        mainViewModel.getAllListItems().observe(getViewLifecycleOwner(), mCategoryAdapter::setMiListItems);
        return view;
    }
}
