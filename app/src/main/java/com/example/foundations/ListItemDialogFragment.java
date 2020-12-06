package com.example.foundations;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.List;

public class ListItemDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    private MainViewModel mainViewModel;
    List<SubCategory> allSubcategories;
    int reportId;
    int categoryId;
    int subcategoryId;

    public ListItemDialogFragment(MainViewModel mainViewModel, List<SubCategory> allSubcategories, int reportId) {
        this.mainViewModel = mainViewModel;
        this.allSubcategories = allSubcategories;
        this.reportId = reportId;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_list_item, null);
        Spinner spinner = (Spinner) view.findViewById(R.id.subcategory_spinner);
        ArrayAdapter<SubCategory> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, allSubcategories);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        builder.setView(view)
                .setPositiveButton("Submit Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = view.findViewById(R.id.list_item_notes);
                        String notes = editText.getText().toString();
                        ListItem item = new ListItem(reportId, categoryId, subcategoryId, notes, null);
                        mainViewModel.insertListItem(item);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ListItemDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categoryId = allSubcategories.get(position).getCategoryId();
        subcategoryId = allSubcategories.get(position).getSubCategoryId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
