package com.example.foundations;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

public class DeleteDialogFragment extends DialogFragment {
    private MainViewModel mainViewModel;
    int itemId;

    public DeleteDialogFragment(MainViewModel mainViewModel, int itemId) {
        this.mainViewModel = mainViewModel;
        this.itemId = itemId;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_delete_item, null);

        builder.setView(view)
                .setPositiveButton("Yes, Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mainViewModel.deleteListItem(itemId);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
