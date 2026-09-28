package com.richfield.smartpantrymanager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch switchAlert = findViewById(R.id.switchAlert);
        switchAlert.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(this, isChecked ? "Expiring alerts ON" : "Expiring alerts OFF", Toast.LENGTH_SHORT).show();
        });
    }
}