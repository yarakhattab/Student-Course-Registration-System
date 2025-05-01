package com.example.a1210520_yara_khattab;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class QueryActivity extends AppCompatActivity {

    Button btnAll, btnWith2, btnNoStudents, btnPopular, btnSchedule;
    EditText etStudentId;
    TextView tvResults;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        dbHelper = new DatabaseHelper(this);

        btnAll = findViewById(R.id.btnAllStudentsWithCourses);
        btnWith2 = findViewById(R.id.btnStudentsWith2Courses);
        btnNoStudents = findViewById(R.id.btnCoursesWithNoStudents);
        btnPopular = findViewById(R.id.btnMostPopularCourse);
        btnSchedule = findViewById(R.id.btnStudentSchedule);
        etStudentId = findViewById(R.id.etStudentScheduleId);
        tvResults = findViewById(R.id.tvQueryResults);

        btnAll.setOnClickListener(v -> showResults(dbHelper.getAllStudents()));
        btnWith2.setOnClickListener(v -> showResults(dbHelper.getStudentsWithMoreThanTwoCourses()));
        btnNoStudents.setOnClickListener(v -> showResults(dbHelper.getCoursesWithNoStudents()));
        btnPopular.setOnClickListener(v -> showResults(dbHelper.getMostPopularCourse()));
        btnSchedule.setOnClickListener(v -> {
            String idStr = etStudentId.getText().toString().trim();
            if (idStr.isEmpty()) {
                Toast.makeText(this, "Enter student ID", Toast.LENGTH_SHORT).show();
            } else {
                int id = Integer.parseInt(idStr);
                showResults(dbHelper.getStudentSchedule(id));
            }
        });
    }

    private void showResults(List<String> data) {
        if (data.isEmpty()) {
            tvResults.setText("No results found.");
        } else {
            StringBuilder resultText = new StringBuilder();
            for (String line : data) {
                resultText.append(line).append("\n");
            }
            tvResults.setText(resultText.toString());
        }
    }
}
