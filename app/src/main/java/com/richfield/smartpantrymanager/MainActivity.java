package com.richfield.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName, etQty, etExpiry;
    Button btnAdd, btnRecipes, btnSettings;
    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    PantryAdapter adapter;
    ArrayList<PantryItem> list;
    PantryItem editingItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etQty = findViewById(R.id.etQty);
        etExpiry = findViewById(R.id.etExpiry);
        btnAdd = findViewById(R.id.btnAdd);
        btnRecipes = findViewById(R.id.btnRecipes);
        btnSettings = findViewById(R.id.btnSettings);
        recyclerView = findViewById(R.id.recyclerView);

        dbHelper = new DatabaseHelper(this);
        list = dbHelper.getAllItems();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PantryAdapter(list, new PantryAdapter.OnItemActionListener() {
            @Override
            public void onEdit(PantryItem item) {
                editingItem = item;
                etName.setText(item.getName());
                etQty.setText(String.valueOf(item.getQuantity()));
                etExpiry.setText(item.getExpiryDate());
                btnAdd.setText("Update");
                Toast.makeText(MainActivity.this, "Editing: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onDelete(PantryItem item) {
                dbHelper.deleteItem(item.getId());
                refreshList();
                Toast.makeText(MainActivity.this, "Deleted: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String qtyStr = etQty.getText().toString().trim();
            String expiry = etExpiry.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("Name required"); return;
            }
            if (qtyStr.isEmpty()) {
                etQty.setError("Qty required"); return;
            }
            if (expiry.isEmpty()) {
                etExpiry.setError("Expiry required YYYY-MM-DD"); return;
            }
            if (!expiry.matches("\\d{4}-\\d{2}-\\d{2}")) {
                etExpiry.setError("Use YYYY-MM-DD"); return;
            }

            int qty;
            try {
                qty = Integer.parseInt(qtyStr);
                if (qty <= 0) { etQty.setError("Qty must be >0"); return; }
            } catch (NumberFormatException e) {
                etQty.setError("Must be a number"); return;
            }

            if (editingItem != null) {
                editingItem.setName(name);
                editingItem.setQuantity(qty);
                editingItem.setExpiryDate(expiry);
                dbHelper.updateItem(editingItem);
                editingItem = null;
                btnAdd.setText("Add Item");
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            } else {
                PantryItem item = new PantryItem(0, name, qty, "pcs", expiry);
                dbHelper.addItem(item);
                Toast.makeText(this, name + " added", Toast.LENGTH_SHORT).show();
            }

            clearFields();
            refreshList();
        });

        btnRecipes.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RecipeActivity.class)));
        btnSettings.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    private void refreshList() {
        list.clear();
        list.addAll(dbHelper.getAllItems());
        adapter.notifyDataSetChanged();
    }

    private void clearFields() {
        etName.setText(""); etQty.setText(""); etExpiry.setText("");
        etName.requestFocus();
    }
}