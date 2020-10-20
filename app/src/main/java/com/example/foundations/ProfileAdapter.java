package com.example.foundations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<Profile> profiles;
    private final LayoutInflater inflater;

    class ProfileViewHolder extends RecyclerView.ViewHolder {

        private final TextView profileItemView;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            profileItemView = itemView.findViewById(R.id.profileItem);
        }
    }

    ProfileAdapter(Context context) {
        inflater = LayoutInflater.from(context);
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
