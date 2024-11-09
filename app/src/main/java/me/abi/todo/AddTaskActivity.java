package me.abi.todo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText editTextTaskName, editTextTaskDescription;
    private Button buttonSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        db = new DatabaseHelper(this);
        editTextTaskName = findViewById(R.id.editTextTaskName);
        editTextTaskDescription = findViewById(R.id.editTextTaskDescription);
        buttonSaveTask = findViewById(R.id.buttonSaveTask);

        buttonSaveTask.setOnClickListener(view -> saveTask());
    }

    private void saveTask() {
        String name = editTextTaskName.getText().toString();
        String description = editTextTaskDescription.getText().toString();

        if (getIntent().hasExtra("taskId")) { // Editing an existing task
            int taskId = getIntent().getIntExtra("taskId", -1);
            db.updateTask(taskId, name, description, "", 0); // Status is 0 (incomplete by default)
        } else { // Adding a new task
            db.insertTask(name, description, "");
        }

        startActivity(new Intent(AddTaskActivity.this, MainActivity.class));
        finish();
    }
}
