package com.richfield.smartpantrymanager;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch switchAlert = findViewById(R.id.switchAlert);
        switchAlert.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Expiring alerts enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Expiring alerts disabled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}