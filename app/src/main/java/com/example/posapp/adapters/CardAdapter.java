package com.example.posapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posapp.R;
import com.example.posapp.model.RegItemWithSale;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<RegItemWithSale> mCardList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public TextView mItemTextView;
        public TextView mQxUTextView;
        public TextView mTotalTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mCardView = itemView.findViewById(R.id.cardViewCart);
            mItemTextView = itemView.findViewById(R.id.regItemCard);
            mQxUTextView = itemView.findViewById(R.id.itemQxU);
            mTotalTextView = itemView.findViewById(R.id.itemUnitCard);
        }
    }

    public CardAdapter(List<RegItemWithSale> cardList) {
        mCardList = cardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RegItemWithSale currentItem = mCardList.get(position);

        holder.mItemTextView.setText(String.format("%sc.%s", currentItem.getCode(), currentItem.get_item()));
        holder.mQxUTextView.setText(String.format("%dx%s", currentItem.getQuantity(), currentItem.getUnit_price()));
        holder.mTotalTextView.setText(String.valueOf(currentItem.getCost()));
    }


    @Override
    public int getItemCount() {
        return mCardList.size();
    }
}