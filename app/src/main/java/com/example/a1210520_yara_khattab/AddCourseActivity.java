package com.example.a1210520_yara_khattab;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddCourseActivity extends AppCompatActivity {

    EditText etCourseId, etCourseTitle, etInstructor;
    Button btnSaveCourse;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        dbHelper = new DatabaseHelper(this);

        etCourseId = findViewById(R.id.etCourseId);
        etCourseTitle = findViewById(R.id.etCourseTitle);
        etInstructor = findViewById(R.id.etInstructor);
        btnSaveCourse = findViewById(R.id.btnSaveCourse);

        btnSaveCourse.setOnClickListener(view -> {
            String idStr = etCourseId.getText().toString().trim();
            String title = etCourseTitle.getText().toString().trim();
            String instructor = etInstructor.getText().toString().trim();

            if (idStr.isEmpty() || title.isEmpty() || instructor.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idStr);
            boolean inserted = dbHelper.insertCourse(id, title, instructor);

            if (inserted) {
                Toast.makeText(this, "Course added successfully", Toast.LENGTH_SHORT).show();
                etCourseId.setText("");
                etCourseTitle.setText("");
                etInstructor.setText("");
            } else {
                Toast.makeText(this, "Failed to add course", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
