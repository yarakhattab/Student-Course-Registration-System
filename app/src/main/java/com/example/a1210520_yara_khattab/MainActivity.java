package com.example.a1210520_yara_khattab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAddStudent, btnAddCourse, btnEnroll, btnQueries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddCourse = findViewById(R.id.btnAddCourse);
        btnEnroll = findViewById(R.id.btnEnroll);
        btnQueries = findViewById(R.id.btnQueries);

        btnAddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivity(intent);
        });

        btnAddCourse.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
            startActivity(intent);
        });

        btnEnroll.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EnrollActivity.class);
            startActivity(intent);
        });

        btnQueries.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QueryActivity.class);
            startActivity(intent);
        });
    }
}
