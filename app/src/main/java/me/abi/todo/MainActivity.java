package me.abi.todo;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Switch to main theme after splash screen
        setTheme(R.style.Theme_Todo);
        super.onCreate(savedInstanceState);

        // Add a 2-second delay before loading the main layout
        new Handler().postDelayed(() -> setContentView(R.layout.activity_main), 2000);
    }
}
