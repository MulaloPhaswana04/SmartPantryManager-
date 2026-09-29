package com.richfield.smartpantrymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.ViewHolder> {

    public interface OnEditListener { void onEdit(PantryItem item); }
    public interface OnDeleteListener { void onDelete(int id); }

    private Context context;
    private List<PantryItem> list;
    private OnEditListener editListener;
    private OnDeleteListener deleteListener;

    public PantryAdapter(Context context, List<PantryItem> list, OnEditListener edit, OnDeleteListener del) {
        this.context = context;
        this.list = list;
        this.editListener = edit;
        this.deleteListener = del;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_pantry, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PantryItem item = list.get(position);
        holder.txtItemName.setText(item.getName() + " - " + item.getQuantity());
        holder.btnEdit.setOnClickListener(v -> editListener.onEdit(item));
        holder.btnDelete.setOnClickListener(v -> deleteListener.onDelete(item.getId()));
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtItemName;
        ImageButton btnEdit, btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}