package com.example.computingquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // define ui elements
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // home screen button
        button = (Button) findViewById(R.id.button);
        // open new activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainQuizActivity();

            }
        });
    }
    // intent for new activity
    public void openMainQuizActivity() {
        Intent intent = new Intent(this, MainQuizActivity.class);
        startActivity(intent);
    }

}