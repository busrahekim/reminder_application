package com.example.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ImageButton exitButton;
    ImageButton addButton;
    ArrayList<Model> datable = new ArrayList<Model>();
    myAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        exitButton = findViewById(R.id.exitButton);
        addButton = findViewById(R.id.addButton);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Cursor cursor = new DBManager(getApplicationContext()).readAllReminders();
        while (cursor.moveToNext()) {
            Model model = new Model(cursor.getString(1),  cursor.getString(2));
            datable.add(model);
        }

        adapter = new myAdapter(datable);
        recyclerView.setAdapter(adapter);

    }

    public void exitApp(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Do you really want to exit ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    public void getInfo(View view) {
        this.startActivity(new Intent(this.getApplicationContext(),InfoActivity.class));
    }

    public void addReminder(View view) {
        this.startActivity(new Intent(this.getApplicationContext(),ReminderActivity.class));
    }
}