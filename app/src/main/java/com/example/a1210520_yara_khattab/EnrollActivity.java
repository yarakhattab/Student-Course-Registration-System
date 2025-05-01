package com.example.a1210520_yara_khattab;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity extends AppCompatActivity {

    EditText etEnrollStudentId, etEnrollCourseId;
    Button btnEnroll;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        dbHelper = new DatabaseHelper(this);

        etEnrollStudentId = findViewById(R.id.etEnrollStudentId);
        etEnrollCourseId = findViewById(R.id.etEnrollCourseId);
        btnEnroll = findViewById(R.id.btnEnroll);

        btnEnroll.setOnClickListener(view -> {
            String studentIdStr = etEnrollStudentId.getText().toString().trim();
            String courseIdStr = etEnrollCourseId.getText().toString().trim();

            if (studentIdStr.isEmpty() || courseIdStr.isEmpty()) {
                Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int studentId = Integer.parseInt(studentIdStr);
            int courseId = Integer.parseInt(courseIdStr);

            boolean enrolled = dbHelper.enrollStudent(studentId, courseId);

            if (enrolled) {
                Toast.makeText(this, "Enrollment successful", Toast.LENGTH_SHORT).show();
                etEnrollStudentId.setText("");
                etEnrollCourseId.setText("");
            } else {
                Toast.makeText(this, "Enrollment failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
