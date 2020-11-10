package com.example.foundations;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private static final String TAG = ReportAdapter.class.getSimpleName();
    private List<Report> reports;
    private final LayoutInflater inflater;
    private Context mContext;

    static class ReportViewHolder extends RecyclerView.ViewHolder {

        private final Button reportItemView;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            reportItemView = itemView.findViewById(R.id.reportItem);
        }
    }

    public ReportAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View reportLayoutView = inflater.inflate(R.layout.report_recyclerview_item, parent, false);
        return new ReportViewHolder(reportLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        if (reports != null) {
            Report current = reports.get(position);
            holder.reportItemView.setText(current.getStreetAddress());
        }
    }

    @Override
    public int getItemCount() {
        if (reports != null) {
            return reports.size();
        } else return 0;
    }

    void setReports(List<Report> reports) {
        this.reports = reports;
        reports.forEach(report -> {
            Log.d(TAG, "setReports: " + report.getReportId());
        });
        notifyDataSetChanged();
    }
}
