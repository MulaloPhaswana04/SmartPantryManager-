package com.richfield.smartpantrymanager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerPantry;
    private FloatingActionButton fabAdd;
    private DatabaseHelper db;
    private List<PantryItem> pantryList;
    private PantryAdapter adapter;
    private Button btnSuggested, btnPantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerPantry = findViewById(R.id.recyclerPantry);
        fabAdd = findViewById(R.id.fabAdd);
        btnSuggested = findViewById(R.id.btnSuggested);
        btnPantry = findViewById(R.id.btnPantry);
        db = new DatabaseHelper(this);

        recyclerPantry.setLayoutManager(new LinearLayoutManager(this));
        loadPantry();

        fabAdd.setOnClickListener(v -> showAddDialog(null));

        btnSuggested.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SuggestedRecipesActivity.class));
        });

        btnPantry.setOnClickListener(v -> {
            loadPantry();
        });
    }

    private void loadPantry() {
        pantryList = db.getAllItems();
        adapter = new PantryAdapter(this, pantryList,
                item -> showAddDialog(item),
                id -> { db.deleteItem(id); loadPantry(); Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show(); }
        );
        recyclerPantry.setAdapter(adapter);
    }

    private void showAddDialog(PantryItem editItem) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null);
        EditText edtName = view.findViewById(R.id.edtName);
        EditText edtQuantity = view.findViewById(R.id.edtQuantity);
        EditText edtUnit = view.findViewById(R.id.edtUnit);
        EditText edtExpiry = view.findViewById(R.id.edtExpiry);
        Button btnSave = view.findViewById(R.id.btnSave);

        if (editItem != null) {
            edtName.setText(editItem.getName());
            edtQuantity.setText(editItem.getQuantity());
            edtUnit.setText(editItem.getUnit());
            edtExpiry.setText(editItem.getExpiryDate());
        }

        AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        dialog.show();

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String qty = edtQuantity.getText().toString().trim();
            String unit = edtUnit.getText().toString().trim();
            String expiry = edtExpiry.getText().toString().trim();

            if (name.isEmpty()) { edtName.setError("Required"); return; }
            if (qty.isEmpty()) { edtQuantity.setError("Required"); return; }

            if (editItem == null) {
                db.addItem(new PantryItem(name, qty, unit, expiry));
                Toast.makeText(this,"Added: "+name,Toast.LENGTH_SHORT).show();
            } else {
                editItem.setName(name);
                editItem.setQuantity(qty);
                editItem.setUnit(unit);
                editItem.setExpiryDate(expiry);
                db.updateItem(editItem);
                Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            loadPantry();
        });
    }
}