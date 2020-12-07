package com.example.foundations;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.PDFViewHolder>{

    private static final String TAG = "PDFAdapter";
    private List<Report> reports;
    private final LayoutInflater inflater;
    private final Context mContext;
    private PDFHandler pdfHandler;
    int selected = -1;

    public PDFAdapter(Context context, PDFHandler pdfHandler) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        this.pdfHandler = pdfHandler;
    }

    @NonNull
    @Override
    public PDFViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pdfLayoutView = inflater.inflate(R.layout.pdf_recyclerview_item, parent, false);
        return new PDFViewHolder(pdfLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull PDFViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + reports.size());
        if (reports != null) {
            if (selected==position){
                holder.pdfItemView.setBackgroundColor(Color.parseColor("#ffff00"));
            } else{
                holder.pdfItemView.setBackgroundColor(Color.parseColor("#44bcda"));
            }
            Report current = reports.get(position);
            Log.d(TAG, "onBindViewHolder: " + current.getStreetAddress());
            holder.pdfItemView.setText(current.getStreetAddress());
            holder.pdfItemView.setOnClickListener(v -> {
                selected = position;
                notifyDataSetChanged();
                pdfHandler.setPDF(current.getPdf());
            });
        }
    }

    @Override
    public int getItemCount() {
        if (reports != null) {
            return reports.size();
        } else return 0;
    }

    void setPDFs(List<Report> reports) {
        Log.d(TAG, "setPDFs: " + reports.size());
        List<Report> filtered = new ArrayList<>();
        for (int i = 0; i < reports.size(); i++) {
            Log.d(TAG, "setPDFs: " + reports.get(i).getPdf());
            if (reports.get(i).getPdf() != null) {
                filtered.add(reports.get(i));
                Log.d(TAG, "setPDFs: " + reports.get(i).getStreetAddress());
            }
        }
        this.reports = filtered;
    }

    static class PDFViewHolder extends RecyclerView.ViewHolder {

        private final Button pdfItemView;

        public PDFViewHolder(@NonNull View itemView) {
            super(itemView);
            pdfItemView = itemView.findViewById(R.id.pdfItem);
        }
    }
}
