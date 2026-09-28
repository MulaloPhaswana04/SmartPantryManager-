package com.richfield.smartpantrymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.ViewHolder> {

    public interface OnItemActionListener {
        void onEdit(PantryItem item);
        void onDelete(PantryItem item);
    }

    private ArrayList<PantryItem> list;
    private OnItemActionListener listener;

    public PantryAdapter(ArrayList<PantryItem> list, OnItemActionListener listener) {
        this.list = list;
        this.listener = listener;
    }

    // Keep old constructor for backward compatibility
    public PantryAdapter(ArrayList<PantryItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pantry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PantryItem item = list.get(position);
        holder.tvName.setText(item.getName());
        holder.tvDetails.setText("Qty: " + item.getQuantity() + " " + item.getUnit() + " | Exp: " + item.getExpiryDate());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onEdit(item);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) {
                listener.onDelete(item);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItemName);
            tvDetails = itemView.findViewById(R.id.tvItemDetails);
        }
    }
}