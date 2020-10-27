package com.example.foundations;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<Profile> profiles;
    private final LayoutInflater inflater;
    private SetProfileHandler profileHandler;
    private Context mContext;

    static class ProfileViewHolder extends RecyclerView.ViewHolder {

        private final TextView profileItemView;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            profileItemView = itemView.findViewById(R.id.profileItem);
        }
    }

    public ProfileAdapter(Context context, SetProfileHandler profileHandler) {
        inflater = LayoutInflater.from(context);
        this.profileHandler = profileHandler;
        mContext = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View profileLayoutView = inflater
                .inflate(R.layout.profile_recyclerview_item, parent, false);
        return new ProfileViewHolder(profileLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        if (profiles != null) {
            Profile current = profiles.get(position);
            holder.profileItemView.setText(current.getFullName());
            holder.profileItemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: clicked on: " + current.getFullName());
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Your porfile");
                    builder.setMessage(current.getFullName());

                    builder.setPositiveButton("Ok",null);
                    builder.setNegativeButton("Cancel", null);
                    builder.show();



                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (profiles != null) {
            return profiles.size();
        } else return 0;
    }

    void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
        notifyDataSetChanged();
    }
}
