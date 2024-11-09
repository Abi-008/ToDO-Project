package me.abi.todo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set theme after splash screen delay
        setTheme(R.style.Theme_Todo);

        // Delay splash screen and then initialize UI
        new Handler().postDelayed(() -> {
            setContentView(R.layout.activity_main);

            // Initialize DatabaseHelper and UI after setting content view
            db = new DatabaseHelper(this);
            initializeUI();
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Only load tasks if DatabaseHelper and recyclerView are initialized
        if (db != null && recyclerView != null) {
            loadTasks();
        } else {
            System.out.println("Database initialization or RecyclerView setup failed.");
        }
    }

    private void initializeUI() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddTaskActivity.class)));

        // Load tasks into the adapter
        loadTasks();
    }

    private void loadTasks() {
        if (db != null) {
            Cursor cursor = db.getTasks();
            taskAdapter = new TaskAdapter(this, cursor);
            recyclerView.setAdapter(taskAdapter);
        } else {
            System.out.println("DatabaseHelper is null; cannot load tasks.");
        }
    }
}
