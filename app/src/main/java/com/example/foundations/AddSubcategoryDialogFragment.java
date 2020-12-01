package com.example.foundations;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

public class AddSubcategoryDialogFragment extends DialogFragment {

    private MainViewModel mainViewModel;
    private int categoryId;

    public AddSubcategoryDialogFragment(MainViewModel mainViewModel, int categoryId) {
        this.mainViewModel = mainViewModel;
        this.categoryId = categoryId;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_subcategory, null);

        builder.setView(view)
                .setPositiveButton("Add Subcategory", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = view.findViewById(R.id.asc_dialog_title_edit);
                        String subcategoryName = editText.getText().toString();
                        SubCategory subcategory = new SubCategory(categoryId, subcategoryName);
                        mainViewModel.insertSubCategory(subcategory);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddSubcategoryDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
