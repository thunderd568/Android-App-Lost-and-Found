package com.cmsc436.final_project.lostandfoundapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ItemReport> reportList;

    //getting the context and product list with constructor
    public ReportAdapter(Context mCtx, List<ItemReport> productList) {
        this.mCtx = mCtx;
        this.reportList = reportList;
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_item_report, null);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        //getting the product of the specified position
        ItemReport report = reportList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(report.getTitle());
        holder.textViewDateOccur.setText(report.getDateOccurred().toString());
        holder.textViewAddress.setText(
                (report.getAddress()!=null) ? report.getAddress() : report.getLatLng().toString());
        holder.textViewDescription.setText(report.getDescription());

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.lostfound_icon,null));

    }


    @Override
    public int getItemCount() {
        return reportList.size();
    }


    class ReportViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewDateOccur, textViewAddress, textViewDescription;
        ImageView imageView;

        public ReportViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDateOccur = itemView.findViewById(R.id.textViewDateOccur);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
