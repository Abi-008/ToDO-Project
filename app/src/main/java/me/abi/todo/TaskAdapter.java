package me.abi.todo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private Context context;
    private Cursor cursor;

    public TaskAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            String taskName = cursor.getString(cursor.getColumnIndex("NAME"));
            String taskDescription = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            holder.taskNameTextView.setText(taskName);
            holder.taskDescriptionTextView.setText(taskDescription);
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskNameTextView;
        TextView taskDescriptionTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.task_name);
            taskDescriptionTextView = itemView.findViewById(R.id.task_description);
        }
    }
}
