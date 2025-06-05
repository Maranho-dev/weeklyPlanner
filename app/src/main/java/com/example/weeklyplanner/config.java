package com.example.weeklyplanner;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Config extends AppCompatActivity {

    private Switch switchNotifications;
    private Button buttonLogout, buttonExit;

    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_NOTIFICATIONS = "notifications_enabled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config); // layout config.xml

        switchNotifications = findViewById(R.id.switch_notifications);
        buttonLogout = findViewById(R.id.button_logout);
        buttonExit = findViewById(R.id.button_exit_app);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean notificationsEnabled = prefs.getBoolean(KEY_NOTIFICATIONS, true);
        switchNotifications.setChecked(notificationsEnabled);

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_NOTIFICATIONS, isChecked);
            editor.apply();
            Toast.makeText(this, "Notificações " + (isChecked ? "ativadas" : "desativadas"), Toast.LENGTH_SHORT).show();
        });

        buttonLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Logout realizado", Toast.LENGTH_SHORT).show();
        });

        buttonExit.setOnClickListener(v -> finishAffinity());
    }
}
