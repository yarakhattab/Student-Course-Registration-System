package com.example.a1210520_yara_khattab;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    EditText etStudentId, etStudentName, etStudentEmail;
    Button btnSaveStudent;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        dbHelper = new DatabaseHelper(this);

        etStudentId = findViewById(R.id.etStudentId);
        etStudentName = findViewById(R.id.etStudentName);
        etStudentEmail = findViewById(R.id.etStudentEmail);
        btnSaveStudent = findViewById(R.id.btnSaveStudent);

        btnSaveStudent.setOnClickListener(view -> {
            String idStr = etStudentId.getText().toString().trim();
            String name = etStudentName.getText().toString().trim();
            String email = etStudentEmail.getText().toString().trim();

            if (idStr.isEmpty() || name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idStr);
            boolean inserted = dbHelper.insertStudent(id, name, email);

            if (inserted) {
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                etStudentId.setText("");
                etStudentName.setText("");
                etStudentEmail.setText("");
            } else {
                Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
