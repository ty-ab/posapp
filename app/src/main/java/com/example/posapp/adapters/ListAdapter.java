package com.example.posapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posapp.R;
import com.example.posapp.db.SalesSummary;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<SalesSummary> salesSummaries;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView transactionTextView;
        public TextView totalTextView;
        public TextView taxTextView;
        public TextView quantityTextView;
        public TextView paidTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardViewCartList);
            transactionTextView = itemView.findViewById(R.id.transactionTextView);
            totalTextView = itemView.findViewById(R.id.totalTextView);
            taxTextView = itemView.findViewById(R.id.taxTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            paidTextView = itemView.findViewById(R.id.paidTextView);
        }
    }

    public ListAdapter(List<SalesSummary> cardList) {
        salesSummaries = cardList;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_sale_summary, parent, false);
        ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        SalesSummary currentItem = salesSummaries.get(position);

        holder.transactionTextView.setText(String.format("Transaction: %s", currentItem.getTransactionDate().toString()));
        holder.totalTextView.setText(String.format("total: %s", currentItem.getTotal()));
        holder.taxTextView.setText(String.format("TAX(15%%): %s", currentItem.getTax()));
        holder.quantityTextView.setText(String.format("Quantity :%s", currentItem.getTotal()));
        holder.paidTextView.setText(String.format("Paid: %s", currentItem.getPayable()));
    }


    @Override
    public int getItemCount() {
        return salesSummaries.size();
    }
}